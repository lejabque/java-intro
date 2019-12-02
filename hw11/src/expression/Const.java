package expression;

public class Const implements Expression {
    int value;

    public Const(int value) {
        this.value = value;
    }

    @Override
    public int evaluate(int x) {
        return value;
    }

    @Override
    public int getPriority() {
        return 1000;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
