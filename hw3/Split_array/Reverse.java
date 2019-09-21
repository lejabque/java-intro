import java.util.Arrays;
import java.util.Scanner;

public class Reverse {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[][] result = new String[1000000][]; // NxM array
        int resultHeight = 0;
        while (scanner.hasNextLine()) {
            String s = scanner.nextLine();
            result[resultHeight] = s.split("\\p{javaWhitespace}+");
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