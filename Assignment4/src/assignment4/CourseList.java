package assignment4;

import java.util.ArrayList;
import java.util.LinkedList;

public class CourseList {

    private Course head;
    private int size;
    private LinkedList<CourseNode> courseList;

    public CourseList() {
        courseList = new LinkedList<>();
    }

    public CourseList(CourseList other) {
    //TODO: finish implement this method
    }

    public void addToStart(Course course){
        CourseNode cn = new CourseNode();
        cn.course = course;
        
        courseList.addFirst(cn);     
    }
    
//    public void insertAtIndex(Course course, int index){
//        try{
//        courseList.add(new CourseNode);    
//        courseList.remove(index + 1);
//        }
//    }
    
//    public void deleteFromIndex(){}
//    public void deleteFromStart(){}
//    public void replaceAtIndex(){}
//    public void find(){}
//    public void contains(){}
//    public boolean equals(){ return true;}
    
    
    //getters and setters
    public Course getHead() {
        return courseList.getFirst().course;
    }

    public int getSize() {
        return courseList.size();
    }

    private class CourseNode {

        private Course course;
//TODO: i have no idea what a pointer to a CourseNode onject is        
        private CourseNode pointer;

        public CourseNode() {
            this.course = null;
            this.pointer = null;
        }

        public CourseNode(Course c, CourseNode cn) {
            this.course = c;
            this.pointer = cn;
        }

        public CourseNode(CourseNode other) {
            this.course = other.course;
            this.pointer = other.pointer;
        }

        public CourseNode clone() {
            return new CourseNode(this.course, this.pointer);
        }

        //getters and setters
        public Course getCourse() {
            return course;
        }

        public void setCourse(Course course) {
            this.course = course;
        }

        public CourseNode getPointer() {
            return pointer;
        }

        public void setPointer(CourseNode pointer) {
            this.pointer = pointer;
        }

    }
}
