package mnkGame;


public class PlayerBoard implements Position {
    private final Position board;

    public PlayerBoard(Position board) {
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
    public int getM() {
        return board.getM();
    }

    @Override
    public int getN() {
        return board.getN();
    }

    @Override
    public String toString() {
        return board.toString();
    }
}