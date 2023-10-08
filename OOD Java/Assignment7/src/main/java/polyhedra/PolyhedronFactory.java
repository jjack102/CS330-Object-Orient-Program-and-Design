package polyhedra;

import java.util.Scanner;

/**
 * This is the Polyhedron Creating Wizard.
 */
public final class PolyhedronFactory {

    /**
     * List of known Polyhedra.
     */
    private static Polyhedron[] knownPolyhedra = {
        new Sphere(),
        new Cylinder(),
        new Composite()
    };

    /**
     *  Create a Polyhedron.
     *
     *  @param name the Polyhedron to be created
     *
     *  @return A Polyhedron with the specified name
     *      or null if no matching Polyhedron is found
     */
    public static Polyhedron createPolyhedron(final String name)
    {
        for (Polyhedron polyModel : knownPolyhedra) {
            // if (polyModel.getType().toLowerCase().equals(name)) {
            if (polyModel.getType().equalsIgnoreCase(name)) {
                return polyModel.clone();
            }
        }

        return null;
    }

    /**
     *  Determine whether a given Polyhedron is known.
     *
     *  @param name the Polyhedron for which to query
     *
     *  @return true if name is a known polyhedron
     */
    public static boolean isKnown(final String name)
    {
        for (Polyhedron polyModel : knownPolyhedra) {
            // if (polyModel.getType().toLowerCase().equals(name)) {
            if (polyModel.getType().equalsIgnoreCase(name)) {
                return true;
            }
        }

        return false;
    }

    /**
     *  Print a list of known Polyhedra.
     *
     *  @return string containing the names of all known polyhedra, one per
     *  line
     */
    public static String listKnown()
    {
        StringBuilder bld = new StringBuilder();

        for (Polyhedron polyModel : knownPolyhedra) {
            bld.append("  " + polyModel + "\n");
        }

        return bld.toString();
    }

    /**
     *  Determine the number of known Polyhedra.
     *
     *  @return the number of known Polyhedra
     */
    public static int numberKnown()
    {
        return knownPolyhedra.length;
    }

    /**
     * Create and return a new Polyhedron. This is an object that is one of the
     * three subtypes, Sphere, Cylinder, or Composite
     *
     * @param snr source from which a polyhedron will be read
     *
     * @return polyhedron object, or null if an "invalid" polyhedron was read
     */
    public static Polyhedron createAndRead(final Scanner snr)
    {
        Polyhedron ply = null;

        if (snr.hasNext()) {
            final String polyhedronType = snr.next();

            ply = PolyhedronFactory.createPolyhedron(polyhedronType);

            if (ply != null) {
                ply.read(snr);
            }
            else {
                snr.nextLine();
                snr.nextLine();
            }
        }

        return ply;
    }

    //--------------------------------------------------------------------------
    /**
     * Do not allow PolyhedronFactory objects to be created.
     */
    private PolyhedronFactory()
    {
    }
}
