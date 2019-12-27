package expression;

import java.util.Objects;

public abstract class BinaryOperation implements PriorityExpression {
    private final PriorityExpression first, second;

    public BinaryOperation(PriorityExpression first, PriorityExpression second) {
        this.first = first;
        this.second = second;
    }

    protected abstract int calculate(int x, int y);

    @Override
    public int evaluate(int x, int y, int z) {
        return calculate(first.evaluate(x, y, z), second.evaluate(x, y, z));
    }

    @Override
    public int evaluate(int x) {
        return calculate(first.evaluate(x), second.evaluate(x));
    }

    @Override
    public String toString() {
        return "(" + first + " " + getOperationType() + " " + second + ")";
    }

    private boolean requireBrackets(PriorityExpression expr) {
        return second.getPriority() < this.getPriority() ||
                second.getPriority() == this.getPriority() &&
                        (second.isImportant() || this.isImportant());
    }

    @Override
    public String toMiniString() {
        StringBuilder sb = new StringBuilder();
        PriorityExpression op = this.first;
        termToString(sb, op, op.getPriority() < this.getPriority());
        sb.append(" ").append(getOperationType()).append(" ");
        termToString(sb, second, requireBrackets(second));
        return sb.toString();
    }

    private void termToString(StringBuilder sb, PriorityExpression op, boolean brackets) {
        if (brackets) {
            sb.append("(").append(op.toMiniString()).append(")");
        } else {
            sb.append(op.toMiniString());
        }
    }

    protected abstract String getOperationType();

    @Override
    public int hashCode() {
        return Objects.hash(first, second, this.getClass());
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof BinaryOperation) {
            BinaryOperation newSecond = (BinaryOperation) obj;
            return Objects.equals(this.getClass(), obj.getClass()) &&
                    Objects.equals(first, newSecond.first) && Objects.equals(second, newSecond.second);
        } else {
            return false;
        }
    }
}
