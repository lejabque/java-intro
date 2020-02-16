package expression.exceptions;

import expression.BinaryOperation;
import expression.PriorityExpression;

public final class CheckedPow extends BinaryOperation {

    public CheckedPow(PriorityExpression first, PriorityExpression second) {
        super(first, second);
    }

    private int checkOverflow(int x, int y) {
        int cur = x * y;
        if (x != 0 && y != 0 && (cur / x != y || cur / y != x)){
            throw new OverflowException();
        }
        return cur;
    }

    @Override
    protected int calculate(int x, int y) throws OverflowException {
        if (x == 0 && y == 0 || y < 0) {
            throw new PowArgumentException(x, y);
        }
        if (x == 0) {
            return 0;
        }
        int res = 1;
        while (y != 0) {
            if (y % 2 == 1) {
                res = checkOverflow(res, x);
                y--;
            } else {
                y = y / 2;
                x = checkOverflow(x, x);
            }
        }
        return res;
    }

    @Override
    protected String getOperationType() {
        return "**";
    }

    @Override
    public int getPriority() {
        return 2;
    }

    @Override
    public boolean isImportant() {
        return false;
    }
}
