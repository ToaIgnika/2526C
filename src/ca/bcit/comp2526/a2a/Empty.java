package ca.bcit.comp2526.a2a;

import java.awt.Color;

public class Empty {
    private Cell home;
    public Empty(Cell location) {
        home = location;
        home.setUser(this);
        init();
    }
    
    /**
     * 
     */
    public void init() {
        home.setColor(Color.WHITE);
        home.setBackground(Color.WHITE);
    }
    
    /**
     * puts plant in specific cell.
     * @param location
     */
    public void setCell(Cell location) {
        location.setUser(new Empty(location));
    }
}
