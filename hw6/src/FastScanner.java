import java.io.*;
import java.util.NoSuchElementException;

public class FastScanner implements AutoCloseable {
    private InputStreamReader inputReader;
    private boolean isClosed = false;
    private char[] buffer = new char[1024];
    private int bufferPtr = 0;
    private int bufferSize = 0;

    // constructors
    FastScanner(File file, String charset) throws FileNotFoundException, UnsupportedEncodingException {
        this(new FileInputStream(file), charset);
    }

    FastScanner(InputStream source, String charset) throws UnsupportedEncodingException {
        inputReader = new InputStreamReader(source, charset);
    }

    // utils
    private void checkNotClosed() {
        if (isClosed) {
            throw new IllegalStateException("Input is closed");
        }
    }

    private void updateBuffer() throws IOException, IllegalStateException {
        bufferSize = inputReader.read(buffer);
        bufferPtr = 0;
    }

    boolean inputNotEmpty() throws IOException, IllegalStateException {
        if (bufferPtr == bufferSize) {
            updateBuffer();
        }
        return bufferPtr < bufferSize;
    }

    boolean lastInLine() throws IllegalStateException {
        checkNotClosed();
        return (buffer[bufferPtr] == '\n' || buffer[bufferPtr] == '\r');
    }

    void toNextLine() throws IllegalStateException, IOException {
        checkNotClosed();
        if (lastInLine()) {
            bufferPtr++;
            if (bufferPtr == 1024) {
                updateBuffer();
            }
        }
    }

    // custom checkers
    private interface Checker {
        boolean check(char c) throws IOException;
    }

    private boolean intChecker(char c) {
        return (Character.isDigit(c) || c == '-');
    }

    private boolean wordChecker(char c) {
        return Character.isLetter(c) || Character.getType(c) == Character.DASH_PUNCTUATION || c == '\'';
    }

    // hasNext methods
    private boolean hasCustomNext(Checker charChecker) throws IOException, IllegalStateException {
        return beginCustomNext(charChecker) < bufferSize;
    }

    boolean hasNextInt() throws IOException, IllegalStateException {
        return hasCustomNext(this::intChecker);
    }

    boolean hasNextWord() throws IOException, IllegalStateException {
        return hasCustomNext(this::wordChecker);
    }

    // custom next methods
    String nextWord() throws NoSuchElementException, IOException, IllegalStateException {
        if (!hasCustomNext(this::wordChecker)) {
            throw new NoSuchElementException("Input is empty");
        }
        return next(this::wordChecker);
    }

    int nextInt() throws NoSuchElementException, IOException, IllegalStateException {
        if (!hasCustomNext(this::intChecker)) {
            throw new NoSuchElementException("Input is empty");
        }
        return Integer.parseInt(next(this::intChecker));
    }

    // find beginIndex of custom next token
    private int beginCustomNext(Checker charChecker) throws IOException, IllegalStateException {
        checkNotClosed();
        if (bufferSize == 0) {
            updateBuffer();
        }
        int nextPtr = bufferPtr;
        while (nextPtr < bufferSize && !charChecker.check(buffer[nextPtr])) {// add checker
            nextPtr++;
            if (nextPtr == 1024) {
                updateBuffer();
                nextPtr = bufferPtr;
            }
        }
        return nextPtr;
    }

    // common next method
    private String next(Checker charChecker) throws NoSuchElementException, IOException, IllegalStateException {
        int beginNext = beginCustomNext(charChecker);
        if (beginNext >= bufferSize) {
            throw new NoSuchElementException("Input is empty");
        }
        StringBuilder nextRes = new StringBuilder();
        bufferPtr = beginNext;
        while (bufferPtr < bufferSize && charChecker.check(buffer[bufferPtr])) { //add checker
            bufferPtr++;
            if (bufferPtr == 1024) {
                nextRes.append(buffer, beginNext, bufferPtr - beginNext);
                updateBuffer();
                beginNext = bufferPtr;
            }
        }
        nextRes.append(buffer, beginNext, bufferPtr - beginNext);
        return nextRes.toString();
    }

    public void close() throws IllegalStateException, IOException {
        if (!isClosed) {
            isClosed = true;
            inputReader.close();
        } else {
            throw new IllegalStateException("Scanner was closed before");
        }
    }
}
