/**
 * Mutable Cartesian vector. Compare to {@link ImmutableVector}
 *
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public class MutableVector {
    private double x;
    private double y;

    public MutableVector(final double x, final double y) {
        this.x = x;
        this.y = y;
    }

    public MutableVector(final MutableVector v) {
        x = v.x;
        y = v.y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getPhi() {
        return Math.atan2(y, x);
    }

    public double getRho() {
        return Math.hypot(x, y);
    }

    public MutableVector copy() {
        return new MutableVector(this);
    }

    public void add(final MutableVector v) {
        x += v.x;
        y += v.y;
    }

    public void subtract(final MutableVector v) {
        x -= v.x;
        y -= v.y;
    }

    @Override
    public String toString() {
        return "MutableVector(x = " + x + ", y = " + y + ")";
    }
}
