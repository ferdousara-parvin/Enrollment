package assignment4;

import java.util.Scanner;

public class Course implements DirectlyRelatable{
    
    private String courseID;
    private String courseName; // Always a single word
    private double credit;
    private String preReqID;
    private String coReqID;
    
    private Scanner kb;

    // Parameterized constructor 
    public Course(String courseID, String courseName, double credit, String preReqID, String coReqID) {
        this.courseID = courseID;
        this.courseName = courseName;
        this.credit = credit;
        this.preReqID = preReqID;
        this.coReqID = coReqID;
    }
    
    // Copy constructor
    public Course(Course other, String value)
    {
        this.courseID = value;
        this.courseName = other.courseName;
        this.credit = other.credit;
        this.preReqID = other.preReqID;
        this.coReqID = other.coReqID;
        
    }
    
    public Object clone()
    {
        kb = new Scanner(System.in);
        System.out.println("Please enter the courseID of the course you wish to clone");
        
        return new Course(this, kb.next());
    }
    
    // Implement method from interface
    public boolean isDirectlyRelatable(Course c)
    {
        return (c.coReqID.equals(this.courseID) || 
                c.preReqID.equals(this.courseID) || 
                c.courseID.equals(this.preReqID) || 
                c.courseID.equals(this.coReqID)); 
    }

    // Equals method
    @Override
    public boolean equals(Object obj) {
        if ((this == null) || (obj == null) || (this.getClass() != obj.getClass())) {
            return false;
        }

        Course other = (Course) obj;
        
        return (this.credit == other.credit && this.courseName == other.courseName && this.preReqID == other.preReqID && this.coReqID == other.coReqID);
    
    }

    // ToString method
    @Override
    public String toString() {
        return "Course{" + "courseID= " + courseID + ", courseName= " + courseName + ", credit= " + credit + ", preReqID= " + preReqID + ", coReqID= " + coReqID + '}';
    }

    // Accessors and mutators
    public String getCourseID() {
        return courseID;
    }

    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public double getCredit() {
        return credit;
    }

    public void setCredit(double credit) {
        this.credit = credit;
    }

    public String getPreReqID() {
        return preReqID;
    }

    public void setPreReqID(String preReqID) {
        this.preReqID = preReqID;
    }

    public String getCoReqID() {
        return coReqID;
    }

    public void setCoReqID(String coReqID) {
        this.coReqID = coReqID;
    }
    
}
