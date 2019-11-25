package mnkGame;

import java.io.PrintStream;
import java.util.Scanner;

public class CheatPlayer implements Player {
    private final PrintStream out;
    private final Scanner in;

    public CheatPlayer(final PrintStream out, final Scanner in) {
        this.out = out;
        this.in = in;
    }

    public CheatPlayer() {
        this(System.out, new Scanner(System.in));
    }

    @Override
    public Move move(final Position position, final Cell cell) {
        while (true) {
            out.println("Position");
            out.println(position);
            out.println(cell + "'s move");
            out.println("Enter row and column");
            final Move move = new Move(in.nextInt(), in.nextInt(), cell);
            // cast pos to board
            if (position.isValid(move)) {
                return move;
            }
            final int row = move.getRow();
            final int column = move.getColumn();
            out.println("Move " + move + " is invalid");
        }
    }
}
