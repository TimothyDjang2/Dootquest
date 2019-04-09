package dootquest;

import dootquest.Room;
import dootquest.stuff.Item;
import dootquest.Direction;

/**
 * Generates all your rooms, items, etc. here.
 */
public class WorldMap {
    Room 
    main,
    second, 
    locked,
    
    end; //dummy room to end the list of rooms.

    public WorldMap() {
        main = new Room("Cubic Room", "You are standing in a giant, empty room. Its walls are perfectly cubic, and are covered in what looks like if graph paper became a wallpaper.");
        second = new Room("Cubicn't Room", "This room is decidedly less cubic than the first.");
        locked = new Room("Secret Room");

        main.connect(Direction.NORTH, second, false);
        main.connect(Direction.EAST, locked, true);

        second.connect(Direction.SOUTH, main, false);
        second.addItem(new Item("Apple", "A nice ZerdCorp brand ISO standard apple, fresh off the press."));
        second.addItem(new Item("Big Apple", "A slightly oversized ZerdCorp brand ISO standard apple, fresh off the press.", false));
        Item theBiggestApple = new Item("The Biggest Apple", "It cHoNkY.", false, "You are too indimidated by the size of this apple to lift it.");
        theBiggestApple.setHidden(true);
        second.addItem(theBiggestApple);


        
    }

}