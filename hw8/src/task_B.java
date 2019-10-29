import java.util.Scanner;

import java.lang.Math;

public class task_B {
    public static void main(String[] args) {
        int n;
        long begin = -710 * 40000 + 4;
        Scanner in = new Scanner(System.in);
        n = in.nextInt();
        for (int i = 0; i < n; ++i) {
            System.out.println(begin);
            // System.out.println(Math.sin((double) begin));
            begin += 710;
        }
    }
}
