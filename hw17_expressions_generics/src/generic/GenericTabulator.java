package generic;

import expression.GenericExpression;
import evaluator.*;
import exceptions.*;
import parser.ExpressionParser;
import parser.StringSource;

import java.util.Map;

public class GenericTabulator implements Tabulator {
    private final static Map<String, Evaluator<?>> EVALUATORS = Map.of(
            "i", new CheckedIntegerEvaluator(),
            "u", new IntegerEvaluator(),
            "l", new LongEvaluator(),
            "s", new ShortEvaluator(),
            "b", new ByteEvaluator(),
            "d", new DoubleEvaluator(),
            "f", new FloatEvaluator(),
            "bi", new BigIntegerEvaluator()
    );

    @Override
    public Object[][][] tabulate(String mode, String expression, int x1, int x2,
                                 int y1, int y2, int z1, int z2) throws ParsingException {
        return makeTable(EVALUATORS.get(mode), expression, x1, x2, y1, y2, z1, z2);
    }

    private <T> Object[][][] makeTable(Evaluator<T> evaluator, String expression, int x1, int x2,
                                       int y1, int y2, int z1, int z2) throws ParsingException {
        GenericExpression<T> expr = new ExpressionParser<T>(new StringSource(expression)).parseExpression();
        Object[][][] ans = new Object[x2 - x1 + 1][y2 - y1 + 1][z2 - z1 + 1];
        for (int i = 0; i <= x2 - x1; i++) {
            for (int j = 0; j <= y2 - y1; j++) {
                for (int k = 0; k <= z2 - z1; k++) {
                    try {
                        ans[i][j][k] = expr.evaluate(evaluator.getValue(i + x1),
                                evaluator.getValue(j + y1), evaluator.getValue(k + z1), evaluator);
                    } catch (EvaluatingException ignored) {
                    }
                }
            }
        }
        return ans;
    }
}
