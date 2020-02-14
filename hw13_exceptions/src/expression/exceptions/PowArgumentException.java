package expression.exceptions;

public class PowArgumentException extends EvaluatingException {
    public PowArgumentException(int x, int y) {
        super("Invalid argument of power: x: " + Integer.toString(x) + " y: " + Integer.toString(y));
    }
}
