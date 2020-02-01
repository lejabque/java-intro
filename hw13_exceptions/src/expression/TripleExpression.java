package expression;

import exceptions.EvaluatingException;

public interface TripleExpression extends ToMiniString {
    int evaluate(int x, int y, int z) throws EvaluatingException;
}
