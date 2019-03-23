package dootquest;

import dootquest.Room;
import dootquest.Direction;

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

        
    }

}