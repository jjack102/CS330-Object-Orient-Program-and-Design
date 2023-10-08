package polyhedra;

import java.util.Scanner;

/**
 * Polyhedron representing a sphere.
 */
public class Sphere extends Polyhedron {
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
        super("Sphere");
        this.radius = r;

        double d = this.getDiameter();
        this.boundingBox.setUpperRightVertex(d, d, d);
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

        double d = getDiameter();
        boundingBox.setUpperRightVertex(d, d, d);
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
    public void read(Scanner scanner)
    {
        this.radius = scanner.nextDouble();

        double d = this.getDiameter();
        boundingBox.setUpperRightVertex(d, d, d);
    }

    @Override
    public void scale(double scalingFactor)
    {
        this.radius *= scalingFactor;
        this.boundingBox.scale(scalingFactor);
    }

    @Override
    public Polyhedron clone()
    {
        return new Sphere(this.radius);
    }

    @Override
    public String toString()
    {
        return super.toString()
             + "Radius: " + this.radius
             + " "
             + "Diameter: " + this.getDiameter();
    }
}
