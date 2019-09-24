/**
 * Immutable polart vector. Compare to {@link ImmutableVectorGetters}.
 *
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public class ImmutableVectorPolar {
    private final double phi;
    private final double rho;

    public ImmutableVectorPolar(final double x, final double y) {
        phi = Math.atan2(y, x);
        rho = Math.sqrt(x * x + y * y);
    }

    public double getX() {
        return rho * Math.cos(phi);
    }

    public double getY() {
        return rho * Math.sin(phi);
    }

    public double getPhi() {
        return Math.atan2(getY(), getX());
    }

    public double getRho() {
        return Math.sqrt(getX() * getX() + getY() * getY());
    }

    public ImmutableVectorPolar add(ImmutableVectorPolar v) {
        return new ImmutableVectorPolar(getX() + v.getX(), getY() + v.getY());
    }

    public ImmutableVectorPolar subtract(ImmutableVectorPolar v) {
        return new ImmutableVectorPolar(getX() - v.getX(), getY() - v.getY());
    }

    @Override
    public String toString() {
        return "ImmutableVectorPolar(x = " + getX() + ", y = " + getY() + ")";
    }
}
