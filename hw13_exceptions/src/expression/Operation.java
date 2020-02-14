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
            LOG, 3, POW, 3,
            CONST, 4, VAR, 4
    );

    public static final Map<Operation, String> OPERATORS_STRING = Map.of(
            ADD, "+", SUB, "-",
            MUL, "*", DIV, "/",
            LOG2, "log2", POW2, "pow2",
            LOG, "//", POW, "**"
    );

    public static final List<List<Operation>> PRIORITY_TO_OPER = List.of(
            List.of(ADD, SUB),
            List.of(MUL, DIV),
            List.of(LOG, POW),
            List.of(CONST, VAR)
    );

    public static final List<Operation> UNARY_OPERATORS = List.of(
            LOG2, POW2
    );
}
