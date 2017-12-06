package ca.bcit.comp2526.a2c;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

/**
 * Plant.java.
 * 
 *
 * "I made this code longer than usual 
 * because I lack time to make it short"
 * @author Yevhen
 * @version Oct 19, 2017
 *
 */
public class Plant extends Element  {
    
    /**
     * Def UID.
     */
    private static final long serialVersionUID = 1L;
    private Cell home;
    private ETYPE cType;
    /**
     * Constructor for object of type Plant.
     * @param location of type Cell.
     */
    public Plant(Cell location) {
        super();
        cType = ETYPE.Plant;
        home = location;
        home.setUser(this);
        init();
    }
    
    /**
     * Initialize the frame parameters.
     */
    public void init() {
        home.setColor(Color.GREEN);
        home.setBackground(Color.GREEN);
    }
    
    /**
     * Getter for type.
     * @return plant type.
     */
    public ETYPE getType() {
        return cType;
    }
    
    /**
     * puts plant in specific cell.
     * @param location of type Cell.
     */
    public void setCell(Cell location) {
        location.setUser(new Plant(location));
    }
    
    /**
     * setter for home of the cell.
     * @param location of type Cell.
     */
    public void setHome(Cell location) {
        home = location;
    }
    
    /**
     * Checks if plants can have babies.
     */
    public void pollinate() {
        int plantCount;
        int emptyCount;
        int randomCount;
        int randomCheck;
        Random rand = new Random();
        ArrayList<Cell> adjCells;
        adjCells = home.getAdjecentCells();
        plantCount = home.getElemCount(adjCells, ETYPE.Plant);
        emptyCount = home.getElemCount(adjCells, ETYPE.Empty);
        if (emptyCount >= (2 + 1) && plantCount >= 2) {
            randomCount = 0;
            randomCheck = rand.nextInt(emptyCount) + 1;
             
            for (int i = 0; randomCount <= randomCheck; i++) {
                
                if (adjCells.get(i).getUser().getType() == ETYPE.Empty) {
                    randomCount++;
                }
                if (randomCount == randomCheck) {
                    adjCells.get(i).setUser(new Plant(adjCells.get(i)));
                    break;
                }
            }
        }
    }
}
