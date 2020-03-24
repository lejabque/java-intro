package expression;

import exceptions.OverflowException;
import expression.PriorityExpression;
import expression.UnaryOperation;

public final class CheckedNegate extends UnaryOperation {

    public CheckedNegate(PriorityExpression first) {
        super(first);
    }

    @Override
    protected int calculate(int x) throws OverflowException {
        if (x == Integer.MIN_VALUE){
            throw new OverflowException();
        }
        return -x;
    }

    @Override
    protected String getOperationType() {
        return "-";
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
