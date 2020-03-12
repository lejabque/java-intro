package expression;

import exceptions.EvaluatingException;
import evaluator.Evaluator;

import java.util.Objects;

public abstract class UnaryOperation<T> implements TripleExpression<T> {
    protected final TripleExpression<T> first;

    public UnaryOperation(TripleExpression<T> first) {
        this.first = first;
    }

    protected abstract T calculate(T x, Evaluator<T> evaluator) throws EvaluatingException;

    @Override
    public T evaluate(T x, T y, T z, Evaluator<T> evaluator) throws EvaluatingException {
        return calculate(first.evaluate(x, y, z, evaluator), evaluator);
    }

    @Override
    public String toString() {
        return "(" + getOperationType() + first + ")";
    }

    protected abstract String getOperationType();

    @Override
    public int hashCode() {
        return Objects.hash(first, getOperationType());
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof UnaryOperation) {
            @SuppressWarnings("unchecked") UnaryOperation<T> newSecond = (UnaryOperation<T>) obj;
            return Objects.equals(first, newSecond.first)
                    && Objects.equals(getOperationType(), newSecond.getOperationType());
        } else {
            return false;
        }
    }
}
