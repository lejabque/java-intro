package expression.parser;

import expression.PriorityExpression;

public class Main {
    public static void main(String[] args) {
        ExpressionParser parser = new ExpressionParser(new StringSource("2*(x--2)* x+1"));
        PriorityExpression result = parser.parseExpression();
        System.out.println(result.evaluate(3));
    }
}
