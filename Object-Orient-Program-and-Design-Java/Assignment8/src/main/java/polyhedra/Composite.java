package polyhedra;

import java.util.List;
import java.util.Vector;
import java.util.Iterator;

/**
 * A Composite Polyhedron. Polyhedra of this type are built from
 * other Polyhedra. This,in theory, can include Composite objects
 * composed of other (nested) Composite objects.
 *
 */
public class Composite
    implements Polyhedron, Iterable<Polyhedron>
{
    /**
     * Cached (i.e., precomputed and updated) BoundingBox.
     *
     * Computing the boundingbox for a Composite Polyhedron is non-trivial
     * (i.e., requires a loop and has O(n) temperoral complexity).
     */
    private BoundingBox theBox;

    /**
     * Collection of polyhedra of which this composite polyhedron is composed.
     */
    private List<Polyhedron> allPolyhedra;

    /**
     * Default Composite Constructor.
     */
    public Composite()
    {
        this.allPolyhedra = new Vector<Polyhedron>();
        this.theBox = new BoundingBox();
    }

    @Override
    public String getType()
    {
        // Replace the return line
        return "Composite";
    }

    @Override
    public BoundingBox getBoundingBox()
    {
        return theBox;
    }

    /**
     * Add a cloned copy of a Polyhedron to the `Composite` collection.
     *
     * @param toAdd is cloned and the copy is added
     */
    public void add(Polyhedron toAdd)
    {
        // Write this function.
        allPolyhedra.add(toAdd.clone());
        theBox.merge(toAdd.getBoundingBox());
    }

    /**
     * Iterate through all sub-polyhedra, scale them, and update all
     * bounding boxes.
     *
     * @param scalingFactor scaling factor
     */
    public void scale(double scalingFactor)
    {
        // Write this function.
        for(Polyhedron p : this.allPolyhedra) {
            p.scale(scalingFactor);
        }

        theBox.scale(scalingFactor);
    }

    /**
     * Retrive the number of Polyhedra.
     *
     * @return the number of Polyhedra that comprise this Composite object
     */
    public int size()
    {
        return allPolyhedra.size();
    }

    @Override
    public Iterator<Polyhedron> iterator()
    {
        return allPolyhedra.iterator();
    }

    @Override
    public Polyhedron clone()
    {
        Composite aCopy = new Composite();

        // A loop might be helpful to 'add' each entry from this.allPolyhedra
        for(Polyhedron poly : this.allPolyhedra){
            aCopy.add(poly);
        }

        return aCopy;
    }

    @Override
    public boolean equals(Object rhs)
    {
        if (!(rhs instanceof Composite)) {
            return false;
        }

        Composite rhsComp = (Composite) rhs;

        // List.equals compares each pair in order (magic)!
        return this.allPolyhedra.equals(rhsComp.allPolyhedra);
    }

    @Override
    public int hashCode()
    {
        return this.getType().hashCode()
             + this.allPolyhedra.hashCode();
    }

    /**
     * "Print" all polyhedra.
     *
     * @return String containing all component _Polyhedra_ objects
     */
    @Override
    public String toString()
    {
        StringBuilder bld = new StringBuilder();

        bld.append(Polyhedron.super.toStringHelper());
        bld.append(String.format("%d polyhedra%n", this.size()));

        // Maybe a loop can help...
        for(Polyhedron poly : this.allPolyhedra) {
            bld.append("  " + poly + "\n");
        }

        return bld.toString();
    }

    //--------------------------------------------------------------------------
    @Override
    public boolean isComplex()
    {
        // Is the return corrrect?
        return true;
    }

    @Override
    public boolean isSimple()
    {
        // Is the return corrrect?
        return false;
    }
}

