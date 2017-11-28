package ca.bcit.comp2526.a2b;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * ControlPanel.java.
 * 
 *
 * "I made this code longer than usual because I lack time to make it short"
 * 
 * @author Yevhen
 * @version Nov 19, 2017
 *
 */
public class ControlPanel extends JPanel {

    /**
     * min fps.
     */
    private static final int FPS_MIN = 0;
    /**
     * max fps.
     */
    private static final int FPS_MAX = 20;
    /**
     * initial fps.
     */
    private static final int FPS_INIT = 1; // initial frames per second
    /**
     * serial uid.
     */
    private static final long serialVersionUID = 1L;
    /**
     * icon dimention variable.
     */
    private static final int ICON_DIM = 32;
    private final TimerFrame timer;
    private final GameFrame frame;

    /**
     * Constructor for control panel.
     * 
     * @param t
     *            of type TimerFrame.
     * @param g
     *            of type GameFrame.
     * @throws IOException a.
     */
    public ControlPanel(TimerFrame t, GameFrame g) throws IOException {
        timer = t;
        frame = g;
        JSlider framesPerSecond = new JSlider(JSlider.HORIZONTAL, FPS_MIN,
                FPS_MAX, FPS_INIT);
        framesPerSecond.addChangeListener(new SlideListener());
        // Turn on labels at major tick marks.
        framesPerSecond.setMajorTickSpacing(Integer.valueOf("5"));
        framesPerSecond.setMinorTickSpacing(1);
        framesPerSecond.setPaintTicks(true);
        framesPerSecond.setPaintLabels(true);

        JButton bprev = new JButton();
        createButton(bprev, "icons/prev-button.png", true, new PrevEvent());

        JButton bstart = new JButton();
        createButton(bstart, "icons/play-button.png", true, new StartEvent());

        JButton bnext = new JButton();
        createButton(bnext, "icons/next-button.png", true, new NextEvent());
        
        JButton bpause = new JButton();
        createButton(bpause, "icons/pause-button.png", true, new PauseEvent());

        JButton brefresh = new JButton();
        createButton(brefresh, "icons/refresh-button.png", true,
                new RefreshEvent());

        JLabel bseed = new JLabel("<FPS>");
        add(bseed);
        add(framesPerSecond);

        JButton bsave = new JButton();
        createButton(bsave, "Save", false, new SaveEvent());

        JButton bload = new JButton();
        createButton(bload, "Load", false, new LoadEvent());
    }

    /**
     * Method to create and add button with listener and image..
     * @param b of type JButton.
     * @param s of type String.
     * @param img of type boolean.
     * @param e of type ActionListener.
     * @throws IOException a.
     */
    private void createButton(JButton b, String s, boolean img,
            ActionListener e) throws IOException {
        if (img) {
            Image g = ImageIO.read(getClass().getResource(s));
            b = new JButton(new ImageIcon(g));
            b.setPreferredSize(new Dimension(ICON_DIM, ICON_DIM));
        } else {
            b = new JButton(s);
        }
        b.addActionListener(e);
        add(b);
    }

    /**
     * Helper method for picking files.
     * @param save of type boolean.
     * @return String for file location.
     */
    private String filePicker(boolean save) {
        String result = "";

        JFileChooser chooser = new JFileChooser();

        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "GOL save files", "out");
        chooser.setFileFilter(filter);
        if (save) {
            int returnVal = chooser.showSaveDialog(this);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                result = chooser.getCurrentDirectory().toString() + "\\"
                        + chooser.getSelectedFile().getName();
                System.out.println(result);
            }
        } else {
            int returnVal = chooser.showOpenDialog(this);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                result = chooser.getCurrentDirectory().toString() + "\\"
                        + chooser.getSelectedFile().getName();
            }
        }

        return result;
    }

    /**
     * NextEvent class.
     * 
     *
     * "I made this code longer than usual 
     * because I lack time to make it short"
     * @author Yevhen
     * @version Nov 27, 2017
     *
     */
    class NextEvent implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent arg0) {
            // TODO Auto-generated method stub
            try {
                frame.nextState();
            } catch (CouldNotAddException | CouldNotRemoveException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

    }

    /**
     * PrevEvent class.
     * 
     *
     * "I made this code longer than usual 
     * because I lack time to make it short"
     * @author Yevhen
     * @version Nov 27, 2017
     *
     */
    class PrevEvent implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                frame.prevState();
            } catch (CouldNotAddException | CouldNotRemoveException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        }

    }

    /**
     * SlideListener class.
     * 
     *
     * "I made this code longer than usual because I lack time to make it short"
     * 
     * @author Yevhen
     * @version Nov 19, 2017
     *
     */
    class SlideListener implements ChangeListener {
        /**
         * statechange listener.
         * 
         * @param e
         *            ChangeEvent e.
         */
        public void stateChanged(ChangeEvent e) {
            JSlider source = (JSlider) e.getSource();
            if (!source.getValueIsAdjusting()) {
                int fps = (int) source.getValue();

                if (fps == 0) {
                    timer.pauseTimer();
                } else {
                    timer.setTimer(Integer.valueOf("1000") / fps);
                    if (!timer.runStatus()) {
                        timer.startTimer();
                    }
                }
            }
        }
    }

    /**
     * PauseEvent class.
     * 
     *
     * "I made this code longer than usual because I lack time to make it short"
     * 
     * @author Yevhen
     * @version Nov 19, 2017
     *
     */
    class PauseEvent implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // TODO Auto-generated method stub
            timer.pauseTimer();
        }
    }

    /**
     * StartEvent class.
     * 
     *
     * "I made this code longer than usual because I lack time to make it short"
     * 
     * @author Yevhen
     * @version Nov 19, 2017
     *
     */
    class StartEvent implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // TODO Auto-generated method stub
            timer.startTimer();
        }
    }

    /**
     * RefreshEvent class.
     * 
     *
     * "I made this code longer than usual because I lack time to make it short"
     * 
     * @author Yevhen
     * @version Nov 19, 2017
     *
     */
    class RefreshEvent implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // TODO Auto-generated method stub
            timer.pauseTimer();
            try {
                frame.worldUpdate();
            } catch (CouldNotAddException | CouldNotRemoveException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        }
    }

    /**
     * SaveEvent class.
     * 
     *
     * "I made this code longer than usual 
     * because I lack time to make it short"
     * @author Yevhen
     * @version Nov 27, 2017
     *
     */
    class SaveEvent implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                StateControl state = new StateControl();
                state.save(frame.getWorld(), filePicker(true));
            } catch (FileNotFoundException e1) {
                e1.printStackTrace();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

    /**
     * LoadEvent class.
     * 
     *
     * "I made this code longer than usual 
     * because I lack time to make it short"
     * @author Yevhen
     * @version Nov 27, 2017
     *
     */
    class LoadEvent implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                StateControl state = new StateControl();
                try {
                    state.load(frame.getWorld(), filePicker(false));
                } catch (ClassNotFoundException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            } catch (FileNotFoundException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        }
    }

}
