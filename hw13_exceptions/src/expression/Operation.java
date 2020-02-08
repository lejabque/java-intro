package expression;

import java.util.Map;

public enum Operation {
    ADD, SUB,
    MUL, DIV,
    CONST, VAR,
    LOG, POW,
    LOG2, POW2;

    public static final Map<Operation, Integer> PRIORITIES = Map.of(
            ADD, 1, SUB, 1,
            MUL, 2, DIV, 2,
            CONST, 3, VAR, 3,
            LOG2, 4, POW2, 4,
            LOG, 5, POW, 5
    );

    public static final Map<Character, Operation> CHAROPERANDS = Map.of(
            '+', ADD, '-', SUB,
            '*', MUL, '/', DIV
    );
}
