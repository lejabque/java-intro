package expression.parser;

import expression.exceptions.ParsingException;

public interface ExpressionSource {
    boolean hasNext();
    char next();
    String getPart();
    ParsingException error(final String message);
}
