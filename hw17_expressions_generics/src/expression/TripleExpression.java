package expression;

import evaluator.Evaluator;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public interface TripleExpression<T> {
    T evaluate(T x, T y, T z, Evaluator<T> evaluator);
}
