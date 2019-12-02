package expression;

public class Divide extends Operation {

    public Divide(PriorityExpression first, PriorityExpression second) {
        super(first, second);
    }

    @Override
    public int evaluate(int x) {
        return first.evaluate(x) / second.evaluate(x);
    }

    @Override
    protected String getOperationType() {
        return "/";
    }

    @Override
    public int getPriority() {
        return 1;
    }
}
