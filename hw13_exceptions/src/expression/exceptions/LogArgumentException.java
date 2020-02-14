package expression.exceptions;

public class LogArgumentException extends EvaluatingException {
    public LogArgumentException(int x, int y) {
        super("Invalid argument of logarithm: x: " + Integer.toString(x) + " y: " + Integer.toString(y));
    }
}
