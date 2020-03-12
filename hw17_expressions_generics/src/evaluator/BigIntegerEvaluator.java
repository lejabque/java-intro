package evaluator;

import exceptions.ZeroDivisionException;

import java.math.BigInteger;

public class BigIntegerEvaluator implements Evaluator<BigInteger> {

    @Override
    public BigInteger add(BigInteger x, BigInteger y) {
        return x.add(y);
    }

    @Override
    public BigInteger subtract(BigInteger x, BigInteger y) {
        return x.subtract(y);
    }

    @Override
    public BigInteger multiply(BigInteger x, BigInteger y) {
        return x.multiply(y);
    }

    @Override
    public BigInteger divide(BigInteger x, BigInteger y) throws ZeroDivisionException {
        if (y.compareTo(BigInteger.ZERO) == 0) {
            throw new ZeroDivisionException();
        }
        return x.divide(y);
    }

    @Override
    public BigInteger negate(BigInteger x) {
        return x.negate();
    }

    @Override
    public BigInteger count(BigInteger x) {
        return new BigInteger(String.valueOf(x.bitCount()));
    }

    @Override
    public BigInteger max(BigInteger x, BigInteger y) {
        return x.max(y);
    }

    @Override
    public BigInteger min(BigInteger x, BigInteger y) {
        return x.min(y);
    }

    @Override
    public BigInteger parse(String value) {
        return new BigInteger(value);
    }
}
