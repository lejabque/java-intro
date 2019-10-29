import java.util.Arrays;

public class IntList {
    private int[] list;
    private int listSize;

    public IntList() {
        list = new int[3];
        listSize = 0;
    }

    public void add(int x) {
        if (listSize == list.length) {
            list = Arrays.copyOf(list, listSize + listSize << 1);
        }
        list[listSize++] = x;
    }

    public String listToString() {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < listSize; ++i) {
            res.append(" ").append(list[i]);
        }
        return res.toString();
    }
}
