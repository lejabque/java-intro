import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class WordStatWords {
    public static void main(String[] args) {
        Map<String, Integer> wordCounter = new HashMap<>();
        try (FastScanner in = new FastScanner(new File(args[0]), "utf-8")) {
            while (in.hasNextWord()) {
                String s = in.nextWord().toLowerCase();
                wordCounter.merge(s, 1, Integer::sum);
            }
        } catch (UnsupportedEncodingException e) {
            System.err.println("Uncorrect encoding: " + e.getMessage());
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("I/O error: " + e.getMessage());
        }

        try (BufferedWriter out = new BufferedWriter(
                new OutputStreamWriter(new FileOutputStream(args[1]), StandardCharsets.UTF_8))) {
            String[] keys = wordCounter.keySet().toArray(new String[0]);
            Arrays.sort(keys);
            for (String key : keys) {
                out.write(key + " " + wordCounter.get(key) + "\n");
            }
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("I/O error: " + e.getMessage());
        }
    }
}