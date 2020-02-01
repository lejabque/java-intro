package expression;

import exceptions.EvaluatingException;
import exceptions.OverflowException;
import exceptions.ZeroDivisionException;

import java.util.Objects;

public abstract class BinaryOperation implements CommonExpression {
    protected final PriorityExpression first, second;

    public BinaryOperation(PriorityExpression first, PriorityExpression second) {
        this.first = first;
        this.second = second;
    }

    protected abstract int calculate(int x, int y) throws EvaluatingException;

    @Override
    public int evaluate(int x, int y, int z) throws EvaluatingException {
        return calculate(first.evaluate(x, y, z), second.evaluate(x, y, z));
    }

    @Override
    public int evaluate(int x) throws EvaluatingException {
        return calculate(first.evaluate(x), second.evaluate(x));
    }

    @Override
    public String toString() {
        return "(" + first + " " + getOperationType() + " " + second + ")";
    }

    private boolean requireBrackets() {
        return second.getPriority() < this.getPriority() ||
                second.getPriority() == this.getPriority() &&
                        (second.isImportant() || this.isImportant());
    }

    @Override
    public String toMiniString() {
        StringBuilder sb = new StringBuilder();
        if (first.getPriority() < this.getPriority()) {
            sb.append("(").append(first.toMiniString()).append(")");
        } else {
            sb.append(first.toMiniString());
        }

        sb.append(" ").append(getOperationType()).append(" ");
        if (requireBrackets()) {
            sb.append("(").append(second.toMiniString()).append(")");
        } else {
            sb.append(second.toMiniString());
        }
        return sb.toString();
    }

    protected abstract String getOperationType();

    @Override
    public int hashCode() {
        return Objects.hash(first, second, getOperationType());
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof BinaryOperation) {
            BinaryOperation new_second = (BinaryOperation) obj;
            return Objects.equals(first, new_second.first) && Objects.equals(second, new_second.second)
                    && Objects.equals(getOperationType(), new_second.getOperationType());
        } else {
            return false;
        }
    }
}
