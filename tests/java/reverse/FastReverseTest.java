package reverse;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public class FastReverseTest {
    public static final int MAX_SIZE = 1000_000;
    public static void main(String... args) {
        new ReverseTest(MAX_SIZE).run();
    }
}
