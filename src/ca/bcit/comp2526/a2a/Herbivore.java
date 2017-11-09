package ca.bcit.comp2526.a2a;

import java.awt.Color;
import java.util.Random; 

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
     * Constructor for object of type Herbivore.
     * @param location of type Cell.
     */
    public Herbivore(Cell location) {
        super(location, 10, Color.YELLOW, eType.Herbivore);

    }
    
    public void move() {
        eType s[] = {eType.Plant};
        super.move(s);
    }

}
