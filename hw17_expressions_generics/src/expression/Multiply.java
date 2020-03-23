package expression;

import evaluator.Evaluator;
import exceptions.OverflowException;

public final class Multiply<T> extends BinaryOperation<T> {

    public Multiply(GenericExpression<T> first, GenericExpression<T> second) {
        super(first, second);
    }

    @Override
    protected T calculate(T x, T y, Evaluator<T> evaluator) throws OverflowException {
        return evaluator.multiply(x, y);
    }

    @Override
    protected String getOperationType() {
        return "*";
    }
}
