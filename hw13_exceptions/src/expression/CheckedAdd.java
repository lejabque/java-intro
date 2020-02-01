package expression;

import exceptions.OverflowException;

public final class CheckedAdd extends BinaryOperation {

    public CheckedAdd(PriorityExpression first, PriorityExpression second) {
        super(first, second);
    }

    @Override
    protected int calculate(int x, int y) throws OverflowException {
        if (y > 0 && x > Integer.MAX_VALUE - y) {
            throw new OverflowException();
        }
        if (y < 0 && x < Integer.MIN_VALUE - y) {
            throw new OverflowException();
        }
        return x + y;
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
