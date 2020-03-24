package exceptions;

public class InvalidOperatorException extends ParsingException {
    public InvalidOperatorException(String cur, String message) {
        super("Invalid operator: '" + cur + "'. " + message);
    }
}
