import java.util.*;
import java.io.*;

public class FastScanner {
    private BufferedReader inputReader;
    private boolean isClosed = false;
    private String cachedNext = null;
    private char lastChar = Character.MIN_VALUE;

    public FastScanner(String s) throws UnsupportedEncodingException {
        inputReader = new BufferedReader(new StringReader(s));
    }

    public FastScanner(File file, String charset) throws FileNotFoundException, UnsupportedEncodingException {
        this(new FileInputStream(file), charset);
    }

    private void checkNotClosed() {
        if (isClosed) {
            throw new IllegalStateException("Input is closed");
        }
    }

    public FastScanner(InputStream source, String charset) throws UnsupportedEncodingException {
        inputReader = new BufferedReader(new InputStreamReader(source, charset));
    }

    private static boolean charInWord(Character c) {
        return Character.isLetter(c) || Character.getType(c) == Character.DASH_PUNCTUATION || c == '\'';
    }

    public boolean hasNextWord() throws IOException, IllegalStateException {
        checkNotClosed();
        if (!hasNext()) {
            return false;
        }
        boolean res = false;
        int index = 0;
        while (!res && index < cachedNext.length()) {
            res = res || charInWord(cachedNext.charAt(index));
            index++;
        }
        return res;
    }

    public void skipNext() throws IOException, IllegalStateException {
        cachedNext = null;
    }

    public String nextWord() throws NoSuchElementException, IOException, IllegalStateException {
        if (!hasNextWord()) {
            throw new NoSuchElementException("Input is empty");
        }
        int index = 0;
        while (index < cachedNext.length()) {
            if (charInWord(cachedNext.charAt(index))) {
                int beginIndex = index;
                while (index < cachedNext.length() && charInWord(cachedNext.charAt(index))) {
                    index++;
                }
                String newNext = cachedNext.substring(beginIndex, index);
                cachedNext = cachedNext.substring(index);
                return newNext;
            } else {
                index++;
            }
        }
        return null;
    }

    public void cacheNext() throws IOException, IllegalStateException {
        checkNotClosed();

        int charInt = -1;
        lastChar = ' '; // skip all spaces
        while (Character.isWhitespace(lastChar) && lastChar != '\n') {
            charInt = inputReader.read();
            lastChar = charInt != -1 ? (char) charInt : Character.MIN_VALUE;
        }
        // return empty string
        if (lastChar == '\n') {
            cachedNext = "";
            return;
        }
        StringBuilder newNext = new StringBuilder();
        while (charInt != -1 && !Character.isWhitespace(lastChar) && lastChar != '\n' && lastChar != '\r') {
            newNext.append(lastChar);
            charInt = inputReader.read();
            if (charInt != -1) {
                lastChar = (char) charInt;
            }
        }
        if (newNext.length() != 0) {
            cachedNext = newNext.toString();
        } else {
            cachedNext = null;
        }
    }

    public boolean hasNext() throws IOException, IllegalStateException {
        checkNotClosed();
        if (cachedNext == null) {
            cacheNext();
        }
        return cachedNext != null;
    }

    public boolean lastInLine() throws IllegalStateException {
        checkNotClosed();
        return (lastChar == '\n' || lastChar == '\r');
    }

    public String next() throws NoSuchElementException, IOException, IllegalStateException {
        if (!hasNext()) {
            throw new NoSuchElementException("Input is empty");
        }
        String newNext = cachedNext;
        cachedNext = null;
        return newNext;
    }

    public boolean hasNextInt() throws IOException, IllegalStateException {
        if (!hasNext()) {
            return false;
        }
        try {
            Integer.parseInt(cachedNext);
        } catch (NumberFormatException | NullPointerException e) {
            return false;
        }
        return true;
    }

    public int nextInt() throws NoSuchElementException, IOException, IllegalStateException {
        if (!hasNextInt()) {
            throw new NoSuchElementException("Input is empty");
        }
        return Integer.parseInt(next());
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
