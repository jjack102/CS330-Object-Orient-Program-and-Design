package polyhedra;

/**
 * Polyhedron representing a sphere.
 */
public class Sphere
    implements Polyhedron, TraitFromDimensions
{
    /**
     * Radius of the sphere. It must be > 0.
     */
    private double radius;

    /**
     * Default Constructor - set the radius to 1.
     */
    public Sphere()
    {
        this(1);
    }

    /**
     * Construct a sphere from a provided radius.
     *
     * @param r desired radius
     */
    public Sphere(double r)
    {
        this.radius = r;
    }

    @Override
    public String getType()
    {
        return "Sphere";
    }

    @Override
    public BoundingBox getBoundingBox()
    {
        final double d = this.getDiameter();

        return new BoundingBox(d, d, d);
    }

    /**
     * Retrieve the radius.
     *
     * @return current radius
     */
    public double getRadius()
    {
        return this.radius;
    }

    /**
     * Update the radius.
     *
     * @param r desired radius
     */
    public void setRadius(double r)
    {
        this.radius = r;
    }

    /**
     * Compute diameter.
     *
     * @return diameter
     */
    public final double getDiameter()
    {
        return 2 * this.radius;
    }

    @Override
    public void scale(double scalingFactor)
    {
        this.radius *= scalingFactor;
    }

    @Override
    public Polyhedron clone()
    {
        return new Sphere(this.radius);
    }

    @Override
    public boolean equals(Object rhs)
    {
        if (!(rhs instanceof Sphere)) {
            return false;
        }

        Sphere rhsSphere = (Sphere) rhs;

        return Point.equalWithinDftThreshold(this.radius, rhsSphere.radius);
    }

    @Override
    public int hashCode()
    {
        return this.getType().hashCode()
             + Double.valueOf(this.radius).hashCode();
    }

    @Override
    public String toString()
    {
        return Polyhedron.super.toStringHelper()
             + "Radius: " + this.radius
             + " "
             + "Diameter: " + this.getDiameter();
    }

    // TraitFromDimensions Overrides
    @Override
    public int numberOfDimensions()
    {
        return 1;
    }

    @Override
    public void fromDimensions(double[] theDims)
    {
        this.radius = theDims[0];
    }
}
