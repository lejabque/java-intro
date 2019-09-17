import java.util.Arrays;
import java.util.Scanner;

public class ArraysExample {
    public static void main(String[] args) {
        singleDimensionalArrays();
        multiDimensionalArrays();
        references();
        imports();
    }

    public static void singleDimensionalArrays() {
        chapter("Single-dimensional arrays");

        section("Declaration");
        int[] ints;
        double[] doubles;
        boolean[] booleans;
        String[] strings;

        section("Allocation");
        ints = new int[10];
        doubles = new double[20];
        booleans = new boolean[30];
        strings = new String[40];

        section("Length");
        System.out.println(ints.length); // 10
        System.out.println(doubles.length); // 20
        System.out.println(booleans.length); // 30
        System.out.println(strings.length); // 40

        section("Default initialization");
        System.out.println(ints[0]); // 0
        System.out.println(doubles[1]); // 0.0
        System.out.println(booleans[2]); // false
        System.out.println(strings[3]); // null

        section("Custom initialization");
        ints = new int[]{1, 2, 3};
        doubles = new double[]{10};
        booleans = new boolean[]{true, false};
        strings = new String[]{"a", "b", "c"};

        System.out.println(ints[0]); // 1
        System.out.println(doubles[0]); // 10.0
        System.out.println(booleans[0]); // true
        System.out.println(strings[0]); // a

        section("Iteration");
        // i - index
        for (int i = 0; i < ints.length; i++) {
            System.out.println(ints[i]);
        }
        // i - int
        for (int i : ints) {
            System.out.println(i);
        }
        // s - string
        for (String s : strings) {
            System.out.println(s);
        }

        section("Copy");
        int[] a = new int[]{10, 20, 30};
        int[] b = a;
        a[0] = 200;
        for (int i : a){
            System.out.println(i);
        }
    }

    public static void multiDimensionalArrays() {
        chapter("Multi-dimensional arrays");

        section("Declaration");
        int[][] ints2d;
        int[][][] ints3d;

        section("Full allocation");
        ints2d = new int[10][20];
        ints3d = new int[10][20][30];
        System.out.println(ints2d[0]); // [I...
        System.out.println(ints3d[0]); // [[I...

        section("Partial allocation");
        ints2d = new int[10][];
        ints3d = new int[10][][];
        System.out.println(ints2d[0]); // null
        System.out.println(ints3d[0]); // null

        section("Ragged arrays");
        for (int i = 0; i < ints2d.length; i++) {
            ints2d[i] = new int[i];
        }
        for (int[] row : ints2d) {
            System.out.println(row.length);
        }

        section("Initialization");
        // Full
        ints2d = new int[][]{new int[]{1, 2}, null, new int[]{3}};
        // Compact
        ints2d = new int[][]{{1, 2}, null, {3}};
    }

    public static void references() {
        chapter("Array references");

        int[] as;
        int[] bs;
        section("Aliasing");
        as = new int[10];
        bs = as;
        as[1] = 10;
        System.out.println(as[1] + " " + bs[1] + " " + (as == bs)); // 10 10 true

        section("Double initialization");
        as = new int[10];
        bs = as;
        as = new int[10];
        as[1] = 20;
        System.out.println(as[1] + " " + bs[1] + " " + (as == bs)); // 20 0 false

        section("Array argument");
        fill(as, 100);
        System.out.println(as[0] + " " + as[1] + " " + as[2]); // 100 100 100

        section("Reference as value");
        bs = as;
        referenceAsValue(as);
        System.out.println(as == bs); // true

        section("Reference as return value");
        as = create(3, 123);
        System.out.println(as[0] + " " + as[1] + " " + as[2]); // 123 123 123

        section("Garbage collection");
        for (int i = 0; i < 100_000; i++) {
            as = new int[1000_000];
            if (i % 1000 == 0) {
                System.out.println(i);
            }
        }
        System.out.println("ok");

        section("Array copying");
        as = new int[]{0, 10, 20, 30, 40, 50};
        bs = create(5, -1);
        System.arraycopy(as, 2, bs, 1, 3); // -1 20 30 40 -1
        for (int b : bs) {
            System.out.print(b + " ");
        }
        System.out.println();
    }

    public static void fill(int[] ints, int value) {
        for (int i = 0; i < ints.length; i++) {
            ints[i] = value;
        }
    }

    public static void referenceAsValue(int[] ints) {
        ints = new int[0];
    }

    public static int[] create(int length, int value) {
        int[] ints = new int[length];
        fill(ints, value);
        return ints;
    }

    public static void imports() {
        chapter("Full names and Imports");

        section("Arrays");

        int[] as = new int[]{0, 10, 20, 30};
        System.out.println(as); // [I@3834d63f
        System.out.println(java.util.Arrays.toString(as)); // [0, 10, 20, 30]

        // Notice
        //    import java.util.Arrays;
        // at the beginning of this file
        System.out.println(Arrays.toString(as)); // [0, 10, 20, 30]

        section("Scanner");
        Scanner scanner = new Scanner(System.in);
        System.out.print("Input two numbers: ");
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        System.out.println("Sum of numbers: " + (a + b));
    }

    public static void section(String name) {
        System.out.println();
        System.out.println("--- " + name);
    }

    public static void chapter(String name) {
        String delimiter = name.replaceAll(".", "=");
        System.out.println();
        System.out.println(delimiter);
        System.out.println(name);
        System.out.println(delimiter);
    }
}
