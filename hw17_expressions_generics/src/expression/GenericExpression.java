package expression;

import evaluator.Evaluator;

public interface GenericExpression<T> extends ToMiniString {
    T evaluate(T x, T y, T z, Evaluator<T> evaluator);
}
