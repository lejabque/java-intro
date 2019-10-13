import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class WordStatWords {

    public static void main(String[] args) {
        Map<String, Integer> wordCounter = new HashMap<>();
//        if (args[0].equals("test9.in")){
//            System.err.println();
//            return;
//        }
        try {
            FastScanner in = new FastScanner(new File(args[0]), "utf-8");
            while (in.hasNext()) {
                if (in.hasNextWord()) {
                    String s = in.nextWord().toLowerCase();
                    wordCounter.merge(s, 1, Integer::sum);
                } else {
                    in.skipNext();
                }
            }
        } catch (UnsupportedEncodingException e) {
            System.err.println("Uncorrect encoding: " + e.getMessage());
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("I/O error: " + e.getMessage());
        }

        try (BufferedWriter out = new BufferedWriter(
                new OutputStreamWriter(
                        new FileOutputStream(args[1]),
                        "utf8"
                )
        )) {
            String[] keys = wordCounter.keySet().toArray(new String[wordCounter.size()]);
            Arrays.sort(keys);
            for (String key : keys) {
                out.write(key + " " + wordCounter.get(key) + "\n");
            }
        } catch (
                FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
        } catch (
                IOException e) {
            System.err.println("I/O error: " + e.getMessage());
        }
    }
}