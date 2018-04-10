package assignment4;

import java.util.Scanner;

/**
 * This class is the Course class (creates Course objects) and implements
 * DirectlyRelatable
 *
 * @author Viveks Anban and Ferdousara Parvin
 */
public class Course implements DirectlyRelatable {

    private String courseID;
    private String courseName; // Always a single word
    private double credit;
    private String preReqID;
    private String coReqID;

    private Scanner kb;

    /**
     * Constructs a new parameterized Course object
     *
     * @param courseID Course ID
     * @param courseName Course name
     * @param credit Number of credits
     * @param preReqID Pre-requisite ID
     * @param coReqID Co-requisite ID
     */
    public Course(String courseID, String courseName, double credit, String preReqID, String coReqID) {
        this.courseID = courseID;
        this.courseName = courseName;
        this.credit = credit;
        this.preReqID = preReqID;
        this.coReqID = coReqID;
    }

    /**
     * Constructs a new clone Course object
     *
     * @param other Course object to be cloned
     * @param value New value for Course ID
     */
    private Course(Course other, String value) {
        this.courseID = value;
        this.courseName = other.courseName;
        this.credit = other.credit;
        this.preReqID = other.preReqID;
        this.coReqID = other.coReqID;

    }

    /**
     * This method creates and returns a cloned Course object by prompting the
     * user to enter the courseID of the course to be cloned
     *
     * @return Object - Returns a cloned Course object
     */
    // ASK PROF: use clone method fro mcloneable interface???
    public Course clone() {
        kb = new Scanner(System.in);
        System.out.println("Please enter the courseID of the course you wish to clone");

        return new Course(this, kb.next());
    }

    /**
     * This method verifies if two Course are directly related to each other
     *
     * @param c Course object
     * @return boolean - Returns true if two courses's ID, co-requisite or
     * pre-requisite match
     */
    public boolean isDirectlyRelatable(Course c) {
        return (c.coReqID.equals(this.courseID)
                || c.preReqID.equals(this.courseID)
                || c.courseID.equals(this.preReqID)
                || c.courseID.equals(this.coReqID));
    }

    /**
     * This method compares if two Courses are equal
     * @Override equals method in class Object
     * @param obj Any object
     * @return boolean - Returns true if two Courses are equal and false if they are not
     */
    @Override
    public boolean equals(Object obj) {
        if ((this == null) || (obj == null) || (this.getClass() != obj.getClass())) {
            return false;
        }

        Course other = (Course) obj;

        return (this.credit == other.credit && this.courseName == other.courseName && this.preReqID == other.preReqID && this.coReqID == other.coReqID);

    }

    /**
     * This method returns a string describing a course
     *
     * @Override toString in class Object
     * @return String - returns the description of a course
     */
    @Override
    public String toString() {
        return "Course{" + "\ncourseID= " + courseID + ",\ncourseName= " + courseName + ",\ncredit= " + credit + ",\npreReqID= " + preReqID + ",\ncoReqID= " + coReqID + "\n}";
    }

    // Accessors and mutators
    /**
     * Gets the course ID of the course
     *
     * @return String - Course ID
     */
    public String getCourseID() {
        return courseID;
    }

    /**
     * Sets the course ID of the course
     *
     * @param courseID Course ID
     */
    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }

    /**
     * Gets the course name
     *
     * @return String - Course name
     */
    public String getCourseName() {
        return courseName;
    }

    /**
     * Sets the course name
     *
     * @param courseName Course name
     */
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    /**
     * Gets the course's number of credits
     *
     * @return double - Number of credits
     */
    public double getCredit() {
        return credit;
    }

    /**
     * Sets the course's number of credits
     *
     * @param credit Number of credits
     */
    public void setCredit(double credit) {
        this.credit = credit;
    }

    /**
     * Gets the course's pre-requisite ID
     *
     * @return String - Pre-requisite ID
     */
    public String getPreReqID() {
        return preReqID;
    }

    /**
     * Sets the course's pre-requisite ID
     *
     * @param preReqID Pre-requisite ID
     */
    public void setPreReqID(String preReqID) {
        this.preReqID = preReqID;
    }

    /**
     * Gets the course's co-requisite ID
     *
     * @return String - Co-requisite ID
     */
    public String getCoReqID() {
        return coReqID;
    }

    /**
     * Sets the course's co-requisite ID
     *
     * @param coReqID Co-requisite ID
     */
    public void setCoReqID(String coReqID) {
        this.coReqID = coReqID;
    }

}
