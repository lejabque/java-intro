package mnkGame;

import java.io.PrintStream;
import java.util.Scanner;

public class HumanPlayer implements Player {
    private final PrintStream out;
    private final Scanner in;

    public HumanPlayer(final PrintStream out, final Scanner in) {
        this.out = out;
        this.in = in;
    }

    public HumanPlayer() {
        this(System.out, new Scanner(System.in));
    }

    @Override
    public Move move(final Position position, final Cell cell) {
        while (true) {
            out.println("Position");
            out.println(position);
            out.println(cell.toString() + "'s move");
            out.println("Enter row and column");
            int newRow = nextInt();
            int newColumn = nextInt();
            final Move move = new Move(newRow, newColumn, cell);
            if (position.isValid(move)) {
                return move;
            }
            final int row = move.getRow();
            final int column = move.getColumn();
            out.println("Move " + move + " is invalid");
        }
    }

    private int nextInt() {
        while (true) {
            if (in.hasNextInt()) {
                return in.nextInt();
            }
            in.next();
        }
    }
}
