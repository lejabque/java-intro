//import java.util.*;
//import java.io.*;
//
//public class Scanner {
//    private InputStream inputSource;
//    private InputStreamReader inputReader;
//    private boolean isClosed = false;
//    private char[] charBuffer = new char[1024];
//    private String cachedWord = null;
//    private int bufferPtr = 0;
//    private int bufferSize = 0;
//
//    public static void main(String[] args) {
//        try {
//            Scanner myScanner = new Scanner(new File("/home/lejabque/study/ct_y2019/term1/hw/prog_intro/hw5/src/input.txt"));
//            try {
//                System.out.println(myScanner.next());
//                System.out.println(myScanner.next());
//                System.out.println(myScanner.next());
//            } finally {
//                myScanner.close();
//            }
//        } catch (IOException e) {
//            System.err.println("I/O error: " + e.getMessage());
//        }
//
//    }
//
//    public Scanner(String s) {
//        this(new ByteArrayInputStream(s.getBytes()));
//    }
//
//    public Scanner(File file) throws IOException {
//        this(new FileInputStream(file));
//    }
//
//    private Scanner(InputStream source) {
//        inputSource = source;
//        inputReader = new InputStreamReader(inputSource);
//    }
//
//    private boolean hasNext() throws IOException {
//        if (cachedWord != null) {
//            return true;
//        }
//        if (bufferPtr == bufferSize) {
//            bufferSize = inputReader.read(charBuffer);
//            bufferPtr = 0;
//        }
//        while (bufferPtr < bufferSize && Character.isWhitespace(charBuffer[bufferPtr])) {
//            bufferPtr++;
//            if (bufferPtr == charBuffer.length) {
//                bufferSize = inputReader.read(charBuffer);
//                bufferPtr = 0;
//            }
//        }
//        if (bufferPtr == bufferSize) {
//            return false;
//        } else {
//            return true;
//        }
//    }
//
//    public void cacheNext() throws NoSuchElementException, IOException, IllegalStateException {
//        if (!hasNext()) {
//            throw new NoSuchElementException("Input is empty");
//        }
//        int beginIndex = bufferPtr;
//        while (bufferPtr < bufferSize && !Character.isWhitespace(charBuffer[bufferPtr])) {
//            bufferPtr++;
//            if (bufferPtr == charBuffer.length) {
//                charBuffer = Arrays.copyOf(charBuffer, charBuffer.length * 2);
//                bufferSize = inputReader.read(charBuffer, bufferPtr, bufferPtr);
//            }
//        }
//        cachedWord = new String(charBuffer, beginIndex, bufferPtr - beginIndex);
//    }
//
//    public String next() throws NoSuchElementException, IOException, IllegalStateException {
//        if (cachedWord == null) {
//            cacheNext();
//        }
//        String nextString = cachedWord;
//        cachedWord = null;
//        return nextString;
//    }
//
//    public boolean hasNextInt() throws IOException, IllegalStateException {
//        if (cachedWord == null) {
//            cacheNext();
//        }
//        for (int i = 0; i < cachedWord.length(); ++i) {
//            if (!Character.isDigit(cachedWord.charAt(i))) {
//                return false;
//            }
//        }
//        return true;
//    }
//
//    public int nextInt() throws NoSuchElementException, IllegalStateException {
//        String resInt = Integer.parseInt(next());
//        return 0;
//    }
//
//    public void close() throws IllegalStateException, IOException {
//        if (!this.isClosed) {
//            this.isClosed = true;
//            inputSource.close();
//        } else {
//            throw new IllegalStateException("Scanner was closed before");
//        }
//    }
//
//}
