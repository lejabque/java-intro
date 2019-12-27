import java.util.*;
import java.io.*;

public class WordStatInput {
    private static boolean charInWord(Character c) {
        return Character.isLetter(c) || Character.getType(c) == Character.DASH_PUNCTUATION || c == 0x0027;
    }

    public static void main(String[] args) {
        HashMap<String, Integer> wordCounter = new HashMap<>();
        try {
            try (BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                            new FileInputStream(new File(args[0])),
                            "utf8"
                    ),
                    1024
            )) {
                while (true) {
                    String s = in.readLine();
                    if (s == null) {
                        break;
                    } else {
                        s = s.toLowerCase();
                    }
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