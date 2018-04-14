// Assignment 4: Practicing with LinkedLists
// Written by: Viveka Anban(40063308) and Ferdousara Parvin(40062738) 
package assignment4;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * <h1>EnrollmentResults class is the driver class</h1>
 * The purpose of this program is to review some concepts that we learned
 * previously concerning LinkedLists. This program creates a list that will
 * contain courses by following the linked list format. By reading files that
 * contains courses and their requirements, we are able to determine whether or
 * not a request from a student to enroll in a certain course is possible or
 * not. Later on, we demonstrate the functionality of our custom linked list by
 * showing showing the result of the implementation of all the functions in our
 * class. <br>
 *
 * @author Viveka Anban (40063308) and Ferdousara Parvin (40062738) COMP249</br>
 * Assignment 4 </br>
 * Due date: Friday, April 13th, 2018</br>
 *
 */
public class EnrollmentResults {

    /**
     * This is the main method where all the scanners and printwriters are being
     * created, and where all the different methods are invoked.
     *
     * @param args
     */
    public static void main(String[] args) {
        // Print a welcome message
        System.out.println(
                "--------------------------------------\n"
                + " Welcome to Viveka and Ferdousara's program\n"
                + "--------------------------------------------");
        // -------------- PART A ------------------
        // Create empty course lists
        CourseList list1 = new CourseList();
        CourseList list2 = new CourseList();

        // -------------- PART B ------------------
        Scanner scannerSyllabus = null; // Create a scanner to read through syllabus

        // Use a try-catch block to try to read the syllabus file
        try {
            scannerSyllabus = new Scanner(new FileInputStream("Syllabus.txt")); // Open the stream
            String[] splitLine = null;
            String[] courseInfo = new String[5]; // (courseID, courseName, credit, pre-req ID, co-reqID) --> content of the array
            int lineCounter = 0;

            // Keep on reading while there is something to read
            while (scannerSyllabus.hasNext()) {
                String line = scannerSyllabus.nextLine().trim(); // retrieve line by line
                splitLine = line.split("\\s+"); // seperate line by the spaces in between them

                switch (lineCounter) {
                    case 0:
                        courseInfo[0] = splitLine[0];
                        courseInfo[1] = splitLine[1];
                        courseInfo[2] = splitLine[2];
                        lineCounter++;
                        break;
                    case 1:
                        if (splitLine.length == 2) // There exists a pre-req
                        {
                            courseInfo[3] = splitLine[1];
                        } else { // No pre-req found
                            courseInfo[3] = "";
                        }
                        lineCounter++;
                        break;

                    case 2:
                        if (splitLine.length == 2) // There exists a co-req
                        {
                            courseInfo[4] = splitLine[1];
                        } else { // No co-req found
                            courseInfo[4] = "";
                        }
                        lineCounter++;

                        // Create a courseo object using the courseInfo array which contains all the attributes needed to create a course object
                        Course course = new Course(courseInfo[0], courseInfo[1], Double.parseDouble(courseInfo[2]), courseInfo[3], courseInfo[4]);

                        // Add that course to a course list if there is not already a course node contianing a course object with the same courseID as the one that is oging to be added
                        if (!list1.contains(courseInfo[0])) {
                            list1.addToStart(course);
                        }

                        break;
                    case 3:
                        lineCounter = 0; // Start over the ocunter
                        break;
                }

            }

            System.out.println(list1);
        } catch (FileNotFoundException e) {

            // Warning message
            System.out.println("Could not open file for reading. Please check if file exists! Program will terminate now.");

            // Close scanner
            try {
                scannerSyllabus.close();
            } catch (NullPointerException f) {
                System.out.println("No scanner was opened");
            }

            // Exit the progrsm
            System.exit(0);

        }

        // -------------- PART C (FIRST PART) ------------------
        Scanner kb = new Scanner(System.in); // Create scanner to read useri nput
        System.out.println("\nPlease enter the name of the file that needs to be processed"); // Prompt message
        String requestedFile = kb.next();

        Scanner scannerRequestedFile = null; // Create scanner to read from request file

        // Create two arrays which will hold courseID's
        ArrayList<String> finishedArrayList = new ArrayList<>();
        ArrayList<String> requestedArrayList = new ArrayList<>();

        // Try-ctch block to attempt reading the request file
        try {
            scannerRequestedFile = new Scanner(new FileInputStream(requestedFile)); // Open stream

            String line = scannerRequestedFile.nextLine().trim(); // Read first line
            String line2 = "";

            while (line.equals("Finished")) {

                while (scannerRequestedFile.hasNext()) {
                    line2 = scannerRequestedFile.nextLine().trim();
                    if (line2.equals("Requested")) { // If at some point, the line now reads "Requested", all the finished ocurses has been retrieved (get out of this while loop)
                        line = "Requested";
                        break;
                    }
                    finishedArrayList.add(line2); // Add all the finished courses (under "Finished") in finishedArrayList
                }

            }

            while (line.equals("Requested")) {
                while (scannerRequestedFile.hasNext()) {
                    line2 = scannerRequestedFile.nextLine().trim();
                    requestedArrayList.add(line2); // Add all the requested ocurses (under "Requested") in requestedArrayList
                }
                line = "";

            }

            // Debugger : 
            // System.out.println(Arrays.toString(finishedArrayList.toArray()));
            // System.out.println(Arrays.toString(requestedArrayList.toArray()));
        } catch (FileNotFoundException e) {

            // Warning message
            System.out.println("Could not open file for reading. Please check if file exists! Program will terminate now.");

            // Close scanner
            try {
                scannerRequestedFile.close();
            } catch (NullPointerException f) {
                System.out.println("No scanner was opened");
            }

            // Exit the progrsm
            System.exit(0);

        }

        // -------------- PART C (SECOND PART) ------------------
        boolean preReqMet = false;
        boolean coReqMet = false;
        boolean coReqInFinishedList = false;

        if (requestedArrayList.size() == 0) { // No requested courses
            System.out.println("No enrollment courses found.");

        } else {

            // Run through requestedArrayList
            for (String x : requestedArrayList) {
                String preReq = list1.find(x).getCourse().getPreReqID(); // Retrieve the pre-req ID for the course x requested (from list1 --> syllabus)
                String coReq = list1.find(x).getCourse().getCoReqID(); // Retrieve the co-req ID for the course x requested (from list1 --> syllabus)

                if (preReq.equals("")) { // If no pre-req needed
                    preReqMet = true;
                } else {
                    for (String f : finishedArrayList) { // Run through finishedArrayList of the student and check if the pre-req is there
                        if (f.equals(preReq)) {
                            preReqMet = true;
                            break;
                        }
                    }
                }

                if (coReq.equals("")) { // If no co-req needed
                    coReqMet = true;
                } else {
                    for (String f : finishedArrayList) { // Run through finishedArrayList of the student and check if the co-req is there
                        if (f.equals(coReq)) {
                            coReqMet = true;
                            coReqInFinishedList = true;
                            break;
                        }
                    }

                    if (!coReqMet) {
                        for (String r : requestedArrayList) { // If co-req not found in finishedArrayList, run through requestedArrayList of the student and check if the co-req is there
                            if (r.equals(coReq)) {
                                coReqMet = true;
                                break;
                            }

                        }
                    }
                }

                // Final outputs (if the student can enroll for a course or not)
                System.out.printf("%-20s%-20s%-20s%-20s", "Requested class", "Enrollment Status", "Pre-Requiste", "Co-Requiste\n");
                boolean enrollmentStatus = false;
                String preReqCompleted = "";
                String coReqEnrolled = "";
                //String 

                if (coReq.equals(preReq) && (preReqMet || coReqMet)) {
                    enrollmentStatus = true;
                }

                if (coReqMet && preReqMet) {
                    enrollmentStatus = true;
                    if (!preReq.equals("")) {
                        preReqCompleted = "Completed";
                    }

                    if (!coReq.equals("")) {
                        if (coReqInFinishedList) {
                            coReqEnrolled = "Completed";
                        } else {
                            coReqEnrolled = "Enrolled";
                        }

                    }

                } else {
                    if (!preReqMet) {
                        preReqCompleted = "Not completed";
                    }
                    if (!coReqMet) {
                        coReqEnrolled = "Not Enrolled";
                    }

                }

                System.out.println(x + ((enrollmentStatus) ? " Accepted " : " Refused ") + (!preReq.equals("") ? (preReq + ": " + preReqCompleted) : "") + " " + ((!coReq.equals("") ? (coReq + ": " + coReqEnrolled) : "")));
                coReqMet = false;
                preReqMet = false;
                coReqInFinishedList = false;
            }

        }

        // -------------- PART D ------------------
        System.out.println("\nPlease enter 5 course IDs");
        ArrayList<String> courseIdArrayList = new ArrayList<>();

        // Add user inputs into arrayList
        for (int i = 0; i < 5; i++) {
            String id = kb.next();
            courseIdArrayList.add(id.trim());
        }

        for (String x : courseIdArrayList) {
            System.out.println("The course " + x + " has been found: " + list1.contains(x)); // Use contains method to find a course containing specified course ID

        }

        kb.close(); // Close scanner

        // -------------- PART E ------------------
        // Parameterized constructors of course object
        Course c1 = new Course("COMP248", "OOP1", 4.0, "MATH201", "");
        Course c2 = new Course("COMP249", "OOP2", 4.0, "COMP248", "COMP232");

        Course c3 = new Course("ENGR233", "CAL3", 4.5, "ENGR213", "COMP232");
        Course c4 = new Course("ENGR233_2.0", "CAL3", 4.5, "ENGR213", "COMP232");
        Course c5 = new Course("ENGR213", "ODE", 3.0, " MATH201", "COMP248");

        Course c6 = new Course("SOEN228", "System_Hardware", 3.0, " ", "COMP249");
        Course c7 = new Course("SOEN229", "System_Hardware_2.0", 3.0, " ", "COMP249");

        // toString method of the Course
        System.out.println("\n-----Sample Course-------\n");
        System.out.println(c1);
        System.out.println(c2);
        System.out.println(c3);
        System.out.println(c4);
        System.out.println(c5);
        System.out.println(c6);
        System.out.println(c7);

        // Equals method of Course
        System.out.println("\n-----Testing equals method of Course-------\n");
        System.out.println("Is " + c1.getCourseID() + " equal to " + c2.getCourseID() + "? : " + c1.equals(c2));
        System.out.println("Is " + c3.getCourseID() + " equal to " + c4.getCourseID() + "? : " + c3.equals(c4));

        // AddToStart method
        System.out.println("\ns-----Testing add to start method-------\n");
        list2.addToStart(c1);
        list2.addToStart(c2);
        list2.addToStart(c3);
        list2.addToStart(c5);
        System.out.println("After adding some courses:\n" + list2);

        // DeleteFromIndex method
        System.out.println("\n-----Testing delete from index method-------\n");
        list2.deleteFromIndex(2);
        System.out.println("After deleting course node at index 2: " + list2);

//        // InsetAtIndex SPECIAL CASE (terminates program)
//        System.out.println("/n/n-----Testing insert at index method-------\n");
//        System.out.println("After trying to add a course at index 17: ");
//        list2.insertAtIndex(c4, 17);
        // DeleteFromStart method
        System.out.println("\n-----Testing delete from start method-------\n");

//        // SPECIAL CASE (delete from empty list)
//        CourseList list4 = new CourseList();
//        list4.deleteFromStart();
//        System.out.println("Empty list: " + list4);
//        System.out.println("After deleting head course node: " + list4);
        list2.deleteFromStart();
        System.out.println("After deleting head course node: " + list2);

        // ReplaceAtIndex method
        System.out.println("\n-----Testing replace at index method-------\n");
        list2.replaceAtIndex(c4, list2.getSize() - 1);
        System.out.println("After replacing the course at index 1 by another course: " + list2);

        // Find method
        System.out.println("\n-----Testing find method-------\n");
        System.out.println("Find COMP249 in list1 (syllabus list): " + list1.find("COMP249"));
        list1.deleteFromIndex(2);
        System.out.println("\nRemove COMP249 from list1: " + list1); // Was able to remove it since we were able to find the index where it was
        System.out.println("\nFind SOEN233 in list1 (syllabus list): " + list1.find("SOEN233"));

        // Copy constructor of courseList
        System.out.println("\n-----Testing copy constructor of course list-------\n");
        CourseList copyCourseList = new CourseList(list2);
        System.out.println("Original list: " + list2);
        System.out.println("Copied list: " + copyCourseList);

        // Equals method of course list
        System.out.println("\n------Testing equals method of course list-------\n");

        System.out.println("Is the original list (list2) equal to the copied list (from above testing)? : " + list2.equals(copyCourseList));

        CourseList list3 = new CourseList();
        list3.addToStart(c3);
        list3.addToStart(c1);
        System.out.println("\nList2: " + list2);
        System.out.println("List3: " + list3);
        System.out.println("Is list3 equal to list2? : " + list3.equals(list2)); // same size course list but with different courses

        // isDirectlyRelative method
        System.out.println("\n------Testing isDirectlyRelatble method-------\n");
        System.out.println("Is " + c1.getCourseID() + " directly relatable to " + c2.getCourseID() + "?: " + c1.isDirectlyRelatable(c2));
        System.out.println("Is " + c2.getCourseID() + " directly relatable to " + c3.getCourseID() + "?: " + c2.isDirectlyRelatable(c3));

        // Print closing message
        System.out.println(
                "\n--------------------------------------------\n"
                + " Thank you for using our program. Goodbye!\n"
                + "--------------------------------------------\n");

    }

}
