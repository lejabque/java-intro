package parser;

import exceptions.ParsingException;
import expression.TripleExpression;

public interface Parser {
    TripleExpression parse(String expression) throws ParsingException;
}
