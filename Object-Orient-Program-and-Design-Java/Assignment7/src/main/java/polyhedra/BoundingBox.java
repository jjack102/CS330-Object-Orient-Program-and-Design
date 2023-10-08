package polyhedra;

/**
 * Rectangular prism representing the boundaries
 * x, y, and z of a polyhedron.
 */
public class BoundingBox implements Cloneable {
    /**
     * Lower boundary. In this exercise, it is
     * fixed at (0,0,0)
     */
    private Point lowerLeftVertex;

    /**
     * Upper boundary (max(x), max(y), max(z))).
     */
    private Point upperRightVertex;

    /**
     * Default Constructor. Set both vertices to (0, 0, 0)
     */
    public BoundingBox()
    {
        this(new Point(0, 0, 0), new Point(0, 0, 0));
    }

    /**
     * Construct a bounding box from lower and upper
     * points that define it.
     *
     * @param lowerLeft point representing min(x), min(y), min(z)
     * @param upperRight point representing max(x), max(y), max(z)
     */
    public BoundingBox(Point lowerLeft, Point upperRight)
    {
        lowerLeftVertex  = lowerLeft;
        upperRightVertex = upperRight;
    }

    /**
     * Retrieve the lower boundary.
     *
     * @return lower boundary Point(min(x), min(y), min(z))
     */
    public Point getLowerLeftVertex()
    {
        return lowerLeftVertex;
    }

    /**
     * Retrieve the upper boundary.
     *
     * @return lower boundary Point(max(x), max(y), max(z))
     */
    public Point getUpperRightVertex()
    {
        return upperRightVertex;
    }

    /**
     * Set the upper boundary using a Point.
     *
     * @param newPoint point representing max(x), max(y), max(z)
     */
    public void setUpperRightVertex(Point newPoint)
    {
        upperRightVertex = newPoint;
    }

    /**
     * Set the upper boundary.
     *
     * @param x new max(x)
     * @param y new max(y)
     * @param z new max(z)
     */
    public void setUpperRightVertex(double x, double y, double z)
    {
        upperRightVertex.x = x;
        upperRightVertex.y = y;
        upperRightVertex.z = z;
    }

    /**
     * Merge two bounding boxes, taking the
     * largest values for each of x, y, and z.
     *
     * @param other bounding box to merge with `this`
     */
    public void merge(BoundingBox other)
    {
        upperRightVertex.x = Math.max(this.upperRightVertex.x,
                                      other.upperRightVertex.x);

        upperRightVertex.y = Math.max(this.upperRightVertex.y,
                                      other.upperRightVertex.y);

        upperRightVertex.z = Math.max(this.upperRightVertex.z,
                                      other.upperRightVertex.z);
    }

    /**
     * Apply a scaling factor.
     *
     * @param scalingFactor scaling factor that is greater than or equal to 1
     */
    public void scale(double scalingFactor)
    {
        upperRightVertex.scale(scalingFactor);
    }

    @Override
    public BoundingBox clone()
    {
        return new BoundingBox(lowerLeftVertex.clone(),
                               upperRightVertex.clone());
    }

    @Override
    public String toString()
    {
        return this.upperRightVertex.toString();
    }
}
