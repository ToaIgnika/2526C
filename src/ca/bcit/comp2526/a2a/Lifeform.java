package ca.bcit.comp2526.a2a;

import java.awt.Color;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Random;

public class Lifeform extends Element {
    private boolean hasMoved;
    private boolean newBorn;
    private int lifeCount;
    private final int DEFLIFE;
    protected Cell home;
    private eType type;

    private Color ccolor;

    public Lifeform(Cell location, int maxLife, Color color, eType lType) {
        super();
        newBorn = true;
        home = location;
        DEFLIFE = maxLife;
        lifeCount = DEFLIFE;
        hasMoved = false;
        ccolor = color;
        type = lType;
        init();
    }

    void init() {
        home.setColor(ccolor);
        home.setBackground(ccolor);
    }

    void setCell(Cell location) {
        home = location;
        location.setUser(this);
        init();
    }

    void move(eType[] eList) {
        if (!eat(eList)) {
            int cType = home.getElemCount(home.getAdjecentCells(), eType.Empty);
            if (cType != 0) {
                ArrayList<Cell> list = home.getAdjecentCells();
                Random rand = new Random();
                int randomCount = 0;
                int randomCheck = rand.nextInt(cType) + 1;
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).getUser().getType() == eType.Empty) {
                        randomCount++;
                    }
                    if (randomCount == randomCheck) {
                        // System.out.println("moved:" + home.getLocation().x +
                        // " "+ home.getLocation().y);
                        this.setMoved(true);
                        new Empty(home);
                        this.setCell(list.get(i));
                        break;
                    }
                }
            }
        }
    }

    boolean getMoved() {
        return hasMoved;
    }

    void setMoved(boolean isMoved) {
        hasMoved = isMoved;
    }

    int getLife() {
        newBorn = false;
        return lifeCount;
    }

    void resetLife() {
        lifeCount = DEFLIFE;
    }

    void decLife() {
        lifeCount--;
    }

    // array n:
    // n[0]: min same type
    // n[1]: min empty
    // n[2]: min food
    //
    // array menu:
    // all food eTypes

    void getBorn(eType[] menu, int[] n) {
        ArrayList<Cell> list = home.getAdjecentCells();
        // n[0] same type
        if (home.getElemCount(list, type) < n[0]) {
            return;
        }
        // n[1] min empty
        if (home.getElemCount(list, eType.Empty) < n[1]) {
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
        int cType = home.getElemCount(list, eType.Empty);
        Random rand = new Random();
        int randomCount = 0;
        int randomCheck = rand.nextInt(cType) + 1;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getUser().getType() == eType.Empty) {
                randomCount++;
            }
            if (randomCount == randomCheck) {
                Class cls[] = new Class[] { Cell.class };
                try {
                    Class.forName("ca.bcit.comp2526.a2a." + type).getConstructor(cls)
                            .newInstance(list.get(i));
                } catch (InstantiationException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (IllegalArgumentException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (NoSuchMethodException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (SecurityException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                break;
            }
        }
    }

    boolean eat(eType[] menu) {
        ArrayList<Cell> list = home.getAdjecentCells();
        int fCount = 0;
        for (int i = 0; i < menu.length; i++) {
            fCount += home.getElemCount(list, menu[i]);
        }
        if (fCount == 0) {
            return false;
        } else {
            int choice = RandomGenerator.nextNumber(menu.length);
            // int cType = home.getElemCount(list, menu[choice]);
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

    eType getType() {
        return type;
    }
}
