package parser;

import exceptions.ParsingException;

public interface ExpressionSource {
    boolean hasNext();
    char next();
    int getPos();
    String getParsingInfo();
    ParsingException error(final String message);
}
