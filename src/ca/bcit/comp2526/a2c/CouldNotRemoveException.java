/**
 * 
 */
package ca.bcit.comp2526.a2c;

/**
 * CouldNotRemoveException.java.
 *
 * "I made this code longer than usual 
 * because I lack time to make it short"
 * @author Yevhen
 * @version Nov 22, 2017
 *
 */
public class CouldNotRemoveException extends Exception {
    /**
     * Default SUID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * constructor for CouldNotRemoveException.
     */
    public CouldNotRemoveException() {
        System.out.println("The list is empty. There is nothing to remove");
    }
}
