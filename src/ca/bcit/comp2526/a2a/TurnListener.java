package ca.bcit.comp2526.a2a;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * TurnListener.java.
 * 
 *
 * "I made this code longer than usual 
 * because I lack time to make it short"
 * @author Yevhen
 * @version Oct 19, 2017
 *
 */
public class TurnListener implements MouseListener {
    private GameFrame game;
    
    /**
     * Constructor objects of type TurnListener.
     * @param gameFrame of type GameFrame.
     */
    public TurnListener(GameFrame gameFrame) {
        game = gameFrame;
    }

    @Override
    public void mouseClicked(MouseEvent arg0) {
        // TODO Auto-generated method stub
        game.takeTurn();
    }

    @Override
    public void mouseEntered(MouseEvent arg0) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseExited(MouseEvent arg0) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mousePressed(MouseEvent arg0) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseReleased(MouseEvent arg0) {
        // TODO Auto-generated method stub
        
    }
}
