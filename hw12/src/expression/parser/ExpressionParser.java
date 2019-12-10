package expression.parser;

import expression.*;

public class ExpressionParser extends BaseParser implements Parser {

    public ExpressionParser(StringSource stringSource) {
        super(stringSource);
    }

    public ExpressionParser() {
        super();
    }


    @Override
    public TripleExpression parse(String expression) {
        changeSource(new StringSource(expression));
        nextChar();
        return parseExpression();
    }

    public CommonExpression parseExpression() {
        final CommonExpression left = parseTerm();
        return addRightPart(left);
    }

    public CommonExpression addRightPart(CommonExpression left) {
        skipWhitespace();
        if (!hasNext() || test(')')) {
            return left;
        }
        final Operation operation = parseOperation();
        final CommonExpression right = parseTerm();
        left = buildExpression(left, operation, right);
        skipWhitespace();
        if (test(')') || test('\0')) {
            return left;
        } else {
            return addRightPart(left);
        }
    }

    private CommonExpression buildExpression(CommonExpression left, Operation operation, CommonExpression right) {
        switch (operation) {
            case ADD:
                return new Add(left, right);
            case SUB:
                return new Subtract(left, right);
            case MUL:
                return new Multiply(left, right);
            case DIV:
                return new Divide(left, right);
        }
        throw error("Unknown operation");
    }

    private Operation parseOperation() {
        skipWhitespace();
        if (test('+')) {
            return Operation.ADD;
        }
        if (test('-')) {
            return Operation.SUB;
        }
        if (test('/')) {
            return Operation.DIV;
        }
        if (test('*')) {
            return Operation.MUL;
        }
        throw error("Unrecognized operation");
    }

    // (expression) or [const] or [var]
    public CommonExpression parseTerm() {
        skipWhitespace();
        if (test('(')) {
            return parseExpression();
        }
        if (between('x', 'z')) {
            return parseVariable();
        }
        if (test('-')) {
            return parseConst(false);
        }
        return parseConst(true);
    }

    private Variable parseVariable() {
        skipWhitespace();
        String variable = Character.toString(ch);
        nextChar();
        return new Variable(variable);
    }

    private Const parseConst(boolean positive) {
        return new Const(positive ? parseNumber() : -parseNumber());
    }

    private int parseNumber() {
        final StringBuilder sb = new StringBuilder();
        copyInteger(sb);
        try {
            return Integer.parseInt(sb.toString());
        } catch (NumberFormatException e) {
            throw error("Invalid number " + sb);
        }
    }


    private void copyDigits(final StringBuilder sb) {
        while (between('0', '9')) {
            sb.append(ch);
            nextChar();
        }
    }

    private void copyInteger(final StringBuilder sb) {
        skipWhitespace();
        if (test('-')) {
            sb.append('-');
        }
        if (test('0')) {
            sb.append('0');
        } else if (between('1', '9')) {
            copyDigits(sb);
        } else {
            throw error("Invalid number");
        }
    }

    private void skipWhitespace() {
        while (test(' ') || test('\r') || test('\n') || test('\t')) {
            // skip
        }
    }
}
