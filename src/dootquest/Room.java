package dootquest;

import java.lang.StringBuilder;
import java.util.ArrayList;
import java.util.LinkedList;
import dootquest.stuff.Item;
import java.util.Map;
import dootquest.Direction;
import java.util.HashMap;
import java.util.Set;

public class Room {

    String name = "default";
    String desc = "Looks like the dev was too lazy to describe this room yet.";
    ArrayList<Item> items;
    Map<Integer, ConnectableRoom> connectedRooms;

    public Room(String name, String desc) {
        this.desc = desc;
        this.name = name;
        connectedRooms = new HashMap<>();
        items = new ArrayList<>();
    }

    public Room(String name) {
        this(name, "Looks like the dev was too lazy to describe this room yet.");
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public void removeItem(Item item) {
        items.remove(item);
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

    public Item findItem(String name) {
        for (Item item : items) {
            if (item.getName().toLowerCase().equals(name.toLowerCase())) {
                return item;
            }
        }
        return null;
    }

    public String getPossibleDirections() {
        
        StringBuilder printString = new StringBuilder("You can go ");

        if (connectedRooms.size() == 0) {
            printString.append("You can't go anywhere to exit this room.");
        } else {
            Set<Integer> keys = connectedRooms.keySet();
            for (int i : keys) {
                if (connectedRooms.get(i).locked) {
                    keys.remove(i);
                }
            }

            for (int i : keys) {    
                printString.append(Direction.convertToString(i));
                if (i < keys.size() - 2) {
                    printString.append(", ");
                } else if (i == keys.size() - 2) {
                    printString.append(", or ");
                } else {
                    printString.append(" here.");
                }
            }
        }
        return printString.toString();
    }

    public String getItems() {

        StringBuilder printString = new StringBuilder("There is ");

        if (items.size() > 0) {

            for (int i = 0; i < items.size(); i++) {
                if (items.get(i).getName().substring(0,3).toLowerCase().equals("the")) {
                    printString.append(items.get(i).getName().toLowerCase());
                } else if (Vocabulary.isVowel(items.get(i).getName().charAt(0))) {
                    printString.append("an " + items.get(i).getName().toLowerCase());
                } else {
                    printString.append("a " + items.get(i).getName().toLowerCase());
                }
                if (i < items.size() - 2) {
                    printString.append(", ");
                } else if (i == items.size() - 2) {
                    printString.append(" and ");
                } else {
                    printString.append(" here.");
                }
            }
        }
        return printString.toString();
    }

    public void unlock(int direction) {
        connectedRooms.get(direction).unlock();
    }
}