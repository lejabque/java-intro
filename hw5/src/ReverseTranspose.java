import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;

public class ReverseTranspose {
    public static void main(String[] args) {
        int[][] result = new int[10][]; // NxM array
        int resultHeight = 0;
        int[] intsArray = new int[10]; // arr of current string
        int maxStringLength = 0;
        try {
            FastScanner lineScanner = new FastScanner(System.in);
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
                // изменить здесь
                if (resultHeight == result.length) {
                    result = Arrays.copyOf(result, result.length * 2);
                }

                if (arraySize > maxStringLength) {
                    maxStringLength = arraySize;
                }
                result[resultHeight++] = Arrays.copyOf(intsArray, arraySize);
            }
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("I/O error: " + e.getMessage());
        }

        for (int j = 0; j < maxStringLength; j++) {
            for (int i = 0; i < resultHeight; i++) {
                if (result[i].length > j) {
                    System.out.print(result[i][j] + " ");
                }
            }
            System.out.println();
        }
    }
}
