package ca.bcit.comp2526.a2a;

import java.util.Random;

public class World {

    private int rowCount;

    private int colCount;

    private Cell[][] cellTable;

    private int rnJesus;

    public World(final int width, final int hight) {
        rowCount = hight;
        colCount = width;
    }

    public void init() {
        cellTable = new Cell[rowCount][colCount];
        for (int r = 0; r < rowCount; r++) {
            for (int c = 0; c < colCount; c++) {
                rnJesus = RandomGenerator.nextNumber(100);
                if (rnJesus >= 80) {
                    cellTable[r][c] = new Cell(World.this, r, c);        
                    cellTable[r][c].setUser(new Herbivore(cellTable[r][c]));
                    cellTable[r][c].init();
                } else if (rnJesus >= 50) {
                    cellTable[r][c] = new Cell(World.this, r, c);        
                    cellTable[r][c].setUser(new Plant(cellTable[r][c]));
                    cellTable[r][c].init();
                } else {
                    cellTable[r][c] = new Cell(World.this, r, c);  
                    cellTable[r][c].setUser(new Empty(cellTable[r][c]));
                    cellTable[r][c].init();
                }
            }
        }
    }

    public void takeTurn() {
        // step one - remove the dead animals
        for (int r = 0; r < rowCount; r++) {
            for (int c = 0; c < colCount; c++) {
                if (cellTable[r][c].getUser() instanceof Herbivore) {
                    ((Herbivore) cellTable[r][c].getUser()).decLife();
                    ((Herbivore) cellTable[r][c].getUser()).setMoved(false);
                    if (((Herbivore) cellTable[r][c].getUser()).getLife() == 0) {
                        cellTable[r][c].setUser(new Empty(cellTable[r][c]));
                    }
                }
            }
        }
        // step two - plague the plants
        int plantCount;
        int emptyCount;
        int randomCount;
        int randomCheck;
        Random rand = new Random();
        Cell[] adjCells;
        for (int r = 0; r < rowCount; r++) {
            for (int c = 0; c < colCount; c++) {
                if (cellTable[r][c].getUser() instanceof Plant) {
                    adjCells = cellTable[r][c].getAdjacentCells();
                    plantCount = getPlantCount(adjCells);
                    emptyCount = getEmptyCount(adjCells);
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
        }
        // step 3 - move the mofaka animals AND possibly eat
        for (int r = 0; r < rowCount; r++) {
            for (int c = 0; c < colCount; c++) {
                if (cellTable[r][c].getUser() instanceof Herbivore && !((Herbivore) cellTable[r][c].getUser()).getMoved()) {
                    adjCells = cellTable[r][c].getAdjacentCells();
                    plantCount = getPlantCount(adjCells);
                    emptyCount = getEmptyCount(adjCells);
                    // eat plant if can
                    if (plantCount > 0) {
                        randomCount = 0;
                        randomCheck = rand.nextInt(plantCount)+1;
                        for (int i = 0; randomCount <= randomCheck; i++) {
                            if (adjCells[i].getUser() instanceof Plant) {
                                randomCount ++;
                            }
                            if(randomCount == randomCheck) {
                                ((Herbivore) cellTable[r][c].getUser()).setMoved(true);
                                ((Herbivore) cellTable[r][c].getUser()).setCell(adjCells[i]);
                                ((Herbivore) cellTable[r][c].getUser()).resetLife();
                                new Empty(cellTable[r][c]);
                                break;
                            }
                        }
                    }
                    // move if can
                    else if (emptyCount > 0) {
                        randomCount = 0;
                        randomCheck = rand.nextInt(emptyCount)+1;
                        for (int i = 0; randomCount <= randomCheck; i++) {
                            if (adjCells[i].getUser() instanceof Empty) {
                                randomCount ++;
                            }
                            if(randomCount == randomCheck) {
                                ((Herbivore) cellTable[r][c].getUser()).setMoved(true);
                                ((Herbivore) cellTable[r][c].getUser()).setCell(adjCells[i]);
                                new Empty(cellTable[r][c]);
                                break;
                            }
                        }
                    } else {
                        
                    }
                }
            }
        }
    }
    
    private int getPlantCount(Cell[] arr) {
        if (arr.length == 3) {
            return 0;
        }
        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].getUser() instanceof Plant) {
                count ++;
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
                count ++;
            }
        }
        return count;
    }
    
    public int getRowCount() {
        return rowCount;
    }

    public int getColCount() {
        return colCount;
    }

       public Cell getCellAt(final int row, final int col) {
        return cellTable[row][col];
    }
}
