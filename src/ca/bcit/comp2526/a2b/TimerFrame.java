package ca.bcit.comp2526.a2b;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

/**
 * TimerFrame.
 * 
 *
 * "I made this code longer than usual 
 * because I lack time to make it short"
 * @author Yevhen
 * @version Nov 19, 2017
 *
 */
public class TimerFrame implements ActionListener {

    private Timer timer;
    private GameFrame frame;
    private int delay;

    /**
     * Constructor for timer frame.
     * @param f of type GameFrame.
     */
    public TimerFrame(GameFrame f) {
        frame = f;
        delay = Integer.valueOf("1000");
        timer = new Timer(delay, this);
        timer.setInitialDelay(delay);
    }

    /**
     * Pause the timer.
     */
    public void pauseTimer() {
        timer.stop();
    }

    /**
     * Start the timer.
     */
    public void startTimer() {
        timer.setDelay(delay);
        timer.start();
    }

    /**
     * Set timer delay.
     * @param n of type int.
     */
    public void setTimer(int n) {
        delay = n;
        timer.setDelay(n);
    }

    /**
     * Checks the timer status.
     * @return true if timer is working.
     */
    public boolean runStatus() {
        return timer.isRunning();
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        try {
            frame.takeTurn();
        } catch (CouldNotAddException | CouldNotRemoveException e) {
            e.printStackTrace();
        }
        
    }
}
