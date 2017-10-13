package ca.bcit.comp2526.a2a;

import java.awt.Color;

public class Plant  {
    private Cell home;
    public Plant(Cell location) {
        home = location;
        home.setUser(this);
        init();
    }
    
    /**
     * 
     */
    public void init() {
        home.setColor(Color.GREEN);
        home.setBackground(Color.GREEN);
    }
    
    /**
     * puts plant in specific cell.
     * @param location
     */
    public void setCell(Cell location) {
        location.setUser(new Plant(location));
    }
    
}
