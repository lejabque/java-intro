import java.util.Arrays;
import java.util.Scanner;

public class Reverse {
    public static void main(String[] args) {
        int[][] result = new int[1000000][]; // NxM array
        int resultHeight = 0;
        int[] intsArray = new int[1000000];
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            Scanner intScanner = new Scanner(scanner.nextLine());
            int arraySize = 0;
            while (intScanner.hasNextInt()) {
                intsArray[arraySize] = intScanner.nextInt();
                arraySize++;
            }
            result[resultHeight] = Arrays.copyOf(intsArray, arraySize);
            resultHeight++;
        }
        for (int i = resultHeight - 1; i >= 0; i--) {
            for (int j = result[i].length - 1; j > 0; j--) {
                System.out.print(result[i][j] + " ");
            }
            if (result[i].length > 0) {
                System.out.println(result[i][0]);
            } else {
                System.out.println();
            }
        }
    }
}