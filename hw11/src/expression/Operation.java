package expression;

import java.util.Objects;

public abstract class Operation implements PriorityExpression {
    protected final PriorityExpression first, second;

    public Operation(PriorityExpression first, PriorityExpression second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Operation) {
            Operation new_second = (Operation) obj;
            return Objects.equals(first, new_second.first) && Objects.equals(second, new_second.second)
                    && Objects.equals(getOperationType(), new_second.getOperationType());
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("(").append(first).append(" ").append(getOperationType()).
                append(" ").append(second).append(")");
        return sb.toString();
    }

    private boolean requireBrackets(PriorityExpression expr) {
        return second.getPriority() < this.getPriority() ||
                second.getPriority() == this.getPriority() &&
                        (isImportantOperation(second) || isImportantOperation(this));
    }

    private boolean isImportantOperation(PriorityExpression expr) {
        return expr instanceof Divide || expr instanceof Subtract;
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
        if (requireBrackets(second)) {
            sb.append("(").append(second.toMiniString()).append(")");
        } else {
            sb.append(second.toMiniString());
        }
        return sb.toString();
    }

    protected abstract String getOperationType();

    @Override
    public int hashCode() {
        return (Objects.hashCode(first) * 31 + Objects.hashCode(second)) * 31 + Objects.hashCode(getOperationType());
    }
}
