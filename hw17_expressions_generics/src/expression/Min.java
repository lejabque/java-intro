package expression;

import evaluator.Evaluator;
import exceptions.OverflowException;

public final class Min<T> extends BinaryOperation<T> {

    public Min(GenericExpression<T> first, GenericExpression<T> second) {
        super(first, second);
    }

    @Override
    protected T calculate(T x, T y, Evaluator<T> evaluator) throws OverflowException {
        return evaluator.min(x, y);
    }

    @Override
    protected String getOperationType() {
        return "min";
    }
}
