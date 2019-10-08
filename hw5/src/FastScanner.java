import java.util.*;
import java.io.*;

public class FastScanner {
    private InputStreamReader inputReader;
    private boolean isClosed = false;
    private char[] charBuffer = new char[1024];
    private int bufferPtr = 0; // current pointer in stream
    private int nextBegin = 0; // pointer to begin of next in stream
    private int nextEnd = 0; // pointer to end of next in stream
    private int bufferSize = 0;

    public FastScanner(String s, String charset) throws UnsupportedEncodingException {
        this(new ByteArrayInputStream(s.getBytes()), charset);
    }

    public FastScanner(File file, String charset) throws FileNotFoundException, UnsupportedEncodingException {
        this(new FileInputStream(file), charset);
    }

    public FastScanner(InputStream source, String charset) throws UnsupportedEncodingException {
        inputReader = new InputStreamReader(source, charset);
    }

    private int updateBuffer() throws IOException {
        if (bufferPtr == bufferSize) {
            bufferSize = inputReader.read(charBuffer);
            bufferPtr = 0;
        }
        return bufferSize;
    }

    private String readNext() throws IOException, IllegalStateException {
        if (isClosed) {
            throw new IllegalStateException("Input is closed");
        }
        if (hasNext()) {
            nextEnd = nextBegin;
            while (nextEnd < bufferSize && !Character.isWhitespace(charBuffer[nextEnd]) &&
                    !String.valueOf(charBuffer[bufferPtr]).equals(System.getProperty("line.separator"))) {
                nextEnd++;
                if (nextEnd == charBuffer.length) {
                    charBuffer = Arrays.copyOf(charBuffer, charBuffer.length * 2);
                    bufferSize += inputReader.read(charBuffer, nextEnd, nextEnd);
                }
            }
            return new String(charBuffer, nextBegin, nextEnd - nextBegin);
        }
        return null;
    }

    public boolean hasNext() throws IOException, IllegalStateException {
        checkNotClosed();
        if (updateBuffer() == -1) {
            return false;
        }
        nextBegin = bufferPtr;
        while (nextBegin < bufferSize && Character.isWhitespace(charBuffer[nextBegin])) {
            nextBegin++;
            if (nextBegin == charBuffer.length) {
                charBuffer = Arrays.copyOf(charBuffer, charBuffer.length * 2);
                bufferSize += inputReader.read(charBuffer, nextBegin, nextBegin);
            }
        }
        return nextBegin != bufferSize;
    }

    private void checkNotClosed() {
        if (isClosed) {
            throw new IllegalStateException("Input is closed");
        }
    }

    public boolean hasNextLine() throws IOException, IllegalStateException {
        if (isClosed) {
            throw new IllegalStateException("Input is closed");
        }
        if (updateBuffer() == -1) {
            return false;
        }
        return bufferSize != bufferPtr;
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
        String newWord = readNext();
        bufferPtr = nextEnd;
        return Integer.parseInt(newWord);
    }

    public String nextLine() throws NoSuchElementException, IOException, IllegalStateException {
        if (isClosed) {
            throw new IllegalStateException("Input is closed");
        }
        if (!hasNextLine()) {
            throw new NoSuchElementException("Input is empty");
        }

        if (bufferPtr == bufferSize) {
            bufferSize = inputReader.read(charBuffer);
            bufferPtr = 0;
        }
        int lineIndex = bufferPtr;
        while (bufferPtr < bufferSize && !String.valueOf(charBuffer[bufferPtr]).equals(System.getProperty("line.separator"))) {
            bufferPtr++;
            if (bufferPtr == charBuffer.length) {
                charBuffer = Arrays.copyOf(charBuffer, charBuffer.length * 2);
                bufferSize += inputReader.read(charBuffer, bufferPtr, bufferPtr);
            }
        }
        if (String.valueOf(charBuffer[bufferPtr]).equals(System.getProperty("line.separator"))) {
            bufferPtr++;
        }
        return new String(charBuffer, lineIndex, bufferPtr - lineIndex);
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
