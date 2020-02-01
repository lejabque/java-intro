package parser;

import exceptions.EvaluatingException;
import exceptions.OverflowException;
import exceptions.ZeroDivisionException;
import expression.PriorityExpression;

public class Main {
    public static void main(String[] args) {
        ExpressionParser parser = new ExpressionParser(new StringSource("1000000*x*x*x*x*x/(x-1)"));
        PriorityExpression result = parser.parseExpression();
        System.out.println("x f");
        for (int i = 0; i < 11; i++) {
            System.out.print(Integer.toString(i) + " ");
            try {
                System.out.println(result.evaluate(i));
            } catch (ZeroDivisionException e) {
                System.out.println("division by zero");
            } catch (OverflowException e) {
                System.out.println("overflow");
            }
        }
    }
}
