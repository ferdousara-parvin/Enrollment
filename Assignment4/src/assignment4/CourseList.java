package assignment4;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.NoSuchElementException;

/**
 * This class is the Course list class and contains an inner Course node class
 *
 * @author Viveks Anban and Ferdousara Parvin
 */
public class CourseList {

    private CourseNode head;
    private int size; // how many nodes in the list

    /**
     * Constructs a new empty Course List
     *
     * @param none
     */
    public CourseList() {
        size = 0;
        head = null;
    }

    /**
     * Constructs a new copy Course list object
     *
     * @param other Course list
     */
    public CourseList(CourseList other) {
        CourseNode temp1, temp2 = null, endNode = null;
        temp1 = other.head;

        while (temp1 != null) {
            Course c1 = new Course(temp1.course, temp1.course.getCourseID().concat("_copyctor"));
            temp2 = new CourseNode(c1, null);

            if (head == null) {
                head = temp2;
                endNode = head;

            } else {
                endNode.nextCourse = temp2;
                endNode = temp2;
            }
            temp1 = temp1.nextCourse;
        }

        this.size = other.size;

        endNode = temp2 = null;
    }

    /**
     * This method adds a Course node at the head of the list as a node
     *
     * @param course Course object
     */
    public void addToStart(Course course) {
        head = new CourseNode(course, head); // Add the course to index 0 and make it point to the ex-head
        size++; // increment the size
    }

    /**
     * This method inserts a Course node at a specific index in the list
     *
     * @param course Course object
     * @param index Position in the list where the course node must be inserted
     */
    public void insertAtIndex(Course course, int index) {
        try {
            if (index < 0 || (index > size - 1)) //check if the index is within bounds (when list is empty, size = 0, so index > 0 -1 will return true)
            {
                throw new NoSuchElementException();

            } else if (index == 0) {
                addToStart(course);
            } else {

                CourseNode temp = head; // create a temperory reference to the head

                // Iteration will stop when temp is equal to the node at index-1
                for (int i = 1; i < index; i++) {
                    temp = temp.nextCourse;
                }
//debugger                
//                System.out.println("want to put it between courses: " + temp.course.getCourseID() + " and " + temp.nextCourse.course.getCourseID());

                //Change value of the node at index and make it point to the node that used to be at that index
                temp.nextCourse = new CourseNode(course, temp.nextCourse);
                size++; //increment the size
            }

        } catch (NoSuchElementException e) {
            System.out.println("No such element. Program will terminate.");
            System.exit(0); // terminate program
        }
    }

    /**
     * This method deletes the Course node from a specific index in the list
     *
     * @param index The position of the Course node to be deleted
     */
    public void deleteFromIndex(int index) {
        try {
            if (index < 0 || index > size - 1 || head == null) { // verify that index is whithin bounds and that the list is not empty
                throw new NoSuchElementException();
            } else if (index == 0) {
                deleteFromStart(); //recycling code
                return;
            } else {
//debugger                
//                System.out.println("DELETING INDEX " + index);
                CourseNode temp = head; // create temperory reference pointing at the head

                //iteration will stop at the node at index-1
                for (int i = 1; i < index && temp != null; i++) {
                    temp = temp.nextCourse;
                }
//debugger
//            System.out.println("should delete: " + temp.nextCourse.course.getCourseID());
                temp.nextCourse = temp.nextCourse.nextCourse; //the node at index will now be the node that followed the original node at index. 
                size--; //decrement size
            }

        } catch (NoSuchElementException e) {
            System.out.println("No such element. Program will terminate.");
            System.exit(0);//terminate program
        }

    }

    /**
     * This method deletes the first Course node in the list
     *
     * @param none
     */
    public void deleteFromStart() {
        // Special case: empty list
        if(head == null)
        {
            return;
        }
        
        //the head will now be the node originally at index 1
        head = new CourseNode(head.nextCourse.course, head.nextCourse.nextCourse);
        size--; // decrement size
    }

    /**
     * This method replaced the Course node at a specific index with another
     * Course node
     *
     * @param course Course object
     * @param index The position of the Course node to be replaced with another
     * Course node
     */
    public void replaceAtIndex(Course course, int index) {

        if (index < 0 || index > size - 1) {
            return; // do nothing if the index is not within bounds
        } else if (index == 0) {
            //replace the node at the head with the given course and it will precede the node originally at index 1
            head = new CourseNode(course, head.nextCourse);
        } else {
            //create a temperory reference pointing to the head
            CourseNode temp = this.head;

            //iteration will stop at the node at index-1
            for (int i = 1; i < index; i++) {
                temp = temp.nextCourse;
            }

            //node at index will be replaced by this new course and will precede the node originally att index + 1
            temp.nextCourse = new CourseNode(course, temp.nextCourse);
        }

    }

    /**
     * This method searches through the Course list and returns (if found) the
     * Course Node where there is a course object with the specific course ID
     *
     * @param searchedCourseID A course ID (Attribute of a Course object)
     * @return CourseNode - A Course node
     */
    public CourseNode find(String searchedCourseID) {

        if (head == null) {
            return null; //return null if the list is empty
        }

        //create a temperory reference pointing to the head
        CourseNode temp = this.head;
        int index = 0; //initialize counter

        while (temp != null) {
            if (temp.course.getCourseID().equals(searchedCourseID)) {
                return temp;
            } else {
                temp = temp.nextCourse;
                index++;
            }
        }
        return null;

    }

    /**
     * This method uses the find method to verify if there exist a Course Node
     * that contains a course object with the specified course ID
     *
     * @param searchedCourseID A course ID (Attribute of a Course object)
     * @return boolean - Returns true if the course list contains a Course node
     * with a course object with the specified courseID, otherwise, returns
     * false
     */
    public boolean contains(String searchedCourseID) {
        // return true if the courseID can be found in the list
        return (find(searchedCourseID) != null);
    }

    /**
     * This method compares if two Course Lists are equal
     *
     * @Override equals method in class Object
     * @param course Course object
     * @return boolean - Returns true if two Course lists are equal and false
     * otherwise
     */
    public boolean equals(CourseList courseList) {
        if (courseList.size != this.size) {
            return false;
        }

        boolean courseFound = true;

        CourseNode t1 = this.head;
        CourseNode t2 = courseList.head;
        while (t1 != null && courseFound) {
            while (t2 != null) {
                if (!t1.course.equals(t2.course)) {
                    courseFound = false;
                    t2 = t2.nextCourse;
                } else {
                    courseFound = true;
                    t2 = courseList.head;
                    break;
                }

            }

            t1 = t1.nextCourse;

        }
        
        if (courseFound) {
            return true;
        } else {
            return false;
        }

    }

    /**
     * This method returns a string describing a course list (by the courseID of
     * each course object)
     *
     * @Override toString in class Object
     * @return String - returns the course IDs of each course object in the
     * course list
     */
    @Override
    public String toString() {
        String output = "";
        CourseNode temp = head;
        for (int i = 0; i < size && temp != null; i++) {
            output += "course: " + temp.course.getCourseID() + " ";
            temp = temp.nextCourse;
        }
        return output;
    }

    /**
     * Gets the course list's head course object
     *
     * @return Course - The first course
     */
    public Course getHead() {
        return head.course;
    }

    /**
     * Gets the course list's size
     *
     * @return int - Size of the course list
     */
    public int getSize() {
        return size;
    }

    /**
     * This class an inner class which represents the nodes of the list
     */
    public class CourseNode {

        // Attributes
        private Course course;
        private CourseNode nextCourse; // pointer to CourseNode object

        /**
         * Constructs a new default Course node object
         */
        public CourseNode() {
            this.course = null;
            this.nextCourse = null;
        }

        /**
         * Constructs a new parameterized Course node object
         *
         * @param course Course object
         * @param next Course node object
         */
        public CourseNode(Course course, CourseNode next) {
            this.course = course;
            this.nextCourse = next;
        }

        /**
         * Constructs a new copy Course node object
         *
         * @param other Course node object
         */
        private CourseNode(CourseNode other) {
            this.course = new Course(other.course, other.course.getCoReqID().concat("_copyCtorOfCourseNode"));
            this.nextCourse = other.nextCourse;
        }

        /**
         * This method creates and returns a cloned Course node object
         *
         * @return Course Node - Returns a cloned Course node object
         */
        public CourseNode clone() {
            return new CourseNode(this.course, this.nextCourse);
        }

        /**
         * Gets the course object
         *
         * @return Course - Course object
         */
        public Course getCourse() {
            return course;
        }

        /**
         * Sets the course object
         *
         * @param course Course object
         */
        public void setCourse(Course course) {
            this.course = course;
        }

        /**
         * Gets the course node object
         *
         * @return CourseNode - Course node object
         */
        public CourseNode getNextCourse() {
            return nextCourse;
        }

        /**
         * Sets the course node object
         *
         * @param nextCourse Course node object
         */
        public void setNextCourse(CourseNode nextCourse) {
            this.nextCourse = nextCourse;
        }

        /**
         * This method returns a string describing a course node
         *
         * @Override toString in class Object
         * @return String - returns the description of a course node
         */
        public String toString() {
            return "CourseNode{" + "\ncourse=" + course.getCourseID() + ", \nnextCourse=" + ((nextCourse != null) ? nextCourse.course.getCourseID() : "null") + '}';
        }

    }
}
