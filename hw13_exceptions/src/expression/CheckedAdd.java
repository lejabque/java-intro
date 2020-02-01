package expression;

import exceptions.OverflowException;

public final class CheckedAdd extends BinaryOperation {

    public CheckedAdd(PriorityExpression first, PriorityExpression second) {
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
        return "+";
    }

    @Override
    public int getPriority() {
        return 1;
    }

    @Override
    public boolean isImportant() {
        return false;
    }
}
