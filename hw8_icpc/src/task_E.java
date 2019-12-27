import java.util.*;

class Vinfo {
    Vinfo(int parent, int x, int y) {
        prev = parent;
        point = x;
        d = y;
    }

    int prev;
    int point;
    int d;
}

public class task_E {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        var cnts = new int[n];
        for (int i = 0; i < n; ++i) {
            cnts[i] = 0;
        }
        var x = new int[n - 1];
        var y = new int[n - 1];
        for (int i = 0; i < n - 1; ++i) {
            x[i] = in.nextInt() - 1;
            y[i] = in.nextInt() - 1;
            cnts[x[i]]++;
            cnts[y[i]]++;
        }
        var childs = new int[n][];
        for (int i = 0; i < n; ++i) {
            childs[i] = new int[cnts[i]];
        }
        for (int i = 0; i < n - 1; ++i) {
            childs[x[i]][--cnts[x[i]]] = y[i];
            childs[y[i]][--cnts[y[i]]] = x[i];
        }


        int[] teams = new int[m];
        for (int i = 0; i < m; ++i) {
            teams[i] = in.nextInt() - 1;
        }

        int curV = teams[0];
        int[] dists = new int[n];
        findDists(dists, curV, childs);
        int maxDist = 0;
        int v_max = curV;
        for (int i = 0; i < m; ++i) {
            int ind = teams[i];
            if (dists[ind] >= maxDist) {
                maxDist = dists[ind];
                v_max = ind;
            }
        }
        if (maxDist % 2 != 0) {
            System.out.println("NO");
        } else {
            int distAns = maxDist / 2;
            // поиск вершины в середине - найдем расстояния от конечной до остальных
            curV = v_max;
            int[] distsFromMax = new int[n];
            findDists(distsFromMax, curV, childs);
            int vAns = -1;
            for (int i = 0; i < n; ++i) {
                if (dists[i] == distsFromMax[i] && dists[i] == distAns) {
                    vAns = i;
                    break;
                }
            }
            // если не нашли вершину
            if (vAns == -1) {
                System.out.println("Impossible");
                return;
            }
            // поиск расстояний от вершины-ответа
            curV = vAns;
            findDists(dists, curV, childs);
            dists[curV] = 0;
            for (int i = 0; i < m; ++i) {
                int ind = teams[i];
                if (dists[ind] != distAns) {
                    System.out.println("NO");
                    return;
                }
            }
            System.out.println("YES");
            System.out.println(vAns + 1);
        }
    }

    public static void findDists(int[] dists, int curV, int[][] childs) {
        Queue<Vinfo> bfsQueue = new LinkedList<Vinfo>();
        dists[curV] = 0;
        for (int child : childs[curV]) {
            bfsQueue.add(new Vinfo(curV, child, 1));
        }
        while (bfsQueue.size() != 0) {
            Vinfo newVinfo = bfsQueue.remove();
            dists[newVinfo.point] = newVinfo.d;
            for (int child : childs[newVinfo.point]) {
                if (newVinfo.prev != child) {
                    Vinfo add_vinfo = new Vinfo(newVinfo.point, child, newVinfo.d + 1);
                    bfsQueue.add(add_vinfo);
                }
            }
        }
    }
}