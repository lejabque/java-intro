import java.util.Arrays;
import java.util.Scanner;

public class ReverseEven {
    public static void main(String[] args) {
        int[][] result = new int[10][]; // NxM array
        int resultHeight = 0;
        int[] intsArray = new int[10]; // arr of current string

        Scanner lineScanner = new Scanner(System.in);
        while (lineScanner.hasNextLine()) {
            Scanner intScanner = new Scanner(lineScanner.nextLine());
            int arraySize = 0;
            while (intScanner.hasNextInt()) {
                int newElement = intScanner.nextInt();
                if (newElement % 2 == 0) {
                    if (arraySize == intsArray.length) {
                        intsArray = Arrays.copyOf(intsArray, intsArray.length * 2);
                    }
                    intsArray[arraySize++] = newElement;
                }
            }
            if (resultHeight == result.length) {
                result = Arrays.copyOf(result, result.length * 2);
            }
            result[resultHeight++] = Arrays.copyOf(intsArray, arraySize);
        }

        for (int i = resultHeight - 1; i >= 0; i--) {
            for (int j = result[i].length - 1; j >= 0; j--) {
                System.out.print(result[i][j] + " ");
            }
            System.out.println();
        }
    }
}
