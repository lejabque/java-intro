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
        int max_dist = 0;
        int v_max = curV;
        for (int i = 0; i < m; ++i) {
            int ind = teams[i];
            if (dists[ind] >= max_dist) {
                max_dist = dists[ind];
                v_max = ind;
            }
        }
        if (max_dist % 2 != 0) {
            System.out.println("NO");
        } else {
            int dist_ans = max_dist / 2;
            // поиск вершины в середине - найдем расстояния от конечной до остальных
            curV = v_max;
            int[] dists_from_max = new int[n];
            findDists(dists_from_max, curV, childs);
            int v_ans = -1;
            for (int i = 0; i < n; ++i) {
                if (dists[i] == dists_from_max[i] && dists[i] == dist_ans) {
                    v_ans = i;
                    break;
                }
            }
            // если не нашли вершину
            if (v_ans == -1) {
                System.out.println("Impossible");
                return;
            }
            // поиск расстояний от вершины-ответа
            curV = v_ans;
            findDists(dists, curV, childs);
            dists[curV] = 0;
            for (int i = 0; i < m; ++i) {
                int ind = teams[i];
                if (dists[ind] != dist_ans) {
                    System.out.println("NO");
                    return;
                }
            }
            System.out.println("YES");
            System.out.println(v_ans + 1);
        }
    }

    public static void findDists(int[] dists, int curV, int[][] childs) {
        Queue<Vinfo> bfsQueue = new LinkedList<Vinfo>();
        dists[curV] = 0;
        for (int child : childs[curV]) {
            bfsQueue.add(new Vinfo(curV, child, 1));
        }
        while (bfsQueue.size() != 0) {
            Vinfo new_vinfo = bfsQueue.remove();
            dists[new_vinfo.point] = new_vinfo.d;
            for (int child : childs[new_vinfo.point]) {
                if (new_vinfo.prev != child) {
                    Vinfo add_vinfo = new Vinfo(new_vinfo.point, child, new_vinfo.d + 1);
                    bfsQueue.add(add_vinfo);
                }
            }
        }
    }
}