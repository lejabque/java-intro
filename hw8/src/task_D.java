import java.util.Scanner;

public class task_D {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        long MOD = 998_244_353;
        int n = in.nextInt();
        int k = in.nextInt();
        long[] kPowers = new long[n + 1];
        kPowers[0] = 1;
        for (int i = 1; i <= n; ++i) {
            kPowers[i] = (kPowers[i - 1] * k) % MOD;
        }

        long[] R = new long[n + 1];
        R[0] = 0;
        if (n > 0) {
            R[1] = kPowers[(1) / 2] * kPowers[(1 + 1) / 2] % MOD;
        }
        if (n > 1) {
            R[2] = kPowers[(1) / 2] * kPowers[(2 + 1) / 2] % MOD + kPowers[(1 + 1) / 2] * kPowers[(2 - 1 + 1) / 2] % MOD;
        }
        for (int i = 3; i <= n; ++i) {
            R[i] = R[i - 2] * k % MOD
                    + kPowers[((i - 1) + 1) / 2] * kPowers[(i - (i - 1) + 1) / 2] % MOD
                    + kPowers[((i - 2) + 1) / 2] * kPowers[(i - (i - 2) + 1) / 2] % MOD;
            R[i] %= MOD;
        }

        long[] D = new long[n + 1];
        D[0] = R[0];
        if (n > 0) {
            D[1] = R[1];
        }
        for (int i = 2; i <= n; ++i) {
            D[i] = R[i] % MOD;
            D[i] -= i * D[1];
            for (int l = 2; l * l <= i && l <= i; ++l) {
                if (i % l == 0 && l * l != i) {
                    D[i] -= (i / l) * D[l];
                    D[i] -= l * D[i / l];
                } else if (l * l == i) {
                    D[i] -= l * D[l];
                }
                while (D[i] < 0) {
                    D[i] += MOD;
                }
            }
            D[i] %= MOD;
        }
        long ans = 0;
        for (int i = 1; i <= n; ++i) {
            for (int l = 1; l * l <= i && l <= i; ++l) {
                if (i % l == 0 && l * l != i) {
                    ans += D[l] % MOD;
                    ans %= MOD;
                    ans += D[i / l] % MOD;
                    ans %= MOD;
                } else if (l * l == i) {
                    ans += D[l] % MOD;
                }
                ans %= MOD;
            }
        }

        System.out.println(ans % MOD);
    }
}
