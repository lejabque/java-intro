package evaluator;

import exceptions.ZeroDivisionException;

public class ByteEvaluator implements Evaluator<Byte> {

    @Override
    public Byte add(Byte x, Byte y) {
        return (byte) (x + y);
    }

    @Override
    public Byte subtract(Byte x, Byte y) {
        return (byte) (x - y);
    }

    @Override
    public Byte multiply(Byte x, Byte y) {
        return (byte) (x * y);
    }

    @Override
    public Byte divide(Byte x, Byte y) {
        if (y == 0) {
            throw new ZeroDivisionException();
        }
        return (byte) (x / y);
    }

    @Override
    public Byte negate(Byte x) {
        return (byte) -x;
    }

    @Override
    public Byte count(Byte x) {
        return x == -1 ? 16 : (byte) (Integer.bitCount(x) % 16);
    }

    @Override
    public Byte max(Byte x, Byte y) {
        return (byte) Math.max(x, y);
    }

    @Override
    public Byte min(Byte x, Byte y) {
        return (byte) Math.min(x, y);
    }

    @Override
    public Byte parse(String value) {
        return Byte.parseByte(value);
    }
}
