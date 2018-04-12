package assignment4;

/**
 * This class is an interface
 *
 * @author Viveks Anban and Ferdousara Parvin
 */
public interface DirectlyRelatable {

    /**
     * This method verifies if two courses are directly relatable meaning that either the co-req ID or pre-req ID for a course matches the courseID of the other course
     *
     * @return boolean - Returns true if two courses are directly relatable and false otherwise
     */
    public boolean isDirectlyRelatable(Course C);

}
