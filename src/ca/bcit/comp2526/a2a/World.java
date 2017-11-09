package ca.bcit.comp2526.a2a;

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
public class World {

    private int rowCount;

    private int colCount;

    private Cell[][] cellTable;

    private int rnJesus;

    /**
     * Constructor for world object.
     * 
     * @param width
     *            of int.
     * @param hight
     *            of int.
     */
    public World(final int width, final int hight) {
        rowCount = hight;
        colCount = width;
    }

    /**
     * Initialize the world of cells.
     */
    public void init() {
        cellTable = new Cell[rowCount][colCount];
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
                } else if (rnJesus >= 40) {
                    cellTable[r][c] = new Cell(World.this, r, c);
                    cellTable[r][c].setUser(new Carnivore(cellTable[r][c]));
                    cellTable[r][c].init();
                } else if (rnJesus >= 32) {
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
     * Take turn method.
     */
    public void takeTurn() {
        // step one - remove the dead animals
        for (int r = 0; r < rowCount; r++) {
            for (int c = 0; c < colCount; c++) {
                if (cellTable[r][c].getUser().getType() != eType.Plant) {
                    (cellTable[r][c].getUser()).decLife();
                    ( cellTable[r][c].getUser()).setMoved(false);
                    if ((cellTable[r][c].getUser())
                            .getLife() == 0) {
                        cellTable[r][c].setUser(new Empty(cellTable[r][c]));
                    }
                }                 
            }
        }
        // step two - plague the plants
        for (int r = 0; r < rowCount; r++) {
            for (int c = 0; c < colCount; c++) {
                if (cellTable[r][c].getUser().getType() == eType.Plant) {
                    ((Plant) cellTable[r][c].getUser()).pollinate();
                } else {
                    cellTable[r][c].getUser().getBorn();
                }
            }
        }

        // step 3 - move the mofaka animals AND possibly eat
        for (int r = 0; r < rowCount; r++) {
            for (int c = 0; c < colCount; c++) {
                if (cellTable[r][c].getUser().getType() != eType.Plant
                        && ! cellTable[r][c].getUser().getMoved()) {
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
}
