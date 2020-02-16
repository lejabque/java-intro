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

    public static int BinaryIterative(int[] numbers, int x) {
        int l = -1;
        int r = numbers.length;
        while (l < r - 1) {
            int m = (l + r) / 2;
            if (numbers[m] > x) {
                l = m;
            } else {
                r = m;
            }
        }
        return r;
    }

    public static int BinaryRecursive(int[] numbers, int l, int r, int x) {
        if (l < r - 1) {
            int m = (l + r) / 2;
            if (numbers[m] > x) {
                return BinaryRecursive(numbers, m, r, x);
            } else {
                return BinaryRecursive(numbers, l, m, x);
            }
        }
        return r;
    }
}
