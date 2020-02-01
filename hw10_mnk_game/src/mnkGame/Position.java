package mnkGame;

public interface Position {
    int getM();
    int getN();
    boolean isValid(Move move);
    Cell getCell(final int row, final int column);
}
