/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment4;

/**
 *TODO:
 * -> CourseList
 *      JAVADOC
 *      equals method
 *      clarifications from prof
 * 
 * -> the entire main method 
 */
public class Assignment4 {

    public static void main(String[] args) {
        //FERDOU YOU SHOULD DELETE THESE LINE BUT I KEPT THEM TO SHOW THAT MY METHODS WORK AND HOW TO USE EM
        
        Course c1 = new Course("COMP_249", "OOP", 4.0, "COMP_248", "COMP_232");
        Course c2 = new Course("ENGR_233", "CAL3", 4.0, "ENGR_213", "COMP_232");
        Course c3 = new Course("SOEN_287", "html", 4.0, " ", " ");
        Course c4 = new Course("SOEN_228", "system_hardware", 4.0, " ", " ");
        CourseList list = new CourseList();
        
        list.addToStart(c1);
        list.addToStart(c2);
        list.addToStart(c3);
        System.out.println("SOP List (Verify addToStart): \n" + list);
        
        list.deleteFromIndex(2);
        System.out.println("Verify deleteFromIndex: \n" + list);
        
        list.insertAtIndex(c4, 17);
        System.out.println("Verify insertAtIndex: \n" + list);
        
        list.replaceAtIndex(new Course(c1, "COMP_249_2.0"), 0);
        System.out.println("Verify replaceAtIndex: \n" + list);
        
        System.out.println("Verify find(): \n"+ list.find("COMP_249"));
        
        System.out.println("Verify contains(): \n"+ list.contains("COMp_49"));

    }

}
