import java.util.Arrays;
import java.util.Scanner;
import java.util.ArrayList;

public class Reverse {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<String[]> result = new ArrayList<String[]>();
        int resultHeight = 0;
        while (scanner.hasNextLine()) {
            String s = scanner.nextLine();
            result.add(s.split("\\p{javaWhitespace}+"));
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