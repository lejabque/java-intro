package expression;

import exceptions.EvaluatingException;
import expression.ToMiniString;

public interface Expression extends ToMiniString {
    int evaluate(int x) throws EvaluatingException;
}
