import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.io.*;

public class task_H {
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        int n = in.nextInt();
        int[] pref = new int[n + 1];
        var f = new int[1_000_000];
        pref[0] = 0;
        int maxTrans = -1;
        for (int i = 0; i < n; i++) {
            int a = in.nextInt();
            pref[i + 1] = pref[i] + a;
            for (int j = pref[i]; j < pref[i + 1]; j++) {
                f[j] = i;
            }
            if (maxTrans < a) {
                maxTrans = a;
            }
        }

        int q = in.nextInt();
        var ans = new int[1_000_001];

        for (int i = 0; i < q; i++) {
            int t = in.nextInt();
            if (t < maxTrans) {
                out.println("Impossible");
            } else if (t >= pref[n]) {
                out.println(1);
            } else if (ans[t] != 0) {
                out.println(ans[t]);
            } else {
                int cnt = 0;
                int lastInBlock = 1;
                while (lastInBlock <= n) {
                    // бинпоиском скипаем один блок
                    int leftBorder = lastInBlock;
                    int rightBorder = n + 1;
                    int val = pref[lastInBlock - 1] + t;
                    while (rightBorder - leftBorder > 1) {
                        int mid = rightBorder + leftBorder >>> 1;
                        if (pref[mid] > val) {
                            rightBorder = mid;
                        } else {
                            leftBorder = mid;
                        }
                    }
                    cnt++;
                    lastInBlock = leftBorder + 1;
                }
                out.println(cnt);
                ans[t] = cnt;
            }
        }
        out.close();

    }
}
