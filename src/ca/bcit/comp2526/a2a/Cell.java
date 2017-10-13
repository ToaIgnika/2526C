package ca.bcit.comp2526.a2a;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class Cell extends JPanel{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private Color cellColor;
    private Point point;
    private Object cellUser;
    private Cell[] surround;
    private World cellWorld;

    public Cell(World world, int row, int column) {
        cellWorld = world;
        cellColor = Color.WHITE;
        cellUser = null;
        point = new Point();
        point.x = row;
        point.y = column;
        
    }

    public void init() {
        setBackground(cellColor);
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        setPreferredSize(new Dimension(20, 20));
    }
    
    public Point getLocation() {
        return point;
    }
    
    public Cell[] getAdjacentCells() {
        if (point.x == 0 && point.y == 0) {
            surround = new Cell[3];
            surround[0] = cellWorld.getCellAt(point.x + 1, point.y);
            surround[1] = cellWorld.getCellAt(point.x, point.y + 1);
            surround[2] = cellWorld.getCellAt(point.x + 1, point.y + 1);
        } else if (point.x == 0 && point.y == cellWorld.getColCount() - 1) {
            surround = new Cell[3];
            surround[0] = cellWorld.getCellAt(point.x + 1, point.y);
            surround[1] = cellWorld.getCellAt(point.x, point.y - 1);
            surround[2] = cellWorld.getCellAt(point.x + 1, point.y - 1);
        } else if (point.x == cellWorld.getRowCount() - 1 
                && point.y == cellWorld.getColCount() -1) {
            surround = new Cell[3];
            surround[0] = cellWorld.getCellAt(point.x - 1, point.y);
            surround[1] = cellWorld.getCellAt(point.x, point.y);
            surround[2] = cellWorld.getCellAt(point.x, point.y - 1);
        }else if (point.x == cellWorld.getRowCount() -1 && point.y == 0) {
            surround = new Cell[3];
            surround[0] = cellWorld.getCellAt(point.x, point.y + 1);
            surround[1] = cellWorld.getCellAt(point.x - 1, point.y + 1);
            surround[2] = cellWorld.getCellAt(point.x - 1, point.y);
        }  else if (point.x == 0) {
            surround = new Cell[5];
            surround[0] = cellWorld.getCellAt(point.x, point.y - 1);
            surround[1] = cellWorld.getCellAt(point.x, point.y + 1);
            surround[2] = cellWorld.getCellAt(point.x + 1, point.y - 1);
            surround[3] = cellWorld.getCellAt(point.x + 1, point.y + 1);
            surround[4] = cellWorld.getCellAt(point.x + 1, point.y);
        } else if (point.y == cellWorld.getColCount() -1) {
            surround = new Cell[5];
            surround[0] = cellWorld.getCellAt(point.x + 1, point.y);
            surround[1] = cellWorld.getCellAt(point.x - 1, point.y);
            surround[2] = cellWorld.getCellAt(point.x, point.y - 1);
            surround[3] = cellWorld.getCellAt(point.x + 1, point.y -1);
            surround[4] = cellWorld.getCellAt(point.x - 1, point.y -1);
        } else if (point.x == cellWorld.getRowCount() - 1) {
            surround = new Cell[5];
            surround[0] = cellWorld.getCellAt(point.x, point.y - 1);
            surround[1] = cellWorld.getCellAt(point.x, point.y + 1);
            surround[2] = cellWorld.getCellAt(point.x - 1, point.y);
            surround[3] = cellWorld.getCellAt(point.x - 1, point.y + 1);
            surround[4] = cellWorld.getCellAt(point.x - 1, point.y - 1);
        } else if (point.y == 0) {
            surround = new Cell[5];
            surround[0] = cellWorld.getCellAt(point.x + 1, point.y );
            surround[1] = cellWorld.getCellAt(point.x - 1, point.y);
            surround[2] = cellWorld.getCellAt(point.x, point.y + 1);
            surround[3] = cellWorld.getCellAt(point.x - 1, point.y + 1);
            surround[4] = cellWorld.getCellAt(point.x +1, point.y + 1);
        } else {
            surround = new Cell[8];
            surround[0] = cellWorld.getCellAt(point.x + 1, point.y);
            surround[1] = cellWorld.getCellAt(point.x - 1, point.y);
            surround[2] = cellWorld.getCellAt(point.x, point.y - 1);
            surround[3] = cellWorld.getCellAt(point.x, point.y + 1);
            surround[4] = cellWorld.getCellAt(point.x + 1, point.y + 1);
            surround[5] = cellWorld.getCellAt(point.x + 1, point.y - 1);
            surround[6] = cellWorld.getCellAt(point.x - 1, point.y + 1);
            surround[7] = cellWorld.getCellAt(point.x - 1, point.y - 1);
        }
        return surround;
    }
    
    public int getPlantCount(Cell[] arr) {
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
    
    public void setUser(Object j) {
        cellUser = j;
    }
    
    public void setColor(Color c) {
        cellColor = c;
    }
    
    public Object getUser() {
        return cellUser;
    }
}
