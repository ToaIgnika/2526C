/**
 * 
 */
package ca.bcit.comp2526.a2c;

/**
 * CouldNotAddException.java.
 *
 * "I made this code longer than usual 
 * because I lack time to make it short"
 * @author Yevhen
 * @version Nov 22, 2017
 *
 */
public class CouldNotAddException extends Exception {
    /**
     * Default SID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Constructor for CouldNotAddException.
     */
    public CouldNotAddException() {
        System.out.println("Wrong type adder");
    }
}
