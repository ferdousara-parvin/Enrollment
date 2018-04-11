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

        CourseList list1 = new CourseList();
        CourseList list2 = new CourseList();

        Scanner scannerSyllabus = null;

        try {
            scannerSyllabus = new Scanner(new FileInputStream("Syllabus.txt"));
            String[] splitLine = null;
            String[] courseInfo = new String[5];
            int lineCounter = 0;

            while (scannerSyllabus.hasNext()) {
                String line = scannerSyllabus.nextLine().trim();
                splitLine = line.split("\\s+");

                switch (lineCounter) {
                    case 0:
                        courseInfo[0] = splitLine[0];
                        courseInfo[1] = splitLine[1];
                        courseInfo[2] = splitLine[2];
                        lineCounter++;
                        break;
                    case 1:
                        if (splitLine.length == 2) // There is a pre-req that exists
                        {
                            courseInfo[3] = splitLine[1];
                        } else { // No pre-req found
                            courseInfo[3] = "None";
                        }
                        lineCounter++;
                        break;

                    case 2:
                        if (splitLine.length == 2) // There is a co-req that exists
                        {
                            courseInfo[4] = splitLine[1];
                        } else { // No co-req found
                            courseInfo[4] = "None";
                        }
                        lineCounter++;

                        Course course = new Course(courseInfo[0], courseInfo[1], Double.parseDouble(courseInfo[2]), courseInfo[3], courseInfo[4]);
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

            try
            {
                scannerSyllabus.close();
            }
            catch(NullPointerException f)
            {
                System.out.println("No scanner was opened");
            }
            

            // Exit the progrsm
            System.exit(0);

        }

        Scanner kb = new Scanner(System.in);
        System.out.println("Please enter the name of the file that needs to be processed");
        String requestedFile = kb.next();

        Scanner scannerRequestedFile = null;

        try {
            scannerRequestedFile = new Scanner(new FileInputStream(requestedFile));

            ArrayList<String> finishedArrayList = new ArrayList<>();
            ArrayList<String> requestedArrayList = new ArrayList<>();

            String line = scannerRequestedFile.nextLine().trim();
            String line2 = "";
            while (line.equals("Finished")) {
               
                while (scannerRequestedFile.hasNext()) {
                    line2 = scannerRequestedFile.nextLine().trim();
                    if (line2.equals("Requested")) {
                        line = "Requested";
                        System.out.println("Found requested");
                        break;
                    }
                    finishedArrayList.add(line2);
                }

            }

            while (line.equals("Requested")) {
                while (scannerRequestedFile.hasNext()) {
                     line2 = scannerRequestedFile.nextLine().trim();
                    requestedArrayList.add(line2);
                }
                line = "";

            }

            System.out.println(Arrays.toString(finishedArrayList.toArray()));
            System.out.println(Arrays.toString(requestedArrayList.toArray()));        

        } catch (FileNotFoundException e) {

            // Warning message
            System.out.println("Could not open file for reading. Please check if file exists! Program will terminate now.");

            try
            {
                scannerRequestedFile.close();
            }
            catch(NullPointerException f)
            {
                System.out.println("No scanner was opened");
            }
            

            // Exit the progrsm
            System.exit(0);

        }

    }

}

//        Course c1 = new Course("COMP_249", "OOP", 4.0, "COMP_248", "COMP_232");
//        Course c2 = new Course("ENGR_233", "CAL3", 4.0, "ENGR_213", "COMP_232");
//        Course c3 = new Course("SOEN_287", "html", 4.0, " ", " ");
//        Course c4 = new Course("SOEN_228", "system_hardware", 4.0, " ", " ");
//        CourseList list = new CourseList();
//
//        list.addToStart(c1);
//        list.addToStart(c2);
//        list.addToStart(c3);
//        System.out.println("SOP List (Verify addToStart): \n" + list);
//
//        list.deleteFromIndex(2);
//        System.out.println("Verify deleteFromIndex: \n" + list);
//
//        list.insertAtIndex(c4, 17);
//        System.out.println("Verify insertAtIndex: \n" + list);
//
//        //list.replaceAtIndex(new Course(c1, "COMP_249_2.0"), 0);
//        System.out.println("Verify replaceAtIndex: \n" + list);
//
//        System.out.println("Verify find(): \n" + list.find("COMP_249"));
//
//        System.out.println("Verify contains(): \n" + list.contains("COMp_49"));
