import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

class IntList {
    private int[] list;
    private int listSize;

    IntList(int x) {
        list = new int[5];
        listSize = 0;
        this.add(x);
    }

    void add(int x) {
        list[listSize++] = x;
        if (listSize == list.length) {
            list = Arrays.copyOf(list, listSize * 2);
        }
    }

    String printList() {
        StringBuilder res = new StringBuilder();
        res.append(listSize);
        for (int i = 0; i < listSize; ++i) {
            res.append(" ").append(list[i]);
        }
        return res.toString();
    }
}

public class WordStatIndex {
    public static void main(String[] args) {
        Map<String, IntList> wordCounter = new LinkedHashMap<>();
        try (FastScanner in = new FastScanner(new File(args[0]), "utf-8")) {
            int i = 1;
            while (in.hasNextWord()) {
                String word = in.nextWord().toLowerCase();
                IntList foundList = wordCounter.get(word);
                if (foundList == null) {
                    wordCounter.put(word, new IntList(i));
                } else {
                    foundList.add(i);
                }
                i++;
            }
        } catch (UnsupportedEncodingException e) {
            System.err.println("Incorrect encoding: " + e.getMessage());
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("I/O error: " + e.getMessage());
        }

        try (BufferedWriter out = new BufferedWriter(
                new OutputStreamWriter(new FileOutputStream(args[1]), StandardCharsets.UTF_8))) {
            String[] keys = wordCounter.keySet().toArray(new String[0]);
            for (String key : keys) {
                out.write(key + " " + wordCounter.get(key).printList() + "\n");
            }
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("I/O error: " + e.getMessage());
        }
    }
}