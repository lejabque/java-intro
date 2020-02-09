package expression.exceptions;

import expression.PriorityExpression;
import expression.UnaryOperation;

public final class CheckedLog2 extends UnaryOperation {

    public CheckedLog2(PriorityExpression first) {
        super(first);
    }

    @Override
    protected int calculate(int x) throws LogarithmException {
        if (x < 1) {
            throw new LogarithmException();
        }
        int res = 0;
        int cur = 1;
        while (cur <= x / 2) {
            res += 1;
            cur *= 2;
        }
        return res;
    }

    @Override
    protected String getOperationType() {
        return "log2";
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
