package expression;

public final class Reverse extends UnaryOperation {

    public Reverse(PriorityExpression first) {
        super(first);
    }

    @Override
    protected int calculate(int x) {
        int ans = 0;
        while (x != 0) {
            ans = ans * 10 + x % 10;
            x /= 10;
        }
        return ans;
    }

    @Override
    protected String getOperationType() {
        return "reverse";
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
