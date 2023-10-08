package polyhedra.io;

import java.io.BufferedReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import polyhedra.Polyhedron;
import polyhedra.Sphere;
import polyhedra.Cylinder;
import polyhedra.Composite;


/**
 *
 * This is technically an Integration Test
 *
 * 1 - Does this piece of code perform the operations
 *     it was designed to perform?
 *
 * 2 - Does this piece of code do something it was not
 *     designed to perform?
 *
 * 1 Test per mutator
 */
@TestMethodOrder(MethodOrderer.MethodName.class)
public class TestPolyhedronReader {

    /**
     * Test input that includes only simple shapes.
     */
    public static final String simpleInput = "sphere 1\n"
                                           + "cylinder 2 1\n"
                                           + "sphere 4\n"
                                           + "cylinder 2 3\n";

    /**
     * Test input that includes only simple shapes and multiple interleaved
     * empty lines (i.e., completely empty or just spaces).
     */
    public static final String simpleInputWithEmpty = "sphere 1\n"
                                                    + "cylinder 2 1\n"
                                                    + "\n"
                                                    + "sphere 4\n"
                                                    + "  \n"
                                                    + "\n"
                                                    + " \n"
                                                    + "cylinder 2 3\n"
                                                    + " \n";

    /**
     * Test input that includes simple and complex shapes.
     */
    public static final String complexInput = "cylinder 2 3\n"
                                            + "composite 3\n"
                                            + "    sphere 3\n"
                                            + "    sphere 5\n"
                                            + "    sphere 7\n"
                                            + "composite 2\n"
                                            + "    cylinder 1 2\n"
                                            + "    sphere 5\n"
                                            + "sphere 3\n";

    /**
     * Test input that includes simple and complex shapes, where the complex
     * shape counts are correct, but include empty lines (i.e., completely
     * empty or just spaces).
     */
    public static final String complexInputWithEmpty = "cylinder 2 3\n"
                                                     + "composite 3\n"
                                                     + "\n"
                                                     + "    sphere 3\n"
                                                     + "    sphere 5\n"
                                                     + "    sphere 7\n"
                                                     + "  \n"
                                                     + "composite 2\n"
                                                     + "    cylinder 1 2\n"
                                                     + "  \n"
                                                     + "    sphere 5\n"
                                                     + "sphere 3\n";

    /**
     * Polyhedra objects corresponding to simple input.
     */
    List<Polyhedron> simpleList;

    /**
     * Polyhedra objects corresponding to simple &amp; complex input.
     */
    List<Polyhedron> complexList;

    @BeforeEach
    public void setUp()
    {
        simpleList = new ArrayList<>();

        simpleList.add(new Sphere(1));
        simpleList.add(new Cylinder(1, 2));
        simpleList.add(new Sphere(4));
        simpleList.add(new Cylinder(3, 2));

        complexList = new ArrayList<>();

        complexList.add(new Cylinder(3, 2));

        Composite tmpCmp = new Composite();
        tmpCmp.add(new Sphere(3));
        tmpCmp.add(new Sphere(5));
        tmpCmp.add(new Sphere(7));
        complexList.add(tmpCmp);

        tmpCmp = new Composite();
        tmpCmp.add(new Cylinder(2, 1));
        tmpCmp.add(new Sphere(5));
        complexList.add(tmpCmp);

        complexList.add(new Sphere(3));
    }

    @Test
    public void testReadInvalidPolyhedra()
    {
        BufferedReader aReader = new BufferedReader(new StringReader("InvalidName\n"));
        PolyhedronReader aPolyReader = new PolyhedronReader(aReader);

        List<Polyhedron> actualList = aPolyReader.readAll();

        assertThat(actualList.size(), equalTo(0));
    }

    @Test
    public void testReadSimplePolyhedra()
    {
        BufferedReader aReader = new BufferedReader(new StringReader(simpleInput));
        PolyhedronReader aPolyReader = new PolyhedronReader(aReader);

        List<Polyhedron> actualList = aPolyReader.readAll();

        // List.equals calls equal on each pair of items. Ordering is enforced.
        assertThat(actualList, equalTo(simpleList));
    }

    @Test
    public void testReadSimplePolyhedraWithEmptyLines()
    {
        BufferedReader aReader = new BufferedReader(new StringReader(simpleInputWithEmpty));
        PolyhedronReader aPolyReader = new PolyhedronReader(aReader);

        List<Polyhedron> actualList = aPolyReader.readAll();

        // List.equals calls equal on each pair of items. Ordering is enforced.
        assertThat(actualList, equalTo(simpleList));
    }

    @Test
    public void testReadComplexPolyhedra()
    {
        BufferedReader aReader = new BufferedReader(new StringReader(complexInput));
        PolyhedronReader aPolyReader = new PolyhedronReader(aReader);

        List<Polyhedron> actualList = aPolyReader.readAll();

        // List.equals calls equal on each pair of items. Ordering is enforced.
        assertThat(actualList, equalTo(complexList));
    }

    @Test
    public void testReadComplexPolyhedraWithEmptyLines()
    {
        BufferedReader aReader = new BufferedReader(new StringReader(complexInputWithEmpty));
        PolyhedronReader aPolyReader = new PolyhedronReader(aReader);

        List<Polyhedron> actualList = aPolyReader.readAll();

        // List.equals calls equal on each pair of items. Ordering is enforced.
        assertThat(actualList, equalTo(complexList));
    }
}

