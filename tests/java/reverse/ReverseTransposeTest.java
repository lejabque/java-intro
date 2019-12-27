package reverse;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public class ReverseTransposeTest extends ReverseTest {
    public ReverseTransposeTest(final int maxSize) {
        super("ReverseTranspose", maxSize);
    }

    public static void main(String... args) {
        new ReverseTransposeTest(MAX_SIZE).run();
    }

    @Override
    protected int[][] transform(final int[][] ints) {
        final List<int[]> rows = new ArrayList<>(List.of(ints));
        return IntStream.range(0, Arrays.stream(ints).mapToInt(r -> r.length).max().orElse(0))
                .mapToObj(c -> {
                    rows.removeIf(r -> r.length <= c);
                    return rows.stream().mapToInt(r -> r[c]).toArray();
                })
                .toArray(int[][]::new);
    }
}
