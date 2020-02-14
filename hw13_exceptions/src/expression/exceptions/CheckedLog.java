package expression.exceptions;

import expression.BinaryOperation;
import expression.PriorityExpression;

public final class CheckedLog extends BinaryOperation {

    public CheckedLog(PriorityExpression first, PriorityExpression second) {
        super(first, second);
    }

    @Override
    protected int calculate(int x, int y) throws LogArgumentException {
        if (x < 1 || y <= 0 || y == 1) {
            throw new LogArgumentException(x, y);
        }
        int res = 0;
        int cur = 1;
        while (cur <= x / y) {
            res += 1;
            cur *= y;
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
