package parser;

import expression.TripleExpression;

public interface Parser {
    TripleExpression parse(String expression);
}
