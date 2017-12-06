package ca.bcit.comp2526.a2c;

import java.io.Serializable;


/**
 * World.java.
 * 
 *
 * "I made this code longer than usual because I lack time to make it short"
 * 
 * @author Yevhen
 * @version Oct 19, 2017
 *
 */

public class World implements Serializable {

    /**
     * Def UID.
     */
    private static final long serialVersionUID = 1L;

    private int rowCount;

    private int colCount;

    private Cell[][] cellTable;

    private int rnJesus;
    
    private boolean isNew;

    /**
     * Constructor for world object.
     * 
     * @param width
     *            of int.
     * @param hight
     *            of int.
     */
    public World(final int width, final int hight) {
        isNew = false;
        rowCount = hight;
        colCount = width;
        cellTable = new Cell[rowCount][colCount];
    }
    
    /**
     * getter for world Status.
     * @return isNew as boolean.
     */
    public boolean getStatus() {
        return isNew;
    }
    
    /**
     * setter for world status.
     */
    public void flickStatus() {
        isNew = !isNew;
    }

    /**
     * Initialize the world of cells.
     */
    public void init() {

        for (int r = 0; r < rowCount; r++) {
            for (int c = 0; c < colCount; c++) {
                rnJesus = RandomGenerator.nextNumber(Integer.valueOf("100"));
                if (rnJesus >= Integer.valueOf("80")) {
                    cellTable[r][c] = new Cell(World.this, r, c);
                    cellTable[r][c].setUser(new Herbivore(cellTable[r][c]));
                    cellTable[r][c].init();
                } else if (rnJesus >= Integer.valueOf("50")) {
                    cellTable[r][c] = new Cell(World.this, r, c);
                    cellTable[r][c].setUser(new Plant(cellTable[r][c]));
                    cellTable[r][c].init();
                } else if (rnJesus >= Integer.valueOf("40")) {
                    cellTable[r][c] = new Cell(World.this, r, c);
                    cellTable[r][c].setUser(new Carnivore(cellTable[r][c]));
                    cellTable[r][c].init();
                } else if (rnJesus >= Integer.valueOf("32")) {
                    cellTable[r][c] = new Cell(World.this, r, c);
                    cellTable[r][c].setUser(new Omnivore(cellTable[r][c]));
                    cellTable[r][c].init();
                } else {
                    cellTable[r][c] = new Cell(World.this, r, c);
                    cellTable[r][c].setUser(new Empty(cellTable[r][c]));
                    cellTable[r][c].init();
                }
            }
        }

        for (int r = 0; r < rowCount; r++) {
            for (int c = 0; c < colCount; c++) {
                cellTable[r][c].setAdjacentCells();

            }
        }
    }

    /**
     * Method to recreate a new grid.
     */
    public void reinit() {

        for (int r = 0; r < rowCount; r++) {
            for (int c = 0; c < colCount; c++) {
                rnJesus = RandomGenerator.nextNumber(Integer.valueOf("100"));
                if (rnJesus >= Integer.valueOf("80")) {
                    cellTable[r][c].setUser(new Herbivore(cellTable[r][c]));
                    cellTable[r][c].init();
                } else if (rnJesus >= Integer.valueOf("50")) {
                    cellTable[r][c].setUser(new Plant(cellTable[r][c]));
                    cellTable[r][c].init();
                } else if (rnJesus >= Integer.valueOf("40")) {
                    cellTable[r][c].setUser(new Carnivore(cellTable[r][c]));
                    cellTable[r][c].init();
                } else if (rnJesus >= Integer.valueOf("32")) {
                    cellTable[r][c].setUser(new Omnivore(cellTable[r][c]));
                    cellTable[r][c].init();
                } else {
                    cellTable[r][c].setUser(new Empty(cellTable[r][c]));
                    cellTable[r][c].init();
                }
            }
        }

        for (int r = 0; r < rowCount; r++) {
            for (int c = 0; c < colCount; c++) {
                cellTable[r][c].setAdjacentCells();
            }
        }
    }

    /**
     * Take turn method.
     */
    public void takeTurn() {
        // step one - remove the dead animals
        
        for (int r = 0; r < rowCount; r++) {
            for (int c = 0; c < colCount; c++) {
                if (cellTable[r][c].getUser().getType() != ETYPE.Plant) {
                    (cellTable[r][c].getUser()).decLife();
                    (cellTable[r][c].getUser()).setMoved(false);
                    if ((cellTable[r][c].getUser()).getLife() == 0) {
                        cellTable[r][c].setUser(new Empty(cellTable[r][c]));
                    }
                }
            }
        }
        // step two - plague the plants
        for (int r = 0; r < rowCount; r++) {
            for (int c = 0; c < colCount; c++) {
                if (cellTable[r][c].getUser().getType() == ETYPE.Plant) {
                   ((Plant) cellTable[r][c].getUser()).pollinate();
                } else {
                    cellTable[r][c].getUser().getBorn();
                }
            }
        }
        
        // step 3 - move the mofaka animals AND possibly eat
        for (int r = 0; r < rowCount; r++) {
            for (int c = 0; c < colCount; c++) {
                if (cellTable[r][c].getUser().getType() != ETYPE.Plant
                        && !cellTable[r][c].getUser().getMoved()) {
                    (cellTable[r][c].getUser()).move();

                }
            }
        }
    }

    /**
     * getter for row count.
     * 
     * @return rowCount as int.
     */
    public int getRowCount() {
        return rowCount;
    }

    /**
     * getter for col count.
     * 
     * @return colCount as int.
     */
    public int getColCount() {
        return colCount;
    }

    /**
     * getter for cell at specific location.
     * 
     * @param row
     *            of type int.
     * @param col
     *            of type int.
     * @return Cell as Cell.
     */
    public Cell getCellAt(final int row, final int col) {
        return cellTable[row][col];
    }

    /**
     * getter for world table.
     * @return cellTable.
     */
    public Cell[][] getWorld() {
        return cellTable;
    }

    /**
     * copy function for world.
     * @param w of type World.
     */
    public void copy(World w) {
        this.rowCount = w.getRowCount();
        this.colCount = w.getColCount();
        this.cellTable = new Cell[rowCount][colCount];
        for (int r = 0; r < this.rowCount; r++) {
            for (int c = 0; c < this.colCount; c++) {
                cellTable[r][c].reinit(w.getCellAt(r, c));
                //cellTable[r][c].setAdjacentCells();
            }
        }
        //return w;
    }
  
    /**
     * reloads the cell table.
     */
    public void reload() {
        for (int r = 0; r < rowCount; r++) {
            for (int c = 0; c < colCount; c++) {
                cellTable[r][c].init();
                cellTable[r][c].setAdjacentCells();
                
            }
        }
    }
}
