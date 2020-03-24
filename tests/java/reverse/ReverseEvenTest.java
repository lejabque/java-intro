package reverse;

import java.util.Arrays;
import java.util.stream.Stream;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public class ReverseEvenTest extends ReverseTest {
    public ReverseEvenTest(final int maxSize) {
        super("ReverseEven", maxSize);
    }

    public static void main(String... args) {
        new ReverseEvenTest(MAX_SIZE).run();
    }

    @Override
    protected int[][] transform(final int[][] ints) {
        return super.transform(Stream.of(ints)
                .map(row -> Arrays.stream(row).filter(v -> v % 2 == 0).toArray())
                .toArray(int[][]::new));
    }
}
