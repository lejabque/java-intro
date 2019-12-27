package expression;

public interface PriorityExpression extends TripleExpression, Expression {
    int getPriority();

    boolean isImportant();
}
