package expression;

public class Variable implements Expression {
    String var;

    public Variable(String var) {
        this.var = var;
    }

    public String getValue(String var) {
        return var;
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
    public String toString() {
        return var;
    }
}
