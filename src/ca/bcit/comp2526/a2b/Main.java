package ca.bcit.comp2526.a2b;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.io.IOException;

import javax.swing.JFrame;

/**
 * Main.java.
 * 
 * Main file for running the Game Of Life program.
 *
 * "I made this code longer than usual because I lack time to make it short"
 * 
 * @author Yevhen
 * @version Oct 19, 2017
 *
 */
public final class Main extends JFrame {
    /**
     * Serial version id.
     */
    private static final long serialVersionUID = 1L;

    private static final Toolkit TOOLKIT;

    /**
     * Constant for frame width size.
     */
    private static final float SCREENW = 0.80f;

    /**
     * Constant for frame height size.
     */
    private static final float SCREENH = 0.85f;

    /**
     * Constant for frame width size.
     */
    private static final float SCREENWCHECK = 100.0f;

    /**
     * Constant for frame height size.
     */
    private static final float SCREENHCHECK = 100.0f;

    private final GameFrame frame;

    private final TimerFrame timer;

    static {
        TOOLKIT = Toolkit.getDefaultToolkit();
    }

    /**
     * Default constructor for main.
     * 
     * @throws IOException a.
     * @throws CouldNotAddException b.
     * @throws CouldNotRemoveException c.
     */
    private Main()
            throws IOException, CouldNotAddException, CouldNotRemoveException {
        super("Assignment2");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        World world;
        setLayout(new BorderLayout());
        RandomGenerator.reset();
        world = new World(Integer.valueOf("25"), Integer.valueOf("25"));
        world.init();
        frame = new GameFrame(world);
        frame.init();
        timer = new TimerFrame(frame);
        add(frame, BorderLayout.CENTER);
        add(new ControlPanel(timer, frame), BorderLayout.NORTH);
        setSize(calculateScreenArea(SCREENW, SCREENH));
        setLocation(centreOnScreen(calculateScreenArea(SCREENW, SCREENH)));
        setVisible(true);
    }

    /**
     * Drives the program.
     * 
     * @param argv
     *            command line arguments.
     * @throws IOException a.
     * @throws CouldNotAddException b.
     * @throws CouldNotRemoveException c.
     */
    public static void main(final String[] argv)
            throws IOException, CouldNotAddException, CouldNotRemoveException {
        new Main();
    }

    /**
     * Method to center the game frame.
     * 
     * @param size
     *            of type Dimension.
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
     * 
     * @param widthPercent
     *            of type float.
     * @param heightPercent
     *            of type float.
     * @return size of the fram of type Dimensions.
     */
    public static Dimension calculateScreenArea(final float widthPercent,
            final float heightPercent) {
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
