package search;

public class BinarySearchShift {
    // Pre: forall i: args[i] is int &&
    // (i != j && 0 <= i < args.length && 0 <= j < args.length) -> args[i] != args[j]) &&
    //  exists 0 <= k < args.length:
    //  (0 <= i < j < k -> args[i] < args[j]) && (k <= i < j < args.length -> args[i] < args[j])
    public static void main(String[] args) {
        int[] numbers = new int[args.length];
        for (int i = 0; i < args.length; i++) {
            numbers[i] = Integer.parseInt(args[i]);
        }
        //System.out.println(BinaryShiftIterative(numbers));
        System.out.println(BinaryShiftRecursive(numbers, -1, numbers.length));
        // Post: k: 0 <= k < args.length
    }


    // Pre: (i != j && 0 <= i < a.length && 0 <= j < a.length) -> a[i] != a[j]) &&
    // exists 0 <= k < a.length:
    // (0 <= i < j < k -> a[i] < a[j]) && (k <= i < j < a.length -> a[i] < a[j])
    public static int BinaryShiftIterative(int[] a) {
        int l = -1;
        int r = a.length;
        // l = -1 && r = a.length && l <= r - 1

        // Inv: -1 <= l < k <= r <= a.length
        while (l < r - 1) {
            // l < r - 1
            int m = (l + r) / 2;
            // (l < r - 1 && m = (l + r)/2) -> l < m < r
            if (a[m] < a[0]) {
                // a[m] < a[0] -> k <= m
                r = m;
                // r' = (l + r)/2 && k <= r'
            } else {
                // a[m] > a[0] -> m < k
                l = m;
                // l' = (l + r)/2 && l' < k
            }
            // (l' > l || r' < r) && (l < m < r) -> (0 < r' - l' < r - l)
        }
        // (-1 < l < k <= r <= a.length) && (r - l == 1) -> (r == k || (k = 0 && r = a.length))
        // r == a.length -> k = 0
        if (r == a.length) {
            return 0;
        }
        // r < a.length -> r = k
        return r;
        // Post: R = k
    }

    // Pre: -1 <= l < r <= a.length &&
    // (i != j && 0 <= i < a.length && 0 <= j < a.length) -> a[i] != a[j]) &&
    // exists -1 <= l < k < r <= a.length:
    // (0 <= i < j < k -> a[i] < a[j]) && (k <= i < j < a.length -> a[i] < a[j])
    public static int BinaryShiftRecursive(int[] a, int l, int r) {
        if (l < r - 1) {
            // l < r - 1
            int m = (l + r) / 2;
            // (l < r - 1 && m = (l + r)/2) -> l < m < r
            if (a[m] < a[0]) {
                // a[m] < a[0] -> k <= m
                return BinaryShiftRecursive(a, l, m);
                // R = BinaryRecursive(a, (l+r)/2, r, x) -> R = k
            } else {
                // a[m] > a[0] -> m < k
                return BinaryShiftRecursive(a, m, r);
                // R = BinaryRecursive(a, l, (l + r)/2, x) -> R = k
            }
        }
        // (-1 < l < k <= r <= a.length) && (r - l == 1) -> (r == k || (k = 0 && r = a.length))
        // r == a.length -> k = 0
        if (r == a.length) {
            return 0;
        }
        // r < a.length -> r = k
        return r;
        // Post: R = k
    }
}
