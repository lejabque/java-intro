public class WordStatTuple {
    private int counter;
    private int lastLine;
    private IntList list;

    public WordStatTuple() {
        list = new IntList();
    }

    public void add(int x, int newLastLine) {
        if (newLastLine != lastLine) {
            list.add(x);
            lastLine = newLastLine;
        }
        updateCounter();
    }

    private void updateCounter() {
        counter++;
    }

    public String tupleToString() {
        return counter + list.listToString();
    }
}