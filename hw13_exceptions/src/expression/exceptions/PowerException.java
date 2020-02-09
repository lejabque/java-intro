package expression.exceptions;

public class PowerException extends EvaluatingException {
    public PowerException() {
        super("Invalid argument of power");
    }
}
