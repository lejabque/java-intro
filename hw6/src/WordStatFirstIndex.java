import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;
import java.util.Map;

public class WordStatFirstIndex {
    private static final FastScanner.Checker wordChecker = c -> (Character.isLetter(c)
            || Character.getType(c) == Character.DASH_PUNCTUATION
            || c == '\'');

    public static void main(String[] args) {
        Map<String, WordStatTuple> wordCounter = new LinkedHashMap<>();
        try (FastScanner in = new FastScanner(new File(args[0]), "utf-8")) {
            int lineNum = 1;
            int i = 1;
            while (in.hasCustomNext(wordChecker)) {
                String word = in.next(wordChecker).toLowerCase();
                wordCounter.computeIfAbsent(word, k -> new WordStatTuple()).add(i, lineNum);
                i++;
                if (in.nothingInLine(wordChecker)) {
                    lineNum += 1;
                    i = 1;
                }
            }
        } catch (UnsupportedEncodingException e) {
            System.err.println("Incorrect encoding: " + e.getMessage());
            return;
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
            return;
        } catch (IOException e) {
            System.err.println("I/O error: " + e.getMessage());
            return;
        }

        try (BufferedWriter out = new BufferedWriter(
                new OutputStreamWriter(new FileOutputStream(args[1]), StandardCharsets.UTF_8))) {
            for (Map.Entry<String, WordStatTuple> entry : wordCounter.entrySet()) {
                out.write(entry.getKey());
                out.write(" ");
                out.write(entry.getValue().tupleToString());
                out.newLine();
            }
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("I/O error: " + e.getMessage());
        }
    }
}