package expression.exceptions;

import expression.BinaryOperation;
import expression.PriorityExpression;

public final class CheckedPow extends BinaryOperation {

    public CheckedPow(PriorityExpression first, PriorityExpression second) {
        super(first, second);
    }

    private void CheckException(int x, int y) {
        int cur = x * y;
        if (x != 0 && y != 0 && (cur / x != y || cur / y != x)){
            throw new OverflowException();
        }
    }

    @Override
    protected int calculate(int x, int y) throws OverflowException {
        if (x == 0 && y == 0 || y < 0) {
            throw new PowerException();
        }
        if (x == 0) {
            return 0;
        }
        int res = 1;
        while (y != 0) {
            if (y % 2 == 1) {
                CheckException(res, x);
                res = res * x;
                y--;
            }
            else {
                y = y / 2;
                CheckException(x, x);
                x = x * x;
            }
        }
        return res;
    }

    @Override
    protected String getOperationType() {
        return "**";
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
