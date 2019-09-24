/**
 * Immutable circle with {@link MutableVector} as center.
 * Compare to {@link ImmutableCircle}.
 *
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public class MutableCircle {
    private final MutableVector center;
    private final double radius;

    public MutableCircle(final MutableVector center, final double radius) {
        // We should make a copy to preserve current center location
        this.center = center.copy();
        this.radius = radius;
    }

    /** Copy constructor. */
    public MutableCircle(final MutableCircle circle) {
        // We should make a copy, so client may not move our circle
        this.center = circle.center.copy();
        this.radius = circle.radius;
    }

    public MutableVector getCenter() {
        // We should make a copy, so client may not move our circle
        return center.copy();
    }

    public double getRadius() {
        return radius;
    }

    public void shift(MutableVector v) {
        center.add(v);
    }

    @Override
    public String toString() {
        return "MutableCircle(" + getCenter() + ", " + getRadius() + ")";
    }
}
