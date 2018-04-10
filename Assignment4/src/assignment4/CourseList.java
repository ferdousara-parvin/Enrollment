//TODO: FERDOU -> EQUALS METHOD
package assignment4;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.NoSuchElementException;

/**
 * This class is the Course list class
 *
 * @author Viveks Anban and Ferdousara Parvin
 */
public class CourseList {

    private CourseNode head;
    private int size; // how many nodes in the list

    /**
     * Constructs a new default Course list object (empty list)
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
        //TODO: finish implement this method
    }

    /**
     * This method adds a Course object at the head of the list as a node
     *
     * @param course Course object
     */
    public void addToStart(Course course) {
        head = new CourseNode(course, head); // Add the course to index 0 and make it point to the ex-head
        size++; // increment the size
    }

//ASK PROF: IM INSERTING A COURSE AT A CERTAIN INDEX AND IM MAKING THE REST SHIFT. IS THAT OK?   
    /**
     * This method inserts Course object at a specific index in the list
     *
     * @param course Course object
     * @param index Position in the list where the course object must be inserted
     */
    public void insertAtIndex(Course course, int index) {
        try {
            if (index < 0) { // excluded the condtion index > size - 1 because if index > size; the course is added at the end
                throw new NoSuchElementException();
            } else if (index == 0) {
                addToStart(course);
            } else {
                if (index > size - 1) { // adding course at the end of the list
                    index = size;
                }

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

    public void deleteFromIndex(int index) {
        try {
            if (index < 0 || index > size - 1) { // verify that index is whithin bounds
                throw new NoSuchElementException();
            }

            if (index == 0) {
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

    public void deleteFromStart() {
        //the head will now be the node originally at index 1
        head = new CourseNode(head.nextCourse.course, head.nextCourse.nextCourse);
        size--;//decrement size
    }

    public void replaceAtIndex(Course course, int index) {

        if (index < 0 || index > size - 1) {
            return; //do nothing if the index is not within bounds
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

    public CourseNode find(String searchedCourseID) {
        //create a temperory reference pointing to the head
        CourseNode temp = this.head;
        int index = 0; //initialize counter

        //iteration will stop at the index containing the CourseNode corresponding with the given courseID
        while (!temp.course.getCourseID().equals(searchedCourseID)) {
            index++;
            temp = head.nextCourse;

            if (index >= this.size) {
                return null; //return null if the courseID cannot be found in the list
            }
        }
//TODO: ask prof: should we return the index or the courseNode?        
        return temp;
    }

    public boolean contains(String searchedCourseID) {
        // return true if the courseID can be found in the list
        return (find(searchedCourseID) != null);
    }

//FERDOU    
//TODO: what does he mean by type syllabus?!?? (p.4 (m) of the assignment directives)    
    public boolean equals(CourseList syllabus) {
        return true;
    }

    //getters and setters
    public Course getHead() {
        return head.course;
    }

    public int getSize() {
        return size;
    }

//TODO: DELETE THIS METHOD; NOT REQUIRED BUT NEEDED FOR DEBUGGING    
    @Override
    public String toString() {
        String output = "List{";
        CourseNode temp = head;
        for (int i = 0; i < size && temp != null; i++) {
            output += "\ncourse = " + temp.course.getCourseID();
            temp = temp.nextCourse;
        }

        output += "\n}";
        return output;
    }

    /**
     * This class an inner class which represents the nodes of the list
     */
    private class CourseNode {

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
            this.course = other.course;
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
            return "CourseNode{" + "\ncourse=" + course.getCourseID() + ", \nnextCourse=" + nextCourse.course.getCourseID() + '}';
        }

    }
}
