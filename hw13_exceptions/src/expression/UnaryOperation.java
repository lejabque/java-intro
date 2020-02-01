package expression;

import exceptions.EvaluatingException;
import exceptions.OverflowException;

import java.util.Objects;

public abstract class UnaryOperation implements CommonExpression {
    protected final PriorityExpression first;

    public UnaryOperation(PriorityExpression first) {
        this.first = first;
    }

    protected abstract int calculate(int x) throws EvaluatingException;

    @Override
    public int evaluate(int x, int y, int z) throws EvaluatingException {
        return calculate(first.evaluate(x, y, z));
    }

    @Override
    public int evaluate(int x) throws EvaluatingException {
        return calculate(first.evaluate(x));
    }

    @Override
    public String toString() {
        return "(" + getOperationType() + first + ")";
    }

    @Override
    public String toMiniString() {
        StringBuilder sb = new StringBuilder();

        if (this.isImportant()) {
            sb.append("(").append(getOperationType()).append(first.toMiniString()).append(")");
        } else {
            sb.append(getOperationType()).append(first.toMiniString());
        }
        return sb.toString();
    }

    protected abstract String getOperationType();

    @Override
    public int hashCode() {
        return Objects.hash(first, getOperationType());
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof UnaryOperation) {
            UnaryOperation new_second = (UnaryOperation) obj;
            return Objects.equals(first, new_second.first)
                    && Objects.equals(getOperationType(), new_second.getOperationType());
        } else {
            return false;
        }
    }
}
