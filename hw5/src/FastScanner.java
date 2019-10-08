import java.util.*;
import java.io.*;

public class FastScanner {
    private BufferedReader inputReader;
    private boolean isClosed = false;
    private int bufferPtr = 0; // current pointer in cached string
    private int nextBegin = 0; // pointer to begin of next in string
    private int nextEnd = 0; // pointer to end of next in stream
    private String cachedString = null;

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

    private String readNext() throws IOException, IllegalStateException {
        if (isClosed) {
            throw new IllegalStateException("Input is closed");
        }
        if (hasNext()) {
            nextEnd = nextBegin;
            while (nextEnd < cachedString.length() && !Character.isWhitespace(cachedString.charAt(nextEnd))) {
                nextEnd++;
            }
            return cachedString.substring(nextBegin, nextEnd);
        }
        return null;
    }

    public boolean hasNext() throws IOException, IllegalStateException {
        checkNotClosed();
        if (!hasNextLine()) {
            return false;
        }
        nextBegin = bufferPtr;
        while (nextBegin < cachedString.length() && Character.isWhitespace(cachedString.charAt(nextBegin))) {
            nextBegin++;
        }
        if (nextBegin == cachedString.length()) {
            cachedString = null;
            return false;
        }
        return true;
    }

    public boolean hasNextLine() throws IOException, IllegalStateException {
        if (isClosed) {
            throw new IllegalStateException("Input is closed");
        }
        if (cachedString == null || bufferPtr == cachedString.length()) {
            cachedString = inputReader.readLine();
        }
        return cachedString != null;
    }

    public boolean hasNextInt() throws IOException, IllegalStateException {
        String newWord = readNext();
        try {
            Integer.parseInt(newWord);
        } catch (NumberFormatException | NullPointerException e) {
            return false;
        }
        return true;
    }

    public String next() throws NoSuchElementException, IOException, IllegalStateException {
        if (!hasNext()) {
            throw new NoSuchElementException("Input is empty");
        }
        String newWord = readNext();
        bufferPtr = nextEnd;
        return newWord;
    }

    public int nextInt() throws NoSuchElementException, IOException, IllegalStateException {
        if (!hasNextInt()) {
            throw new NoSuchElementException("Input is empty");
        }
        return Integer.parseInt(next());
    }

    public String nextLine() throws NoSuchElementException, IOException, IllegalStateException {
        if (isClosed) {
            throw new IllegalStateException("Input is closed");
        }
        bufferPtr = 0;
        if (cachedString != null && (bufferPtr < cachedString.length() || cachedString.length() == 0)) {
            String newString = cachedString.substring(bufferPtr);
            cachedString = null;
            return newString;
        }
        return inputReader.readLine();
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
