package ca.bcit.comp2526.a2a;

import java.awt.Color;

public class Omnivore extends Lifeform {
    
    public Omnivore(Cell location) {
        super(location, 2, Color.BLUE, eType.Omnivore);
    }
    
    public void move() {
        eType s[] = {eType.Plant, eType.Herbivore, eType.Carnivore};
        super.move(s);
    }
    
    public void getBorn() {
       eType s[] = {eType.Plant, eType.Herbivore, eType.Carnivore};
       int n[] = {1, 3, 3};
       super.getBorn(s, n);
    }
    
}
