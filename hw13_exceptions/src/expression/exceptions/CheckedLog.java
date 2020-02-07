package expression.exceptions;

import expression.BinaryOperation;
import expression.PriorityExpression;

public final class CheckedLog extends BinaryOperation {

    public CheckedLog(PriorityExpression first, PriorityExpression second) {
        super(first, second);
    }

    @Override
    protected int calculate(int x, int y) throws OverflowException {
        int res = x + y;
        if (y > 0 && x > 0 && res <= 0) {
            throw new OverflowException();
        }
        if (y < 0 && x < 0 && res >= 0) {
            throw new OverflowException();
        }
        return res;
    }

    @Override
    protected String getOperationType() {
        return "//";
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
