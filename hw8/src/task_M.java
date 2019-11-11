import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class task_M {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        for (int testNum = 0; testNum < t; ++testNum) {
            int n = in.nextInt();
            int[] a = new int[n];
            for (int i = 0; i < n; ++i) {
                a[i] = in.nextInt();
            }
            var C = new HashMap<Integer, Integer>();
            int ans = 0;
            for (int j = n - 2; j >= 1; --j) {
                C.merge(a[j + 1], 1, Integer::sum);
                for (int i = 0; i <= j - 1; ++i) {
                    int res = 2 * a[j] - a[i];
                    Integer newInt = C.get(res);
                    if (newInt != null) {
                        ans += newInt;
                    }
                }
            }
            System.out.println(ans);
        }
    }
}
