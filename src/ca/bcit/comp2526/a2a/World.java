package ca.bcit.comp2526.a2a;


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
        for (int r = 0; r < rowCount; r++) {
            for (int c = 0; c < colCount; c++) {
                if (cellTable[r][c].getUser() instanceof Plant) {
                    ((Plant) cellTable[r][c].getUser()).pollinate();
                }
            }
        }
        
        // step 3 - move the mofaka animals AND possibly eat
        for (int r = 0; r < rowCount; r++) {
            for (int c = 0; c < colCount; c++) {
                if (cellTable[r][c].getUser() instanceof Herbivore && !((Herbivore) cellTable[r][c].getUser()).getMoved()) {
                    ((Herbivore) cellTable[r][c].getUser()).move();
                }
            }
        }
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
