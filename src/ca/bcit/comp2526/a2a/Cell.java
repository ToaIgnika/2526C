package ca.bcit.comp2526.a2a;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

/**
 * Cell.java.
 * 
 * Cell class for Game of Life, extends JPanel.
 *
 * "I made this code longer than usual because I lack time to make it short"
 * 
 * @author Yevhen
 * @version Oct 19, 2017
 *
 */
public class Cell extends JPanel {

    /**
     * Serial version UID.
     */
    private static final long serialVersionUID = 1L;
    private Color cellColor;
    private Point point;
    private Object cellUser;
    private Cell[] surround;
    private World cellWorld;

    /**
     * Constructor for object of type Cell.
     * 
     * @param world
     *            of type World.
     * @param row
     *            of type int.
     * @param column
     *            of type int.
     */
    public Cell(World world, int row, int column) {
        cellWorld = world;
        cellColor = Color.WHITE;
        cellUser = null;
        point = new Point();
        point.x = row;
        point.y = column;

    }

    /**
     * method to initialise JFrame properties.
     */
    public void init() {
        setBackground(cellColor);
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        setPreferredSize(
                new Dimension(Integer.valueOf("20"), Integer.valueOf("20")));
    }

    /**
     * Getter for cell location.
     * 
     * @return point of type point.
     */
    public Point getLocation() {
        return point;
    }

    /**
     * Getter for adjacent cells of the cell.
     * 
     * @return Cell[] as adjacent cell array.
     */
    public Cell[] getAdjacentCells() {
        if ((point.x == 0 && point.y == 0)
                || (point.x == 0 && point.y == cellWorld.getColCount() - 1)
                || (point.x == cellWorld.getRowCount() - 1
                        && point.y == cellWorld.getColCount() - 1)
                || (point.x == cellWorld.getRowCount() - 1 && point.y == 0)) {
            return getCorner();
        } else if ((point.x == 0) || (point.y == cellWorld.getColCount() - 1)
                || (point.x == cellWorld.getRowCount() - 1) || (point.y == 0)) {
            return getSide();
        } else {
            surround = new Cell[Integer.valueOf("8")];
            surround[0] = cellWorld.getCellAt(point.x + 1, point.y);
            surround[1] = cellWorld.getCellAt(point.x - 1, point.y);
            surround[2] = cellWorld.getCellAt(point.x, point.y - 1);
            surround[2 + 1] = cellWorld.getCellAt(point.x, point.y + 1);
            surround[2 + 2] = cellWorld.getCellAt(point.x + 1, point.y + 1);
            surround[2 + 2 + 1] = cellWorld.getCellAt(point.x + 1, point.y - 1);
            surround[2 + 2 + 2] = cellWorld.getCellAt(point.x - 1, point.y + 1);
            surround[2 + 2 + 2 + 1] = cellWorld.getCellAt(point.x - 1,
                    point.y - 1);
        }
        return surround;
    }

    /**
     * Check for getting cornered cell.
     * 
     * @return cell[].
     */
    private Cell[] getCorner() {
        if (point.x == 0 && point.y == 0) {
            surround = new Cell[Integer.valueOf("3")];
            surround[0] = cellWorld.getCellAt(point.x + 1, point.y);
            surround[1] = cellWorld.getCellAt(point.x, point.y + 1);
            surround[2] = cellWorld.getCellAt(point.x + 1, point.y + 1);
        } else if (point.x == 0 && point.y == cellWorld.getColCount() - 1) {
            surround = new Cell[Integer.valueOf("3")];
            surround[0] = cellWorld.getCellAt(point.x + 1, point.y);
            surround[1] = cellWorld.getCellAt(point.x, point.y - 1);
            surround[2] = cellWorld.getCellAt(point.x + 1, point.y - 1);
        } else if (point.x == cellWorld.getRowCount() - 1
                && point.y == cellWorld.getColCount() - 1) {
            surround = new Cell[Integer.valueOf("3")];
            surround[0] = cellWorld.getCellAt(point.x - 1, point.y);
            surround[1] = cellWorld.getCellAt(point.x, point.y);
            surround[2] = cellWorld.getCellAt(point.x, point.y - 1);
        } else if (point.x == cellWorld.getRowCount() - 1 && point.y == 0) {
            surround = new Cell[Integer.valueOf("3")];
            surround[0] = cellWorld.getCellAt(point.x, point.y + 1);
            surround[1] = cellWorld.getCellAt(point.x - 1, point.y + 1);
            surround[2] = cellWorld.getCellAt(point.x - 1, point.y);
        }
        return surround;
    }

    /**
     * Check for getting side cells.
     * @return list of adjecent cells.
     */
    private Cell[] getSide() {
        if (point.x == 0) {
            surround = new Cell[Integer.valueOf("5")];
            surround[0] = cellWorld.getCellAt(point.x, point.y - 1);
            surround[1] = cellWorld.getCellAt(point.x, point.y + 1);
            surround[2] = cellWorld.getCellAt(point.x + 1, point.y - 1);
            surround[2 + 1] = cellWorld.getCellAt(point.x + 1, point.y + 1);
            surround[2 + 2] = cellWorld.getCellAt(point.x + 1, point.y);
        } else if (point.y == cellWorld.getColCount() - 1) {
            surround = new Cell[Integer.valueOf("5")];
            surround[0] = cellWorld.getCellAt(point.x + 1, point.y);
            surround[1] = cellWorld.getCellAt(point.x - 1, point.y);
            surround[2] = cellWorld.getCellAt(point.x, point.y - 1);
            surround[2 + 1] = cellWorld.getCellAt(point.x + 1, point.y - 1);
            surround[2 + 2] = cellWorld.getCellAt(point.x - 1, point.y - 1);
        } else if (point.x == cellWorld.getRowCount() - 1) {
            surround = new Cell[Integer.valueOf("5")];
            surround[0] = cellWorld.getCellAt(point.x, point.y - 1);
            surround[1] = cellWorld.getCellAt(point.x, point.y + 1);
            surround[2] = cellWorld.getCellAt(point.x - 1, point.y);
            surround[2 + 1] = cellWorld.getCellAt(point.x - 1, point.y + 1);
            surround[2 + 2] = cellWorld.getCellAt(point.x - 1, point.y - 1);
        } else if (point.y == 0) {
            surround = new Cell[Integer.valueOf("5")];
            surround[0] = cellWorld.getCellAt(point.x + 1, point.y);
            surround[1] = cellWorld.getCellAt(point.x - 1, point.y);
            surround[2] = cellWorld.getCellAt(point.x, point.y + 1);
            surround[2 + 1] = cellWorld.getCellAt(point.x - 1, point.y + 1);
            surround[2 + 2] = cellWorld.getCellAt(point.x + 1, point.y + 1);
        }
        return surround;
    }

    /**
     * Getter for plant count.
     * 
     * @param arr
     *            of type Cell.
     * @return count of plants.
     */
    public int getPlantCount(Cell[] arr) {
        if (arr.length == Integer.valueOf("3")) {
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

    /**
     * Getter for empty cells count.
     * 
     * @param arr
     *            of type Cell[].
     * @return number of empty cells as int.
     */
    public int getEmptyCount(Cell[] arr) {
        if (arr.length == Integer.valueOf("3")) {
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

    /**
     * Setter for user of the cell.
     * 
     * @param j
     *            as Object j.
     */
    public void setUser(Object j) {
        cellUser = j;
    }

    /**
     * Setter for color.
     * 
     * @param c
     *            of type Color.
     */
    public void setColor(Color c) {
        cellColor = c;
    }

    /**
     * getter for cell user.
     * 
     * @return cellUser as Object.
     */
    public Object getUser() {
        return cellUser;
    }
}
