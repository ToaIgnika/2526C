package ca.bcit.comp2526.a2a;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import javax.swing.JFrame;

/**
 * Main.java.
 * 
 * Main file for running the Game Of Life program.
 *
 * "I made this code longer than usual 
 * because I lack time to make it short"
 * @author Yevhen
 * @version Oct 19, 2017
 *
 */
public final class Main {
    private static final Toolkit TOOLKIT;
    
    /**
     * Constant for frame width size.
     */
    private static final float SCREENW = 0.80f;
    
    /**
     * Constant for frame height size.
     */
    private static final float SCREENH = 0.80f;
    
    /**
     * Constant for frame width size.
     */
    private static final float SCREENWCHECK = 100.0f;
    
    /**
     * Constant for frame height size.
     */
    private static final float SCREENHCHECK = 100.0f;
    
    static {
        TOOLKIT = Toolkit.getDefaultToolkit();
    }
    
    /**
     * Default constructor for main.
     */
    private Main() {
    }

    /**
     * Drives the program.
     * @param argv command line arguments.
     */
    public static void main(final String[] argv) {
        final GameFrame frame;
        final World world;

        RandomGenerator.reset();
        world = new World(Integer.valueOf("25"), Integer.valueOf("25"));
        world.init();
        frame = new GameFrame(world);
        position(frame);
        frame.init();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    /**
     * Sets size and position of the gameframe screen.
     * @param frame of type GameFrame.
     */
    private static void position(final GameFrame frame) {
        final Dimension size;
        
        size = calculateScreenArea(SCREENW, SCREENH);
        frame.setSize(size);
        frame.setLocation(centreOnScreen(size));
    }

    /**
     * Method to center the game frame.
     * @param size of type Dimension.
     * @return point of screen.
     */
    public static Point centreOnScreen(final Dimension size) {
        final Dimension screenSize;

        if (size == null) {
            throw new IllegalArgumentException("Size cannot be null");
        }

        screenSize = TOOLKIT.getScreenSize();

        return (new Point((screenSize.width - size.width) / 2,
                (screenSize.height - size.height) / 2));
    }

    /**
     * Method to calculate screen area.
     * @param widthPercent of type float.
     * @param heightPercent of type float.
     * @return size of the fram of type Dimensions.
     */
    public static Dimension calculateScreenArea(
            final float widthPercent, final float heightPercent) {
        final Dimension screenSize;
        final Dimension area;
        final int width;
        final int height;
        final int size;

        if ((widthPercent <= 0.0f) || (widthPercent > SCREENWCHECK)) {
            throw new IllegalArgumentException("widthPercent cannot be " 
        + "<= 0 or > 100 - got: " + widthPercent);
        }

        if ((heightPercent <= 0.0f) || (heightPercent > SCREENHCHECK)) {
            throw new IllegalArgumentException("heightPercent cannot be " 
        + "<= 0 or > 100 - got: " + heightPercent);
        }

        screenSize = TOOLKIT.getScreenSize();
        width = (int) (screenSize.width * widthPercent);
        height = (int) (screenSize.height * heightPercent);
        size = Math.min(width, height);
        area = new Dimension(size, size);

        return (area);
    }
}
