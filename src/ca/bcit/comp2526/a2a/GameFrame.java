package ca.bcit.comp2526.a2a;

import java.awt.GridLayout;
import javax.swing.JFrame;

/**
 * GameFrame.
 * 
 *
 * "I made this code longer than usual 
 * because I lack time to make it short"
 * @author Yevhen
 * @version Oct 19, 2017
 *
 */
public class GameFrame extends JFrame {
    
    /**
     * UID version. 
     */
    private static final long serialVersionUID = 1L;
    private final World world;

    /**
     * Constructor for gameframe object.
     * @param w of type World.
     */
    public GameFrame(final World w) {
        world = w;
    }

    /**
     * initialize the layout.
     */
    public void init() {
        setTitle("Assignment 2a");
        setLayout(new GridLayout(world.getRowCount(), world.getColCount()));

        for (int row = 0; row < world.getRowCount(); row++) {
            for (int col = 0; col < world.getColCount(); col++) {
                add(world.getCellAt(row, col));
            }
        }

       
        addMouseListener(new TurnListener(this));
    }

    /**
     * Take turn.
     */
    public void takeTurn() {
        world.takeTurn();
        repaint();
    }
}
