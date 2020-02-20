package search;

public class BinarySearch {
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

    // Pre: forall i > j in [0..arr.length - 1]: arr[i] <= arr[j]
    public static int BinaryIterative(int[] arr, int x) {
        int l = -1;
        int r = arr.length;
        // l = -1 && r = arr.length
        // I: l < r && (l >= 0 -> arr[l] > x) && (r >= 0 -> arr[r] <= x)
        while (l < r - 1) {
            // I && l < r - 1
            int m = (l + r) / 2;
            // I && m = (l + r)/2 && l < r - 1
            if (arr[m] > x) {
                // I && arr[m] > x  && l < r - 1
                l = m;
                // I && arr[m] > x && L = m
            } else {
                // I && arr[m] <= x && l < r - 1
                r = m;
                // I && arr[m] <= x && R = m
            }
            // I && (L > l || R < r) && (R - L < r - l)
        }
        // l = r - 1
        return r;
        // Post: (arr.length == 0 -> r = 0) || (arr.length > 0 -> (arr[r] <= x && (r > 0 -> arr[r - 1] > x))
    }

    // Pre: (forall i > j in [0..arr.length - 1]: arr[i] <= arr[j])
    public static int BinaryRecursive(int[] arr, int l, int r, int x) {
        if (l < r - 1) {
            // l < r - 1
            int m = (l + r) / 2;
            if (arr[m] > x) {
                // arr[m] > x  && l < r - 1
                r = BinaryRecursive(arr, m, r, x);
            } else {
                // arr[m] <= x  && l < r - 1
                r = BinaryRecursive(arr, l, m, x);
            }
        }
        return r;
        // Post: (arr.length == 0 -> r = 0) || (arr.length > 0 -> (arr[r] <= x && (r > 0 -> arr[r - 1] > x))
    }
}
