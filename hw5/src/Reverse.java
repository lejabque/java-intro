import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;

public class Reverse {
    public static void main(String[] args) {
        int[][] result = new int[10][]; // NxM array
        int resultHeight = 0;
        int[] intsArray = new int[10]; // arr of current string

        try {
            FastScanner lineScanner = new FastScanner(System.in, "utf-8");
            while (lineScanner.hasNextLine()) {
                FastScanner intScanner = new FastScanner(lineScanner.nextLine());
                int arraySize = 0;
                while (intScanner.hasNext()) {
                    int newElement = intScanner.nextInt();
                    if (arraySize == intsArray.length) {
                        intsArray = Arrays.copyOf(intsArray, intsArray.length * 2);
                    }
                    intsArray[arraySize++] = newElement;
                }
                if (resultHeight == result.length) {
                    result = Arrays.copyOf(result, result.length * 2);
                }
                result[resultHeight++] = Arrays.copyOf(intsArray, arraySize);
            }
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("I/O error: " + e.getMessage());
        }

        for (int i = resultHeight - 1; i >= 0; i--) {
            for (int j = result[i].length - 1; j >= 0; j--) {
                System.out.print(result[i][j] + " ");
            }
            System.out.println();
        }
    }
}
