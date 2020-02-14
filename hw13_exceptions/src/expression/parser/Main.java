package expression.parser;

import expression.exceptions.ParsingException;
import expression.PriorityExpression;

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
        ExpressionParser parser = new ExpressionParser(new StringSource("log2x"));
        PriorityExpression result = null;
        try {
            result = parser.parseExpression();
        } catch (ParsingException e) {
            e.printStackTrace();
        }
        System.out.println("x f");
//        for (int i = 0; i < 11; i++) {
//            try {
//                System.out.println(i + " " + result.evaluate(i));
//            } catch (OverflowException e) {
//                System.out.println(i + " overflow");
//            } catch (ZeroDivisionException e) {
//                System.out.println(i + " division by zero");
//            }
//        }
        System.out.println(result.evaluate(3, 3, 2));
    }
}
