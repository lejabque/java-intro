package expression.exceptions;

import expression.*;
import expression.parser.BaseParser;
import expression.parser.StringSource;

public class ExpressionParser extends BaseParser implements Parser {
    public int bracketsBalance = 0;

    public ExpressionParser(StringSource stringSource) {
        super(stringSource);
    }

    public ExpressionParser() {
    }

    @Override
    public CommonExpression parse(String expression) throws ParsingException {
        this.bracketsBalance = 0;
        changeSource(new StringSource(expression));
        nextChar();
        return parseExpression();
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

        while (true) {
            skipWhitespace();
            final Operation curOperation = Operation.CHAROPERANDS.get(ch);
            if (ch == 0) {
                if (bracketsBalance == 0) {
                    return parsed;
                } else {
                    throw new MissingClosingParenthesis(getParsingInfo());
                }
            } else if (ch == ')') {
                if (bracketsBalance > 0) {
                    return parsed;
                } else {
                    throw new MissingOpeningParenthesis(getParsingInfo());
                }
            } else if (curOperation == null) {
                throw new InvalidOperatorException(ch, getParsingInfo());
            } else if (priority != Operation.PRIORITIES.get(curOperation)) {
                return parsed;
            }

            nextChar();
            if (curOperation == Operation.LEFTSHIFT) {
                if (!expect('<')) {
                    throw new InvalidOperatorException(ch, getParsingInfo());
                }
            } else if (curOperation == Operation.RIGHTSHIFT) {
                if (!expect('>')) {
                    throw new InvalidOperatorException(ch, getParsingInfo());
                }
            }
            parsed = buildOperation(parsed, parseTerm(priority + 1), curOperation);
        }
    }

    private CommonExpression parseValue() throws ParsingException {
        if (test('(')) {
            bracketsBalance++;
            CommonExpression parsed = parseExpression();
            skipWhitespace();
            if (!expect(')')) {
                throw new MissingClosingParenthesis(getParsingInfo());
            }
            bracketsBalance--;
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
            return parseVariable();
        }
    }

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
            case LEFTSHIFT:
                return new LeftShift(left, right);
            case RIGHTSHIFT:
                return new RightShift(left, right);
        }
        return null;
    }

    private CommonExpression parseVariable() throws InvalidVariableException {
        skipWhitespace();
        final String variable = Character.toString(ch);
        nextChar();
        if (variable.equals("x") || variable.equals("y") || variable.equals("z")) {
            return new Variable(variable);
        }
        throw new InvalidVariableException(variable, getParsingInfo());
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
