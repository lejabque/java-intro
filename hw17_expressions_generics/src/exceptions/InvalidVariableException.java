package exceptions;

public class InvalidVariableException extends ParsingException {
    public InvalidVariableException(String variable, String message) {
        super("Invalid or missed variable: " + variable + " " + message);
    }
}
