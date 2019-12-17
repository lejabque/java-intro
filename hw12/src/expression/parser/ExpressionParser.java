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
    public CommonExpression parse(String expression) {
        changeSource(new StringSource(expression));
        nextChar();
        return parseExpression();
    }

    public CommonExpression parseExpression() {
        skipWhitespace();
        return parseTerm(0);
    }

    private CommonExpression parseTerm(int priority) {
        skipWhitespace();
        if (priority == Operation.operToPriority.get(Operation.CONST)) {
            return parseValue();
        }
        int nextPriority = priority + 1;
        CommonExpression parsed = parseTerm(nextPriority);

        while (true) {
            skipWhitespace();
            Operation curOperation = Operation.stringToOper.get(Character.toString(ch));
            int curPriority = curOperation == null ? -1000 : Operation.operToPriority.get(curOperation);
            if (curOperation == null || priority != curPriority) {
                return parsed;
            }
            nextPriority = curPriority + 1;
            nextChar();
            if (curOperation == Operation.LEFTSHIFT){
                expect('<');
            } else if (curOperation == Operation.RIGHTSHIFT){
                expect('>');
            }
            parsed = buildOperation(parsed, parseTerm(nextPriority), curOperation);
        }
    }

    private CommonExpression parseValue() {
        if (test('(')) {
            CommonExpression parsed = parseExpression();
            skipWhitespace();
            expect(')');
            return parsed;
        }
        if (test('-')) {
            skipWhitespace();
            if (between('0', '9')) {
                return parseConst(false);
            }
            return new UnaryMinus(parseValue());
        }
        if (between('0', '9')) {
            return parseConst(true);
        } else {
            return parseVariable();
        }
    }

    private CommonExpression buildOperation(CommonExpression left, CommonExpression right,
                                            Operation oper) {
        switch (oper) {
            case ADD:
                return new Add(left, right);
            case SUB:
                return new Subtract(left, right);
            case MUL:
                return new Multiply(left, right);
            case DIV:
                return new Divide(left, right);
            case LEFTSHIFT:
                return new LeftShift(left, right);
            case RIGHTSHIFT:
                return new RightShift(left, right);
        }
        return null;
    }

    private CommonExpression parseVariable() {
        skipWhitespace();
        String variable = Character.toString(ch);
        nextChar();
        return new Variable(variable);
    }

    private CommonExpression parseConst(boolean positive) {
        final StringBuilder sb = new StringBuilder();
        if (!positive) {
            sb.append('-');
        }
        copyInteger(sb);
        try {
            return new Const(Integer.parseInt(sb.toString()));
        } catch (NumberFormatException e) {
            throw error("Invalid number " + sb);
        }
    }
}
