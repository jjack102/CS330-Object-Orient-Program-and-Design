#ifndef CYLINDER_H_INCLUDED
#define CYLINDER_H_INCLUDED

#include "Polyhedron.h"

class Cylinder : public Polyhedron {
    private:
        double height;
        double radius;

    public:
        /**
         * Default Constructor
         */
        Cylinder();

        /**
         * Construct a cylinder with specified height and radius
         *
         * @param r desired radius
         * @param h desired height
         */
        Cylinder(double r, double h);

        /**
         * Destructor
         */
        ~Cylinder() = default;

        // Use the compiler generated version
        Cylinder(const Cylinder& src) = default;

        // Use the compiler generated version
        Cylinder& operator=(const Cylinder& rhs) = default;

        /**
         * Retrieve the radius
         */
        double getRadius() const;

        /**
         * Retrieve the height
         */
        double getHeight() const;

        /**
         * Update the radius
         */
        void setRadius(double r);

        /**
         * Update the height
         */
        void setHeight(double h);

        /**
         * Compute and return the diameter
         */
        double getDiameter() const;

        virtual Polyhedron* clone() const;
        virtual void read(std::istream& ins);
        virtual void display(std::ostream& outs) const;
        virtual void scale(double scalingFactor);
};


//------------------------------------------------------------------------------
inline
double Cylinder::getRadius() const
{
    return this->radius;
}

//------------------------------------------------------------------------------
inline
double Cylinder::getHeight() const
{
    return this->height;
}

//------------------------------------------------------------------------------
inline
void Cylinder::setRadius(double r)
 {
     radius = r;

    double d = getDiameter();
    boundingBox.setUpperRightVertex(d, d, height);
}

//------------------------------------------------------------------------------
inline
void Cylinder::setHeight(double h)
{
    height = h;

    double d = getDiameter();
    boundingBox.setUpperRightVertex(d, d, height);
}

//------------------------------------------------------------------------------
inline
double Cylinder::getDiameter() const
{
    return 2 * radius;
}

#endif
