package expression.parser;

import expression.exceptions.ParsingException;

public class BaseParser {
    private ExpressionSource source;

    protected char ch;

    protected BaseParser(final ExpressionSource source) {
        this.source = source;
        nextChar();
    }

    public BaseParser() {

    }

    protected void changeSource(final ExpressionSource source) {
        this.source = source;
    }

    protected void nextChar() {
        ch = source.hasNext() ? source.next() : '\0';
    }

    protected boolean hasNext() {
        return source.hasNext();
    }

    protected boolean test(char expected) {
        if (ch == expected) {
            nextChar();
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
            ;
        }
        return true;
    }

    protected int getPosFromSource() {
        return source.getPos();
    }

    protected String getParsingInfo(){
        return source.getErrorMessage();
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

    protected void copyInteger(final StringBuilder sb) throws ParsingException {
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

    protected void skipWhitespace() {
        while (test(' ') || test('\r') || test('\n') || test('\t')) {
            // skip
        }
    }
}
