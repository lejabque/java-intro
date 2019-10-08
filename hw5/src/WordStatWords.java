import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class WordStatWords {
    private static boolean charInWord(Character c) {
        return Character.isLetter(c) || Character.getType(c) == Character.DASH_PUNCTUATION || c == '\'';
    }

    public static void main(String[] args) {
        Map<String, Integer> wordCounter = new HashMap<>();
        try {
            FastScanner in = new FastScanner(new File(args[0]));
            while (in.hasNext()) {
                String s = in.next().toLowerCase();
                int index = 0;
                while (index < s.length()) {
                    if (charInWord(s.charAt(index))) {
                        int beginIndex = index;
                        while (index < s.length() && charInWord(s.charAt(index))) {
                            index++;
                        }
                        wordCounter.merge(s.substring(beginIndex, index), 1, Integer::sum);
                    } else {
                        index++;
                    }

                }
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
            }
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("I/O error: " + e.getMessage());
        }
    }
}