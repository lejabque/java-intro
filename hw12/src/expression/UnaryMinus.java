package expression;

public final class UnaryMinus extends UnaryOperation {

    public UnaryMinus(PriorityExpression first, PriorityExpression second) {
        super(first);
    }

    @Override
    protected int calculate(int x) {
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
