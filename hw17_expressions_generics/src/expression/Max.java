package expression;

import evaluator.Evaluator;
import exceptions.OverflowException;

public final class Max<T> extends BinaryOperation<T> {

    public Max(TripleExpression<T> first, TripleExpression<T> second) {
        super(first, second);
    }

    @Override
    protected T calculate(T x, T y, Evaluator<T> evaluator) throws OverflowException {
        return evaluator.max(x, y);
    }

    @Override
    protected String getOperationType() {
        return "max";
    }
}
