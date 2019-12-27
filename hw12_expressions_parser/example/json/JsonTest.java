package info.kgeorgiy.json;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public class JsonTest {
    private void valid(final Object expected, final String json) {
        Assert.assertEquals(expected, parse(json));
    }

    private Object parse(final String json) {
        return Json.parse(json);
    }

    private void invalid(final String input) {
        try {
            final Object value = parse(input);
            Assert.fail("Expected fail, found " + value + " for " + input);
        } catch (JsonException e) {
            System.out.println("Expected error");
            System.out.println("    " + input);
            System.out.println("    " + e.getMessage());
        }
    }

    @Test
    public void parseString() {
        valid("", "\"\"");
        valid("abc", "\"abc\"");
        // \"
        valid("\"", "\"\\\"\"");
        //\\\/\b\f\n\r\t
        valid("\\/\b\f\n\r\t", "\"\\\\\\/\\b\\f\\n\\r\\t\"");
    }

    @Test
    public void parseUnicodeEscape() {
        valid("\u1234", "\"\\u1234\"");
        valid("\uabef", "\"\\uabef\"");
        valid("\uabef", "\"\\uABEF\"");
        valid("\uabef0", "\"\\uABEF0\"");
        invalid("\"\\uABE\"");
    }

    @Test
    public void parseInvalidEscape() {
        invalid("\"\\q\"");
    }

    @Test
    public void parseWhitespaceString() {
        valid("hello", " \t   \r\n\"hello\"");
        valid("hello", "\"hello\" \t   \r\n");
    }

    @Test
    public void parseInvalidSuffix() {
        invalid("\"hello\" abc");
    }

    @Test
    public void parseEmptyInput() {
        invalid("");
        invalid("   ");
    }

    @Test
    public void parseNumbers() {
        assertNumber(1, "1");
        assertNumber(1, "1.0");
        assertNumber(12.3, "12.3");
        assertNumber(-12.3, "-12.3");
        assertNumber(3.14e10, "3.14e10");
        assertNumber(3.14E-10, "3.14E-10");
        assertNumber(3.14E+10, "3.14E+10");
    }

    @Test
    public void invalidNumber() {
        invalid("1p1");
        invalid("--1");
        invalid("-+1");
    }

    @Test
    public void invalidPlusNumber() {
        invalid("+1");
    }

    @Test
    public void invalidOctalNumber() {
        invalid("070");
    }

    @Test
    public void invalidNumberSequence() {
        invalid("1+1");
    }

    private void assertNumber(final double expected, final String json) {
        Assert.assertEquals(expected, ((Number) parse(json)).doubleValue(), 1e-10);
    }

    @Test
    public void testBoolean() {
        valid(true, "true");
        valid(false, "false");
    }

    @Test
    public void testNull() {
        valid(null, "null");
    }

    @Test
    public void testNumber() {
        invalid("number");
    }

    @Test
    public void testArray() {
        valid(List.of(), "[]");
        valid(Collections.singletonList("hello"), "[\"hello\"]");
        valid(Arrays.asList("hello", "world"), "[\"hello\", \"world\"]");
    }

    @Test
    public void testObject() {
        valid(Map.of(), "{}");
        valid(Map.of("hello", "world"), "{\"hello\": \"world\"}");
        valid(Map.of("hello", Boolean.TRUE), "{\"hello\": true}");
        Map<String, Object> expected = Map.of(
                "key1", "value1",
                "key2", "value2",
                "key3", "value3"
        );
        valid(expected, "{\"key1\": \"value1\", \"key2\": \"value2\", \"key3\": \"value3\"}");
    }
}
