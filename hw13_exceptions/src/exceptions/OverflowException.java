package exceptions;

public class OverflowException extends EvaluatingException {
    public OverflowException() {
        super("overflow");
    }
}
