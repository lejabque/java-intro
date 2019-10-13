import java.util.*;
import java.io.*;

public class FastScanner implements AutoCloseable {
    private InputStreamReader inputReader;
    private boolean isClosed = false;
    private String cachedNext = null;
    private char cachedChar = Character.MIN_VALUE;
    private char lastChar = Character.MIN_VALUE;
    private char[] buffer = new char[1024];
    private int bufferPtr = 0;
    private int bufferSize = 0;

    public FastScanner(File file, String charset) throws FileNotFoundException, UnsupportedEncodingException {
        this(new FileInputStream(file), charset);
    }

    private void checkNotClosed() {
        if (isClosed) {
            throw new IllegalStateException("Input is closed");
        }
    }

    public FastScanner(InputStream source, String charset) throws UnsupportedEncodingException {
        inputReader = new InputStreamReader(source, charset);
    }

    private static boolean charInWord(Character c) {
        return Character.isLetter(c) || Character.getType(c) == Character.DASH_PUNCTUATION || c == '\'';
    }

//    public boolean hasNextWord() throws IOException, IllegalStateException {
//        checkNotClosed();
//        if (!hasNext()) {
//            return false;
//        }
//        boolean res = false;
//        int index = 0;
//        while (!res && index < cachedNext.length()) {
//            res = res || charInWord(cachedNext.charAt(index));
//            index++;
//        }
//        return res;
//    }
//
//    public String nextWord() throws NoSuchElementException, IOException, IllegalStateException {
//        if (!hasNextWord()) {
//            throw new NoSuchElementException("Input is empty");
//        }
//        int index = 0;
//        while (index < cachedNext.length()) {
//            if (charInWord(cachedNext.charAt(index))) {
//                int beginIndex = index;
//                while (index < cachedNext.length() && charInWord(cachedNext.charAt(index))) {
//                    index++;
//                }
//                String newNext = cachedNext.substring(beginIndex, index);
//                cachedNext = cachedNext.substring(index);
//                return newNext;
//            } else {
//                index++;
//            }
//        }
//        return null;
//    }

    public boolean updateBuffer() throws IOException, IllegalStateException {
        bufferSize = inputReader.read(buffer);
        bufferPtr = 0;
        return bufferSize > 0;
    }

    private interface Checker {
        boolean check(char c) throws IOException;
    }

    public boolean hasNextInt() throws IOException, IllegalStateException {
        return hasCustomNext(this::intChecker);
    }


    public boolean inputNotEmpty() throws IOException, IllegalStateException {
        if (bufferPtr == bufferSize) {
            updateBuffer();
        }
        return bufferPtr < bufferSize;
    }

    public boolean hasCustomNext(Checker charChecker) throws IOException, IllegalStateException {
        return beginCustomNext(charChecker) < bufferSize;
    }

    public int beginCustomNext(Checker charChecker) throws IOException, IllegalStateException {
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


    public boolean lastInLine() throws IllegalStateException {
        checkNotClosed();
        return (buffer[bufferPtr] == '\n' || buffer[bufferPtr] == '\r');
    }

    public void toNextLine() throws IllegalStateException, IOException {
        checkNotClosed();
        if (lastInLine()) {
            bufferPtr++;
            if (bufferPtr == 1024) {
                updateBuffer();
            }
        }
    }

    public String next(Checker charChecker) throws NoSuchElementException, IOException, IllegalStateException {
        if (!hasCustomNext(charChecker)) {
            throw new NoSuchElementException("Input is empty");
        }
        StringBuilder nextRes = new StringBuilder();
        bufferPtr = beginCustomNext(charChecker);
        int beginNext = bufferPtr;
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

    public boolean intChecker(char c) throws IOException, IllegalStateException {
        return (Character.isDigit(c) || c == '-');
    }

    public boolean wordChecker(char c) throws IOException, IllegalStateException {
        return Character.isLetter(c) || Character.getType(c) == Character.DASH_PUNCTUATION || c == '\'';
    }

    public String nextWord() throws NoSuchElementException, IOException, IllegalStateException {
        if (!hasCustomNext(this::wordChecker)) {
            throw new NoSuchElementException("Input is empty");
        }
        return next(this::wordChecker);
    }

    public boolean hasNextWord() throws IOException, IllegalStateException {
        return hasCustomNext(this::wordChecker);
    }

    public int nextInt() throws NoSuchElementException, IOException, IllegalStateException {
        if (!hasCustomNext(this::intChecker)) {
            throw new NoSuchElementException("Input is empty");
        }
        return Integer.parseInt(next(this::intChecker));
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
