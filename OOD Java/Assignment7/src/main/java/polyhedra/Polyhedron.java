package polyhedra;

import java.util.Scanner;

/**
 * Abstract Polyhedron Base Class.
 */
public abstract class Polyhedron implements Cloneable {
    /**
     * A string representing the name of this polyhedron.
     */
    private String type;

    /**
     * Box (rectangular prism) that contains this element.
     */
    protected BoundingBox boundingBox;

    /**
     * Default Constructor.
     */
    public Polyhedron()
    {
        this("Polyhedron");
    }

    /**
     * Constructor which allows a name to be set.
     *
     * @param theType literal string representing the polyhedron name
     */
    public Polyhedron(String theType)
    {
        this.type        = theType;
        this.boundingBox = new BoundingBox();
    }

    /**
     * Retrieve the polyhedron name.
     *
     * @return name
     */
    public String getType()
    {
        return this.type;
    }

    /**
     * Change the polyhedron name.
     *
     * @param theType literal string representing the polyhedron name
     */
    public void setType(String theType)
    {
        this.type = theType;
    }

    /**
     * Retrieve the bounding box.
     *
     * @return current bounding box
     */
    public BoundingBox getBoundingBox()
    {
        return this.boundingBox;
    }

    /**
     * Retrieve and reconstruct the polyhedron from an input scanner.
     *
     * @param s scanner to use
     */
    public abstract void read(Scanner s);

    /**
     * Apply a geometric scaling operation.
     *
     * @param scalingFactor scaling factor that is grather than or equal to 1
     */
    public abstract void scale(double scalingFactor);

    /**
     * Clone a Polyhedron and minimize casting.
     *
     * @return identical (deep) copy of current polyhedron
     */
    public abstract Polyhedron clone();

    @Override
    public String toString()
    {
        return "[" + type + "] " + boundingBox + "->";
    }

    /**
     * A helper function to clone and scale a Polyhedron
     *
     * @param original source Polyhedron
     * @param sFactor scaling factor
     *
     * @return scaled copy of the original polyhedron
     */
    public static Polyhedron cloneAndScale(final Polyhedron original,
                                           final double sFactor)
    {
        Polyhedron copy = original.clone();
        copy.scale(sFactor);

        return copy;
    }
}

