package expression;

import java.util.Map;

public abstract class Operation implements Expression {
    protected Expression first, second;
    private int priority;
    private static Map<String, Integer> priorityMap = Map.of("-", 0, "+", 0,
            "*", 1, "/", 1
    );

    public Operation(Expression first, Expression second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("(").append(first).append(" ").append(getOperationType()).
                append(" ").append(second).append(")");
        return sb.toString();
    }

    @Override
    public String toMiniString() {
        StringBuilder sb = new StringBuilder();
        if (first.getPriority() < this.priority) {
            sb.append("(");
        }
        sb.append(first).append(" ");
        if (first.getPriority() < this.priority) {
            sb.append(")");
        }
        sb.append(getOperationType());
        sb.append(" ");
        if (second.getPriority() < this.priority) {
            sb.append("(");
        }
        sb.append(second).append(" ");
        if (second.getPriority() < this.priority) {
            sb.append(")");
        }
        return sb.toString();
    }

    public int getPriority() {
        return priorityMap.get(getOperationType());
    }

    protected abstract String getOperationType();
}
