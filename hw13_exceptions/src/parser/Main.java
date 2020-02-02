package parser;

import exceptions.EvaluatingException;
import exceptions.OverflowException;
import exceptions.ParsingException;
import exceptions.ZeroDivisionException;
import expression.PriorityExpression;

public class Main {
    public static void main(String[] args) {
        ExpressionParser parser = new ExpressionParser(new StringSource(Integer.MAX_VALUE + 1L + ""));
        PriorityExpression result = null;
        try {
            result = parser.parseExpression();
        } catch (ParsingException e) {
            e.printStackTrace();
        }
        System.out.println(result.evaluate(1, 2, 3));
    }
}
