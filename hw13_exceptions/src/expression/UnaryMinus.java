package expression;

public final class UnaryMinus extends UnaryOperation {

    public UnaryMinus(PriorityExpression first) {
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
