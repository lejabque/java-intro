import java.util.Arrays;
import java.util.Scanner;

public class Reverse {
    public static void main(String[] args) {
        int[][] result = new int[1000000][]; // NxM array
        int resultHeight = 0;
        int[] newArray = new int[1000000];
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String s = scanner.nextLine();
            Scanner stringScanner = new Scanner(s);
            int arraySize = 0;
            while (stringScanner.hasNextInt()) {
                newArray[arraySize] = stringScanner.nextInt();
                arraySize++;
            }
            result[resultHeight] = new int[arraySize];
            for (int i = 0; i < arraySize; i++) {
                result[resultHeight][i] = newArray[i];
            }
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