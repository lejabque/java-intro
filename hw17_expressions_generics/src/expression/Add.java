package expression;

import evaluator.Evaluator;
import exceptions.OverflowException;

public final class Add<T> extends BinaryOperation<T> {

    public Add(GenericExpression<T> first, GenericExpression<T> second) {
        super(first, second);
    }

    @Override
    protected T calculate(T x, T y, Evaluator<T> evaluator) throws OverflowException {
        return evaluator.add(x, y);
    }

    @Override
    protected String getOperationType() {
        return "+";
    }
}
