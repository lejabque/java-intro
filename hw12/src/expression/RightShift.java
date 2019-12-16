package expression;

public final class RightShift extends BinaryOperation {

    public RightShift(PriorityExpression first, PriorityExpression second) {
        super(first, second);
    }

    @Override
    protected int calculate(int x, int y) {
        return x >> y;
    }

    @Override
    protected String getOperationType() {
        return ">>";
    }

    @Override
    public int getPriority() {
        return 0;
    }

    @Override
    public boolean isImportant() {
        return false;
    }
}
