package ca.bcit.comp2526.a2a;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.util.ArrayList;

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
    private Element cellUser;
    private ArrayList<Cell> surround;
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
    
    public Cell() {
        cellUser = null;
        point = new Point();
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
    public void  setAdjacentCells() {
        surround = new ArrayList<Cell>();
        for (int x = point.x -1; x <= point.x+1; x++) {
            for (int y = point.y-1; y<= point.y+1; y++) {
                if (!(x== point.x && y==point.y)) {
                    if (x >= 0 && x < cellWorld.getRowCount()  && y >= 0 && y < cellWorld.getColCount()) {
                        surround.add(cellWorld.getCellAt(x,y));
                    }
                }
            }
        }
    }
    
    public ArrayList<Cell> getAdjecentCells() {
        return surround;
    }
    
    public int getElemCount(ArrayList<Cell> arr, eType t) {
        int count = 0;
        eType s;
        for (int i = 0; i < arr.size(); i++) {
            s = arr.get(i).getUser().getType();
            if (arr.get(i).getUser().getType() == t) {
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
    public void setUser(Element j) {
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
    public Element getUser() {
        return cellUser;
    }
    
    
    
}
