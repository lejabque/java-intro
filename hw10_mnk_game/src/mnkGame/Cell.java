package mnkGame;

import java.util.Map;

public enum Cell {
    player1, player2, player3, player4, E;
    private static final Map<Cell, Character> SYMBOLS = Map.of(
            player1, 'X',
            player2, 'O',
            player3, '|',
            player4, '-',
            E, '.'
    );

    @Override
    public String toString() {
        return SYMBOLS.get(this).toString();
    }
}
