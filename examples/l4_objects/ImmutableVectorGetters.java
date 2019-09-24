/**
 * Immutable Cartesian vector.
 * Compare to {@link ImmutableVector} and {@link ImmutableVectorPolar}.
 *
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public class ImmutableVectorGetters {
    private final double x;
    private final double y;

    public ImmutableVectorGetters(final double x, final double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getPhi() {
        return Math.atan2(getY(), getX());
    }

    public double getRho() {
        return Math.sqrt(getX() * getX() + getY() * getY());
    }

    public ImmutableVectorGetters add(ImmutableVectorGetters v) {
        return new ImmutableVectorGetters(getX() + v.getX(), getY() + v.getY());
    }

    public ImmutableVectorGetters subtract(ImmutableVectorGetters v) {
        return new ImmutableVectorGetters(getX() - v.getX(), getY() - v.getY());
    }

    @Override
    public String toString() {
        return "ImmutableVectorGetters(x = " + getX() + ", y = " + getY() + ")";
    }
}
