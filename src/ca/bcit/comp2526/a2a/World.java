package ca.bcit.comp2526.a2a;

import java.awt.*;
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
    
    /**
     * public void takeTurn() 1. removes dead herbivores, 2. checks each plant
     * to see if it "seeds," and then 3. moves remaining living Herbivores one
     * Cell (and they eat, if possible)
     */
    /*
    public void takeTurn() {

        // check each plant to see if it seeds
        setCellWorld(); // initialize the arrays if neighbours
        for (int r = 0; r < rowCount; r++) {
            for (int c = 0; c < colCount; c++) {
                if (typeToInt(cellTable[r][c]) == 1) {
                    emptyCount = cellTable[r][c].getEmptyCount();
                    plantCount = cellTable[r][c].getPlantCount();
                    if (emptyCount >= 3 && plantCount >= 2) {
                        randomPositionPlant(cellTable[r][c], 0, emptyCount);
                    }
                }
            }
        }
        // move remaining living herbivores one cell
        setCellWorld(); // initialize the arrays if neighbours
        for (int r = 0; r < rowCount; r++) {
            for (int c = 0; c < colCount; c++) {
                if (typeToInt(cellTable[r][c]) == 2) {
                    emptyCount = cellTable[r][c].getEmptyCount();
                    plantCount = cellTable[r][c].getPlantCount();
                    if (plantCount >= 1) {
                        randomPositionHerbivore(cellTable[r][c], 1, plantCount);
                    } else if (emptyCount >= 1) {
                        randomPositionHerbivore(cellTable[r][c], 0, emptyCount);
                    } else {
                        
                    }
                }
            }
        }
    }
*/
    
    private int rowDecode(int i) {
        switch (i) {
        case 1:
            return -1;
        case 2:
            return -1;
        case 3:
            return -1;
        case 4:
            return 0;
        case 5:
            return 0;
        case 6:
            return 1;
        case 7:
            return 1;
        case 8:
            return 1;
        }
        return 0;
    }
    
    private int colDecode(int i) {
        switch (i) {
        case 1:
            return -1;
        case 2:
            return 0;
        case 3:
            return 1;
        case 4:
            return 1;
        case 5:
            return 1;
        case 6:
            return 0;
        case 7:
            return -1;
        case 8:
            return -1;
        }
        return 0;
    }
    
    public int getRowCount() {
        return rowCount;
    }

    public int getColCount() {
        return colCount;
    }

    
    /**
     * Vital function to analyse each cell in the grid.
     * 
     * @param cell
     *            of type Cell.
     * @return integer array. UNDERSTANDING THE ARRAY: I
     * NDEX[0] - type of cell:0-null, 1-plant, 2-herbivore 
     * INDEX[1-8] - type of cell in nearber area 0-null, 1-plant, 2-herbivore 
     * 1|2|3 
     * 8|x|4 
     * 7|6|5 
     * INDEX[9] - count of plant cells 
     * INDEX[10] - count of herbivore cells
     * INDEX[11] -count of empty cells
     
    private void setCellWorld() {
        Cell temp;
        for (int r = 0; r < rowCount; r++) {
            for (int c = 0; c < colCount; c++) {
                temp = cellTable[r][c];
                checkTopLeft(r,c,temp);
                checkTopRight(r,c,temp);
                checkBotLeft(r,c,temp);
                checkBotRight(r,c,temp);
            }
        }
    }
    
    
     * Helper method to suppert cellWorld concept.
     * @param cell of type Cell.
     * @return int depending on class type.
     
    private int typeToInt(Cell cell) {
        if (cell.getUser() == null) {
            return 0;
        } else if (cell.getUser() instanceof Plant) {
            return 1;
        } else if (cell.getUser() instanceof Herbivore) {
            return 2;
        } else {
            return -1;
        }
    }
    
    private boolean checkTop(int r, int c, Cell cell) {
        if (r == 0) {
            cell.setSurround(-1, 2);
            return false;
        } 
        cellTable[r - 1][c].setSurround(typeToInt(cell), 6);
        return true;
    }
    
    private boolean checkBot(int r, int c, Cell cell) {
        if (r == rowCount-1) {
            cell.setSurround(-1, 6);
            return false;
        } 
        cellTable[r + 1][c].setSurround(typeToInt(cell), 2);
        return true;
    }
    
    private boolean checkLeft(int r, int c, Cell cell) {
        if (c == 0) {
            cell.setSurround(-1, 8);
            return false;
        } 
        cellTable[r][c - 1].setSurround(typeToInt(cell), 4);
        return true;
    }
    
    private boolean checkRight(int r, int c, Cell cell) {
        if (c == colCount-1) {
            cell.setSurround(-1, 4);
            return false;
        } 
        cellTable[r][c + 1].setSurround(typeToInt(cell), 8);
        return true;
    }
    
    private void checkTopLeft(int r, int c, Cell cell) {
        if (checkTop(r,c,cell) && checkLeft(r,c,cell)) {
            cellTable[r - 1][c - 1].setSurround(typeToInt(cell), 5);
        } else {
            cell.setSurround(-1, 1);
        }
    }
    
    private void checkTopRight(int r, int c, Cell cell) {
        if (checkTop(r,c,cell) && checkRight(r,c,cell)) {
            cellTable[r - 1][c + 1].setSurround(typeToInt(cell), 7);
        } else {
            cell.setSurround(-1, 3);
        }
    }
    
    private void checkBotLeft(int r, int c, Cell cell) {
        if (checkBot(r,c,cell) && checkLeft(r,c,cell)) {
            cellTable[r + 1][c - 1].setSurround(typeToInt(cell), 3);
        } else {
            cell.setSurround(-1, 7);
        }
    }
    
    private void checkBotRight(int r, int c, Cell cell) {
        if (checkBot(r,c,cell) && checkRight(r,c,cell)) {
            cellTable[r + 1][c + 1].setSurround(typeToInt(cell), 1);
        } else {
            cell.setSurround(-1, 5);
        }
    }
    */
    
    public Cell getCellAt(final int row, final int col) {
        return cellTable[row][col];
    }
}
