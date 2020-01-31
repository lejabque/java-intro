package expression;

import expression.PriorityExpression;
import expression.UnaryOperation;

public final class Digits extends UnaryOperation {

    public Digits(PriorityExpression first) {
        super(first);
    }

    @Override
    protected int calculate(int x) {
        int ans = 0;
        while (x != 0) {
            ans += x % 10;
            x /= 10;
        }
        return ans >= 0 ? ans : -ans;
    }

    @Override
    protected String getOperationType() {
        return "digits";
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
