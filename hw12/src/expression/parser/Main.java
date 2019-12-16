package expression.parser;

import expression.PriorityExpression;

public class Main {
    public static void main(String[] args) {
        ExpressionParser parser = new ExpressionParser(new StringSource("1 << 5"));
        PriorityExpression result = parser.parseExpression();
        System.out.println(result.evaluate(0, 0, 0));
    }
}
