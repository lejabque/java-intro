/**
 * Immutable Cartesian vector. Compare to {@link MutableVector}.
 *
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public class ImmutableVector {
    public final double x;
    public final double y;

    public ImmutableVector(final double x, final double y) {
        this.x = x;
        this.y = y;
    }

    public double getPhi() {
        return Math.atan2(y, x);
    }

    public double getRho() {
        return Math.hypot(x, y);
    }

    public ImmutableVector add(ImmutableVector v) {
        return new ImmutableVector(x + v.x, y + v.y);
    }

    public ImmutableVector subtract(ImmutableVector v) {
        return new ImmutableVector(x - v.x, y - v.y);
    }

    @Override
    public String toString() {
        return "ImmutableVector(x = " + x + ", y = " + y + ")";
    }
}
