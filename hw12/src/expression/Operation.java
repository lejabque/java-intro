package expression;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public enum Operation {
    ADD, SUB,
    MUL, DIV,
    CONST, VAR,
    LEFTSHIFT, RIGHTSHIFT;
    private static Operation[] vals = values();

    public static Map<Operation, Integer> operToPriority = Map.of(
            LEFTSHIFT, 0, RIGHTSHIFT, 0,
            ADD, 1, SUB, 1,
            MUL, 2, DIV, 2,
            CONST, 3, VAR, 3
    );

    public static Map<String, Operation> stringToOper = Map.of(
           "+", ADD, "-", SUB,
            "<", LEFTSHIFT, ">", RIGHTSHIFT,
            "*", MUL, "/", DIV
    );

    public static List<Operation> operationsPriority = List.of(LEFTSHIFT, ADD, MUL, CONST);

    public Operation next(){
        return operationsPriority.get((operationsPriority.indexOf(this) + 1) % operationsPriority.size());
    }
}
