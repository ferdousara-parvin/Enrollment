package assignment4;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

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

            // Debugger: System.out.println(list1);

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
        System.out.println("Please enter the name of the file that needs to be processed"); // Prompt message
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
                        System.out.println("Found requested");
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
                if (coReqMet && preReqMet) {
                    if (!preReq.equals("None")) // if pre-req exists
                    {
                        System.out.println("Student can enroll in " + x + " course as he/she has coompleted the pre-requisites " + preReq);
                    } else {
                        System.out.println("Student can enroll in " + x + " course as he/she is enrolling for co-requisite " + coReq);
                    }
                } else {
                    System.out.println("Student can't enroll in " + x + " as he/she doesn't have sufficient background needed.");

                }
            }

        }

        // -------------- PART D ------------------
        System.out.println("\nPlease enter 5 course IDs");
        ArrayList<String> courseIdArrayList = new ArrayList<>();

        // Add user inputs into arrayList
        for (int i = 0; i < 5; i++) {
            String id = kb.next();
            courseIdArrayList.add(id);
        }

        for (String x : courseIdArrayList) {
            System.out.println("The course " + x + " has been found: " + list1.contains(x)); // Use contains method to find a course containing specified course ID

            // It it has been found, print the number of iterations needed to found it
            if (list1.contains(x)) {
                System.out.println("The number of iterations needed: ");
            }

        }

        kb.close(); // Close scanner

        // -------------- PART E ------------------
        // Parameterized constructors of course object
        Course c1 = new Course("COMP_249", "OOP", 4.0, "COMP_248", "COMP_232");
        Course c2 = new Course("ENGR_233", "CAL3", 4.5, "ENGR_213", "COMP_232");
        Course c3 = new Course("SOEN_287", "html", 4.0, " ", " ");
        Course c4 = new Course("SOEN_228", "system_hardware", 3.0, " ", " ");

        // Equals method of Course
        System.out.println(c1.getCourseID() + " is equal to " + c2.getCourseID() + ": " + c1.equals(c2));

        // AddToStart method
        list2.addToStart(c1);
        list2.addToStart(c2);
        list2.addToStart(c3);
        System.out.println("Verify addToStart: \n" + list2);

        list2.deleteFromIndex(2);
        System.out.println("Verify deleteFromIndex: \n" + list2);

        list2.insertAtIndex(c4, 17);
        System.out.println("Verify insertAtIndex: \n" + list2);

        //list.replaceAtIndex(new Course(c1, "COMP_249_2.0"), 0);
        System.out.println("Verify replaceAtIndex: \n" + list2);

        System.out.println("Verify find(): \n" + list2.find("COMP_249"));

        System.out.println("Verify contains(): \n" + list2.contains("COMp_49"));
        
        // Copy constructor fo courseList
        CourseList copyCourseList = new CourseList(list2);
        System.out.println("Original list: " + list2);
        System.out.println("Copied list: " + copyCourseList);

        // Print closing message
        System.out.println(
                "\n--------------------------------------------\n"
                + " Thank you for using our program. Goodbye!\n"
                + "--------------------------------------------\n");

    }

}
