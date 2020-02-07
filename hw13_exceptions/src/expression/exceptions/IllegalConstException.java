package expression.exceptions;

public class IllegalConstException extends ParsingException {
    public IllegalConstException(String parsed, String message) {
        super("Illegal const (possible overflow): " + parsed + " " + message);
    }
}
