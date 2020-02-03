package expression;

import expression.Expression;
import expression.TripleExpression;

public interface PriorityExpression extends TripleExpression, Expression {
    int getPriority();

    boolean isImportant();
}
