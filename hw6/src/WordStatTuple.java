class WordStatTuple {
    private int counter;
    private int lastLine;
    private IntList list;

    WordStatTuple() {
        list = new IntList();
    }

    void add(int x, int newLastLine) {
        if (newLastLine != lastLine) {
            list.add(x);
            lastLine = newLastLine;
        }
        updateCounter();
    }

    private void updateCounter() {
        counter++;
    }

    String tupleToString() {
        return counter + list.listToString();
    }
}