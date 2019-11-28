package mnkGame;

public class Game {
    private final boolean log;
    private final Player[] players;

    public Game(final boolean log, Player[] players) {
        this.log = log;
        this.players = players;
    }

    public int play(Board board) {
        while (true) {
            for (int i = 0; i < players.length; i++) {
                final int curResult = move(board, players[i], i + 1);
                if (curResult != -1) {
                    return curResult;
                }
            }
        }
    }

    private int move(final Board board, final Player player, final int no) {
        final Move move = player.move(board.getPosition(), board.getCell());
        final Result result = board.makeMove(move);
        log("Player " + no + " move: " + move);
        log("Position:\n" + board);
        if (result == Result.WIN) {
            log("Player " + no + " won");
            return no;
        } else if (result == Result.LOSE) {
            log("Someone is cheater. It's player:" + no + ", GAME OVER");
            return -2;
        } else if (result == Result.DRAW) {
            log("Draw");
            return 0;
        } else {
            return -1;
        }
    }

    private void log(final String message) {
        if (log) {
            System.out.println(message);
        }
    }
}
