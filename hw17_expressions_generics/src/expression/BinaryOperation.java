package expression;

import exceptions.EvaluatingException;
import evaluator.Evaluator;

import java.util.Objects;

public abstract class BinaryOperation<T> implements GenericExpression<T> {
    protected final GenericExpression<T> first, second;

    public BinaryOperation(GenericExpression<T> first, GenericExpression<T> second) {
        this.first = first;
        this.second = second;
    }

    protected abstract T calculate(T x, T y, Evaluator<T> evaluator) throws EvaluatingException;

    @Override
    public T evaluate(T x, T y, T z, Evaluator<T> evaluator) throws EvaluatingException {
        return calculate(first.evaluate(x, y, z, evaluator), second.evaluate(x, y, z, evaluator), evaluator);
    }

    @Override
    public String toString() {
        return "(" + first + " " + getOperationType() + " " + second + ")";
    }

    protected abstract String getOperationType();

    @Override
    public int hashCode() {
        return Objects.hash(first, second, getOperationType());
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof BinaryOperation) {
            @SuppressWarnings("unchecked") BinaryOperation<T> newSecond = (BinaryOperation<T>) obj;
            return Objects.equals(first, newSecond.first) && Objects.equals(second, newSecond.second)
                    && Objects.equals(getOperationType(), newSecond.getOperationType());
        } else {
            return false;
        }
    }
}
