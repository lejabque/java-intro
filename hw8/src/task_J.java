import java.util.Scanner;

public class task_J {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[][] val = new int[n][n];
        int[][] path = new int[n][n];
        for (int i = 0; i < n; ++i) {
            String newData = in.next();
            for (int j = 0; j < n; ++j) {
                val[i][j] = Integer.parseInt(String.valueOf(newData.charAt(j)));
                path[i][j] = 0;
            }
        }
        // проходим все пути i->i+pathlen, обновляя значения
        for (int pathLen = 1; pathLen < n; pathLen++) {
            for (int curV = 0; curV < n - pathLen; curV++) {
                int checkV = curV + pathLen;
                int pathVal = 0;
                // чекнуть через промежуточные пути
                for (int mid = curV + 1; mid < checkV; mid++) {
                    pathVal += path[curV][mid] * val[mid][checkV];
                }
                pathVal = (pathVal + 1) % 10;
                if (val[curV][checkV] == pathVal) {
                    path[curV][checkV] = 1;
                }
            }
        }
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                System.out.print(path[i][j]);
            }
            System.out.println();
        }
    }
}