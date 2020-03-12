package generic;

import evaluator.CheckedIntegerEvaluator;
import evaluator.Evaluator;
import evaluator.IntegerEvaluator;
import exceptions.ParsingException;
import expression.TripleExpression;
import parser.ExpressionParser;
import parser.StringSource;

public class Main {
    public static void main(String[] args) throws ParsingException {
        String s = "count - y";
        StringSource source = new StringSource(s);
        ExpressionParser<Integer> parser = new ExpressionParser<>(source);
        Evaluator<Integer> evaluator = new IntegerEvaluator();
        TripleExpression<Integer> expr = parser.parseExpression();
        System.out.println(expr.evaluate(-6, -19, -2, evaluator));
    }
}
