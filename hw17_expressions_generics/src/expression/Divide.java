package expression;

import evaluator.Evaluator;
import exceptions.OverflowException;

public final class Divide<T> extends BinaryOperation<T> {

    public Divide(TripleExpression<T> first, TripleExpression<T> second) {
        super(first, second);
    }

    @Override
    protected T calculate(T x, T y, Evaluator<T> evaluator) throws OverflowException {
        return evaluator.divide(x, y);
    }

    @Override
    protected String getOperationType() {
        return "/";
    }
}
