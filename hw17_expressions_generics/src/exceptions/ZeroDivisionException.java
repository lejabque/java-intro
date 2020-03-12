package exceptions;

public class ZeroDivisionException extends EvaluatingException {
    public ZeroDivisionException() {
        super("division by zero");
    }
}
