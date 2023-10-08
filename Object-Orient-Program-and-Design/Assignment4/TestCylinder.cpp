#include <iostream>
#include <fstream>
#include <string>
#include <cstdlib>
#include <vector>
#include <sstream>
#include <algorithm>
#include <functional>
#include <cmath>

#include "Polyhedron.h"
#include "Sphere.h"
#include "Cylinder.h"

#include "bodgeUnitTest.h"

//-----------------------------------------------------------------------------
// Helper/Utility Functions
//-----------------------------------------------------------------------------

const double FP_TOLERANCE = 0.01;

/**
 * Compare two floating point numbers for equivalence. Allow them to differ
 * within a set tolerance
 *
 * Note there exists a tremendous amount of literature on how to
 * compare floating point number. For this exercise/example we will
 * use a slightly-better-than naive fp1 == fp2 approach.
 *
 * @param fp1 first floating point number
 * @param fp2 second floating point number
 * @param eps tolerance
 */
inline
bool fpNumsAreEqual(double fp1, double fp2, double eps=FP_TOLERANCE)
{
    return std::abs(fp2 - fp1) <= eps;
}

//-----------------------------------------------------------------------------
// Unit Tests
//-----------------------------------------------------------------------------

const Cylinder* DEFAULT_CYLINDER = new Cylinder();

bool testDefaultConstructor()
{
    bodgeAssert(fpNumsAreEqual(DEFAULT_CYLINDER->getRadius(), 1));
    bodgeAssert(fpNumsAreEqual(DEFAULT_CYLINDER->getDiameter(), 2));

    bodgeAssert(fpNumsAreEqual(DEFAULT_CYLINDER->getHeight(), 1));

    // BoundingBox...
    const Point& point = (DEFAULT_CYLINDER->getBoundingBox()).getUpperRightVertex();

    bodgeAssert(fpNumsAreEqual(point.x, 2));
    bodgeAssert(fpNumsAreEqual(point.y, 2));
    bodgeAssert(fpNumsAreEqual(point.z, 1));

    // I am skipping display in this test

    return true;
}

//-----------------------------------------------------------------------------
bool testNonDefaultConstructor()
{
    Cylinder* cyl = new Cylinder(3, 2);

    bodgeAssert(fpNumsAreEqual(cyl->getRadius(), 3));
    bodgeAssert(fpNumsAreEqual(cyl->getDiameter(), 6));

    bodgeAssert(fpNumsAreEqual(cyl->getHeight(), 2));

    // BoundingBox...
    const Point& point = (cyl->getBoundingBox()).getUpperRightVertex();

    bodgeAssert(fpNumsAreEqual(point.x, 6));
    bodgeAssert(fpNumsAreEqual(point.y, 6));
    bodgeAssert(fpNumsAreEqual(point.z, 2));

    // I am skipping display in this test

    return true;
}

//-----------------------------------------------------------------------------
bool testSetRadius()
{
    Cylinder* cyl = new Cylinder(1, 2);

    cyl->setRadius(12);

    bodgeAssert(fpNumsAreEqual(cyl->getRadius(), 12));
    bodgeAssert(fpNumsAreEqual(cyl->getDiameter(), 24));

    bodgeAssert(fpNumsAreEqual(cyl->getHeight(), 2));

    // BoundingBox...
    const Point& point = (cyl->getBoundingBox()).getUpperRightVertex();

    bodgeAssert(fpNumsAreEqual(point.x, 24));
    bodgeAssert(fpNumsAreEqual(point.y, 24));
    bodgeAssert(fpNumsAreEqual(point.z, 2));

    // I am skipping display in this test

    return true;
}

//-----------------------------------------------------------------------------
bool testSetHeight()
{
    Cylinder* cyl = new Cylinder(1, 2);

    cyl->setHeight(8);

    bodgeAssert(fpNumsAreEqual(cyl->getRadius(), 1));
    bodgeAssert(fpNumsAreEqual(cyl->getDiameter(), 2));

    bodgeAssert(fpNumsAreEqual(cyl->getHeight(), 8));

    // BoundingBox...
    const Point& point = (cyl->getBoundingBox()).getUpperRightVertex();

    bodgeAssert(fpNumsAreEqual(point.x, 2));
    bodgeAssert(fpNumsAreEqual(point.y, 2));
    bodgeAssert(fpNumsAreEqual(point.z, 8));

    // I am skipping display in this test

    return true;
}

//-----------------------------------------------------------------------------
bool testClone()
{
    Cylinder* cyl = new Cylinder(3, 2);
    Cylinder* cpy = (Cylinder*) cyl->clone();

    // No return this shenanigans
    bodgeAssert(cyl != cpy);

    bodgeAssert(fpNumsAreEqual(cpy->getRadius(), 3));
    bodgeAssert(fpNumsAreEqual(cpy->getDiameter(), 6));

    bodgeAssert(fpNumsAreEqual(cpy->getHeight(), 2));

    // BoundingBox...
    const Point& point = (cpy->getBoundingBox()).getUpperRightVertex();

    bodgeAssert(fpNumsAreEqual(point.x, 6));
    bodgeAssert(fpNumsAreEqual(point.y, 6));
    bodgeAssert(fpNumsAreEqual(point.z, 2));

    // I am skipping display in this test

    return true;
}

//-----------------------------------------------------------------------------
bool testRead()
{
    Cylinder* cyl = new Cylinder();

    std::istringstream ins("4 12");

    cyl->read(ins);

    bodgeAssert(fpNumsAreEqual(cyl->getRadius(), 12));
    bodgeAssert(fpNumsAreEqual(cyl->getDiameter(), 24));

    bodgeAssert(fpNumsAreEqual(cyl->getHeight(), 4));

    // BoundingBox...
    const Point& point = (cyl->getBoundingBox()).getUpperRightVertex();

    bodgeAssert(fpNumsAreEqual(point.x, 24));
    bodgeAssert(fpNumsAreEqual(point.y, 24));
    bodgeAssert(fpNumsAreEqual(point.z, 4));

    // I am skipping display in this test

    return true;
}

//-----------------------------------------------------------------------------
bool testScale()
{
    Cylinder* cyl = new Cylinder(5, 3);

    cyl->scale(2);

    bodgeAssert(fpNumsAreEqual(cyl->getRadius(), 10));
    bodgeAssert(fpNumsAreEqual(cyl->getDiameter(), 20));

    bodgeAssert(fpNumsAreEqual(cyl->getHeight(), 6));

    // BoundingBox...
    const Point& point = (cyl->getBoundingBox()).getUpperRightVertex();

    bodgeAssert(fpNumsAreEqual(point.x, 20));
    bodgeAssert(fpNumsAreEqual(point.y, 20));
    bodgeAssert(fpNumsAreEqual(point.z, 6));

    // I am skipping display in this test

    return true;
}

//-----------------------------------------------------------------------------
bool testDisplay()
{
    Cylinder* cyl = new Cylinder(3, 5);

    std::ostringstream outs;
    const std::string expectedOutput = "[Cylinder] (6, 6, 5)->Radius: 3 Height: 5";

    cyl->display(outs);
    bodgeAssert(outs.str() == expectedOutput);

    return true;
}

//------------------------------------------------------------------------------
int main(int argc, char** argv)
{
    UnitTestPair tests[] = {
        {testDefaultConstructor, "testDefaultConstructor"},
        {testNonDefaultConstructor, "testNonDefaultConstructor"},
        {testSetRadius, "testSetRadius"},
        {testSetHeight, "testSetHeight"},
        {testClone, "testClone"},
        {testRead, "testRead"},
        {testScale, "testScale"},
        {testDisplay, "testDisplay"}
    };

    for (const UnitTestPair& testPair : tests) {
        runTest(testPair.first, testPair.second);
    }

    return 0;
}

