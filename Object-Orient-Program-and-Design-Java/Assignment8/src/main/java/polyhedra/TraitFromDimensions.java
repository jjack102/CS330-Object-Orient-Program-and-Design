package polyhedra;

/**
 * This interface is based on the Rust Trait mechanic. Anything that implements
 * this trait can be created from an array of primitive double values.
 */
public interface TraitFromDimensions
{
    /**
     * Number of dimensions needed to initialize an object (e.g., 1 for a
     * Sphere).
     *
     * @return number of required double values
     */
    int numberOfDimensions();

    /**
     * Take an array of size {@code this.numberOfDimensions} and re-set all
     * dimensions.
     *
     * @param theDims array of double values
     */
    void fromDimensions(double[] theDims);
}
