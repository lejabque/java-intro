package parser;

import exceptions.ParsingException;

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
    public ParsingException error(final String message) {
        return new ParsingException(pos + ": " + message);
    }

    @Override
    public String getPart() {
        return data.substring(Math.max(pos - 10, 0), Math.min(pos + 5, data.length()));
    }
}
