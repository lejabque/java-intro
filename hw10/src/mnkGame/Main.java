package mnkGame;

public class Main {
    public static void main(String[] args) {
        final Player[] players = {new HumanPlayer(), new SequentialPlayer(),
                new SequentialPlayer(), new RandomPlayer()};
        final Game game = new Game(true, players);
        int result;
        do {
            result = game.play(new ServerBoard(5, 25, 5, players.length));
            if (result != 0) {
                System.out.println("Player " + result + " player won.");
            } else {
                System.out.println("DRAW");
            }
        } while (false);
    }
}
