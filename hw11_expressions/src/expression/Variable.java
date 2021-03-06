package expression;

import java.util.NoSuchElementException;
import java.util.Objects;

public final class Variable implements PriorityExpression {
    private final String var;

    public Variable(String var) {
        this.var = var;
    }

    @Override
    public int evaluate(int x, int y, int z) {
        switch (var) {
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
    public int evaluate(int x) {
        return x;
    }

    @Override
    public int getPriority() {
        return 1000;
    }

    @Override
    public boolean isImportant() {
        return false;
    }

    @Override
    public String toString() {
        return var;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Variable) {
            Variable second = (Variable) obj;
            return Objects.equals(var, second.var);
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(var);
    }
}
