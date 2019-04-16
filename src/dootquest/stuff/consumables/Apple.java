package dootquest.stuff.consumables;

import dootquest.GameLoop;
import dootquest.stuff.Consumable;

public class Apple extends Consumable {

    public Apple() {
        super("Apple", "A standard ZerdCorp Apple, fresh off the press. Restores 10 health.");
    }

    @Override 
    public void Use() {
        GameLoop.player.incrementHealth(10);
    }
}