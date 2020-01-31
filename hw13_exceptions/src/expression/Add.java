package expression;

public final class Add extends BinaryOperation {

    public Add(PriorityExpression first, PriorityExpression second) {
        super(first, second);
    }

    @Override
    protected int calculate(int x, int y) {
        return x + y;
    }

    @Override
    protected String getOperationType() {
        return "+";
    }

    @Override
    public int getPriority() {
        return 1;
    }

    @Override
    public boolean isImportant() {
        return false;
    }
}
