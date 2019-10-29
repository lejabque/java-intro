import java.util.Scanner;
import java.util.*;

public class task_A {
    public static void main(String[] args) {
        int a, b, n;
        Scanner in = new Scanner(System.in);
        a = in.nextInt();
        b = in.nextInt();
        n = in.nextInt();
        int x = 2 * ((n - b) / (b - a));
        if ((n - b) % (b - a) == 0) {
            System.out.println(x);
        } else {
            System.out.println(x + 3);
        }
    }
}
