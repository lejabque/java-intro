package parser;

import exceptions.EvaluatingException;
import exceptions.OverflowException;
import exceptions.ZeroDivisionException;
import expression.PriorityExpression;

public class Main {
    public static void main(String[] args) {
        ExpressionParser parser = new ExpressionParser(new StringSource("x / y"));
        PriorityExpression result = parser.parseExpression();
        try {
            System.out.println(result.evaluate(Integer.MIN_VALUE, 0, 1));
        } catch (ZeroDivisionException e) {
            e.printStackTrace();
            System.out.println("ыыы");
        } catch (OverflowException e) {
            e.printStackTrace();
            System.out.println("ыыы переполнение");
        }
    }
}
