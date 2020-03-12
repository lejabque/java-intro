package expression;

import evaluator.Evaluator;
import exceptions.OverflowException;

public final class Negate<T> extends UnaryOperation<T> {

    public Negate(TripleExpression<T> first) {
        super(first);
    }

    @Override
    protected T calculate(T x, Evaluator<T> evaluator) throws OverflowException {
        return evaluator.negate(x);
    }

    @Override
    protected String getOperationType() {
        return "-";
    }
}
