package expression.exceptions;

import expression.PriorityExpression;
import expression.UnaryOperation;

public final class CheckedPow2 extends UnaryOperation {

    public CheckedPow2(PriorityExpression first) {
        super(first);
    }

    @Override
    protected int calculate(int y) throws OverflowException {
        if (y < 0) {
            throw new PowerException();
        }
        int res = 0;
        int cur = 1;
        while (res < y) {
            res += 1;
            if (((2 * cur) / cur != 2 || (2 * cur) / 2 != cur)) {
                throw new OverflowException();
            }
            cur *= 2;

        }
        return cur;
    }

    @Override
    protected String getOperationType() {
        return "pow2";
    }

    @Override
    public int getPriority() {
        return 100;
    }

    @Override
    public boolean isImportant() {
        return true;
    }
}
