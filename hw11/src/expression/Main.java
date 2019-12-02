package expression;

public class Main {

    public static void main(String[] args) {
        System.out.println( new Add(new Variable("x"), new Variable("x")).toMiniString());

        System.out.println( new Add(new Variable("x"), new Variable("x")).toString());

        System.out.println( new Add(new Variable("x"), new Variable("x")).evaluate(2));
    }
}
