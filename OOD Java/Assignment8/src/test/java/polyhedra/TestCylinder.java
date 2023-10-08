package polyhedra;

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
public class TestCylinder {

    Cylinder defaultCylinder;

    @BeforeEach
    public void setUp()
    {
        defaultCylinder = new Cylinder();
    }

    @Test
    public void testDefaultConstructor()
    {
        assertThat(defaultCylinder.getRadius(), closeTo(1, Point.EQ_THRESHOLD));
        assertThat(defaultCylinder.getDiameter(), closeTo(2, Point.EQ_THRESHOLD));

        assertThat(defaultCylinder.getHeight(), closeTo(1, Point.EQ_THRESHOLD));

        // BoundingBox...
        Point expectedPoint = new Point(2, 2, 1);
        Point point = (defaultCylinder.getBoundingBox()).getUpperRightVertex();

        assertThat(point, equalTo(expectedPoint));

        // Simple and Complex checks
        assertThat(defaultCylinder.isSimple(), is(true));
        assertThat(defaultCylinder.isComplex(), is(false));

        // I am skipping toString in this test
    }

    @Test
    public void testNonDefaultConstructor()
    {
        Cylinder cyl = new Cylinder(3, 2);

        assertThat(cyl.getRadius(), closeTo(3, Point.EQ_THRESHOLD));
        assertThat(cyl.getDiameter(), closeTo(6, Point.EQ_THRESHOLD));

        assertThat(cyl.getHeight(), closeTo(2, Point.EQ_THRESHOLD));

        // BoundingBox...
        Point expectedPoint = new Point(6, 6, 2);
        Point point = cyl.getBoundingBox().getUpperRightVertex();

        assertThat(point, equalTo(expectedPoint));

        // Simple and Complex checks
        assertThat(cyl.isSimple(), is(true));
        assertThat(cyl.isComplex(), is(false));

        // I am skipping toString in this test
    }

    @Test
    public void testSetRadius()
    {
        Cylinder cyl = new Cylinder(1, 2);

        cyl.setRadius(12);

        assertThat(cyl.getRadius(), closeTo(12, Point.EQ_THRESHOLD));
        assertThat(cyl.getDiameter(), closeTo(24, Point.EQ_THRESHOLD));

        assertThat(cyl.getHeight(), closeTo(2, Point.EQ_THRESHOLD));

        // BoundingBox...
        Point expectedPoint = new Point(24, 24, 2);
        Point point = cyl.getBoundingBox().getUpperRightVertex();

        assertThat(point, equalTo(expectedPoint));

        // Simple and Complex checks
        assertThat(cyl.isSimple(), is(true));
        assertThat(cyl.isComplex(), is(false));

        // I am skipping toString in this test
    }

    @Test
    public void testSetHeight()
    {
        Cylinder cyl = new Cylinder(1, 2);

        cyl.setHeight(8);

        assertThat(cyl.getRadius(), closeTo(1, Point.EQ_THRESHOLD));
        assertThat(cyl.getDiameter(), closeTo(2, Point.EQ_THRESHOLD));

        assertThat(cyl.getHeight(), closeTo(8, Point.EQ_THRESHOLD));

        // BoundingBox...
        Point expectedPoint = new Point(2, 2, 8);
        Point point = cyl.getBoundingBox().getUpperRightVertex();

        // Simple and Complex checks
        assertThat(cyl.isSimple(), is(true));
        assertThat(cyl.isComplex(), is(false));

        assertThat(point, equalTo(expectedPoint));
    }

    @Test
    public void testClone()
    {
        Cylinder cyl = new Cylinder(3, 2);

        Cylinder cpy = (Cylinder) cyl.clone();

        assertThat(cpy.getRadius(), closeTo(3, Point.EQ_THRESHOLD));
        assertThat(cpy.getDiameter(), closeTo(6, Point.EQ_THRESHOLD));

        assertThat(cpy.getHeight(), closeTo(2, Point.EQ_THRESHOLD));

        // BoundingBox...
        Point expectedPoint = new Point(6, 6, 2);
        Point point = cpy.getBoundingBox().getUpperRightVertex();

        assertThat(point, equalTo(expectedPoint));

        // Simple and Complex checks
        assertThat(cyl.isSimple(), is(true));
        assertThat(cyl.isComplex(), is(false));

        assertThat(cpy.isSimple(), is(true));
        assertThat(cpy.isComplex(), is(false));

        // I am skipping toString in this test
    }

    @Test
    public void testFromDimensions()
    {
        Cylinder cyl = new Cylinder();

        cyl.fromDimensions(new double[]{4, 12});

        assertThat(cyl.getRadius(), closeTo(12, Point.EQ_THRESHOLD));
        assertThat(cyl.getDiameter(), closeTo(24, Point.EQ_THRESHOLD));

        assertThat(cyl.getHeight(), closeTo(4, Point.EQ_THRESHOLD));

        // BoundingBox...
        Point expectedPoint = new Point(24, 24, 4);
        Point point = cyl.getBoundingBox().getUpperRightVertex();

        assertThat(point, equalTo(expectedPoint));

        // Simple and Complex checks
        assertThat(cyl.isSimple(), is(true));
        assertThat(cyl.isComplex(), is(false));

        // I am skipping toString in this test
    }

    @Test
    public void testScale()
    {
        Cylinder cyl = new Cylinder(5, 3);

        cyl.scale(2);

        assertThat(cyl.getRadius(), closeTo(10, Point.EQ_THRESHOLD));
        assertThat(cyl.getDiameter(), closeTo(20, Point.EQ_THRESHOLD));

        assertThat(cyl.getHeight(), closeTo(6, Point.EQ_THRESHOLD));

        // BoundingBox...
        Point expectedPoint = new Point(20, 20, 6);
        Point point = cyl.getBoundingBox().getUpperRightVertex();

        assertThat(point, equalTo(expectedPoint));

        // Simple and Complex checks
        assertThat(cyl.isSimple(), is(true));
        assertThat(cyl.isComplex(), is(false));

        // I am skipping toString in this test
    }

    @Test
    public void testToStringDefaultConstructor()
    {
        Cylinder cyl = defaultCylinder;

        final String expectedOutput = "[Cylinder] (1, 2, 1) -> Radius: 1 Height: 1";
        final String actualOutput = cyl.toString();

        // Check the pieces of the resulting string (machine Floating Point)
        assertThat(actualOutput, startsWith("[Cylinder] ("));
        assertThat(actualOutput, containsString((new Point(2, 2, 1)).toString()));
        assertThat(actualOutput, containsString(") -> Radius: 1"));
        assertThat(actualOutput, containsString("Height: 1"));
    }

    @Test
    public void testToStringConstructor()
    {
        Cylinder cyl = new Cylinder(3, 5);

        final String expectedOutput = "[Cylinder] (6, 6, 5) -> Radius: 3 Height: 5";
        final String actualOutput = cyl.toString();

        // Check the pieces of the resulting string (machine Floating Point)
        assertThat(actualOutput, startsWith("[Cylinder] ("));
        assertThat(actualOutput, containsString((new Point(6, 6, 5)).toString()));
        assertThat(actualOutput, containsString(") -> Radius: 3"));
        assertThat(actualOutput, containsString("Height: 5"));
    }

    @Test
    public void testToStringAfterScale()
    {
        Cylinder cyl = new Cylinder(3, 5);
        cyl.scale(2);

        final String expectedOutput = "[Cylinder] (12, 12, 10) -> Radius: 6 Height: 10";
        final String actualOutput = cyl.toString();

        // Check the pieces of the resulting string (machine Floating Point)
        assertThat(actualOutput, startsWith("[Cylinder] ("));
        assertThat(actualOutput, containsString((new Point(12, 12, 10)).toString()));
        assertThat(actualOutput, containsString(") -> Radius: 6"));
        assertThat(actualOutput, containsString("Height: 10"));
    }

    @Test
    public void testEquals()
    {
        // This should also be checked in other tests, but that is a discussion
        // for CS 350.
        Cylinder cyl1 = new Cylinder(1, 1);
        Cylinder cyl2 = new Cylinder(2, 1);
        Cylinder cyl4 = new Cylinder(4, 5);

        // Each Polyhedron must be equal to itself
        assertThat(cyl1, equalTo(cyl1));
        assertThat(cyl2, equalTo(cyl2));
        assertThat(cyl4, equalTo(cyl4));

        // Different cylinder should not be equal to each other
        assertThat(cyl1, not(equalTo(cyl2)));
        assertThat(cyl1, not(equalTo(cyl4)));
        assertThat(cyl2, not(equalTo(cyl4)));
    }

    @Test
    public void testEqualsDifferentClass()
    {
        Cylinder cyl1 = new Cylinder(1, 2);
        String str1 = "A String";

        // Test the "not instanceof" branch
        assertThat(cyl1, not(equalTo(str1)));
    }

    @Test
    public void testHashCode()
    {
        // This should also be checked in other tests, but that is a discussion
        // for CS 350.
        Cylinder cyl1 = new Cylinder(1, 1);
        Cylinder cyl2 = new Cylinder(2, 1);
        Cylinder cyl4 = new Cylinder(4, 5);

        // Different cylinder hashcodes should not be equal to each other
        assertThat(cyl1.hashCode(), not(equalTo(cyl2.hashCode())));
        assertThat(cyl1.hashCode(), not(equalTo(cyl4.hashCode())));
        assertThat(cyl2.hashCode(), not(equalTo(cyl4.hashCode())));
    }
}

