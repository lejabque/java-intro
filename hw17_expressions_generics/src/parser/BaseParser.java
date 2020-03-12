package parser;

import exceptions.ParsingException;

public class BaseParser {
    private ExpressionSource source;

    protected char ch;
    private char[] buffer;
    private int head = 0;
    private int size = 0;
    private int pos = 0;

    protected BaseParser(final ExpressionSource source, final int bufferLength) {
        this.source = source;
        buffer = new char[bufferLength];
        updateBuffer();
    }

    protected BaseParser(final ExpressionSource source) {
        this(source, 1);
    }

    protected BaseParser(final int bufferLength) {
        buffer = new char[bufferLength];
    }

    private void flushBuffer() {
        size = 0;
        head = 0;
    }

    private void updateBuffer() {
        while (source.hasNext() && size < buffer.length) {
            buffer[(head + size) % buffer.length] = source.next();
            size++;
        }
        ch = size > 0 ? buffer[head % buffer.length] : '\0';
    }

    protected void changeSource(final ExpressionSource source) {
        this.source = source;
        flushBuffer();
        updateBuffer();
    }

    protected void nextChar() {
        ch = size > 0 ? buffer[(head + 1) % buffer.length] : '\0';
        if (size > 0) {
            pos++;
            size--;
            head = (head + 1) % buffer.length;
            updateBuffer();
        }
    }

    protected boolean hasNext() {
        return size > 0;
    }

    protected boolean test(char expected) {
        if (ch == expected) {
            nextChar();
            return true;
        }
        return false;
    }

    protected String parseToken() {
        StringBuilder parsed = new StringBuilder();
        while (between('0', '9') || between('A', 'z')) {
            parsed.append(ch);
            nextChar();
        }
        return parsed.toString();
    }

    protected boolean test(String expected) {
        updateBuffer();
        if (size >= expected.length()) {
            int ind = 0;
            while (ind < expected.length()) {
                if (expected.charAt(ind) != buffer[(head + ind) % buffer.length]) {
                    return false;
                }
                ind++;
            }
            head = (head + ind) % buffer.length;
            size -= ind;
            updateBuffer();
            return true;
        }
        return false;
    }

    protected boolean expect(final char c) {
        if (ch != c) {
            return false;
        }
        nextChar();
        return true;
    }

    protected boolean expect(final String value) {
        for (char c : value.toCharArray()) {
            if (!expect(c)) {
                return false;
            }
        }
        return true;
    }

    protected String getParsingInfo() {
        return "Current pos: " + pos + " Current part: " + source.getPart();
    }

    protected ParsingException error(final String message) {
        return source.error(message);
    }

    protected boolean between(final char from, final char to) {
        return from <= ch && ch <= to;
    }

    protected void copyDigits(final StringBuilder sb) {
        while (between('0', '9')) {
            sb.append(ch);
            nextChar();
        }
    }

    protected void copyNumber(final StringBuilder sb) throws ParsingException {
        if (test('-')) {
            sb.append('-');
        }
        boolean point = false;
        while (between('0', '9') || (ch == '.' && !point)) {
            sb.append(ch);
            if (ch == '.') {
                point = true;
            }
            nextChar();
        }
    }

    protected void skipWhitespace() {
        while (test(' ') || test('\r') || test('\n') || test('\t')) {
            // skip
        }
    }
}
