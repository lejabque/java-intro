package expression;

public final class Divide extends BinaryOperation {

    public Divide(PriorityExpression first, PriorityExpression second) {
        super(first, second);
    }

    @Override
    protected int calculate(int x, int y) {
        return x / y;
    }

    @Override
    protected String getOperationType() {
        return "/";
    }

    @Override
    public int getPriority() {
        return 2;
    }

    @Override
    public boolean isImportant() {
        return true;
    }
}
