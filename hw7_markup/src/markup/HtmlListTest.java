package markup;

import java.util.Map;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public class HtmlListTest extends ListTest {
    private static final Map<String, String> HTML = Map.of();

    @Override
    protected void test(final Paragraph paragraph, final String expected) {
        test(paragraph::toHtml, expected, HTML);
    }

    protected void test(UnorderedList list, final String expected) {
        test(list::toHtml, expected, HTML);
    }

    protected void test(OrderedList list, final String expected) {
        test(list::toHtml, expected, HTML);
    }

    public static void main(String[] args) {
        new HtmlListTest().run();
    }
}
