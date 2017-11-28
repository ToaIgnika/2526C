package ca.bcit.comp2526.a2b;

import java.awt.Color;

/**
 * Omnivore.java.
 * 
 *
 * "I made this code longer than usual because I lack time to make it short"
 * 
 * @author Yevhen
 * @version Nov 19, 2017
 *
 */
public class Omnivore extends Lifeform {

    /**
     * def UID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Constructor for Omnivore object.
     * 
     * @param location
     *            of type Cell.
     */
    public Omnivore(Cell location) {
        super(location, 2, Color.BLUE, ETYPE.Omnivore);
    }

    /**
     * Move method for Omnivore object.
     */
    public void move() {
        ETYPE[] s = {ETYPE.Plant, ETYPE.Herbivore, ETYPE.Carnivore };
        super.move(s);
    }

    /**
     * Reproduction methid for Omnivore object.
     */
    public void getBorn() {
        ETYPE[] s = {ETYPE.Plant, ETYPE.Herbivore, ETYPE.Carnivore };
        int[] n = {1, 2 + 1, 2 + 1 };
        super.getBorn(s, n);
    }

}
