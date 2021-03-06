import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Comparator;

class Pair {
    long sum;
    int num;

    Pair(long newSum, int newNum) {
        this.sum = newSum;
        this.num = newNum;
    }
}

public class ReverseSort {
    private static final FastScanner.Checker CHECKER = c -> (Character.isDigit(c) || c == '-');

    public static void main(String[] args) {
        int[][] result = new int[10][]; // NxM array
        int resultHeight = 0;
        int[] intsArray = new int[10]; // arr of current string
        Pair[] sums = new Pair[10]; // pairs sum:num in stream
        try (FastScanner myScanner = new FastScanner(System.in, "utf-8")) {
            while (myScanner.inputNotEmpty()) {
                int arraySize = 0;
                long sum = 0;
                boolean endOfLine = myScanner.lastInLine();
                while (!endOfLine && myScanner.hasCustomNext(CHECKER)) {
                    int newElement = myScanner.nextInt(CHECKER);
                    sum += newElement;
                    if (arraySize == intsArray.length) {
                        intsArray = Arrays.copyOf(intsArray, intsArray.length * 2);
                    }
                    intsArray[arraySize++] = newElement;
                    endOfLine = myScanner.lastInLine();
                }
                // skip EOL if string is empty
                if (endOfLine) {
                    myScanner.toNextLine();
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
            System.err.println("Incorrect encoding: " + e.getMessage());
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("I/O error: " + e.getMessage());
        }

        result = Arrays.copyOf(result, resultHeight);
        sums = Arrays.copyOf(sums, resultHeight);
        Arrays.sort(sums, (p1, p2) -> p1.sum != p2.sum ? Long.compare(p2.sum, p1.sum) : p2.num - p1.num);
        for (int i = 0; i < resultHeight; i++) {
            int index = sums[i].num;
            for (int j = result[index].length - 1; j >= 0; j--) {
                System.out.print(result[index][j] + " ");
            }
            System.out.println();
        }
    }
}
