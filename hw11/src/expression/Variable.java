package expression;

import java.util.Objects;

public class Variable implements PriorityExpression {
    String var;

    public Variable(String var) {
        this.var = var;
    }

    public String getValue(String var) {
        return var;
    }

    public int evaluate(int x) {
        return x;
    }

    @Override
    public int getPriority() {
        return 1000;
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
