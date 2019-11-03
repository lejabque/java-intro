import java.util.Scanner;

public class task_I {
    public static void main(String[] args) {
        int n;
        Scanner in = new Scanner(System.in);
        n = in.nextInt();
        // координаты: (x_l - h, y_l - h) --> (x_r + h, y_r + h)
        long x_l = Long.MAX_VALUE;
        long x_r = Long.MIN_VALUE;
        long y_l = Long.MAX_VALUE;
        long y_r = Long.MIN_VALUE;
        for (int i = 0; i < n; ++i) {
            int new_x, new_y, new_h;
            new_x = in.nextInt();
            new_y = in.nextInt();
            new_h = in.nextInt();
            x_l = Math.min(x_l, new_x - new_h);
            x_r = Math.max(x_r, new_x + new_h);
            y_l = Math.min(y_l, new_y - new_h);
            y_r = Math.max(y_r, new_y + new_h);

        }

        long x_c = (x_l + x_r) / 2;
        long y_c = (y_l + y_r) / 2;
        double h = Math.max(x_r - x_l, y_r - y_l) / 2.0 + 0.5;
        System.out.print((long) x_c + " ");
        System.out.print((long) y_c + " ");
        if (h < 0) {
            System.out.println(0);
        } else {
            System.out.println((long) Math.floor(h));
        }
    }
}
