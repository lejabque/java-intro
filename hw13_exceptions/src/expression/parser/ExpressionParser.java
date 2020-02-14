package expression.parser;

import expression.*;
import expression.exceptions.*;

public class ExpressionParser extends BaseParser implements Parser {

    public ExpressionParser(StringSource stringSource) {
        super(stringSource, 10);
    }

    public ExpressionParser() {
        super(4);  // max len of operator
    }

    @Override
    public CommonExpression parse(String expression) throws ParsingException {
        changeSource(new StringSource(expression));
        CommonExpression result = parseExpression();
        if (hasNext() && ch != '\0') {
            throw new MissingOpeningParenthesis(getParsingInfo());
        }
        return result;
    }

    public CommonExpression parseExpression() throws ParsingException {
        skipWhitespace();
        return parseTerm(0);
    }

    private CommonExpression parseTerm(int priority) throws ParsingException {
        skipWhitespace();
        if (priority == Operation.PRIORITIES.get(Operation.CONST)) {
            return parseValue();
        }
        CommonExpression parsed = parseTerm(priority + 1);
        while (ch != '0' && ch != ')') {
            Operation curOperation = getBinaryOperator(priority);
            if (curOperation == null) {
                return parsed;
            }
            parsed = buildBinaryOperation(parsed, parseTerm(priority + 1), curOperation);
        }
        return parsed;
    }

    private CommonExpression parseValue() throws ParsingException {
        skipWhitespace();
        if (test('(')) {
            CommonExpression parsed = parseExpression();
            skipWhitespace();
            if (!expect(')')) {
                throw new MissingClosingParenthesis(getParsingInfo());
            }
            return parsed;
        } else if (test('-')) {
            skipWhitespace();
            if (between('0', '9')) {
                return parseConst(false);
            }
            return new Negate(parseValue());
        } else if (between('0', '9')) {
            return parseConst(true);
        } else {
            Operation operation = parseUnaryOperator();
            if (operation != null) {
                if ((ch == '-' || ch == '(' || Character.isWhitespace(ch))) {
                    return buildUnaryOperation(parseValue(), operation);
                } else {
                    throw new InvalidOperatorException(Operation.OPERATORS_STRING.get(operation) + Character.toString(ch),
                            getParsingInfo());
                }
            }
        }
        return parseVariable();
    }

    private Operation getBinaryOperator(int priority) {
        skipWhitespace();
        if (priority > 0) {
            for (Operation operation : Operation.PRIORITY_TO_OPER.get(priority - 1)) {
                if (test(Operation.OPERATORS_STRING.get(operation))) {
                    return operation;
                }
            }
        }
        return null;
    }

    private Operation parseUnaryOperator() {
        skipWhitespace();
        for (Operation operation : Operation.UNARY_OPERATORS) {
            if (test(Operation.OPERATORS_STRING.get(operation))) {
                return operation;
            }
        }
        return null;
    }

    private CommonExpression buildBinaryOperation(CommonExpression left, CommonExpression right,
                                                  Operation operation) {
        switch (operation) {
            case ADD:
                return new CheckedAdd(left, right);
            case SUB:
                return new CheckedSubtract(left, right);
            case MUL:
                return new CheckedMultiply(left, right);
            case DIV:
                return new CheckedDivide(left, right);
            case LOG:
                return new CheckedLog(left, right);
            case POW:
                return new CheckedPow(left, right);
        }
        return null;
    }

    private CommonExpression buildUnaryOperation(CommonExpression expr,
                                                 Operation operation    ) {
        switch (operation) {
            case LOG2:
                return new CheckedLog2(expr);
            case POW2:
                return new CheckedPow2(expr);
        }
        return null;
    }

    private CommonExpression parseVariable() throws InvalidVariableException {
        skipWhitespace();
        if (between('x', 'z')) {
            final String variable = Character.toString(ch);
            nextChar();
            return new Variable(variable);
        }
        throw new InvalidVariableException(Character.toString(ch), getParsingInfo());
    }

    private CommonExpression parseConst(boolean positive) throws ParsingException {
        final StringBuilder sb = new StringBuilder();
        if (!positive) {
            sb.append('-');
        }
        copyInteger(sb);
        try {
            return new Const(Integer.parseInt(sb.toString()));
        } catch (NumberFormatException e) {
            throw new IllegalConstException(sb.toString(), getParsingInfo());
        }
    }
}
