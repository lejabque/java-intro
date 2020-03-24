package sum;

import base.Asserts;
import base.MainChecker;

import java.util.Collections;
import java.util.List;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public class SumDoubleChecker extends MainChecker implements SChecker {
    public SumDoubleChecker(final String className) {
        super(className);
    }

    @Override
    public void test(final Number result, final String... input) {
        final List<String> out = run(input);
        Asserts.assertEquals("Single line expected", 1, out.size());
        final double actual = Double.parseDouble(out.get(0));
        Asserts.assertEquals("Sum", result.doubleValue(), actual, 1e-10);
        checkEquals(Collections.emptyList(), Collections.emptyList());
    }
}
