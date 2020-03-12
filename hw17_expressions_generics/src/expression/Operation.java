package expression;

import java.util.List;
import java.util.Map;
import java.util.Set;

public enum Operation {
    ADD, SUB,
    MUL, DIV,
    CONST, VAR,
    MIN, MAX, COUNT;

    public static final Map<Operation, Integer> PRIORITIES = Map.of(
            MIN, 0, MAX, 0,
            ADD, 1, SUB, 1,
            MUL, 2, DIV, 2,
            CONST, 3, VAR, 3
    );

    public static final Map<Operation, String> OPERATORS_STRING = Map.of(
            ADD, "+", SUB, "-",
            MUL, "*", DIV, "/",
            MIN, "min", MAX, "max",
            COUNT, "count"
    );

    public static final Map<String, Operation> STRING_TO_UNARY = Map.of(
            "count", COUNT
    );

    public static final Set<String> VARIABLES = Set.of(
            "x", "y", "z"
    );

    public static final List<List<Operation>> PRIORITY_TO_OPER = List.of(
            List.of(MIN, MAX),
            List.of(ADD, SUB),
            List.of(MUL, DIV),
            List.of(CONST, VAR)
    );
}
