package search;

public class BinarySearch {
    // Pre:
    public static void main(String[] args) {
        int n = args.length;
        int x = Integer.parseInt(args[0]);
        int[] numbers = new int[n - 1];
        for (int i = 1; i < n; i++) {
            numbers[i - 1] = Integer.parseInt(args[i]);
        }
        System.out.println(BinaryIterative(numbers, x));
        // System.out.println(BinaryRecursive(numbers, -1, numbers.length, x));
    }

    // Pre: forall i > j in [0..a.length - 1]: a[i] <= a[j]
    public static int BinaryIterative(int[] a, int x) {
        int l = -1;
        int r = a.length;
        // l = -1 && r = a.length && l <= r - 1

        // I: (l >= 0 -> a[l] > x) && (r <= a.length - 1 -> a[r] <= x)
        while (l < r - 1) {
            // I && l < r - 1
            int m = (l + r) / 2;
            // I && l < r - 1 && m = (l + r)/2
            if (a[m] > x) {
                // I && l < r - 1 && a[m] > x
                l = m;
                // I && l' = (l + r) / 2 && a[l'] > x
            } else {
                // I && l < r - 1 && a[m] <= x
                r = m;
                // I && r' = m && a[r'] <= x
            }
            // I && (l' > l || r' < r) && (0 < r' - l' < r - l)
        }
        // (l = r - 1 && (l >= 0 -> a[l] > x)) -> (a[r-1] > x))
        // && (l = r - 1 && (r <= a.length - 1 -> a[r] <= x)) -> (a[r] <= x))

        return r;
        // Post: (a.length == 0 -> r = 0)
        //        && (a.length > 0 -> (a[r] <= x && (r > 0 -> a[r - 1] > x)))
    }

    // Pre: r >= 0 && l <= r <= a.length && forall i > j in [0..a.length - 1]: a[i] <= a[j]
    public static int BinaryRecursive(int[] a, int l, int r, int x) {
        if (l < r - 1) {
            // l < r - 1
            int m = (l + r) / 2;
            // l < r - 1 && m = (l + r)/2
            if (a[m] > x) {
                // l < r - 1 && a[m] > x
                r = BinaryRecursive(a, m, r, x);
                // r' = BinaryRecursive(a, (l+r)/2, r, x)
            } else {
                // l < r - 1 && a[m] <= x
                r = BinaryRecursive(a, l, m, x);
                // r' = BinaryRecursive(a, l, (l + r)/2, x)
            }
        }
        return r;
        // Post: R: (r - l <= 1 -> R = r) && (r - l > 1 -> (a[R] <= x && (R > 0 -> a[R - 1] > x))
    }
}
