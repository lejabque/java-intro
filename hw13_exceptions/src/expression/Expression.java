package expression;

import exceptions.EvaluatingException;

public interface Expression extends ToMiniString {
    int evaluate(int x) throws EvaluatingException;
}
