import java.util.*;
import java.io.*;

public class FastScanner implements AutoCloseable {
    private InputStreamReader inputReader;
    private boolean isClosed = false;
    private char[] buffer = new char[1];
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

    boolean nothingInLine(Checker charChecker) throws IllegalStateException, IOException {
        checkNotClosed();
        while (bufferPtr < bufferSize
                && !charChecker.check(buffer[bufferPtr])
                && buffer[bufferPtr] != '\n' && buffer[bufferPtr] != '\r') {
            bufferPtr++;
            if (bufferPtr == bufferSize) {
                updateBuffer();
            }
        }
        return (buffer[bufferPtr] == '\n' || buffer[bufferPtr] == '\r');
    }

    void toNextLine() throws IllegalStateException, IOException {
        checkNotClosed();
        if (lastInLine()) {
            bufferPtr += buffer[bufferPtr] == '\r' ? 2 : 1;
            if (bufferPtr == buffer.length) {
                updateBuffer();
            } else if (bufferPtr == buffer.length + 1) {
                updateBuffer();
                bufferPtr += 1;
            }
        }
    }

    // custom checkers
    public interface Checker {
        boolean check(char c);
    }

    // hasNext methods
    public boolean hasCustomNext(Checker charChecker) throws IOException, IllegalStateException {
        return beginCustomNext(charChecker) < bufferSize;
    }

    // custom next with parse to int
    int nextInt(Checker checker) throws NoSuchElementException, IOException, IllegalStateException {
        if (!hasCustomNext(checker)) {
            throw new NoSuchElementException("Input is empty");
        }
        return Integer.parseInt(next(checker));
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
            if (nextPtr == buffer.length) {
                updateBuffer();
                nextPtr = bufferPtr;
            }
        }
        return nextPtr;
    }

    // common next method
    public String next(Checker charChecker) throws NoSuchElementException, IOException, IllegalStateException {
        int beginNext = beginCustomNext(charChecker);
        if (beginNext >= bufferSize) {
            throw new NoSuchElementException("Input is empty");
        }
        StringBuilder nextRes = new StringBuilder();
        bufferPtr = beginNext;
        while (bufferPtr < bufferSize && charChecker.check(buffer[bufferPtr])) { //add checker
            bufferPtr++;
            if (bufferPtr == buffer.length) {
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
