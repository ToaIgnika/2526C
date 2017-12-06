package ca.bcit.comp2526.a2c;

import java.awt.GridLayout;

import javax.swing.JPanel;

/**
 * GameFrame.
 * 
 *
 * "I made this code longer than usual because I lack time to make it short"
 * 
 * @author Yevhen
 * @version Oct 19, 2017
 *
 */
public class GameFrame extends JPanel {

    /**
     * UID version.
     */
    private static final long serialVersionUID = 1L;
    private final World world;
    private Element[][] elementList;
    private DoubleLinkedList<Element[][]> linklink;

    /**
     * Constructor for gameframe object.
     * 
     * @param w
     *            of type World.
     * @throws CouldNotAddException
     *             a.
     * @throws CouldNotRemoveException
     *             b.
     */
    public GameFrame(final World w)
            throws CouldNotAddException, CouldNotRemoveException {
        world = w;
        linklink = new DoubleLinkedList<Element[][]>();
        elementList = new Element[world.getRowCount()][world.getColCount()];
        getList();
        linklink.addToFront(deepCopy());
    }

    /**
     * getter for list.
     */
    private void getList() {
        for (int row = 0; row < world.getRowCount(); row++) {
            for (int col = 0; col < world.getColCount(); col++) {
                elementList[row][col] = world.getCellAt(row, col).getUser();
            }
        }
    }

    /**
     * loadlist method.
     * @param list of type Element[][].
     */
    private void loadList(Element[][] list) {
        for (int row = 0; row < world.getRowCount(); row++) {
            for (int col = 0; col < world.getColCount(); col++) {
                list[row][col].setCell(world.getCellAt(row, col));
                // world.getCellAt(row,col).setUser(elementList[row][col]);
            }
        }
    }

    /**
     * previous state control.
     * @throws CouldNotAddException a.
     * @throws CouldNotRemoveException b.
     */
    public void prevState() throws CouldNotAddException, 
    CouldNotRemoveException {
        worldCheck();
        loadList(linklink.prevCurrent());
    }

    /**
     * next state control.
     * @throws CouldNotAddException a.
     * @throws CouldNotRemoveException b.
     */
    public void nextState() throws CouldNotAddException,
    CouldNotRemoveException {
        worldCheck();
        loadList(linklink.nextCurrent());
    }

    /**
     * initialize the layout.
     */
    public void init() {
        setLayout(new GridLayout(world.getRowCount(), world.getColCount()));

        for (int row = 0; row < world.getRowCount(); row++) {
            for (int col = 0; col < world.getColCount(); col++) {
                add(world.getCellAt(row, col));
            }
        }
        addMouseListener(new TurnListener(this));
    }

    /**
     * Update the world frame.
     * 
     * @throws CouldNotAddException
     *             a.
     * @throws CouldNotRemoveException
     *             b.
     */
    public void worldUpdate()
            throws CouldNotAddException, CouldNotRemoveException {
        world.reinit();
        linklink.clearList();
        getList();
        linklink.addToFront(deepCopy());
        init();
    }

    /**
     * getter for world.
     * @return world as World.
     */
    public World getWorld() {
        return world;
    }

    /**
     * Deep copy method.
     * @return 2d array of Elements.
     */
    public Element[][] deepCopy() {
        Element[][] l = new Element[world.getRowCount()][world.getColCount()];
        for (int r = 0; r < world.getRowCount(); r++) {
            for (int c = 0; c < world.getColCount(); c++) {
                l[r][c] = elementList[r][c];
            }
        }
        return l;
    }

    /**
     * repaints the world.
     */
    public void worldRepaint() {
        repaint();
    }

    /**
     * checks if the world needs to be repainted.
     * @throws CouldNotAddException a.
     * @throws CouldNotRemoveException b.
     */
    private void worldCheck()
            throws CouldNotAddException, CouldNotRemoveException {
        if (world.getStatus()) {
            world.flickStatus();
            linklink.clearList();
            getList();
            linklink.addToFront(deepCopy());
        }
    }

    /**
     * Take turn.
     * 
     * @throws CouldNotAddException a.
     * @throws CouldNotRemoveException b. 
     */
    public void takeTurn() throws CouldNotAddException, 
    CouldNotRemoveException {
        worldCheck();
        if (linklink.getCurrent().getNext() == null) {
            world.takeTurn();
            getList();
            linklink.addToFront(deepCopy());
            loadList(linklink.getCurrent().getData());
            // ll.addToFront(ct);
        } else {
            linklink.nextCurrent();
            loadList(linklink.getCurrent().getData());
            // ll.addToFront(ct);
        }
        // ll.print();
        // world.takeTurn();
        // linklink.addToFront(world);
        repaint();
    }
}
