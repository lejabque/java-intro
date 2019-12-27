package sum;

import java.util.Arrays;
import java.util.Random;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public class SumLongTest extends SumTest {
    public SumLongTest(final SChecker checker) {
        super(checker);
    }

    public static void main(final String... args) {
        new SumLongTest(new SumChecker("SumLong")).run();
    }

    @Override
    protected void test() {
        test(1, "1");
        test(6, "1", "2", "3");
        test(1, " 1");
        test(1, "1 ");
        test(1, "\u20001\u2000");
        test(12345, "\u200012345\u2000");
        test(1368, " 123 456 789 ");
        test(60, "010", "020", "030");
        test(-1, "-1");
        test(-6, "-1", "-2", "-3");
        test(-12345, "\u2000-12345\u2000");
        test(-1368, " -123 -456 -789 ");
        test(1, "+1");
        test(6, "+1", "+2", "+3");
        test(12345, "\u2000+12345\u2000");
        test(1368, " +123 +456 +789 ");
        test(12345678901234567L, " +12345678901234567 ");
        test(0L, " +12345678901234567 -12345678901234567");
        test(0L, " +12345678901234567 -12345678901234567");
        test(0);
        test(0, " ");
        randomTest(10, 100);
        randomTest(100, Long.MIN_VALUE);
        randomTest(100, Long.MAX_VALUE);
    }

    protected Number randomValue(final Number max, final Random random) {
        return random.nextLong() % max.longValue();
    }

    @Override
    protected Number sum(final Number[] values) {
        return Arrays.stream(values).mapToLong(Number::longValue).sum();
    }
}
