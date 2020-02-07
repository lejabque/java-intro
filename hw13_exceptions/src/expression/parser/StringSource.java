package expression.parser;

import expression.exceptions.ParsingException;

public class StringSource implements ExpressionSource {
    private final String data;
    private int pos;

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
        return "INFO: Current pos: " + pos + " Parsed prefix: " + data.substring(0, pos);
    }
}
