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
public class Herbivore {
    private static final int DEFLIFE = 10;
    
    private Cell home;
    private int lifeCount;
    private boolean hasMoved;

    /**
     * Constructor for object of type Herbivore.
     * @param location of type Cell.
     */
    public Herbivore(Cell location) {
        home = location;
        home.setUser(this);
        init();
        lifeCount = DEFLIFE;
        hasMoved = false;
    }

    /**
     * Initializes the jpanel properties.
     */
    public void init() {
        home.setColor(Color.YELLOW);
        home.setBackground(Color.YELLOW);
    }

    /**
     * puts plant in specific cell.
     * 
     * @param location of type Cell.
     */
    public void setCell(Cell location) {
        home = location;
        location.setUser(this);
        init();
    }

    /**
     * Preforms a move of the plant.
     */
    public void move() {
        int plantCount;
        int emptyCount;
        int randomCount;
        int randomCheck;
        Random rand = new Random();
        Cell[] adjCells;
        adjCells = home.getAdjacentCells();
        plantCount = home.getPlantCount(adjCells);
        emptyCount = home.getEmptyCount(adjCells);
        // eat plant if can
        if (plantCount > 0) {
            randomCount = 0;
            randomCheck = rand.nextInt(plantCount) + 1;
            for (int i = 0; randomCount <= randomCheck; i++) {
                if (adjCells[i].getUser() instanceof Plant) {
                    randomCount++;
                }
                if (randomCount == randomCheck) {
                    this.setMoved(true);
                    new Empty(home);
                    this.setCell(adjCells[i]);
                    this.resetLife();
                    break;
                }
            }
        } else if (emptyCount > 0) {
            randomCount = 0;
            randomCheck = rand.nextInt(emptyCount) + 1;
            for (int i = 0; randomCount <= randomCheck; i++) {
                if (adjCells[i].getUser() instanceof Empty) {
                    randomCount++;
                }
                if (randomCount == randomCheck) {
                    this.setMoved(true);
                    new Empty(home);
                    this.setCell(adjCells[i]);
                    break;
                }
            }
        } else {
            return;
        }
    }
    
    /**
     * Getter for moved variable.
     * @return hasMoved as boolean.
     */
    public boolean getMoved() {
        return hasMoved;
    }
    
    /**
     * setter for isMoved.
     * @param isMoved as boolean.
     */
    public void setMoved(boolean isMoved) {
        hasMoved = isMoved;
    }
    
    /**
     * getter for life count.
     * @return lifeCount as int.
     */
    public int getLife() {
        return lifeCount;
    }

    /**
     * resets the lifecount.
     */
    public void resetLife() {
        lifeCount = DEFLIFE;
    }

    /**
     * reduces the lifecount.
     */
    public void decLife() {
        lifeCount--;
    }

}
