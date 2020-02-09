package expression.exceptions;

import expression.BinaryOperation;
import expression.PriorityExpression;

public final class CheckedPow extends BinaryOperation {

    public CheckedPow(PriorityExpression first, PriorityExpression second) {
        super(first, second);
    }

    @Override
    protected int calculate(int x, int y) throws OverflowException {
        if (x == 0 && y == 0 || y < 0) {
            throw new PowerException();
        }
        if (x == 0) {
            return 0;
        }
        int res = 0;
        int cur = 1;
        while (res < y) {
            res += 1;
            if (((x * cur) / cur != x || (x * cur) / x != cur)) {
                throw new OverflowException();
            }
            cur *= x;

        }
        return cur;
    }

    @Override
    protected String getOperationType() {
        return "**";
    }

    @Override
    public int getPriority() {
        return 100;
    }

    @Override
    public boolean isImportant() {
        return false;
    }
}
