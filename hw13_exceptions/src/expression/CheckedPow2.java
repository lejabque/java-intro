package expression;

import exceptions.OverflowException;
import exceptions.PowArgumentException;
import expression.PriorityExpression;
import expression.UnaryOperation;

public final class CheckedPow2 extends UnaryOperation {

    public CheckedPow2(PriorityExpression first) {
        super(first);
    }

    @Override
    protected int calculate(int y) throws OverflowException {
        if (y < 0) {
            throw new PowArgumentException(2, y);
        }
        if (y >= 31){
            throw new OverflowException();
        }
        return 1 << y;
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
