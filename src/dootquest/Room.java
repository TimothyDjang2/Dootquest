package dootquest;

import java.util.List;
import dootquest.stuff.Item;
import java.util.Map;
import dootquest.Direction;
import java.util.HashMap;
import java.util.Set;

public class Room {

    String name = "default";
    String desc = "Looks like the dev was too lazy to describe this room yet.";
    List<Item> items;
    Map<Integer, ConnectableRoom> connectedRooms;

    public Room(String name) {
        this.name = name;
        connectedRooms = new HashMap<>();
    }

    public Room(String name, String desc) {
        this.desc = desc;
        this.name = name;
        connectedRooms = new HashMap<>();
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    /**
     * <p>Used to connect rooms. When inputting a direction, it's the direction you go
     * to get to room 2 from room 1.
     * 
     * <p>So if you called connect(north, cellar); the cellar would be north
     * of your room.
     * 
     * <p>!!IMPORTANT!! This only allows you to go from room 1 to room 2, you can't go back
     * once you're there. 
     * 
     * <p>Rooms can only have one connection in any direction.
     */
    public void connect(int direction, Room r, boolean locked) {
        ConnectableRoom cr = new ConnectableRoom(r, locked);
        if (connectedRooms.get(direction) != null) {
            connectedRooms.replace(direction, cr);
        } else {
            connectedRooms.put(direction, cr);
        }
    }

    public String getPossibleDirections() {
        
        String printString = "You can go ";

        if (connectedRooms.size() == 0) {
            printString = "You can't go anywhere to exit this room.";
        } else {
            Set<Integer> keys = connectedRooms.keySet();
            for (int i : keys) {
                if (connectedRooms.get(i).locked) {
                    keys.remove(i);
                }
            }

            for (int i : keys) {    
                printString = (printString + Direction.convertToString(i));
                if (i < keys.size() - 2) {
                    printString = (printString + ", ");
                } else if (i == keys.size() - 2) {
                    printString = (printString + ", or");
                } else {
                    printString = (printString + " here.");
                }
            }
        }
        return printString;
    }

    public void unlock(int direction) {
        connectedRooms.get(direction).unlock();
    }
}