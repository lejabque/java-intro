class WordStatTuple {
    private int counter;
    private int lastLine;
    private IntList list;

    WordStatTuple() {
        list = new IntList();
    }

    WordStatTuple(int x, int newLastLine) {
        list = new IntList(x);
        lastLine = newLastLine;
        updateCounter();
    }

    void add(int x, int newLastLine) {
        if (newLastLine != lastLine) {
            list.add(x);
            lastLine = newLastLine;
        }
        updateCounter();
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