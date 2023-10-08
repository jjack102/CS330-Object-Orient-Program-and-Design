package polyhedra;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

import java.util.Scanner;

import java.util.List;
import java.util.ArrayList;

import static java.util.stream.Collectors.toList;

/**
 * Wrapper around the main function.
 */
public final class CreatePolyhedra {
    /**
     * The 60 dash dividing line.
     */
    private static final String DASH_DIVIDER = createDivider('-', 60);

    /**
     * Program Usage Message for display when the program is invoked without
     * command line arguments.
     */
    private static final String USAGE_MSG =
        "Usage: java -jar CreatePolyhedra.jar inputFile scalingFactor";

    /**
     * This is the main function.
     *
     * @param args[0] polyhedron input file
     * @param args[1] scaling factor
     */
    public static void main(String[] args)
    {
        if (args.length < 2) {
            System.err.println(USAGE_MSG);
            System.exit(1);
        }

        double scalingFactor = 1;

        try {
            scalingFactor = Double.parseDouble(args[1]);
        }
        catch (NumberFormatException e) {
            System.err.println("Scaling Factor must be a positive number");
            System.exit(1);
        }

        if (scalingFactor < 1) {
            System.err.println("Scaling Factor must be >= 1");
            System.exit(1);
        }

        BufferedReader polyIn = null;

        try {
            polyIn = new BufferedReader(new FileReader(args[0]));
        }
        catch (FileNotFoundException e) {
            System.err.printf("Could not locate %s%n", args[0]);
            System.exit(2);
        }

        //----------------------------------------------------------------------
        List<Polyhedron> polyhedra = readPolyhedra(polyIn);
        List<Polyhedron> scaledCopies = duplicateAndScale(polyhedra, scalingFactor);

        //----------------------------------------------------------------------
        printPolyhedra(polyhedra, "Original Polyhedra");
        System.out.println();
        System.out.println();
        printPolyhedra(scaledCopies, "Scaled Polyhedra (Clones)");
    }

    /**
     * Construct a list of polyhedra from an input stream.
     *
     * @param inBuffer input source
     *
     * @return list of Polyhedra
     */
    public static List<Polyhedron> readPolyhedra(BufferedReader inBuffer)
    {
        List<Polyhedron> polyhedra = new ArrayList<>();
        Scanner          inf       = new Scanner(inBuffer);

        while (inf.hasNext()) {
            Polyhedron poly = PolyhedronFactory.createAndRead(inf);

            if (poly != null) {
                polyhedra.add(poly);
            }
        }

        return polyhedra;
    }

    /**
     * Print a collection of polyhedra.
     *
     * @param polyhedra polyhedron collection to print
     * @param heading title to use
     */
    public static void printPolyhedra(final List<Polyhedron> polyhedra,
                                      final String heading)
    {
        System.out.println(heading);
        System.out.println(DASH_DIVIDER);

        for (Polyhedron poly : polyhedra) {
            System.out.println(poly);
        }
    }

    /**
     * Copy each polyhedron and apply the scaling factor to each copy.
     *
     * @param polyhedra original polyhedron collection
     * @param sFactor scaling factor
     *
     * @return list of copied and scaled Polyhedra
     */
    public static List<Polyhedron> duplicateAndScale(final List<Polyhedron> polyhedra,
                                                     final double sFactor)
    {
        List<Polyhedron> scaledCopies = polyhedra.stream()
                                      .map(poly -> Polyhedron.cloneAndScale(poly, sFactor))
                                      .collect(toList());

        return scaledCopies;
    }

    //--------------------------------------------------------------------------
    /**
     * Generate a horizontal line.
     *
     * @param lineChar character that will comprise the line
     * @param width horizontal length of the line (left to right)
     *
     * @return horizontal line as a string
     */
    public static String createDivider(char lineChar, int width)
    {
        return String.format("%0" + width + "d", 0).replace("0", "" + lineChar);
    }
}
