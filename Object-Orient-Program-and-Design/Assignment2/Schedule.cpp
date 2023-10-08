#include <utility>
#include <algorithm>
#include <numeric>

#include "Schedule.h"

using namespace std::rel_ops;

//------------------------------------------------------------------------------
/**
 * @todo sanity check - is this function complete?
 */
Schedule::Schedule()
{
}

//------------------------------------------------------------------------------
/**
 * @todo implement this method.
 */

/**
* Retrieve the total number of credits
*/
int Schedule::getCredits() const
{
  /*  const_iterator it = allCourses.begin();

    while(it < allCourses.end()){
        it;
  */

    int totalCredits = std::accumulate(allCourses.begin(), allCourses.end(), 0,
                                       [](int i, const Course& c) -> int
                                       {
                                            return i + c.getCredits();
                                       });

    return totalCredits;
}

//------------------------------------------------------------------------------
/**
 * @todo implement this function
 */
void Schedule::appendNoCheck(Course course)
{
    allCourses.push_back(course);
}

//------------------------------------------------------------------------------
/**
 * @todo implement this function
 */
bool Schedule::wouldViolateCreditLimit(Course course) const
{
    if((course.getCredits() + this->getCredits()) > CREDIT_LIMIT){
        return true;
    }

    return false;
}

//------------------------------------------------------------------------------
/**
 * @todo implement this function
 */
bool Schedule::alreadyInSchedule(Course course) const
{
    const_iterator it = allCourses.begin();

    while(it < allCourses.end()){
        if(*it == course){
            return true;
        }

        it++;
    }

    return false;
}

//------------------------------------------------------------------------------
/**
 * @todo implement this function
 */
void Schedule::display(std::ostream& outs) const
{
    const_iterator it = allCourses.begin();

    while(it < allCourses.end()){
        outs << "  - " << *it << "\n";
        it++;
    }

    // Write your output loop before this line
    outs << "  (" << getCredits() << " Total Credits)" << "\n";
}
