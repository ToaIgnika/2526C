package ca.bcit.comp2526.a2a;

import java.awt.Color;

public class Carnivore extends Lifeform {
    //private Cell home;
    
    public Carnivore(Cell location) {
         super(location, 7, Color.MAGENTA, eType.Carnivore);
        //home = location;
        //home.setUser(this);
    }
    
    public void move() {
        eType s[] = {eType.Herbivore, eType.Omnivore};
        super.move(s);
    }
    
    public void getBorn() {
        eType s[] = {eType.Herbivore, eType.Omnivore};
        int n[] = {1, 2, 2};
        super.getBorn(s, n);
     }
    
}
