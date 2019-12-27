package expression.parser;

import expression.PriorityExpression;

public class Main {
    public static void main(String[] args) {
        ExpressionParser parser = new ExpressionParser(new StringSource("(reverse (2065523746))"));
        PriorityExpression result = parser.parseExpression();
        System.out.println();
    }
}
