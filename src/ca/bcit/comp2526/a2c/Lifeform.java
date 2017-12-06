package ca.bcit.comp2526.a2c;

import java.awt.Color;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Random;

/**
 * Lifeform.java.
 * 
 *
 * "I made this code longer than usual because I lack time to make it short"
 * 
 * @author Yevhen
 * @version Nov 19, 2017
 *
 */
public class Lifeform extends Element {

    /**
     * Default uid.
     */
    private static final long serialVersionUID = 1L;
    private  int deflife;
    private Cell home;
    private boolean hasMoved;
    private boolean newBorn;
    private int lifeCount;
    
    
    private ETYPE type;

    private Color ccolor;

    /**
     * Constructor for lifeform object.
     * 
     * @param location
     *            of type Cell.
     * @param maxLife
     *            of type Int.
     * @param color
     *            of type Color.
     * @param lType
     *            of type ETYPE.
     */
    public Lifeform(Cell location, int maxLife, Color color, ETYPE lType) {
        super();
        newBorn = true;
        home = location;
        deflife = maxLife;
        lifeCount = deflife;
        hasMoved = false;
        ccolor = color;
        type = lType;
        init();
    }

    /**
     * Initializes the color of the cell.
     */
    void init() {
        home.setColor(ccolor);
        home.setBackground(ccolor);
    }

    /**
     * Setter for Cell.
     * 
     * @param location
     *            of type Cell.
     */
    void setCell(Cell location) {
        home = location;
        location.setUser(this);
        init();
    }

    /**
     * Setter for home.
     * @param location of type Cell.
     */
    public void setHome(Cell location) {
        home = location;
    }
    
    /**
     * Method to move the object.
     * 
     * @param eList
     *            of type ETYPE[].
     */
    void move(ETYPE[] eList) {
        if (hasMoved || newBorn) {
            return;
        }
        if (!eat(eList)) {
            int cType = home.getElemCount(home.getAdjecentCells(), ETYPE.Empty);
            if (cType != 0) {
                ArrayList<Cell> list = home.getAdjecentCells();
                Random rand = new Random();
                int randomCount = 0;
                int randomCheck = rand.nextInt(cType) + 1;
                // int randomCheck = RandomGenerator.nextNumber(cType) + 1;
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).getUser().getType() == ETYPE.Empty) {
                        randomCount++;
                    }
                    if (randomCount == randomCheck) {
                        this.setMoved(true);
                        new Empty(home);
                        this.setCell(list.get(i));
                        break;
                    }
                }
            }
        }
    }

    /**
     * getter for moved status.
     * 
     * @return hasMoved as boolean.
     */
    boolean getMoved() {
        return hasMoved;
    }

    /**
     * setter for isMoved method.
     * 
     * @param isMoved
     *            as boolean.
     */
    void setMoved(boolean isMoved) {
        newBorn = false;
        hasMoved = isMoved;
    }

    /**
     * getter for lifecount.
     * 
     * @return lifeCount as int.
     */
    int getLife() {
        newBorn = false;
        return lifeCount;
    }

    /**
     * resets the life count.
     */
    void resetLife() {
        lifeCount = deflife;
    }

    /**
     * decreases the life count.
     */
    void decLife() {
        lifeCount--;
    }

    /**
     * Born method.
     * 
     * @param menu
     *            of type ETYPE.
     * @param n
     *            of type int[].
     */
    @SuppressWarnings("rawtypes")
    void getBorn(ETYPE[] menu, int[] n) {
        // array n:
        // n[0]: min same type
        // n[1]: min empty
        // n[2]: min food
        //
        // array menu:
        // all food eTypes
        if (newBorn) {
            return;
        }
        ArrayList<Cell> list = home.getAdjecentCells();
        // n[0] same type
        if (home.getElemCount(list, type) < n[0]) {
            return;
        }
        // n[1] min empty
        if (home.getElemCount(list, ETYPE.Empty) < n[1]) {
            return;
        }
        // n[2] food
        int fCount = 0;
        for (int i = 0; i < menu.length; i++) {
            fCount += home.getElemCount(list, menu[0]);
        }
        if (fCount < n[2]) {
            return;
        }
        int cType = home.getElemCount(list, ETYPE.Empty);
        Random rand = new Random();
        int randomCount = 0;
        int randomCheck = rand.nextInt(cType) + 1;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getUser().getType() == ETYPE.Empty) {
                randomCount++;
            }
            if (randomCount == randomCheck) {
                          
                Class[] cls = new Class[] {Cell.class };
                try {
                    list.get(i).setUser(
                    (Element) Class.forName("ca.bcit.comp2526.a2c." + type)
                            .getConstructor(cls).newInstance(list.get(i)));
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (SecurityException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                break;
            }
        }
    }

    /**
     * eat method.
     * @param menu of type ETYPE.
     * @return true if ate.
     */
    boolean eat(ETYPE[] menu) {
       
        ArrayList<Cell> list = home.getAdjecentCells();
        int fCount = 0;
        for (int i = 0; i < menu.length; i++) {
            fCount += home.getElemCount(list, menu[i]);
        }
        if (fCount == 0) {
            return false;
        } else {
            Random rand = new Random();
            int randomCount = 0;
            int randomCheck = rand.nextInt(fCount) + 1;
            for (int i = 0; randomCount <= randomCheck; i++) {
                for (int j = 0; j < menu.length; j++) {
                    if (list.get(i).getUser().getType() == menu[j]) {
                        randomCount++;
                    }
                }

                if (randomCount == randomCheck) {
                    this.setMoved(true);
                    new Empty(home);
                    this.setCell(list.get(i));
                    this.resetLife();
                    break;
                }
            }
            return true;
        }
    }

    /**
     * Getter for cell type.
     * @return type as ETYPE.
     */
    ETYPE getType() {
        return type;
    }
}
