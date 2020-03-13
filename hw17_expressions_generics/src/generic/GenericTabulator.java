package generic;

import expression.TripleExpression;
import evaluator.*;
import exceptions.*;
import parser.ExpressionParser;
import parser.StringSource;

public class GenericTabulator implements Tabulator {
    @Override
    public Object[][][] tabulate(String mode, String expression, int x1, int x2,
                                 int y1, int y2, int z1, int z2) throws ParsingException {
        StringSource source = new StringSource(expression);

        return evaluateTable(new ExpressionParser<>(source), getEvaluator(mode), x1, x2,
                y1, y2, z1, z2);
    }

    private Evaluator<?> getEvaluator(String mode) {
        switch (mode) {
            case "i":
                return new CheckedIntegerEvaluator();
            case "u":
                return new IntegerEvaluator();
            case "l":
                return new LongEvaluator();
            case "s":
                return new ShortEvaluator();
            case "d":
                return new CheckedDoubleEvaluator();
            case "f":
                return new FloatEvaluator();
            case "bi":
                return new BigIntegerEvaluator();
        }
        return null;
    }

    private <T> Object[][][] evaluateTable(ExpressionParser<T> parser, Evaluator<T> evaluator,
                                           int x1, int x2, int y1, int y2, int z1, int z2) throws ParsingException {

        assert parser != null;
        TripleExpression<T> expr = parser.parseExpression();
        Object[][][] ans = new Object[x2 - x1 + 1][y2 - y1 + 1][z2 - z1 + 1];
        for (int i = 0; i <= x2 - x1; i++) {
            for (int j = 0; j <= y2 - y1; j++) {
                for (int k = 0; k <= z2 - z1; k++) {
                    try {
                        ans[i][j][k] = expr.evaluate(evaluator.parse(Integer.toString(i + x1)),
                                evaluator.parse(Integer.toString(j + y1)), evaluator.parse(Integer.toString(k + z1)), evaluator);
                    } catch (EvaluatingException e) {
                        ans[i][j][k] = null;
                    }
                }
            }
        }
        return ans;
    }
}
