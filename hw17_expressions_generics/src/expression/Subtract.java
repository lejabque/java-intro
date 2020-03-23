package expression;

import evaluator.Evaluator;
import exceptions.OverflowException;

public final class Subtract<T> extends BinaryOperation<T> {
    public Subtract(GenericExpression<T> first, GenericExpression<T> second) {
        super(first, second);
    }

    @Override
    protected T calculate(T x, T y, Evaluator<T> evaluator) throws OverflowException {
        return evaluator.subtract(x, y);
    }

    @Override
    protected String getOperationType() {
        return "-";
    }
}
