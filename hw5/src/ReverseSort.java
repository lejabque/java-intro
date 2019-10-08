import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Arrays;

public class FastReverseSort {
    public static void main(String[] args) {
        String[] result = new String[10]; // NxM array
        int resultHeight = 0;

        try {
            FastScanner lineScanner = new FastScanner(System.in);
            while (lineScanner.hasNextLine()) {
                FastScanner intScanner = new FastScanner(lineScanner.nextLine());
                StringBuilder newLine = new StringBuilder();
                long sum = 0;
                while (intScanner.hasNext()) {
                    int newElement = intScanner.nextInt();
                    newLine.append(newElement).append(" ");
                }
                if (resultHeight == result.length) {
                    result = Arrays.copyOf(result, result.length * 2);
                }
                result[resultHeight++] = sum + " " + resultHeight + " " + newLine.toString();
            }
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("I/O error: " + e.getMessage());
        }
        Arrays.sort(result);
        for (int i = resultHeight - 1; i >= 0; i--) {
            int whitespace = 0;
            int j = 0;
            while (whitespace< 2){
                if (Character.isWhitespace(result[i].charAt(j))) {
                    whitespace++;
                }
                j++;
            }
            System.out.println(result[i].substring(j));
        }
    }
}
