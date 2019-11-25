package mnkGame;


public class ProxyPosition implements Position {
    private final Position board;

    public ProxyPosition(Position board) {
        this.board = board;
    }

    @Override
    public boolean isValid(Move move) {
        return board.isValid(move);
    }

    @Override
    public Cell getCell(int row, int column) {
        return board.getCell(row, column);
    }

    @Override
    public String toString() {
        return board.toString();
    }
}