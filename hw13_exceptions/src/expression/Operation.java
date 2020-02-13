package expression;

import java.util.List;
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

    public static final Map<Operation, String> OPERATORS = Map.of(
            ADD, "+", SUB, "-",
            MUL, "*", DIV, "/",
            LOG2, "log2", POW2, "pow2",
            LOG, "//", POW, "**"
    );

    public static final Map<Character, Operation> CHAROPERANDS = Map.of(
            '+', ADD, '-', SUB,
            '*', MUL, '/', DIV
    );

    public static final List<List<Operation>> PRIORITIESLIST = List.of(
            List.of(ADD, SUB),
            List.of(MUL, DIV),
            List.of(LOG2, POW2),
            List.of(LOG, POW),
            List.of(CONST, VAR)
    );
}
