package info.kgeorgiy.json;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public interface JsonSource {
    boolean hasNext();
    char next();
    JsonException error(final String message);
}
