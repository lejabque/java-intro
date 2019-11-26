package mnkGame;

import java.util.Arrays;
import java.util.Map;

public class ServerBoard implements Board, Position {
    private static final Map<Cell, Character> SYMBOLS = Map.of(
            Cell.X, 'X',
            Cell.O, 'O',
            Cell.E, '.'
    );

    private final Cell[][] cells;
    private Cell turn;
    private int m, n, k, empty;
    private Position playerBoard;

    public ServerBoard(int m, int n, int k) {
        this.m = m;
        this.n = n;
        this.k = k;
        this.empty = m * n;
        this.cells = new Cell[m][n];
        this.playerBoard = new PlayerBoard(this);
        for (Cell[] row : cells) {
            Arrays.fill(row, Cell.E);
        }
        turn = Cell.X;
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

    private boolean checkWin(int row, int column) {
        int[][] deltas = {{0, 1}, {1, 0}, {1, 1}, {-1, 1}};
        for (int[] deltasLine : deltas) {
            int deltaRow = deltasLine[0];
            int deltaColumn = deltasLine[1];
            if (countSymbols(row, column, deltaRow, deltaColumn) +
                    countSymbols(row, column, -deltaRow, -deltaColumn) + 1 >= k) {
                return true;
            }
        }
        return false;
    }

    private int countSymbols(int row, int column, int deltaRow, int deltaColumn) {
        int count = 0;
        for (int curRow = row + deltaRow, curColumn = column + deltaColumn;
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
        if (checkWin(move.getRow(), move.getColumn())) {
            return Result.WIN;
        }
        if (empty == 0) {
            return Result.DRAW;
        }

        turn = turn == Cell.X ? Cell.O : Cell.X;
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
                sb.append(String.format("%" + spacesCount + "c", SYMBOLS.get(cells[r][c]))).append(" ");
            }
        }
        return sb.toString();
    }
}
