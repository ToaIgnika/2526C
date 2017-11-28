package ca.bcit.comp2526.a2b;

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
public class Empty extends Element {
    
    /**
     * Default uid.
     */
    private static final long serialVersionUID = 1L;
    private Cell home;
    private ETYPE type;
    /**
     * constructor for empty object.
     * @param location of type Cell.
     */
    public Empty(Cell location) {
        type = ETYPE.Empty;
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
    
    /**
     * Type getter.
     * @return type of cell.
     */
    public ETYPE getType() {
        return type;
    }
}
