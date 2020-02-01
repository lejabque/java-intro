package expression;

import exceptions.OverflowException;
import exceptions.ZeroDivisionException;

public final class CheckedDivide extends BinaryOperation {

    public CheckedDivide(PriorityExpression first, PriorityExpression second) {
        super(first, second);
    }

    @Override
    protected int calculate(int x, int y) throws ZeroDivisionException, OverflowException {
        if (y == 0) {
            throw new ZeroDivisionException();
        }
        if (y == -1 && x == Integer.MIN_VALUE){
            throw new OverflowException();
        }
        return x / y;
    }

    @Override
    protected String getOperationType() {
        return "/";
    }

    @Override
    public int getPriority() {
        return 2;
    }

    @Override
    public boolean isImportant() {
        return true;
    }
}
