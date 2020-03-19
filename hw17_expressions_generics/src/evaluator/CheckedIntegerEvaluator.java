package evaluator;

import exceptions.OverflowException;
import exceptions.ZeroDivisionException;

public class CheckedIntegerEvaluator extends IntegerEvaluator {

    @Override
    public Integer add(Integer x, Integer y) throws OverflowException {
        int res = x + y;
        if (y > 0 && x > 0 && res <= 0) {
            throw new OverflowException();
        }
        if (y < 0 && x < 0 && res >= 0) {
            throw new OverflowException();
        }
        return res;
    }

    @Override
    public Integer subtract(Integer x, Integer y) throws OverflowException {
        int res = x - y;
        if (x > 0 && y < 0 && res <= 0) {
            throw new OverflowException();
        }
        if (x < 0 && y > 0 && res >= 0) {
            throw new OverflowException();
        }
        if (x == 0 && y == Integer.MIN_VALUE) {
            throw new OverflowException();
        }
        return res;
    }

    @Override
    public Integer multiply(Integer x, Integer y) throws OverflowException {
        int res = x * y;
        if (x != 0 && y != 0 && (res / y != x || res / x != y)) {
            throw new OverflowException();
        }
        return res;
    }

    @Override
    public Integer divide(Integer x, Integer y) throws OverflowException, ZeroDivisionException {
        if (y == 0) {
            throw new ZeroDivisionException();
        }
        if (y == -1 && x == Integer.MIN_VALUE) {
            throw new OverflowException();
        }
        return x / y;
    }

    @Override
    public Integer negate(Integer x) throws OverflowException {
        if (x == Integer.MIN_VALUE) {
            throw new OverflowException();
        }
        return -x;
    }
}
