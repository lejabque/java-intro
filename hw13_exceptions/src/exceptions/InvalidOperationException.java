package exceptions;

public class InvalidOperationException extends ParsingException {
    public InvalidOperationException(String message) {
        super(message);
    }
}
