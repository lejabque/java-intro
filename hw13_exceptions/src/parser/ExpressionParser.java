package parser;

import exceptions.*;
import expression.*;

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
            if (ch == '\u0000') {
                if (bracketsBalance == 0) {
                    return parsed;
                } else {
                    throw new SourceException("Missed closing parentheses.");
                }
            } else if (ch == ')') {
                if (bracketsBalance > 0) {
                    return parsed;
                } else {
                    throw new SourceException("Unexpected closing parentheses.");
                }
            } else if (curOperation == null) {
                throw new InvalidOperationException("Invalid operation: '" + ch + "'");
            } else if (priority != Operation.PRIORITIES.get(curOperation)) {
                return parsed;
            }

            nextChar();
            if (curOperation == Operation.LEFTSHIFT) {
                expect('<');
            } else if (curOperation == Operation.RIGHTSHIFT) {
                expect('>');
            }
            parsed = buildOperation(parsed, parseTerm(priority + 1), curOperation);
        }
    }

    private CommonExpression parseDigits() throws ParsingException {
        expect("igits");
        skipWhitespace();
        return new Digits(parseValue());
    }

    private CommonExpression parseReverse() throws ParsingException {
        expect("everse");
        skipWhitespace();
        return new Reverse(parseValue());
    }

    private CommonExpression parseValue() throws ParsingException {
        if (test('(')) {
            bracketsBalance++;
            CommonExpression parsed = parseExpression();
            skipWhitespace();
            expect(')');
            bracketsBalance--;
            return parsed;
        } else if (test('r')) {
            return parseReverse();
        } else if (test('d')) {
            return parseDigits();
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
        throw new InvalidVariableException("Invalid or missed variable: '" + variable + "'");
    }

    private CommonExpression parseConst(boolean positive) throws IllegalConstException {
        final StringBuilder sb = new StringBuilder();
        if (!positive) {
            sb.append('-');
        }
        copyInteger(sb);
        try {
            return new Const(Integer.parseInt(sb.toString()));
        } catch (NumberFormatException e) {
            throw new IllegalConstException("Illegal const (possible overflow): " + sb.toString());
        }

    }
}
