package polyhedra;

/**
 * Polyhedron Interface.
 * <p>
 * This should really be split into multiple interfaces using bounded generics.
 */
public interface Polyhedron extends Cloneable
{
    /**
     * Retrieve the polyhedron name.
     *
     * @return name
     */
    String getType();

    /**
     * Retrieve the bounding box.
     *
     * @return current bounding box
     */
    BoundingBox getBoundingBox();

    /**
     * Apply a geometric scaling operation.
     *
     * @param scalingFactor scaling factor that is greater than or equal to 1
     */
    void scale(double scalingFactor);

    /**
     * Clone a Polyhedron and minimize casting.
     *
     * @return identical (deep) copy of current polyhedron
     */
    Polyhedron clone();

    /**
     * A helper function to clone and scale a Polyhedron.
     *
     * @param sFactor scaling factor
     *
     * @return scaled copy of the original polyhedron
     */
    default Polyhedron cloneAndScale(final double sFactor)
    {
        Polyhedron copy = this.clone();
        copy.scale(sFactor);

        return copy;
    }

    /**
     * This is a helper function to generate the String representation of
     * the type and bounding box.
     *
     * @return type-bounding-box string in the form
     *     {@code "[{type:}] -> {boundingBox:}"}
     */
    default String toStringHelper()
    {
        return String.format("[%s] %s -> ",
                             this.getType(),
                             this.getBoundingBox());
    }

    /**
     * Specify whether a Polyhedron is simple or complex (i.e., defined by a
     * combination of sub-polyhedra).
     *
     * @return true if the polyhedron is complex, and false if the polyhedron
     *     is simple.
     *
     */
    default boolean isComplex()
    {
        return false;
    }

    /**
     * Specify whether a Polyhedron is simple or complex (i.e., defined by a
     * combination of sub-polyhedra).
     *
     * @return false if the polyhedron is complex, and true if the polyhedron
     *     is simple.
     *
     */
    default boolean isSimple()
    {
        return true;
    }
}

