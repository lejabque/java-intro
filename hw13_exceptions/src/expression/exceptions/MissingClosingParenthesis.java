package expression.exceptions;

public class MissingClosingParenthesis extends ParsingException {
    public MissingClosingParenthesis(String message) {
        super("Unexpected end of source without closing parenthesis. " + message);
    }
}
