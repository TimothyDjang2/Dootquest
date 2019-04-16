package dootquest.stuff;

import dootquest.GameLoop;

public abstract class Consumable extends Item {

    public Consumable(String name, String description) {
        super(name, description);
    }

    public void useAndDelete() {
        Use();
        GameLoop.player.removeItem(this);
    }

    public abstract void Use();

}