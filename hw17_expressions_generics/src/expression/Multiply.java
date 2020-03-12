package expression;

import evaluator.Evaluator;
import exceptions.OverflowException;

public final class Multiply<T> extends BinaryOperation<T> {

    public Multiply(TripleExpression<T> first, TripleExpression<T> second) {
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
