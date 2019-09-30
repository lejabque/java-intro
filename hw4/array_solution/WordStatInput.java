import java.util.*;
import java.io.*;

public class WordStatInput {
    public static final <T> void swap(T[] a, int i, int j) {
        T t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    private static boolean charInWord(Character c) {
        return Character.isLetter(c) || Character.getType(c) == Character.DASH_PUNCTUATION || c == 0x0027;
    }

    private static int incDicts(String[] words, int[] counters, int arraySize, String newWord) {
        for (int i = 0; i < arraySize; i++) {
            if (words[i].compareTo(newWord) == 0) {
                counters[i]++;
                return arraySize;
            }
        }
        words[arraySize] = newWord;
        counters[arraySize] = 1;
        return arraySize + 1;
    }

    private static void sortByKey(String[] words, int[] counters, int arraySize) {
        for (int i = 0; i < arraySize - 1; i++) {
            int min_index = i;
            for (int j = i + 1; i < arraySize; i++) {
                if (words[j].compareTo(words[min_index]) < 0) {
                    min_index = j;
                }
            }
//            String t = words[i];
//            words[i] = words[min_index];
//            words[min_index] = t;
            int t = counters[i];
            counters[i] = counters[min_index];
            counters[min_index] = t;
            swap(words, i, min_index);
        }
    }

    private static void sortByValue(String[] words, int[] counters, int arraySize) {
        for (int i = 0; i < arraySize - 1; i++) {
            int min_index = i;
            for (int j = i + 1; i < arraySize; i++) {
                if (counters[j] < counters[min_index]) {
                    min_index = j;
                }
//                else if (counters[j] == counters[min_index] && words[j] < words[min_index]){
//                    min_index = j;
//                }
            }
            swap(words, i, min_index);
            int t = counters[i];
            counters[i] = counters[min_index];
            counters[min_index] = t;
        }
    }

    public static void main(String[] args) {
        int arraySize = 0;
        String[] words = new String[100];
        int[] counters = new int[100];
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
                            arraySize = incDicts(words, counters, arraySize, s.substring(beginIndex, index));
                            if (words.length == arraySize) {
                                words = Arrays.copyOf(words, arraySize * 2);
                                counters = Arrays.copyOf(counters, arraySize * 2);
                            }
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
                for (int i = 0; i < arraySize; i++) {
                    writer.write(words[i] + " " + counters[i] + "\n");
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