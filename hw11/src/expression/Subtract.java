package expression;

public class Subtract extends Operation {

    public Subtract(Expression first, Expression second) {
        super(first, second);
    }

    @Override
    public int evaluate(int x) {
        return first.evaluate(x) - second.evaluate(x);
    }

    @Override
    protected String getOperationType() {
        return "-";
    }
}
