package ca.bcit.comp2526.a2a;

import java.awt.Color;

public class Herbivore {
    private Cell home;
    private int lifeCount;
    private boolean hasMoved;

    public Herbivore(Cell location) {
        home = location;
        home.setUser(this);
        init();
        lifeCount = 10;
        hasMoved = false;
    }

    /**
     * 
     */
    public void init() {
        home.setColor(Color.YELLOW);
        home.setBackground(Color.YELLOW);
    }

    /**
     * puts plant in specific cell.
     * 
     * @param location
     */
    public void setCell(Cell location) {
        home = location;
        location.setUser(this);
        init();
    }

    public void move() {
       
    }

    public boolean getMoved() {
        return hasMoved;
    }
    
    public void setMoved(boolean isMoved) {
        hasMoved = isMoved;
    }
    
    public int getLife() {
        return lifeCount;
    }

    public void resetLife() {
        lifeCount = 10;
    }

    public void decLife() {
        lifeCount--;
    }

}
