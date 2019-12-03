package expression;

public class Main {

    public static void main(String[] args) {
        System.out.println(new Add(new Const(2), new Subtract(new Variable("x"), new Variable("x"))).toMiniString());
    }
}
