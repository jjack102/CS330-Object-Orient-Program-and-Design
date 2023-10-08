package polyhedra;

import java.util.Iterator;

import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * 1 - Does this piece of code perform the operations
 *     it was designed to perform?
 *
 * 2 - Does this piece of code do something it was not
 *     designed to perform?
 *
 * 1 Test per mutator
 */
@TestMethodOrder(MethodOrderer.MethodName.class)
public class TestComposite {

    /**
     * Point representing the origin in 3D Cartesian coordinates.
     */
    private static final Point ORIGIN = new Point(0, 0, 0);

    /**
     * Composite object contains no sub-polyhedra (i.e., it is empty).
     */
    private static final Composite EMPTY_POLY = new Composite();

    //--------------------------------------------------------------------------
    // General test variables that appear in multiple tests
    private Sphere sphere;
    private Cylinder cylinder;

    /**
     * A Composite object with a Sphere(radius=2) and a
     * Cylinder(radius=3, height=5).
     */
    private Composite compositeWith2;

    //--------------------------------------------------------------------------
    @BeforeEach
    public void setUp()
    {
        sphere  = new Sphere(2);
        cylinder = new Cylinder(3, 5);

        compositeWith2 = new Composite();
        compositeWith2.add(sphere);
        compositeWith2.add(cylinder);
    }

    @Test
    public void testDefaultConstructor()
    {
        Point lowerPoint = (EMPTY_POLY.getBoundingBox()).getLowerLeftVertex();
        Point upperPoint = (EMPTY_POLY.getBoundingBox()).getUpperRightVertex();

        assertThat(lowerPoint, equalTo(ORIGIN));
        assertThat(upperPoint, equalTo(ORIGIN));

        assertThat(EMPTY_POLY.size(), equalTo(0));

        assertFalse(EMPTY_POLY.iterator().hasNext());

        // Simple and Complex checks
        assertThat(EMPTY_POLY.isSimple(), is(false));
        assertThat(EMPTY_POLY.isComplex(), is(true));

        // I am skipping toString in this test
    }

    @Test
    public void testAdd()
    {
        Composite comp1 = new Composite();

        comp1.add(sphere);
        assertThat(comp1.size(), equalTo(1));
        assertTrue(comp1.iterator().hasNext());

        Point lowerPoint = (comp1.getBoundingBox()).getLowerLeftVertex();
        Point upperPoint = (comp1.getBoundingBox()).getUpperRightVertex();

        assertThat(lowerPoint, equalTo(ORIGIN));
        assertThat(upperPoint, equalTo(new Point(4, 4, 4)));

        comp1.add(cylinder);

        assertThat(comp1.size(), equalTo(2));
        assertTrue(comp1.iterator().hasNext());

        lowerPoint = (comp1.getBoundingBox()).getLowerLeftVertex();
        upperPoint = (comp1.getBoundingBox()).getUpperRightVertex();

        assertThat(lowerPoint, equalTo(ORIGIN));
        assertThat(upperPoint, equalTo(new Point(6, 6, 5)));

        // Simple and Complex checks
        assertThat(comp1.isSimple(), is(false));
        assertThat(comp1.isComplex(), is(true));

        // I am skipping toString in this test. It is covered in
        // testToStringNoScale
    }

    @Test
    public void testClone()
    {
        // Sanity Check Original
        assertThat(compositeWith2.size(), equalTo(2));
        assertTrue(compositeWith2.iterator().hasNext());

        Point lowerPoint = (compositeWith2.getBoundingBox()).getLowerLeftVertex();
        Point upperPoint = (compositeWith2.getBoundingBox()).getUpperRightVertex();

        assertThat(lowerPoint, equalTo(ORIGIN));
        assertThat(upperPoint, equalTo(new Point(6, 6, 5)));

        // Make the copy and check it
        Polyhedron theCopyAsBase = compositeWith2.clone();
        Composite  theCopyAsComp = (Composite) theCopyAsBase;

        assertThat(theCopyAsComp.size(), equalTo(2));
        assertTrue(theCopyAsComp.iterator().hasNext());

        lowerPoint = theCopyAsComp.getBoundingBox().getLowerLeftVertex();
        upperPoint = theCopyAsComp.getBoundingBox().getUpperRightVertex();

        assertThat(lowerPoint, equalTo(ORIGIN));
        assertThat(upperPoint, equalTo(new Point(6, 6, 5)));

        // Technically I should use the iterator to check that I have copies of
        // `sphere` and `cylinder`. However, I do not have a complete
        // interface. There is no _equals_ method!  I will settle for a
        // better-than-nothing test
        Iterator<Polyhedron> it = theCopyAsComp.iterator();

        Sphere itSphere = (Sphere) it.next();
        assertThat(itSphere.getRadius(), closeTo(sphere.getRadius(), 1e-4));

        Cylinder itCylinder = (Cylinder) it.next();
        assertThat(itCylinder.getRadius(), closeTo(cylinder.getRadius(), 1e-4));
        assertThat(itCylinder.getHeight(), closeTo(cylinder.getHeight(), 1e-4));

        // Simple and Complex checks
        assertThat(compositeWith2.isSimple(), is(false));
        assertThat(compositeWith2.isComplex(), is(true));

        assertThat(theCopyAsBase.isSimple(), is(false));
        assertThat(theCopyAsBase.isComplex(), is(true));

        // I am skipping toString in this test
    }

    @Test
    public void testScale()
    {
        // Setup
        final double scalingFactor = 5;

        Sphere scaledSphere = (Sphere) sphere.cloneAndScale(scalingFactor);
        Cylinder scaledCylinder = (Cylinder) cylinder.cloneAndScale(scalingFactor);

        // Start the Test
        compositeWith2.scale(scalingFactor);

        // Bounding box check
        Point lowerPoint = compositeWith2.getBoundingBox().getLowerLeftVertex();
        Point upperPoint = compositeWith2.getBoundingBox().getUpperRightVertex();

        assertThat(lowerPoint, equalTo(ORIGIN));
        assertThat(upperPoint, equalTo(new Point(30, 30, 25)));

        // Sanity Check the number of sub-polyhedra
        assertThat(compositeWith2.size(), equalTo(2));

        // Use the iterator to check that I have the correct `sphere` and
        // `cylinder`. Skip the bounding box checks (those are handled by the
        // Cylinder and Sphere unit test suites).
        Iterator<Polyhedron> it = compositeWith2.iterator();

        Sphere itSphere = (Sphere) it.next();
        assertThat(itSphere.getRadius(), closeTo(scaledSphere.getRadius(), 1e-4));

        Cylinder itCylinder = (Cylinder) it.next();
        assertThat(itCylinder.getRadius(), closeTo(scaledCylinder.getRadius(), 1e-4));
        assertThat(itCylinder.getHeight(), closeTo(scaledCylinder.getHeight(), 1e-4));

        // Simple and Complex checks
        assertThat(compositeWith2.isSimple(), is(false));
        assertThat(compositeWith2.isComplex(), is(true));
    }

    @Test
    public void testToStringNoScale()
    {
        /* Expected Output:
        [Composite] (6, 6, 5) -> 2 polyhedra
          [Sphere] (4, 4, 4) -> Radius: 2 Diameter: 4
          [Cylinder] (6, 6, 5) -> Radius: 3 Height: 5
        */
        String actualOutput = compositeWith2.toString();

        assertThat(actualOutput, containsString("[Composite] (6"));
        assertThat(actualOutput, containsString("(6"));
        assertThat(actualOutput, containsString(", 5"));
        assertThat(actualOutput, containsString(") -> 2 polyhedra"));

        assertThat(actualOutput, containsString("  [Sphere] (4"));
        assertThat(actualOutput, containsString(", 4"));
        assertThat(actualOutput, containsString(" -> Radius: 2"));
        assertThat(actualOutput, containsString(" Diameter: 4"));

        assertThat(actualOutput, containsString("  [Cylinder] (6"));
        assertThat(actualOutput, containsString(", 6"));
        assertThat(actualOutput, containsString("5"));
        assertThat(actualOutput, containsString(") -> Radius: 3"));
        assertThat(actualOutput, containsString(" Height: 5"));
    }

    @Test
    public void testToStringAfterScale()
    {
        Composite compositeWith2 = new Composite();

        compositeWith2.add(sphere);
        compositeWith2.add(cylinder);

        compositeWith2.scale(5);

        /* Expected Output:
        [Composite] (30, 30, 25) -> 2 polyhedra
          [Sphere] (20, 20, 20) -> Radius: 10 Diameter: 20
          [Cylinder] (30, 30, 25) -> Radius: 15 Height: 25
        */
        String actualOutput = compositeWith2.toString();

        assertThat(actualOutput, containsString("[Composite] (3"));
        assertThat(actualOutput, containsString("(30"));
        assertThat(actualOutput, containsString(", 25"));
        assertThat(actualOutput, containsString(") -> 2 polyhedra"));

        assertThat(actualOutput, containsString("  [Sphere] (2"));
        assertThat(actualOutput, containsString(", 20"));
        assertThat(actualOutput, containsString(" -> Radius: 10"));
        assertThat(actualOutput, containsString(" Diameter: 20"));

        assertThat(actualOutput, containsString("  [Cylinder] (3"));
        assertThat(actualOutput, containsString(", 30"));
        assertThat(actualOutput, containsString("25"));
        assertThat(actualOutput, containsString(") -> Radius: 15"));
        assertThat(actualOutput, containsString(" Height: 25"));
    }

    @Test
    public void testEquals()
    {
        // This should also be checked in other tests, but that is a discussion
        // for CS 350.
        Composite compositeWith3 = new Composite();
        compositeWith3.add(new Sphere(5));
        compositeWith3.add(new Sphere(9));
        compositeWith3.add(new Cylinder(5, 7));

        // Each Polyhedron must be equal to itself
        assertThat(compositeWith2, equalTo(compositeWith2));
        assertThat(compositeWith3, equalTo(compositeWith3));
        assertThat(EMPTY_POLY, equalTo(EMPTY_POLY));

        // Different spheres should not be equal to each other
        assertThat(compositeWith2, not(equalTo(EMPTY_POLY)));
        assertThat(compositeWith2, not(equalTo(compositeWith3)));
        assertThat(compositeWith3, not(equalTo(EMPTY_POLY)));
    }

    @Test
    public void testEqualsDifferentClass()
    {
        String str1 = "This is a String object";

        // Test the "not instanceof" branch
        assertThat(compositeWith2, not(equalTo(str1)));
    }

    @Test
    public void testHashCode()
    {
        // This should also be checked in other tests, but that is a discussion
        // for CS 350.
        Composite compositeWith3 = new Composite();
        compositeWith3.add(new Sphere(5));
        compositeWith3.add(new Sphere(9));
        compositeWith3.add(new Cylinder(5, 7));

        // Different Composite polyhedra should not be equal to each other
        assertThat(compositeWith2.hashCode(), not(equalTo(EMPTY_POLY.hashCode())));
        assertThat(compositeWith2.hashCode(), not(equalTo(compositeWith3.hashCode())));
        assertThat(compositeWith3.hashCode(), not(equalTo(EMPTY_POLY.hashCode())));
    }
}
