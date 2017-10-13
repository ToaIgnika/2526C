package ca.bcit.comp2526.a2a;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class TurnListener implements MouseListener {
    private GameFrame game;
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
