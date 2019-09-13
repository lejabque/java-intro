
public class HelloWorld {

	private static final long LARGE_REPEAT_SHIFT = 27;
	private static final long LARGE_REPEAT_COUNT = 1L << LARGE_REPEAT_SHIFT;
	private static final String PATTERN = "Hello, World! Привет, Мир!";

    public static void main(String[] args) {
        for (int i = 99; i < 100; i++) {
			String repeated;
			repeated = PATTERN.repeat(LARGE_REPEAT_COUNT);
			System.out.println(repeated.hashCode());
        }
    }

}
