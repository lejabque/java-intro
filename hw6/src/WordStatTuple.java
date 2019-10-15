class WordStatTuple extends IntList {
    private int counter;
    private int lastLine;

    WordStatTuple(int x, int newLastLine) {
        super(x);
        lastLine = newLastLine;
        updateCounter();
    }

    void add(int x, int newLastLine) {
        super.add(x);
        lastLine = newLastLine;
    }

    int getLastLine() {
        return lastLine;
    }

    void updateCounter() {
        counter++;
    }

    @Override
    String printList() {
        return counter + super.printList();
    }
}