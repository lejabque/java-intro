class WordStatTuple {
    private int counter;
    private int lastLine;
    private IntList list;

    WordStatTuple(int x, int newLastLine) {
        list = new IntList(x);
        lastLine = newLastLine;
        updateCounter();
    }

    void add(int x, int newLastLine) {
        list.add(x);
        lastLine = newLastLine;
    }

    int getLastLine() {
        return lastLine;
    }

    void updateCounter() {
        counter++;
    }

    String tupleToString() {
        return counter + list.listToString();
    }
}