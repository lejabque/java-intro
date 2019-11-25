package mnkGame;

public interface Position {
    boolean isValid(Move move);
    Cell getCell(final int row, final int column);
}
