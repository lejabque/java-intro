package exceptions;

public class InvalidOperatorException extends ParsingException {
    public InvalidOperatorException(char cur, String message) {
        super("Invalid operator: '" + cur + "'. " + message);
    }
}
