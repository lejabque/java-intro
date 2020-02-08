package expression.exceptions;

import expression.PriorityExpression;
import expression.UnaryOperation;

public final class CheckedPow2 extends UnaryOperation {

    public CheckedPow2(PriorityExpression first) {
        super(first);
    }

    @Override
    protected int calculate(int x) throws OverflowException {
        // change
        return -x;
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
