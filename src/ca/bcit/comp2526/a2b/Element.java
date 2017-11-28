package ca.bcit.comp2526.a2b;

import java.io.Serializable;

/**
 * Element.java.
 * 
 *
 * "I made this code longer than usual 
 * because I lack time to make it short"
 * @author Yevhen
 * @version Nov 19, 2017
 *
 */
public abstract class Element implements Serializable  {
    
    /**
     * Default SUID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Constructor for Element.
     */
    public Element() {
        
    }
    
    /**
     * init method.
     */
    void init() {
        
    };
    
    /**
     * setCell method.
     * @param c of type Cell.
     */
    void setCell(Cell c) {
        
    }
    
    /**
     * get moved method.
     * @return true if moved.
     */
    boolean getMoved() {
        return false;
    }

    /**
     * move method.
     */
    void move() {
        
    }
    
    /**
     * sethome method.
     * @param cell of type Cell.
     */
    void setHome(Cell cell) {
        
    }
    
    /**
     * life decreaser.
     */
    void decLife() {
    }
    
    /**
     * setter for moved.
     * @param isMoved of type boolean.
     */
    void setMoved(boolean isMoved) {
        
    }
    
    /**
     * getter for lifecount.
     * @return lifecount.
     */
    int getLife() {
        return 0;
        }
    
    /**
     * reproduction method.
     */
    void getBorn() {
    
    }
    
    /**
     * getter for type.
     * @return ETYPE.
     */
    ETYPE getType() {
        return null;
    };
}
