package parser;

import expression.*;
import exceptions.*;
import expression.Add;
import expression.Divide;
import expression.Count;
import expression.Subtract;

public class ExpressionParser<T> extends BaseParser {

    public ExpressionParser(StringSource stringSource) {
        super(stringSource, 3);
    }

    public ExpressionParser() {
        super(3);  // max length of binary operator
    }

    public GenericExpression<T> parse(String expression) throws ParsingException {
        changeSource(new StringSource(expression));
        GenericExpression<T> result = parseExpression();
        if (hasNext() && ch != '\0') {
            throw new MissingOpeningParenthesis(getParsingInfo());
        }
        return result;
    }

    public GenericExpression<T> parseExpression() throws ParsingException {
        skipWhitespace();
        return parseTerm(0);
    }

    private GenericExpression<T> parseTerm(int priority) throws ParsingException {
        skipWhitespace();
        if (priority == Operation.PRIORITIES.get(Operation.CONST)) {
            return parseValue();
        }
        GenericExpression<T> parsed = parseTerm(priority + 1);
        while (true) {
            Operation curOperation = getBinaryOperator(priority);
            if (curOperation == null) {
                return parsed;
            }
            parsed = buildBinaryOperation(parsed, parseTerm(priority + 1), curOperation);
        }
    }

    private GenericExpression<T> parseValue() throws ParsingException {
        skipWhitespace();
        if (test('(')) {
            GenericExpression<T> parsed = parseExpression();
            skipWhitespace();
            if (!expect(')')) {
                throw new MissingClosingParenthesis(getParsingInfo());
            }
            return parsed;
        } else if (test('-')) {
            if (between('0', '9')) {
                return parseConst(false);
            }
            return new Negate<>(parseValue());
        } else if (between('0', '9')) {
            return parseConst(true);
        } else {
            String token = parseToken();
            Operation operation = Operation.STRING_TO_UNARY.get(token);
            if (operation != null) {
                return buildUnaryOperation(parseValue(), operation);
            }
            return getVariable(token);
        }
    }

    private Operation getBinaryOperator(int priority) {
        skipWhitespace();
        for (Operation operation : Operation.PRIORITY_TO_OPER.get(priority)) {
            if (test(Operation.OPERATORS_STRING.get(operation))) {
                return operation;
            }
        }
        return null;
    }

    private GenericExpression<T> buildBinaryOperation(GenericExpression<T> left, GenericExpression<T> right,
                                                     Operation operation) {
        switch (operation) {
            case ADD:
                return new Add<>(left, right);
            case SUB:
                return new Subtract<>(left, right);
            case MUL:
                return new Multiply<>(left, right);
            case DIV:
                return new Divide<>(left, right);
            case MIN:
                return new Min<>(left, right);
            case MAX:
                return new Max<>(left, right);
        }
        return null;
    }

    private GenericExpression<T> buildUnaryOperation(GenericExpression<T> expr,
                                                 Operation operation) {
        switch (operation) {
            case COUNT:
                return new Count<>(expr);
        }
        return null;
    }

    private GenericExpression<T> getVariable(String token) throws InvalidVariableException {
        if (Operation.VARIABLES.contains(token)) {
            return new Variable<>(token);
        }
        throw new InvalidVariableException(token, getParsingInfo());
    }

    private GenericExpression<T> parseConst(boolean positive) throws ParsingException {
        final StringBuilder sb = new StringBuilder();
        if (!positive) {
            sb.append('-');
        }
        copyNumber(sb);
        try {
            return new Const<>(sb.toString());
        } catch (NumberFormatException e) {
            throw new IllegalConstException(sb.toString(), getParsingInfo());
        }
    }
}
