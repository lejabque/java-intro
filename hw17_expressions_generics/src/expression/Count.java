package expression;

import evaluator.Evaluator;
import exceptions.OverflowException;

public final class Count<T> extends UnaryOperation<T> {

    public Count(GenericExpression<T> first) {
        super(first);
    }

    @Override
    protected T calculate(T x, Evaluator<T> evaluator) throws OverflowException {
        return evaluator.count(x);
    }

    @Override
    protected String getOperationType() {
        return "count";
    }
}
