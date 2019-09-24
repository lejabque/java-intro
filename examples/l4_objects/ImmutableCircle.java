/**
 * Immutable circle with {@link ImmutableVector} as center.
 * Compare to {@link MutableCircle}.
 *
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public class ImmutableCircle {
    private final ImmutableVector center;
    private final double radius;

    public ImmutableCircle(final ImmutableVector center, final double radius) {
        this.center = center;
        this.radius = radius;
    }

    public ImmutableVector getCenter() {
        return center;
    }

    public double getRadius() {
        return radius;
    }

    public ImmutableCircle shift(ImmutableVector v) {
        return new ImmutableCircle(center.add(v), radius);
    }

    @Override
    public String toString() {
        return "ImmutableCircle(" + getCenter() + ", " + getRadius() + ")";
    }
}
