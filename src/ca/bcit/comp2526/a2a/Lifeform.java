package ca.bcit.comp2526.a2a;

import java.awt.Color;
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
                        //System.out.println("moved:" + home.getLocation().x + " "+ home.getLocation().y);
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

    boolean getBorn() {
        return newBorn;
    }

    boolean eat(eType[] menu) {
        ArrayList<Cell> list = home.getAdjecentCells();
        int choice = RandomGenerator.nextNumber(menu.length);
        int cType = home.getElemCount(list, menu[choice]);
        if (cType == 0) {
            return false;
        } else {
            Random rand = new Random();
            int randomCount = 0;
            int randomCheck = rand.nextInt(cType) + 1;
            for (int i = 0; randomCount <= randomCheck; i++) {
                if (list.get(i).getUser().getType() == menu[choice]) {
                    randomCount++;
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
