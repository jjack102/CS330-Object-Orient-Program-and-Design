package polyhedra;

/**
 * Coordinate in 3 dimensional Cartesian space.
 */
public class Point implements Cloneable {
    /**
     * Default tolerance for point component equality.
     */
    public static final double EQ_THRESHOLD = 0.01;

    public double x;
    public double y;
    public double z;

    /**
     * Compare two points for equality within a specified threshold.
     *
     * @param lhs first floating point number
     * @param rhs second floating point number
     * @param threshold amount by which two numbers are allowed to differ and
     *     still be considered equal
     *
     * @return true if the x, y, and z components of both points are equal
     *     within the specified threshold.
     */
    public static boolean equalWithinThreshold(final double lhs,
                                               final double rhs,
                                               final double threshold)
    {
        return Math.abs(rhs - lhs) <= threshold;
    }

    /**
     * Default Point Constructor.
     */
    public Point()
    {
        this(0, 0, 0);
    }

    /**
     * Construct a Point from specified x, y, and z values.
     *
     * @param dx desired x value
     * @param dy desired y value
     * @param dz desired z value
     */
    public Point(final double dx, final double dy, final double dz)
    {
        this.x = dx;
        this.y = dy;
        this.z = dz;
    }

    /**
     * Apply geometric scaling function.
     *
     * @param scalingFactor the scaling factor
     */
    public void scale(final double scalingFactor)
    {
        x *= scalingFactor;
        y *= scalingFactor;
        z *= scalingFactor;
    }

    @Override
    public boolean equals(Object rhs)
    {
        if (!(rhs instanceof Point)) {
            return false;
        }

        final Point rhsP = (Point) rhs;

        if (!equalWithinThreshold(this.x, rhsP.x, EQ_THRESHOLD)) {
            return false;
        }

        if (!equalWithinThreshold(this.y, rhsP.y, EQ_THRESHOLD)) {
            return false;
        }

        return equalWithinThreshold(this.z, rhsP.z, EQ_THRESHOLD);
    }

    @Override
    public int hashCode()
    {
        return (int) x
             + (int) y
             + (int) z;
    }

    @Override
    public Point clone()
    {
        return new Point(this.x, this.y, this.z);
    }

    @Override
    public String toString()
    {
        return "(" + x + ", " + y + ", " + z + ")";
    }
}
