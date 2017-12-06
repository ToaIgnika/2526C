package ca.bcit.comp2526.a2c;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * StateControl.java.
 * 
 *
 * "I made this code longer than usual 
 * because I lack time to make it short"
 * @author Yevhen
 * @version Nov 27, 2017
 *
 */
public class StateControl extends JPanel {
    /**
     * def uid.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Constructor for StateControl class.
     * @throws FileNotFoundException a.
     * @throws IOException b.
     */
    public StateControl() throws FileNotFoundException, IOException {
        
    }
    
    /**
     * Save function for gamesttate.
     * @param world of type World.
     * @param s of type String.
     * @throws IOException a.
     */
    public void save(World world, String s) throws IOException {
        System.out.println(s);
        if (s == null) {
            s = "local.out";
        }
        try {
            ObjectOutputStream out = new ObjectOutputStream(
                    new FileOutputStream(s));
            out.writeObject(world);
            out.flush();
            out.close();
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Save file does not exist!",
                    "InfoBox:  fileNotFound", JOptionPane.INFORMATION_MESSAGE);
        }

    }

    /**
     * Load function for the state control.
     * @param w of type World.
     * @param s of type String.
     * @throws FileNotFoundException a.
     * @throws IOException b.
     * @throws ClassNotFoundException c.
     */
    public void load(World w, String s)
            throws FileNotFoundException, IOException, ClassNotFoundException {
        System.out.println(s);
        if (s == null) {
            s = "local.out";
        }
        try {
            ObjectInputStream in = new ObjectInputStream(
                    new FileInputStream(s));
            World f = ((World) in.readObject());
            for (int r = 0; r < w.getRowCount(); r++) {
                for (int c = 0; c < w.getColCount(); c++) {
                    w.getCellAt(r, c).reinit(f.getCellAt(r, c));
                }
            }
            w.reload();
            w.flickStatus();
            in.close();
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Save file does not exist!",
                    "InfoBox:  fileNotFound", JOptionPane.INFORMATION_MESSAGE);

        }

    }
}
