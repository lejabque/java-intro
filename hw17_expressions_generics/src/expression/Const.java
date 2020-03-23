package expression;

import evaluator.Evaluator;

import java.util.Objects;

public final class Const<T> implements GenericExpression<T> {
    private final String strValue;

    public Const(String value) {
        this.strValue = value;
    }

    @Override
    public T evaluate(T x, T y, T z, Evaluator<T> evaluator) {
        return evaluator.parse(strValue);
    }

    @Override
    public String toString() {
        return strValue;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Const) {
            @SuppressWarnings("unchecked") Const<T> second = (Const<T>) obj;
            return strValue.equals(second.strValue);
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(strValue);
    }
}
