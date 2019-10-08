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

    public FastScanner(String s) {
        this(new ByteArrayInputStream(s.getBytes()));
    }

    public FastScanner(File file) throws FileNotFoundException {
        this(new FileInputStream(file));
    }

    public FastScanner(InputStream source) {
        inputReader = new InputStreamReader(source);
    }

    public boolean hasNext() throws IOException, IllegalStateException {
        if (isClosed) {
            throw new IllegalStateException("Input is closed");
        }
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
        if (nextBegin == bufferSize) {
            return false;
        } else {
            return true;
        }
    }

    public String readNext() throws IOException, IllegalStateException {
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

    private int updateBuffer() throws IOException {
        if (bufferPtr == bufferSize) {
            bufferSize = inputReader.read(charBuffer);
            bufferPtr = 0;
        }
        return bufferSize;
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

    public boolean hasNextInt() throws IOException, IllegalStateException {
        String newWord = readNext();
        if (newWord == null) {
            return false;
        }
        for (int i = 0; i < newWord.length(); ++i) {
            if (!Character.isDigit(newWord.charAt(i)) && newWord.charAt(i) != '-') {
                return false;
            }
        }
        return true;
    }

    public String next() throws NoSuchElementException, IOException, IllegalStateException {
        String newWord = readNext();
        bufferPtr = nextEnd;
        if (newWord == null) {
            throw new NoSuchElementException("Input is empty");
        }
        return newWord;
    }

    public int nextInt() throws NoSuchElementException, IOException, IllegalStateException {
        String newWord = readNext();
        bufferPtr = nextEnd;
        if (newWord == null) {
            throw new NoSuchElementException("Input is empty");
        }
        return Integer.parseInt(newWord);
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
