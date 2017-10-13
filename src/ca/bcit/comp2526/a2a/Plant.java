package ca.bcit.comp2526.a2a;

import java.awt.Color;
import java.util.Random;

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
    
    public void pollinate() {
        int plantCount;
        int emptyCount;
        int randomCount;
        int randomCheck;
        Random rand = new Random();
        Cell[] adjCells;
        adjCells = home.getAdjacentCells();
        plantCount = home.getPlantCount(adjCells);
        emptyCount = home.getEmptyCount(adjCells);
        if (emptyCount >= 3 && plantCount >= 2) {
            randomCount = 0;
            randomCheck = rand.nextInt(emptyCount)+1;
            
            for (int i = 0; randomCount <= randomCheck; i++) {
                
                if (adjCells[i].getUser() instanceof Empty) {
                    randomCount ++;
                }
                if(randomCount == randomCheck) {
                    adjCells[i].setUser(new Plant(adjCells[i]));
                    break;
                }
            }
        }
    }
    

    
}
