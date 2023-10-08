#include <utility>
#include "Student.h"

// Allow the compiler to define the remaining
// comparison operators
using namespace std::rel_ops;

//------------------------------------------------------------------------------
Student::Student()
    :Student("ERROR")
{
}

//------------------------------------------------------------------------------
Student::Student(std::string n)
    :name(n)
{
    // @todo create a new empty schedule
    this->schedule = new Schedule();
}

// @todo implement this function
Student::Student(const Student& src)
{
    // @todo copy the name
    this->name = src.name;

    // @todo create a deep copy of src.schedule
    this->schedule = src.schedule->clone();
}

//------------------------------------------------------------------------------
// @todo implement this function
Student::~Student()
{
    // @todo make sure that the schedule attribute (data member) is deallocated
        delete schedule;
}

//------------------------------------------------------------------------------
void swap(Student& lhs, Student& rhs)
{
    // @todo swap the lhs name and rhs name
    std::swap(lhs.name, rhs.name);

    // @todo swap the lhs schedule and rhs schedule
    std::swap(lhs.schedule, rhs.schedule);
}

//------------------------------------------------------------------------------
void Student::display(std::ostream &outs) const
{
    outs << " " << name << "\n";

    // @todo Add the line to output the schedule.
    outs << *schedule;
}

//------------------------------------------------------------------------------
bool Student::enrollIn(const Course &toAdd)
{
    return schedule->add(toAdd);
}


