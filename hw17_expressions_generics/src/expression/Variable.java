package expression;

import evaluator.Evaluator;

import java.util.NoSuchElementException;
import java.util.Objects;

public final class Variable<T> implements GenericExpression<T> {
    private final String variable;

    public Variable(String var) {
        this.variable = var;
    }

    @Override
    public T evaluate(T x, T y, T z, Evaluator<T> evaluator) {
        switch (variable) {
            case "x":
                return x;
            case "y":
                return y;
            case "z":
                return z;
        }
        throw new NoSuchElementException("Incorrect variable");
    }

    @Override
    public String toString() {
        return variable;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Variable) {
            @SuppressWarnings("unchecked") Variable<T> second = (Variable<T>) obj;
            return Objects.equals(variable, second.variable);
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(variable);
    }
}
