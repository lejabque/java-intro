package mnkGame;

public class Main {
    public static void main(String[] args) {
        final Game game = new Game(false, new HumanPlayer(), new RandomPlayer());
        int result;
        do {
            result = game.play(new MnkBoard(5, 25, 5));
            if (result == 1) {
                System.out.println("First player won.");
            } else if (result == 2) {
                System.out.println("Second player won.");
            } else {
                System.out.println("DRAW");
            }
        } while (result != 0);
    }
}
