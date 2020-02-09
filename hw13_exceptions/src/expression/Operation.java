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
            CONST, 5, VAR, 5,
            LOG2, 3, POW2, 3,
            LOG, 4, POW, 4
    );

    public static final Map<Character, Operation> CHAROPERANDS = Map.of(
            '+', ADD, '-', SUB,
            '*', MUL, '/', DIV
    );
}
