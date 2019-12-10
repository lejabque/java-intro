package info.kgeorgiy.json;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public class Json {
    public static Object parse(final String source) {
        return parse(new StringSource(source));
    }

    public static Object parse(JsonSource source) {
        return new JsonParser(source).parseJson();
    }

    private static class JsonParser extends BaseParser {
        public JsonParser(final JsonSource source) {
            super(source);
            nextChar();
        }

        /*
         * json
         *    element
         */
        public Object parseJson() {
            final Object result = parseElement();
            if (test('\0')) {
                return result;
            }
            throw error("End of JSON expected");
        }

        /*
         * element
         *     ws value ws
         */
        private Object parseElement() {
            skipWhitespace();
            final Object result = parseValue();
            skipWhitespace();
            return result;
        }

        /*
         * value
         *    object
         *    array
         *    string
         *    number
         *    "true"
         *    "false"
         *    "null"
         */
        private Object parseValue() {
            if (test('{')) {
                return parseObject();
            } else if (test('[')) {
                return parseArray();
            } else if (test('"')) {
                return parseString();
            } else if (test('t')) {
                expect("rue");
                return true;
            } else if (test('f')) {
                expect("alse");
                return false;
            } else if (test('n')) {
                expect("ull");
                return null;
            } else {
                return parseNumber();
            }
        }

        /*
         * array
         *     '[' ws ']'
         *     '[' elements ']'
         *
         * elements
         *     element
         *     element ',' elements
         */
        private List<Object> parseArray() {
            skipWhitespace();
            if (test(']')) {
                return List.of();
            }

            List<Object> array = new ArrayList<>();
            array.add(parseValue());
            while (test(',')) {
                array.add(parseElement());
            }

            expect(']');
            return array;
        }

        /*
         * object
         *     '{' ws '}'
         *     '{' members '}'
         */
        private Map<String, Object> parseObject() {
            skipWhitespace();
            if (test('}')) {
                return Map.of();
            }
            Map<String, Object> object = new HashMap<>();
            addMember(object);

            while (test(',')) {
                addMember(object);
            }
            expect('}');
            return object;
        }

        /*
         * member
         *     ws string ws ':' element
         */
        private void addMember(final Map<String, Object> object) {
            skipWhitespace();
            expect('"');
            String key = parseString();
            skipWhitespace();
            expect(':');
            final Object element = parseElement();
            object.put(key, element);
        }

        /*
         * string
         * 	  '"' characters '"'
         * characters
         *     ""
         *     character characters
         * character
         *     '0020' . '10FFFF' - '"' - '\'
         *     '\' escape
         * escape
         *     '"'
         *     '\'
         *     '/'
         *     'b'
         *     'f'
         *     'n'
         *     'r'
         *     't'
         *     'u' hex hex hex hex
         */
        private String parseString() {
            StringBuilder sb = new StringBuilder();
            while (!test('"')) {
                if (test('\\')) {
                    if (
                            escaped(sb, '\"', '\"')
                                    || escaped(sb, '\\', '\\')
                                    || escaped(sb, '/', '/')
                                    || escaped(sb, '\b', 'b')
                                    || escaped(sb, '\f', 'f')
                                    || escaped(sb, '\n', 'n')
                                    || escaped(sb, '\r', 'r')
                                    || escaped(sb, '\t', 't')
                    ) {
                        // next char
                    } else if (test('u')) {
                        int value = 0;
                        for (int i = 0; i < 4; i++) {
                            value <<= 4;
                            if (between('0', '9')) {
                                value = nextHex(value, '0');
                            } else if (between('a', 'z')) {
                                value = nextHex(value, 'a' - 10);
                            } else if (between('A', 'Z')) {
                                value = nextHex(value, 'A' - 10);
                            } else {
                                throw error("Expected hex digit");
                            }
                        }
                        sb.append((char) value);
                    } else {
                        throw error("Unknown escape character \\" + ch);
                    }
                } else {
                    sb.append(ch);
                    nextChar();
                }
            }
            return sb.toString();
        }

        private int nextHex(int value, final int delta) {
            value += ch - delta;
            nextChar();
            return value;
        }

        private boolean escaped(final StringBuilder sb, final char character, final char expected) {
            final boolean consumed = test(expected);
            if (consumed) {
                sb.append(character);
            }
            return consumed;
        }

        /*
         * number
         *     integer fraction exponent
         */
        private Object parseNumber() {
            final StringBuilder sb = new StringBuilder();
            copyInteger(sb);

            /*
             * fraction
             *     ""
             *     '.' digits
             */
            if (test('.')) {
                sb.append('.');
                copyDigits(sb);
            }

            /*
             * exponent
             *     ""
             *     'E' sign digits
             *     'e' sign digits
             * sign
             *     ""
             *     '+'
             *     '-'
             */
            if (test('e') || test('E')) {
                sb.append('e');
                if (test('+')) {
                    // Do nothing
                } else if (test('-')) {
                    sb.append('-');
                }
                copyDigits(sb);
            }

            try {
                return Double.parseDouble(sb.toString());
            } catch (NumberFormatException e) {
                throw error("Invalid number " + sb);
            }
        }

        /*
         * digits
         *     digit
         *     digit digits
         *
         * digit
         *     '0'
         *     onenine
         */
        private void copyDigits(final StringBuilder sb) {
            while (between('0', '9')) {
                sb.append(ch);
                nextChar();
            }
        }

        /*
         * integer
         *     digit
         *     onenine digits
         *     '-' digit
         *     '-' onenine digits
         *
         * onenine
         *     '1' . '9'
         */
        private void copyInteger(final StringBuilder sb) {
            if (test('-')) {
                sb.append('-');
            }
            if (test('0')) {
                sb.append('0');
            } else if (between('1', '9')) {
                copyDigits(sb);
            } else {
                throw error("Invalid number");
            }
        }

        /*
         * ws
         *    ""
         *    '0020' ws
         *    '000A' ws
         *    '000D' ws
         *    '0009' ws
         */
        private void skipWhitespace() {
            while (test(' ') || test('\r') || test('\n') || test('\t')) {
                // skip
            }
        }
    }
}
