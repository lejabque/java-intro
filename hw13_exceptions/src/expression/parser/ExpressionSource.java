package expression.parser;

import expression.exceptions.ParsingException;

public interface ExpressionSource {
    boolean hasNext();
    char next();
    int getPos();
    String getParsingInfo();
    ParsingException error(final String message);
}
