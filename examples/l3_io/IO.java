import java.util.*;
import java.io.*;

public class IO {
    public static void main(String[] args) throws IOException {
        exceptions();
        resources();
        encodings();
        readers();
        writers();
    }

    private static void exceptions() {
        chapter("Exceptions");

        uncheckedExceptions();
        checkedExceptions();
        multipleCatches();
        exceptionHandling();
    }

    private static void uncheckedExceptions() {
        section("Unchecked exceptions");
        try {
            uncheckedExceptionsNeedNotToBeDeclared();
        } catch (NullPointerException e) {
            System.out.println("But still need to be caught");
        }
    }

    private static void uncheckedExceptionsNeedNotToBeDeclared() {
        System.out.println("Unchecked exceptions need not to be declared");
        String nullString = null;
        System.out.println(nullString.length());
    }

    private static void checkedExceptions() {
        section("Checked exceptions");

        checkedExceptionsShouldBeCaught();

        try {
            checkedExceptionsShouldBeDeclaredIfNotCaught();
        } catch (FileNotFoundException e) {
            System.out.println("And still need to be caught somewhere");
        }

        System.out.println("Compiler checks these rules");
        System.out.println("Thus 'checked' exceptions");
    }

    private static void checkedExceptionsShouldBeCaught() {
        System.out.println("Checked exceptions should by caught");
        try {
            new Scanner(new File("no-such-file.txt"));
        } catch (FileNotFoundException e) {
            System.out.println("    Error: Required file not found");
        }
    }

    private static void checkedExceptionsShouldBeDeclaredIfNotCaught() throws FileNotFoundException {
        System.out.println("Or declared to be thrown");
        System.out.println("Notice the 'throws' clause in declaration");
        new Scanner(new File("no-such-file.txt"));
    }

    private static void multipleCatches() {
        section("Multiple catches");

        System.out.println("You may specify multiple catch blocks");
        System.out.println("Each block corresponds to specific exception");
        System.out.println("At most one catch block is executed");

        try {
            Scanner in = scanFile("input.txt");
            while (in.hasNext()) {
                System.out.println(in.nextInt());
            }
            System.out.println("    File exists and contains only integers");
        } catch (FileNotFoundException e) {
            System.out.println("    File does not exist");
        } catch (InputMismatchException e) {
            System.out.println("    File contains non-integers");
        }
    }

    private static Scanner scanFile(final String name) throws FileNotFoundException {
        return new Scanner(new File(name));
    }

    private static void exceptionHandling() {
        section("Exception handling");
        try {
            Scanner in = scanFile("input.txt");
            while (in.hasNext()) {
                System.out.println(in.nextInt());
            }
        } catch (FileNotFoundException e) {
            System.out.println("    Each exception contains message");
            System.out.println("    Error: File not found: " + e.getMessage());
        } catch (InputMismatchException e) {
            System.out.println("    Each exception contains stack trace");
            e.printStackTrace(System.out);
        }
    }

    private static void resources() {
        chapter("Resources");
        System.out.println("multiCatches does not close scanner, let's fix it");
        closeInTry();
        closeInTryAndEveryCatch();
        closeInFinally();
        resourceUsageBlock();
        multipleResources();
    }

    private static void closeInTry() {
        section("Close resource in try block");
        try {
            Scanner in = scanFile("input.txt");
            while (in.hasNext()) {
                System.out.println(in.nextInt());
            }
            in.close();
            System.out.println("File exists and contains only integers");
            System.out.println("Success! Scanners is closed");
        } catch (FileNotFoundException e) {
            System.out.println("File does not exist");
            System.out.println("Success! scanner is not even created");
        } catch (InputMismatchException e) {
            System.out.println("File contains non-integers");
            System.out.println("FAIL! Scanner is still open");
        }
    }

    private static void closeInTryAndEveryCatch() {
        section("Close resource in try and every catch block");

        Scanner in = null;
        try {
            in = new Scanner(new File("input.txt"));
            while (in.hasNext()) {
                System.out.println(in.nextInt());
            }
            in.close();
            System.out.println("File exists and contains only integers");
            System.out.println("Success! Scanners is closed");
        } catch (FileNotFoundException e) {
            System.out.println("File does not exist");
            System.out.println("Success! Scanner not even created");
        } catch (InputMismatchException e) {
            System.out.println("File contains non-integers");
            // We sure, that in is not null, don't we?
            // Let's double check
            if (in != null) {
                in.close();
            }
            System.out.println("Success! Scanner is closed");
        }
    }

    private static void closeInFinally() {
        section("Close resource in finally block");
        try {
            Scanner in = scanFile("input.txt");
            try {
                while (in.hasNext()) {
                    System.out.println(in.nextInt());
                }
                System.out.println("File exists and contains only integers");
            } catch (InputMismatchException e) {
                System.out.println("File contains non-integers");
            } finally {
                in.close();
                System.out.println("Finally block is executed under any circumstances");
                System.out.println("Success! Scanner is closed");
            }
        } catch (FileNotFoundException e) {
            System.out.println("File does not exist");
        }
    }

    private static void resourceUsageBlock() {
        section("Recommended resource usage building block");
        System.out.println("Open or allocate resource");
        Resource resource = new Resource("res");
        System.out.println("Open try block immediately after resource allocation");
        try {
            System.out.println("Use resource in the try block");
            resource.use();
        } finally {
            System.out.println("Close resource in the finally block");
            resource.close();
        }
    }

    private static void multipleResources() {
        section("Multiple resources usage");
        System.out.println("Recommended resource usage building block for first resource");
        Resource resource1 = new Resource("first");
        try {
            System.out.println("Nested Recommended resource usage building block for second resource");
            Resource resource2 = new Resource("second");
            try {
                System.out.println("Using both resources simultaneously");
                resource1.use();
                resource2.use();
            } finally {
                resource2.close();
            }
        } finally {
            resource1.close();
        }
    }

    private static void encodings() {
        chapter("Encodings");
        guessEncoding();
        alwaysSpecifyEncoding();
    }

    private static void guessEncoding() {
        section("Default encoding");
        System.out.println("File is a sequence of bytes");
        try {
            Scanner in = scanFile("input.txt");
            System.out.println("Scanner reads a sequence of characters");
            System.out.println("Something called 'default encoding' is used");
            System.out.println("And you may not sure which one");
            System.out.println("    " + in.next());
        } catch (FileNotFoundException ignored) {
            exceptionsDisclaimer();
        }
    }

    private static void exceptionsDisclaimer() {
        System.out.println("You should never ignore exceptions");
        System.out.println("This code just a brief example");
    }

    private static void alwaysSpecifyEncoding() {
        section("Always specify encoding explicitly");
        try {
            System.out.println("Files is a sequence of bytes in UTF-8 encoding");
            Scanner in = new Scanner(new File("input.txt"), "utf8");
            System.out.println("Scanner reads a sequence of characters, converted from bytes in UTF-8 encoding");
            System.out.println("     " + in.next());
        } catch (FileNotFoundException ignored) {
            exceptionsDisclaimer();
        }
    }

    private static void readers() {
        section("Readers");
        System.out.println("Scanner is slow. Really slow");
        try {
            introduceReader();
            blockReading();
            bufferedReader();
            readerEncoding();
        } catch (IOException ignored) {
            exceptionsDisclaimer();
        }
    }

    private static void introduceReader() throws IOException {
        section("Reader");
        System.out.println("Lets dump file char-by-char");
        System.out.println("Open reader");
        FileReader reader = new FileReader("input.txt");
        try {
            System.out.println("Dump file");
            while (true) {
                int ch = reader.read();
                if (ch == -1) {
                    System.out.println();
                    System.out.println("read() returned -1 that means EOF");
                    return;
                }
                System.out.print((char) ch);
            }
        } finally {
            System.out.println("Close reader");
            reader.close();
        }
    }

    private static void blockReading() throws IOException {
        section("Block reading");
        System.out.println("Char-by-char IO is slow");
        FileReader reader = new FileReader("input.txt");
        try {
            System.out.println("Let's use blocks of 256 bytes");
            char[] block = new char[256];
            while (true) {
                int read = reader.read(block);
                if (read == -1) {
                    System.out.println();
                    System.out.println("read() returned -1 that means EOF");
                    return;
                }
                System.out.println("Read returns the number of actually read chars: " + read);
                System.out.println("Dump only read chars: ");
                System.out.println("    " + new String(block, 0, read));
            }
        } finally {
            System.out.println("Close reader");
            reader.close();
        }
    }

    private static void bufferedReader() throws IOException {
        section("Buffered reader");
        System.out.println("Let's (potentially) reduce the number of syscalls");
        System.out.println("Wrap FileReader in BufferedReader");
        BufferedReader reader = new BufferedReader(new FileReader("input.txt"), 1000_000);
        try {
            System.out.println("BufferedReader additionally provided line reading capability");
            readerAndWriter(reader);
        } finally {
            System.out.println("Close BufferedReader");
            System.out.println("Nested FileReader is closed by BufferedReader");
            reader.close();
        }
    }

    private static void readerAndWriter(final BufferedReader reader) throws IOException {
        while (true) {
            String line = reader.readLine();
            if (line == null) {
                System.out.println();
                System.out.println("readLine() returned null that means EOF");
                return;
            }
            System.out.println("Line: " + line);
        }
    }

    private static void readerEncoding() throws IOException {
        section("What about encoding");
        System.out.println("Russian dolls to the rescue:");
        System.out.println("Use FileInputStream to open file as a sequence of bytes");
        System.out.println("Wrap it by InputStreamReader to specify encoding");
        System.out.println("Wrap it again in BufferedReader to get line reading capabilities");
        BufferedReader reader = new BufferedReader(
            new InputStreamReader(
                new FileInputStream("input.txt"),
                "utf8"
            )
        );
        try {
            readerAndWriter(reader);
        } finally {
            System.out.println("Close BufferedReader");
            System.out.println("And all nested readers and streams");
            reader.close();
        }
    }

    private static void writers() {
        chapter("Writers");
        try {
            fileWriter();
            readerAndWriter();
            printWriter();
        } catch (IOException ignored) {
            exceptionsDisclaimer();
        }
    }

    private static void fileWriter() throws IOException {
        section("Writers like Readers");
        System.out.println("with some substitutions:");
        System.out.println("    Reader -> Writer");
        System.out.println("    Input -> Output");
        System.out.println("    Read -> Write");
        BufferedWriter writer = new BufferedWriter(
            new OutputStreamWriter(
                new FileOutputStream("output.txt"),
                "utf8"
            )
        );
        try {
            writer.write("Hello, world!");
        } finally {
            writer.close();
        }
    }

    public static void readerAndWriter() throws IOException {
        section("You may use readers and writers simultaneously via nested resource blocks");
        BufferedReader reader = new BufferedReader(
            new InputStreamReader(new FileInputStream("input.txt"), "utf8")
        );
        try {
            BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(new FileOutputStream("output.txt"), "utf8")
            );
            try {
                while (true) {
                    String line = reader.readLine();
                    if (line == null) {
                        return;
                    }
                    writer.write(line + "\n");
                }
            } finally {
                writer.close();
            }
        } finally {
            reader.close();
        }
    }

    private static void printWriter() throws IOException {
        section("PrintWriter");
        System.out.println("Writes primitive types");
        Scanner in = new Scanner(new File("input.txt"), "utf8");
        try {
            PrintWriter writer = new PrintWriter(
                new OutputStreamWriter(new FileOutputStream("output.txt"), "utf8")
            );
            try {
                while (in.hasNextInt()) {;
                    writer.println(in.nextInt());
                }
            } finally {
                writer.close();
            }
        } finally {
            in.close();
        }
    }

    public static void section(String name) {
        System.out.println();
        System.out.println("--- " + name);
    }

    public static void chapter(String name) {
        String delimiter = name.replaceAll(".", "=");
        System.out.println();
        System.out.println(delimiter);
        System.out.println(name);
        System.out.println(delimiter);
    }

    public static class Resource {
        private final String name;

        public Resource(final String name) {
            this.name = name;
            System.out.println("    Resource '" + name + "' is created");
        }

        public void use() {
            System.out.println("    Resource  '" + name + "' is used");
        }

        public void close() {
            System.out.println("    Resource  '" + name + "' is closed");
        }
    }
}
