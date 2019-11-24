package mnkGame;

import java.util.Arrays;
import java.util.Map;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public class MnkBoard implements Board, Position {
    private static final Map<Cell, Character> SYMBOLS = Map.of(
            Cell.X, 'X',
            Cell.O, 'O',
            Cell.E, '.'
    );

    private final Cell[][] cells;
    private Cell turn;
    private int m, n, k, empty;

    public MnkBoard(int m, int n, int k) {
        this.m = m;
        this.n = n;
        this.k = k;
        this.empty = m * n;
        this.cells = new Cell[m][n];
        for (Cell[] row : cells) {
            Arrays.fill(row, Cell.E);
        }
        turn = Cell.X;
    }

    @Override
    public Position getPosition() {
        return this;
    }

    @Override
    public Cell getCell() {
        return turn;
    }

    @Override
    public Result makeMove(final Move move) {
        if (!isValid(move)) {
            return Result.LOSE;
        }

        cells[move.getRow()][move.getColumn()] = move.getValue();
        empty--;
        int inRow = 0;
        int inColumn = 0;
        int inDiag1 = 0;
        int inDiag2 = 0;
        int row = move.getRow();
        int column = move.getColumn();
        int columnCheck = column;
        // horizontal check
        while (columnCheck >= 0 && cells[row][columnCheck] == turn) {
            inRow++;
            columnCheck--;
        }
        columnCheck = column + 1;
        while (columnCheck < n && cells[row][columnCheck] == turn) {
            inRow++;
            columnCheck++;
        }
        int rowCheck = row;
        // vertical check
        while (rowCheck >= 0 && cells[rowCheck][column] == turn) {
            inColumn++;
            rowCheck--;
        }
        rowCheck = row + 1;
        while (rowCheck < m && cells[rowCheck][column] == turn) {
            inColumn++;
            rowCheck++;
        }
        // diag1 check
        columnCheck = column;
        rowCheck = row;
        while (columnCheck >= 0 && rowCheck >= 0 && cells[rowCheck][columnCheck] == turn) {
            inDiag1++;
            columnCheck--;
            rowCheck--;
        }
        columnCheck = column + 1;
        rowCheck = row + 1;
        while (columnCheck < n && rowCheck < m && cells[rowCheck][columnCheck] == turn) {
            inDiag1++;
            columnCheck++;
            rowCheck++;
        }

        // diag2 check
        columnCheck = column;
        rowCheck = row;
        while (columnCheck < n && rowCheck >= 0 && cells[rowCheck][columnCheck] == turn) {
            inDiag2++;
            columnCheck++;
            rowCheck--;
        }
        columnCheck = column - 1;
        rowCheck = row + 1;
        while (columnCheck >= 0 && rowCheck < m && cells[rowCheck][columnCheck] == turn) {
            inDiag2++;
            columnCheck--;
            rowCheck++;
        }


//        for (int u = 0; u < m; u++) {
//            int inRow = 0;
//            int inColumn = 0;
//            for (int v = 0; v < n; v++) {
//                if (cells[u][v] == turn) {
//                    inRow++;
//                }
//                if (cells[v][u] == turn) {
//                    inColumn++;
//                }
//                if (cells[u][v] == Cell.E) {
//                    empty++;
//                }
//            }
//
//            if (inRow == k || inColumn == k) {
//                return Result.WIN;
//            }
//            if (cells[u][u] == turn) {
//                inDiag1++;
//            }
//            if (cells[u][n - 1 - u] == turn) {
//                inDiag2++;
//            }
//        }
        if (inRow == k || inColumn == k || inDiag1 == k || inDiag2 == k) {
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
                && turn == getCell();
    }

    @Override
    public Cell getCell(final int r, final int c) {
        return cells[r][c];
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("  ");
        for (int i = 0; i < n; i++) {
            sb.append(i).append(" ");
        }
        for (int r = 0; r < m; r++) {
            sb.append("\n");
            sb.append(r).append(" ");
            for (int c = 0; c < n; c++) {
                sb.append(SYMBOLS.get(cells[r][c])).append(" ");
            }
        }
        return sb.toString();
    }
}
