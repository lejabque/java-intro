package expression.exceptions;

import expression.*;
import expression.parser.BaseParser;
import expression.parser.StringSource;

public class ExpressionParser extends BaseParser implements Parser {

    public ExpressionParser(StringSource stringSource) {
        super(stringSource, 10);
    }

    public ExpressionParser() {
        super(2);
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
            Operation curOperation = getOperation(priority);
            if (curOperation == null) {
                return parsed;
            }
            parsed = buildOperation(parsed, parseTerm(priority + 1), curOperation);
        }
        return parsed;
    }

    private Operation getOperation(int priority) {
        skipWhitespace();
        if (priority > 0) {
            for (Operation op :
                    Operation.PRIORITIESLIST.get(priority - 1)) {
                if (test(Operation.OPERATORS.get(op))) {
                    return op;
                }
            }
        }
        return null;
    }

    private CommonExpression parseValue() throws ParsingException {
        if (test('(')) {
            CommonExpression parsed = parseExpression();
            skipWhitespace();
            if (!expect(')')) {
                throw new MissingClosingParenthesis(getParsingInfo());
            }
            return parsed;
        } else if (test('l')) {
            expect("og2");
            skipWhitespace();
            return new CheckedLog2(parseValue());
        } else if (test('p')) {
            expect("ow2");
            skipWhitespace();
            return new CheckedPow2(parseValue());
        } else if (test('-')) {
            skipWhitespace();
            if (between('0', '9')) {
                return parseConst(false);
            }
            return new Negate(parseValue());
        } else if (between('0', '9')) {
            return parseConst(true);
        } else {
            return parseVariable();
        }
    }

    // private CommonExpression

    private CommonExpression buildOperation(CommonExpression left, CommonExpression right,
                                            Operation oper) {
        switch (oper) {
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
