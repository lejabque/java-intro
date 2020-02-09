package expression.parser;

import expression.exceptions.ExpressionParser;
import expression.exceptions.OverflowException;
import expression.exceptions.ParsingException;
import expression.PriorityExpression;
import expression.exceptions.ZeroDivisionException;

public class Main {
    public static void main(String[] args) {
//        ExpressionParser parser = new ExpressionParser();
//        PriorityExpression result = null;
//        try {
//            result = parser.parse("10");
//        } catch (ParsingException e) {
//            e.printStackTrace();
//        }
//        System.out.println(result.evaluate(-170439996, 1234510033, -1182702066));
        ExpressionParser parser = new ExpressionParser(new StringSource("1000000*x*x*x*x*x/(x-1)"));
        PriorityExpression result = null;
        try {
            result = parser.parseExpression();
        } catch (ParsingException e) {
            e.printStackTrace();
        }
        System.out.println("x f");
        for (int i = 0; i < 11; i++) {
            try {
                System.out.println(i + " " + result.evaluate(i));
            } catch (OverflowException e) {
                System.out.println(i + " overflow");
            } catch (ZeroDivisionException e) {
                System.out.println(i + " division by zero");
            }
        }
//        System.out.println(result.evaluate(3, 1, 2));
    }
}
