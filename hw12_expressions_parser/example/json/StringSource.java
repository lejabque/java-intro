package info.kgeorgiy.json;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public class StringSource implements JsonSource {
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
    public JsonException error(final String message) {
        return new JsonException(pos + ": " + message);
    }
}
