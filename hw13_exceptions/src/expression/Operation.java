package expression;

import java.util.List;
import java.util.Map;
import java.util.Set;

public enum Operation {
    ADD, SUB,
    MUL, DIV,
    CONST, VAR,
    LOG, POW,
    LOG2, POW2;

    public static final Map<Operation, Integer> PRIORITIES = Map.of(
            ADD, 0, SUB, 0,
            MUL, 1, DIV, 1,
            LOG, 2, POW, 2,
            CONST, 3, VAR, 3
    );

    public static final Map<Operation, String> OPERATORS_STRING = Map.of(
            ADD, "+", SUB, "-",
            MUL, "*", DIV, "/",
            LOG2, "log2", POW2, "pow2",
            LOG, "//", POW, "**"
    );

    public static final Map<String, Operation> STRING_TO_UNARY = Map.of(
            "log2", LOG2, "pow2", POW2
    );

    public static final Set<String> VARIABLES = Set.of(
            "x", "y", "z"
    );

    public static final List<List<Operation>> PRIORITY_TO_OPER = List.of(
            List.of(ADD, SUB),
            List.of(MUL, DIV),
            List.of(LOG, POW),
            List.of(CONST, VAR)
    );
}
