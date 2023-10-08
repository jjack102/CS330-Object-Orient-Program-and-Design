package polyhedra.io;

import java.io.BufferedReader;
import java.util.Scanner;

import java.util.List;
import java.util.ArrayList;

import polyhedra.Composite;
import polyhedra.Polyhedron;
import polyhedra.PolyhedronFactory;
import polyhedra.TraitFromDimensions;

/**
 * The PolyhedronReader class handles all high level Polyhedron input logic.
 */
public final class PolyhedronReader
{
    /**
     * A buffer referencing the source of polyhedron input.
     */
    private BufferedReader theBuffer;

    /**
     * Create a PolyhedronReader using an existing buffer. The buffer is
     * consumed in read operations. Concurrent buffer reads, from outside a
     * PolyhedronReader, should be avoided.
     *
     * @param aBuffer source buffer
     */
    public PolyhedronReader(BufferedReader aBuffer)
    {
        this.theBuffer = aBuffer;
    }

    /**
     * Create and return a new Polyhedron. This is an object that is one of the
     * three subtypes, Sphere, Cylinder, or Composite
     *
     * @param snr source from which a polyhedron will be read
     *
     * @return polyhedron object, or null if an "invalid" polyhedron was read
     *
     * @TODO add logic to handle malformed recursive sub-compositeobjects
     */
    private Polyhedron createAndRead(final Scanner snr)
    {
        Polyhedron ply = null;

        if (snr.hasNext()) {
            final String polyhedronType = snr.next();

            ply = PolyhedronFactory.createPolyhedron(polyhedronType);

            if (ply != null) {
                if (ply.isSimple()) {
                    TraitFromDimensions dimPoly = (TraitFromDimensions) ply;
                    ply = initFromDimensions(dimPoly, snr);
                }
                else {
                    Composite cmp = (Composite) ply;

                    ply = this.readMultiple(snr, cmp);
                }
            }
            else {
                snr.nextLine();

                if (snr.hasNext()) {
                    snr.nextLine();
                }
            }
        }

        return ply;
    }

    /**
     * Initialize a polyhedron from an array of double values.
     *
     * @param dimPoly a polyhedron cast into TraitFromDimensions
     * @param snr scanner object from which to read
     *
     * @return initialized Polyhedron object
     */
    private Polyhedron initFromDimensions(TraitFromDimensions dimPoly,
                                          Scanner snr)
    {
        double[] dims = new double[dimPoly.numberOfDimensions()];

        for (int i = 0; i < dims.length; i++) {
            dims[i] = snr.nextDouble();
        }

        dimPoly.fromDimensions(dims);

        return (Polyhedron) dimPoly;
    }

    /**
     * Read all component polyhedra.
     *
     * @param poly intitialized (and empty) composite object
     *
     * @param scanner input source
     *
     * @return intitialized Composite object downcast as a Polyhedron
     */
    private Polyhedron readMultiple(Scanner scanner, Composite poly)
    {
        int numPolyhedra = scanner.nextInt();

        for (int i = 0; i < numPolyhedra; i++) {
            Polyhedron innerPoly = this.createAndRead(scanner);

            poly.add(innerPoly);
        }

        return poly;
    }

    /**
     * Read Polyhedron objects from the input buffer until the buffer is empty
     * (i.e., exhausted).
     *
     * @return list of Polyhedra
     */
    public List<Polyhedron> readAll()
    {
        List<Polyhedron> polyhedra = new ArrayList<>();
        Scanner          inf       = new Scanner(theBuffer);

        while (inf.hasNext()) {
            Polyhedron poly = this.createAndRead(inf);

            if (poly != null) {
                polyhedra.add(poly);
            }
        }

        return polyhedra;
    }
}
