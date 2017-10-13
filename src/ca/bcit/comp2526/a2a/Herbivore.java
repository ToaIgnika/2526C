package ca.bcit.comp2526.a2a;

import java.awt.Color;
import java.util.Random;

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
        int plantCount;
        int emptyCount;
        int randomCount;
        int randomCheck;
        Cell tempCell;
        Random rand = new Random();
        Cell[] adjCells;
        adjCells = home.getAdjacentCells();
        plantCount = getPlantCount(adjCells);
        emptyCount = getEmptyCount(adjCells);
        if (plantCount > 0) {
            randomCount = 0;
            randomCheck = rand.nextInt(plantCount) + 1;
            for (int i = 0; randomCount <= randomCheck; i++) {
                if (adjCells[i].getUser() instanceof Plant) {
                    randomCount++;
                }
                if (randomCount == randomCheck) {
                    home.setUser(new Empty(home));
                    hasMoved = true;
                    this.setCell(adjCells[i]);
                    resetLife();
                    break;
                }
            }
        }/* else if (emptyCount > 0) {
            randomCount = 0;
            randomCheck = rand.nextInt(emptyCount) + 1;
            for (int i = 0; randomCount <= randomCheck; i++) {
                if (adjCells[i].getUser() instanceof Empty) {
                    randomCount++;
                }
                if (randomCount == randomCheck) {
                    home.setUser(new Empty(home));
                    setCell(adjCells[i]);
                    break;
                }
            }
        } else {
            
        }*/
    }

    public boolean getMoved() {
        return hasMoved;
    }
    
    public void setMoved(boolean isMoved) {
        hasMoved = isMoved;
    }
    
    private int getPlantCount(Cell[] arr) {
        if (arr.length == 3) {
            return 0;
        }
        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].getUser() instanceof Plant) {
                count++;
            }
        }
        return count;
    }

    public int getEmptyCount(Cell[] arr) {
        if (arr.length == 3) {
            return 0;
        }
        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].getUser() instanceof Empty) {
                count++;
            }
        }
        return count;
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
