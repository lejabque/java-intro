import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Comparator;

class Pair {
    public long sum;
    public int num;

    public Pair(long new_sum, int new_num) {
        this.sum = new_sum;
        this.num = new_num;
    }
}

public class ReverseSort {
    public static void main(String[] args) {
        int[][] result = new int[10][]; // NxM array
        int resultHeight = 0;
        int[] intsArray = new int[10]; // arr of current string
        Pair[] sums = new Pair[10]; // pairs sum:num in stream
        try {
            FastScanner lineScanner = new FastScanner(System.in, "utf-8");
            while (lineScanner.hasNextLine()) {
                FastScanner intScanner = new FastScanner(lineScanner.nextLine());
                int arraySize = 0;
                long sum = 0;
                while (intScanner.hasNext()) {
                    int newElement = intScanner.nextInt();
                    sum += newElement;
                    if (arraySize == intsArray.length) {
                        intsArray = Arrays.copyOf(intsArray, intsArray.length * 2);
                    }
                    intsArray[arraySize++] = newElement;
                }
                if (resultHeight == result.length) {
                    result = Arrays.copyOf(result, result.length * 2);
                    sums = Arrays.copyOf(sums, result.length * 2);
                }
                sums[resultHeight] = new Pair(sum, resultHeight);
                result[resultHeight] = Arrays.copyOf(intsArray, arraySize);
                Arrays.sort(result[resultHeight++]);
            }
        } catch (UnsupportedEncodingException e) {
            System.err.println("Uncorrect encoding: " + e.getMessage());
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("I/O error: " + e.getMessage());
        }

        result = Arrays.copyOf(result, resultHeight);
        sums = Arrays.copyOf(sums, resultHeight);
        Arrays.sort(sums, new Comparator<>() {
            public int compare(Pair p1, Pair p2) {
                if (p1.sum > p2.sum) {
                    return -1;
                }
                if (p1.sum < p2.sum) {
                    return 1;
                }
                if (p1.num > p2.num) {
                    return -1;
                } else {
                    return 1;
                }
            }
        });

        for (int i = 0; i < resultHeight; i++) {
            int index = sums[i].num;
            for (int j = result[index].length - 1; j >= 0; j--) {
                System.out.print(result[index][j] + " ");
            }
            System.out.println();
        }
    }
}
