#include "PolyhedronFactory.h"

#include "Sphere.h"
#include "Cylinder.h"

#include <iterator>
#include <algorithm>

PolyhedronFactory::PolyhedronPair PolyhedronFactory::_known_polyhedra[] = {
    {"sphere"   ,  new Sphere()},
    /*PolyhedronPair( "composite",  new Composite()), // This will be in part 2*/
    {"cylinder" ,  new Cylinder()}
};

//------------------------------------------------------------------------------
PolyhedronFactory::PolyhedronPair::PolyhedronPair()
    : _name(), _model(nullptr)
{
}

//------------------------------------------------------------------------------
PolyhedronFactory::PolyhedronPair::PolyhedronPair(std::string name, Polyhedron* polygon)
    : _name(name), _model(polygon)
{
}

PolyhedronFactory::PolyhedronPair::~PolyhedronPair()
{
    delete _model;
}

//------------------------------------------------------------------------------
Polyhedron* PolyhedronFactory::createPolyhedron(std::string name)
{
    for(const PolyhedronPair& pair : _known_polyhedra) {
        if(pair._name == name) {
            return pair._model->clone();
        }
    }

    // A polygon with the given name could not be found
    return nullptr;
}

//------------------------------------------------------------------------------
bool PolyhedronFactory::isKnown(std::string name)
{
    /*
    for(const PolyhedronPair& pair : _known_polyhedra){
        if(pair._name == name){
            return true;
        }
    }

    // The polygon with the given name is unknown
    return false;
    */

    const auto it = std::find_if(std::begin(_known_polyhedra),
                                 std::end(_known_polyhedra),
                                 [&name](const PolyhedronPair& pairToCheck) {
                                     return pairToCheck._name == name;
                                 });

    return it != std::end(_known_polyhedra);
}

//------------------------------------------------------------------------------
void PolyhedronFactory::listKnown(std::ostream& outs)
{
    for(const PolyhedronPair& pair : _known_polyhedra) {
        outs << " " << pair._name << "\n";
    }
}

//------------------------------------------------------------------------------
int PolyhedronFactory::numberKnown()
{
    int count = 0;

    for(const PolyhedronPair& pair : _known_polyhedra) {
        count++;
    }

    return count;
}
