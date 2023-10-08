#include <utility>

#include "Schedule.h"

using namespace std::rel_ops;

Schedule::Node::Node(Course c)
    :data(c),
     next(nullptr)
{
}

//------------------------------------------------------------------------------
Schedule::Schedule()
    :head(nullptr),
     tail(nullptr),
     totalCredits(0)
{
}

/**
 * @todo implement this function (it is simliar to Review 01)
 */
Schedule::Schedule(const Schedule& src)
    :head(nullptr),
     tail(nullptr),
     totalCredits(0)
{
    // Copy the src Linked List **data**
    Node* srcIt = src.head;

    while(srcIt != nullptr){
        this->appendNoCheck(srcIt->data);

        srcIt = srcIt->next;
    }
}

/**
 * @todo implement this function (it is similar to Review 01)
 */
Schedule::~Schedule()
{
    Node *this_iterator = nullptr;  // Loop control pointer
    Node *to_delete = nullptr;      // Node to delete

    // start at the beginning of the this
    this_iterator = this->head;

    // transverse through this and delete each node
    while(this_iterator != nullptr){
        to_delete = this_iterator;

        // move to next node
        this_iterator = this_iterator->next;

        // delete current node
        delete to_delete;

        to_delete = nullptr;
    }
}

/**
 * @todo implement this function (it is similar to Review 01)
 */
void Schedule::appendNoCheck(Course course)
{
    // Create a new Node
    Node *new_node = nullptr;

    // Store the course data within the node
    new_node = new Node(course);

    // This node exists for memory leak demos
    // Node* leak_node = new Node(to_add);

    // Handle the case where the first node is added
    if (this->totalCredits == 0) {
        this->head = new_node;
        this->tail = new_node;
    }
    else {
        (this->tail)->next = new_node;
        this->tail = new_node;
    }

    // Increase the number of credits
    this->totalCredits += new_node->data.getCredits();

    new_node = nullptr;
}

/**
 * @todo implement this function
 */
bool Schedule::wouldViolateCreditLimit(Course course) const
{
    if((course.getCredits() + this->totalCredits) > CREDIT_LIMIT){
        return true;
    }

    return false;

}

/**
 * @todo implement this function
 */
bool Schedule::alreadyInSchedule(Course course) const
{
    // Check if the student is registered
    // for a different section of the same course
    Node* it = head;

    while(it != nullptr){
        if(it->data == course){
            return true;
        }

        it = it->next;
    }

    return false;
}

//------------------------------------------------------------------------------
void Schedule::display(std::ostream& outs) const
{
    Node* it = head;

    outs << "  (" << totalCredits << " Total Credits)" << "\n";

    while (it != nullptr) {
        outs << "  - " << (it->data) << "\n";

        it = it->next;
    }
}

//------------------------------------------------------------------------------
Schedule& Schedule::operator=(Schedule rhs)
{
    swap(*this, rhs);

    return *this;
}
