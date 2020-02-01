package expression;

import exceptions.OverflowException;

public final class CheckedMultiply extends BinaryOperation {

    public CheckedMultiply(PriorityExpression first, PriorityExpression second) {
        super(first, second);
    }

    @Override
    protected int calculate(int x, int y) throws OverflowException {
        int res = x * y;
        if (x != 0 && y != 0 && (res / y != x || res / x != y)){
            throw new OverflowException();
        }
//        if (x > 0 && y > 0 && x > Integer.MAX_VALUE / y) {
//            throw new OverflowException();
//        }
//        if (x < 0 && y < 0 && x < Integer.MAX_VALUE / y) {
//            throw new OverflowException();
//        }
//        if (x > 0 && y < 0 && y < Integer.MIN_VALUE / x) {
//            throw new OverflowException();
//        }
//        if (x < 0 && y > 0 && x < Integer.MIN_VALUE / y) {
//            throw new OverflowException();
//        }
        return res;
    }

    @Override
    protected String getOperationType() {
        return "*";
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
