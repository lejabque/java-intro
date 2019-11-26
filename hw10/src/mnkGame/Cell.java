package mnkGame;

import java.util.List;
import java.util.Map;

public enum Cell {
    X, O, E, A, B, C, D;
    private static final Map<Cell, Character> SYMBOLS = Map.of(
            X, 'X',
            O, 'O',
            A, '|',
            B, '-',
            E, '.'
    );

    @Override
    public String toString() {
        return SYMBOLS.get(this).toString();
    }
}
