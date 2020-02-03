package expression;

import exceptions.EvaluatingException;
import expression.ToMiniString;

public interface TripleExpression extends ToMiniString {
    int evaluate(int x, int y, int z) throws EvaluatingException;
}
