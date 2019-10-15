import java.util.Arrays;

class IntList {
    private int[] list;
    private int listSize;

    IntList(int x) {
        this();
        this.add(x);
    }

    private IntList() {
        list = new int[10];
        listSize = 0;
    }

    void add(int x) {
        if (listSize == list.length) {
            list = Arrays.copyOf(list, listSize * 3 / 2);
        }
        list[listSize++] = x;
    }

    String printList() {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < listSize; ++i) {
            res.append(" ").append(list[i]);
        }
        return res.toString();
    }
}
