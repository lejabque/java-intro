import java.util.*;
import java.io.*;

public class WordStatInput {
    private static boolean charInWord(Character c) {
        return Character.isLetter(c) || Character.getType(c) == Character.DASH_PUNCTUATION || c == 0x0027;
    }

    public static void main(String[] args) {
        LinkedHashMap<String, Integer> wordCounter = new LinkedHashMap<>();
        try {
            BufferedReader out = new BufferedReader(
                    new InputStreamReader(
                            new FileInputStream(new File(args[0])),
                            "utf8"
                    ),
                    1024
            );
            try {
                while (true) {
                    String s = out.readLine();
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
            } finally {
                out.close();
            }
        } catch (FileNotFoundException e) {
            System.err.println("Input file not found: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("I/O error: " + e.getMessage());
        }
        try {
            BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(
                            new FileOutputStream(args[1]),
                            "utf8"
                    )
            );
            try {
                for (String key : wordCounter.keySet()) {
                    writer.write(key + " " + wordCounter.get(key) + "\n");
                }
            } finally {
                writer.close();
            }
        } catch (FileNotFoundException e) {
            System.err.println("Input file not found: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("I/O error: " + e.getMessage());
        }

    }
}