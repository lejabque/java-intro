package expression.exceptions;

import expression.BinaryOperation;
import expression.PriorityExpression;

public final class CheckedPow extends BinaryOperation {

    public CheckedPow(PriorityExpression first, PriorityExpression second) {
        super(first, second);
    }

    @Override
    protected int calculate(int x, int y) throws OverflowException {
        int res = 0;
        int cur = 1;
        while (cur * x < y) {
            res += 1;
        }
        return res;
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
