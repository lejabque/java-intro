import java.util.Scanner;

public class task_D {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        long m = 998_244_353;
        int n = in.nextInt();
        int k = in.nextInt();
        long[] k_pows = new long[n + 1];
        k_pows[0] = 1;
        for (int i = 1; i <= n; ++i) {
            k_pows[i] = (k_pows[i - 1] * k) % m;
        }

        long[] R = new long[n + 1];
        R[0] = 0;
        if (n > 0) {
            R[1] = k_pows[(1) / 2] * k_pows[(1 + 1) / 2] % m;
        }
        if (n > 1) {
            R[2] = k_pows[(1) / 2] * k_pows[(2 + 1) / 2] % m + k_pows[(1 + 1) / 2] * k_pows[(2 - 1 + 1) / 2] % m;
        }
        for (int i = 3; i <= n; ++i) {
            R[i] = R[i - 2] * k % m
                    + k_pows[((i - 1) + 1) / 2] * k_pows[(i - (i - 1) + 1) / 2] % m
                    + k_pows[((i - 2) + 1) / 2] * k_pows[(i - (i - 2) + 1) / 2] % m;
            R[i] %= m;
        }

        long[] D = new long[n + 1];
        D[0] = R[0];
        if (n > 0) {
            D[1] = R[1];
        }
        for (int i = 2; i <= n; ++i) {
            D[i] = R[i] % m;
            D[i] -= i * D[1];
            for (int l = 2; l * l <= i && l <= i; ++l) {
                if (i % l == 0 && l * l != i) {
                    D[i] -= (i / l) * D[l];
                    D[i] -= l * D[i / l];
                } else if (l * l == i) {
                    D[i] -= l * D[l];
                }
                while (D[i] < 0) {
                    D[i] += m;
                }
            }
            D[i] %= m;
        }
        long ans = 0;
        for (int i = 1; i <= n; ++i) {
            for (int l = 1; l * l <= i && l <= i; ++l) {
                if (i % l == 0 && l * l != i) {
                    ans += D[l] % m;
                    ans %= m;
                    ans += D[i / l] % m;
                    ans %= m;
                } else if (l * l == i) {
                    ans += D[l] % m;
                }
                ans %= m;
            }
        }

        System.out.println(ans % m);
    }
}
