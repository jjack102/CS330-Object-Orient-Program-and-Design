#include <iostream>
#include <iomanip>
#include <fstream>
#include <vector>

#include "Polyhedron.h"
#include "PolyhedronFactory.h"

using namespace std;

using PolyhedronCollection = vector<unique_ptr<Polyhedron>>;

/**
 * Construct a list of polyhedra from
 * an input strea
 */
void readPolyhedra(istream& inf, PolyhedronCollection& polyhedra);

/**
 * Print a collection of polyhedra
 */
void printPolyhedra(ostream& outs, const PolyhedronCollection& polyhedra);

/**
 * Copy each polyhedron and apply the scaling factor to each copy.
 *
 * @param polyhedra orignal polyhedron collection
 * @param scaledCopies collection in whcih to store the scled copies
 * @param s scaling factor
 */
PolyhedronCollection duplicateAndScale(const PolyhedronCollection& polyhedra, double s);

/**
 * Main function
 *
 * @param argv[1] polyhedron input file
 * @param argv[2] scaling factor
 */
int main(int argc, char** argv)
{
    // Validate number of command line arguments
    if (argc < 3) {
        cerr << "Usage " << argv[0] << " inputFile scalingFactor" << "\n";
        return 1;
    }

    // Validate Scaling Factor
    int scalingFactor = atoi(argv[2]);
    if (scalingFactor < 1) {
        cerr << "Scaling Factor must be >= 1" << "\n";
        return 2;
    }

    // Validate Input File
    ifstream polyIn(argv[1]);
    if (!polyIn) {
        cerr << "Could not open " << argv[1] << "\n";
        return 3;
    }

    // Original polyhedra
    PolyhedronCollection polyhedra;
    readPolyhedra(polyIn, polyhedra);

    // Scaled copies of all polyhedra
    PolyhedronCollection scaledCopies = duplicateAndScale(polyhedra,
                                                          scalingFactor);

    cout << "Original Polyhedra" << "\n"
         << string(54, '-')      << "\n";
    printPolyhedra(cout, polyhedra);

    cout << "\n"
         << "\n";
    cout << "Scaled Polyhedra (Clones)" << "\n"
         << string(54, '-')             << "\n";
    printPolyhedra(cout, scaledCopies);

    // Memory Cleanup - Why is this commented out?
    // for (Polyhedron* polyhedron : polyhedra) {
        // delete polyhedron;
    // }

    // for (Polyhedron* polyhedron : scaledCopies) {
        // delete polyhedron;
    // }

    return 0;
}

//------------------------------------------------------------------------------
void readPolyhedra(istream& inf, PolyhedronCollection& polyhedra)
{
    Polyhedron* p = nullptr;

    while (inf >> p) {
        if (p != nullptr) {
            polyhedra.emplace_back(p);
        }
    }
}

//------------------------------------------------------------------------------
void printPolyhedra(ostream& outs, const PolyhedronCollection& polyhedra)
{
    for (const unique_ptr<Polyhedron>& p : polyhedra) {
        outs << (*p) << "\n";
    }
}

//------------------------------------------------------------------------------
PolyhedronCollection duplicateAndScale(const PolyhedronCollection& polyhedra, double s)
{
    PolyhedronCollection scaledCopies;

    for (const unique_ptr<Polyhedron>& original : polyhedra) {
        Polyhedron* copy = original->clone();
        copy->scale(s);

        scaledCopies.emplace_back(copy);
    }

    return scaledCopies;
}
