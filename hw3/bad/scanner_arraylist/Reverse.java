import java.util.Arrays;
import java.util.Scanner;
import java.util.ArrayList;

public class Reverse {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<int[]> result = new ArrayList<int[]>();
        int[] newArray = new int[1000000];
        int resultHeight = 0;
        while (scanner.hasNextLine()) {
            String s = scanner.nextLine();
            Scanner stringScanner = new Scanner(s);
            int arraySize = 0;
            while (stringScanner.hasNextInt()) {
                newArray[arraySize] = stringScanner.nextInt();
                arraySize++;
            }
            result.add(new int[arraySize]);
            for (int i = 0; i < arraySize; i++) {
                result.get(resultHeight)[i] = newArray[i];
            }
            resultHeight++;
        }
        for (int i = resultHeight - 1; i >= 0; i--) {
            for (int j = result.get(i).length - 1; j > 0; j--) {
                System.out.print(result.get(i)[j] + " ");
            }
            if (result.get(i).length > 0) {
                System.out.println(result.get(i)[0]);
            } else {
                System.out.println();
            }
        }
    }
}