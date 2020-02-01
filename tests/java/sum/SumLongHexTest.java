package sum;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public class SumLongHexTest extends SumLongTest {

    public SumLongHexTest(final SChecker checker) {
        super(checker);
    }

    public static void main(final String... args) {
        new SumLongHexTest(new SumChecker("SumLongHex")).run();
    }

    @Override
    protected void test() {
        test(1, "1");
        test(6, "1", "2", "3");
        test(1, " 1");
        test(1, "1 ");
        test(1, "\t1\t");
        test(12345, "\t12345\t");
        test(1368, " 123 456 789 ");
        test(60, "010", "020", "030");

        test(1, "0x1");
        test(0x1a, "0x1a");
        test(0xA2, "0xA2");
        test(62, " 0X0 0X1 0XF 0XF 0x0 0x1 0xF 0xf");
        test(0x12345678L, "0x12345678");
        test(0x90abcdefL, "0x90abcdef");
        test(0x1234567890abcdefL, "0x1234567890abcdef");
        test(0xCafeBabeL, "0xCafeBabe");
        test(0xDeadBeefDeadBeefL, "0xDeadBeefDeadBeef");

        test(-1, "-1");
        test(-6, "-1", "-2", "-3");
        test(-12345, "\t-12345\t");
        test(-1368, " -123 -456 -789 ");
        test(1, "+1");
        test(6, "+1", "+2", "+3");
        test(12345, "\t+12345\t");
        test(1368, " +123 +456 +789 ");
        test(0);
        test(0, " ");

        test(Long.MAX_VALUE, "0" + Long.MAX_VALUE);
        test(Long.MIN_VALUE, "" + Long.MIN_VALUE);
        test(Long.MAX_VALUE, "0x" + Long.toHexString(Long.MAX_VALUE));
        test(Long.MIN_VALUE, "0x" + Long.toHexString(Long.MIN_VALUE));

        randomTest(10, 100);
        randomTest(10, Long.MAX_VALUE);
        randomTest(100, Long.MAX_VALUE);
    }

    @Override
    protected String toString(final Number number) {
        return checker.getRandom().nextBoolean() ? super.toString(number) : "0X" + Long.toHexString(number.longValue());
    }
}
