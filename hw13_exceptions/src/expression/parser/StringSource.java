package expression.parser;

import expression.exceptions.ParsingException;

public class StringSource implements ExpressionSource {
    private final String data;
    private int pos;
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";

    public StringSource(final String data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        return pos < data.length();
    }

    @Override
    public char next() {
        return data.charAt(pos++);
    }

    @Override
    public int getPos() {
        return pos;
    }

    @Override
    public ParsingException error(final String message) {
        return new ParsingException(pos + ": " + message);
    }

    public String getParsingInfo() {
        return "Error in pos: " + pos + " Part with error: " + data.substring(Math.max(pos - 10, 0), pos - 1)
                + ANSI_RED + data.substring(pos - 1, pos) + ANSI_RESET
                + data.substring(pos, Math.min(pos + 5, data.length()));
    }
}
