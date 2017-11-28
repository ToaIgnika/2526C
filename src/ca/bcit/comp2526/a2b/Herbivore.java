package ca.bcit.comp2526.a2b;

import java.awt.Color;

/**
 * Herbivore.java.
 * 
 *
 * "I made this code longer than usual 
 * because I lack time to make it short"
 * @author Yevhen
 * @version Oct 19, 2017
 *
 */
public class Herbivore extends Lifeform  {
   
    /**
     * Default UID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Constructor for object of type Herbivore.
     * @param location of type Cell.
     */
    public Herbivore(Cell location) {
        super(location, Integer.valueOf("10"), Color.YELLOW, ETYPE.Herbivore);

    }
    
    /**
     * Move method.
     */
    public void move() {
        ETYPE[] s = {ETYPE.Plant};
        super.move(s);
    }
    
    /**
     * method to reproduce the cell.
     */
    public void getBorn() {
        ETYPE[] s = {ETYPE.Plant};
        int[] n = {2, 1, 2};
        super.getBorn(s, n);
     }
    

}
