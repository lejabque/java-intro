package reverse;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public class ReverseSortTest extends ReverseTest {
    public ReverseSortTest(final int maxSize) {
        super("ReverseSort", maxSize);
    }

    public static void main(String... args) {
        new ReverseSortTest(MAX_SIZE).run();
    }

    @Override
    protected int[][] transform(final int[][] ints) {
        final List<int[]> sorted = Arrays.stream(ints)
                .map(Box::new)
                .sorted(Comparator.comparingLong(box -> box.sum))
                .map(box -> box.array)
                .collect(Collectors.toList());
        Collections.reverse(sorted);
        return sorted.toArray(int[][]::new);
    }

    private static class Box {
        private final int[] array;
        private final long sum;

        Box(final int[] array) {
            this.array = Arrays.stream(array).boxed().sorted(Comparator.reverseOrder()).mapToInt(Integer::intValue).toArray();
            this.sum = Arrays.stream(array).mapToLong(a -> a).sum();
        }
    }
}
