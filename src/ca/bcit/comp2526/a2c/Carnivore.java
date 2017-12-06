package ca.bcit.comp2526.a2c;

import java.awt.Color;

/**
 * Carnivore.java.
 * 
 *
 * "I made this code longer than usual 
 * because I lack time to make it short"
 * @author Yevhen
 * @version Nov 19, 2017
 *
 */
public class Carnivore extends Lifeform {
    
    /**
     * Serial version.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Constructor for Carnivore object.
     * @param location of type Cell.
     */
    public Carnivore(Cell location) {
         super(location, Integer.valueOf("7"), Color.MAGENTA, ETYPE.Carnivore);
    }
    
    /**
     * move method.
     */
    public void move() {
        ETYPE[] s = {ETYPE.Herbivore, ETYPE.Omnivore};
        super.move(s);
    }
    
    /**
     * reproduction method.
     */
    public void getBorn() {
        ETYPE[] s = {ETYPE.Herbivore, ETYPE.Omnivore};
        int[] n = {1, 2, 2};
        super.getBorn(s, n);
     }
    
}
