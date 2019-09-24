/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public class Main {
    public static void main(String[] args) {
        vectors();
        circles();
        bank();
    }

    private static void vectors() {
        chapter("Vectors");
        immutableVectors();
        mutableVectors();
        getterVectors();
        polarVectors();
    }

    private static void immutableVectors() {
        section("ImmutableVector");
        ImmutableVector v1 = new ImmutableVector(1, 3);
        ImmutableVector v2 = new ImmutableVector(2, 1);
        ImmutableVector sum = v1.add(v2);
        ImmutableVector difference = v1.subtract(v2);
        dump("v1", v1);
        dump("v2", v2);
        dump("sum", sum);
        dump("difference", difference);
    }

    private static void dump(final String name, ImmutableVector v) {
        System.out.println(name);
        System.out.println("    toString() " + v);
        System.out.println("    getX()     " + v.x);
        System.out.println("    getY()     " + v.y);
        System.out.println("    getPhi()   " + v.getPhi());
        System.out.println("    getRho()   " + v.getRho());
    }

    private static void mutableVectors() {
        section("MutableVector");
        MutableVector v1 = new MutableVector(1, 3);
        MutableVector v2 = new MutableVector(2, 1);

        MutableVector sum = new MutableVector(v1);
        sum.add(v2);

        MutableVector difference = v1.copy();
        difference.subtract(v2);

        dump("v1", v1);
        dump("v2", v2);
        dump("sum", sum);
        dump("difference", difference);
    }

    private static void dump(final String name, MutableVector v) {
        System.out.println(name);
        System.out.println("    toString() " + v);
        System.out.println("    getX()     " + v.getX());
        System.out.println("    getY()     " + v.getY());
        System.out.println("    getPhi()   " + v.getPhi());
        System.out.println("    getRho()   " + v.getRho());
    }

    private static void getterVectors() {
        section("ImmutableVectorGetters");
        ImmutableVectorGetters v1 = new ImmutableVectorGetters(1, 3);
        ImmutableVectorGetters v2 = new ImmutableVectorGetters(2, 1);
        ImmutableVectorGetters sum = v1.add(v2);
        ImmutableVectorGetters difference = v1.subtract(v2);
        dump("v1", v1);
        dump("v2", v2);
        dump("sum", sum);
        dump("difference", difference);
    }

    private static void dump(final String name, ImmutableVectorGetters v) {
        System.out.println(name);
        System.out.println("    toString() " + v);
        System.out.println("    getX()     " + v.getX());
        System.out.println("    getY()     " + v.getY());
        System.out.println("    getPhi()   " + v.getPhi());
        System.out.println("    getRho()   " + v.getRho());
    }

    private static void polarVectors() {
        section("ImmutableVectorPolar");
        ImmutableVectorPolar v1 = new ImmutableVectorPolar(1, 3);
        ImmutableVectorPolar v2 = new ImmutableVectorPolar(2, 1);
        ImmutableVectorPolar sum = v1.add(v2);
        ImmutableVectorPolar difference = v1.subtract(v2);
        dump("v1", v1);
        dump("v2", v2);
        dump("sum", sum);
        dump("difference", difference);
    }

    private static void dump(final String name, ImmutableVectorPolar v) {
        System.out.println(name);
        System.out.println("    toString() " + v);
        System.out.println("    getX()     " + v.getX());
        System.out.println("    getY()     " + v.getY());
        System.out.println("    getPhi()   " + v.getPhi());
        System.out.println("    getRho()   " + v.getRho());
    }


    private static void circles() {
        chapter("Circles");
        immutableCircle();
        mutableCircle();
    }

    private static void immutableCircle() {
        section("ImmutableCircle");

        ImmutableCircle circle = new ImmutableCircle(new ImmutableVector(1, 3), 1);
        System.out.println("Original " + circle);

        ImmutableCircle shifted = circle.shift(new ImmutableVector(3, 0));
        System.out.println("Shifted  " + shifted);
    }

    private static void mutableCircle() {
        section("MutableCircle");

        MutableCircle circle = new MutableCircle(new MutableVector(1, 3), 1);
        System.out.println("Original " + circle);

        MutableCircle shifted = new MutableCircle(circle);
        shifted.shift(new MutableVector(3, 0));
        System.out.println("Shifted  " + shifted);
    }

    private static void bank() {
        chapter("Bank");

        Client c = new Client("123456");
        Account a1 = new Account(c);
        Account a2 = new Account(c);
        // c.passport = "000000"; // No access to private fields
        a2.deposit(1000);
        a1.transfer(a2, 100);
        // a1.setAmount(10); // No access to private methods
        System.out.println(a1.getAmount());
        System.out.println(a1.getClient().getPassport());
        System.out.println(a1.getAmount());
        System.out.println(a2.getAmount());
    }

    public static void section(String name) {
        System.out.println();
        System.out.println("--- " + name);
    }

    public static void chapter(String name) {
        String delimiter = name.replaceAll(".", "=");
        System.out.println();
        System.out.println(delimiter);
        System.out.println(name);
        System.out.println(delimiter);
    }
}
