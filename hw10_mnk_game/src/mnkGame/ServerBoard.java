package mnkGame;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class ServerBoard implements Board, Position {
    private static final Cell[] CELLS = Cell.values();
    private final Cell[][] cells;
    private Cell turn;
    private int m, n, k, empty;
    private Position playerBoard;
    private int currentPlayer = 0;
    private int playersCount;

    public ServerBoard(int m, int n, int k, int playersCount) {
        this.m = m;
        this.n = n;
        this.k = k;
        this.playersCount = playersCount;
        this.empty = m * n;
        this.cells = new Cell[m][n];
        this.playerBoard = new PlayerBoard(this);
        for (Cell[] row : cells) {
            Arrays.fill(row, Cell.E);
        }
        turn = CELLS[currentPlayer];
    }

    @Override
    public Position getPosition() {
        return playerBoard;
    }

    @Override
    public Cell getCell() {
        return turn;
    }

    @Override
    public int getM() {
        return m;
    }

    @Override
    public int getN() {
        return n;
    }

    @Override
    public Cell getCell(final int r, final int c) {
        return cells[r][c];
    }

    private boolean checkWin(Move move) {
        return isWin(move, 1, 0) || isWin(move, 0, 1)
                || isWin(move, 1, 1) || isWin(move, -1, 1);
    }

    private boolean isWin(Move move, int deltaRow, int deltaColumn) {
        return countSymbols(move, deltaRow, deltaColumn) +
                countSymbols(move, -deltaRow, -deltaColumn) + 1 >= k;
    }

    private int countSymbols(Move move, int deltaRow, int deltaColumn) {
        int count = 0;
        for (int curRow = move.getRow() + deltaRow, curColumn = move.getColumn() + deltaColumn;
             curRow < m && curColumn < n && curRow >= 0 && curColumn >= 0 && getCell(curRow, curColumn) == turn;
             curRow += deltaRow, curColumn += deltaColumn) {
            count++;
        }
        return count;
    }

    @Override
    public Result makeMove(final Move move) {
        if (!isValid(move)) {
            return Result.LOSE;
        }

        cells[move.getRow()][move.getColumn()] = move.getValue();
        empty--;
        if (checkWin(move)) {
            return Result.WIN;
        }
        if (empty == 0) {
            return Result.DRAW;
        }
        currentPlayer = (currentPlayer + 1) % playersCount;
        turn = CELLS[currentPlayer];
        return Result.UNKNOWN;
    }

    @Override
    public boolean isValid(final Move move) {
        return 0 <= move.getRow() && move.getRow() < m
                && 0 <= move.getColumn() && move.getColumn() < n
                && cells[move.getRow()][move.getColumn()] == Cell.E
                && turn == move.getValue();
    }

    @Override
    public String toString() {
        final int spacesCount = Math.max(String.valueOf(m).length(), String.valueOf(n).length());
        final StringBuilder sb = new StringBuilder(" ".repeat(spacesCount + 1));
        for (int i = 0; i < n; i++) {
            sb.append(String.format("%" + spacesCount + "d", i)).append(" ");
        }
        for (int r = 0; r < m; r++) {
            sb.append("\n");
            sb.append(String.format("%" + spacesCount + "d", r)).append(" ");
            for (int c = 0; c < n; c++) {
                sb.append(String.format("%" + spacesCount + "s", cells[r][c].toString())).append(" ");
            }
        }
        return sb.toString();
    }
}
