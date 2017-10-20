package ca.bcit.comp2526.a2a;

import java.awt.Color;

/**
 * Empty.java.
 * 
 *
 * "I made this code longer than usual 
 * because I lack time to make it short"
 * @author Yevhen
 * @version Oct 19, 2017
 *
 */
public class Empty {
    
    private Cell home;
    
    /**
     * constructor for empty object.
     * @param location of type Cell.
     */
    public Empty(Cell location) {
        home = location;
        home.setUser(this);
        init();
    }
    
    /**
     * Initialises the JPanel parameters.
     */
    public void init() {
        home.setColor(Color.WHITE);
        home.setBackground(Color.WHITE);
    }
    
    /**
     * puts plant in specific cell.
     * @param location of type cell.
     */
    public void setCell(Cell location) {
        location.setUser(new Empty(location));
    }
}
