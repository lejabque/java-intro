package wordStat;

import base.Pair;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public class WordStatCountTest extends WordStatInputTest {
    public WordStatCountTest(final String className) {
        super(className);
    }

    public static void main(final String... args) {
        new WordStatCountTest("WordStatCount").run();
    }

    // Stream "magic" code. You do not expected to understand it
    protected Stream<Pair<String, Integer>> answer(final Stream<String> input) {
        return input
                .collect(Collectors.toMap(String::toLowerCase, v -> 1, Integer::sum, LinkedHashMap::new)).entrySet().stream()
                .map(Pair::of)
                .sorted(Comparator.comparingInt(Pair::getSecond));
    }
}
