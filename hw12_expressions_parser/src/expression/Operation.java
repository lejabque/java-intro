package expression;
import java.util.Map;

public enum Operation {
    ADD, SUB,
    MUL, DIV,
    CONST, VAR,
    LEFTSHIFT, RIGHTSHIFT;

    public static final Map<Operation, Integer> PRIORITIES = Map.of(
            LEFTSHIFT, 0, RIGHTSHIFT, 0,
            ADD, 1, SUB, 1,
            MUL, 2, DIV, 2,
            CONST, 3, VAR, 3
    );

    public static final Map<Character, Operation> CHAROPERANDS = Map.of(
           '+', ADD, '-', SUB,
            '<', LEFTSHIFT, '>', RIGHTSHIFT,
            '*', MUL, '/', DIV
    );
}
