package dootquest;

import java.util.ArrayList;
import dootquest.stuff.Item;

public class Player {

    private Room position;
    private ArrayList<Item> inventory;

    public Player(Room position) {
        this.position = position;
        inventory = new ArrayList<>();
    }

    public Room getPosition() {
        return position;
    }

    public void setPosition(Room room) {
        position = room;
    }

    public void giveItem(Item item) {
        inventory.add(item);
    }

    public void removeItem(Item item) {
        inventory.remove(item);
    }

    public Item findItem(String name) {
        for (Item item : inventory) {
            if (item.getName().toLowerCase().equals(name.toLowerCase())) {
                return item;
            }
        }
        return null;
    }

    public String getInventory() {
        StringBuilder printString = new StringBuilder("You have ");
        if (inventory.size() > 0) {
            for (int i = 0; i < inventory.size(); i++) {
                if (Vocabulary.isVowel(inventory.get(i).getName().charAt(0))) {
                    printString.append("an " + inventory.get(i).getName().toLowerCase());
                } else {
                    printString.append("a " + inventory.get(i).getName().toLowerCase());
                }
                if (i < inventory.size() - 2) {
                    printString.append(", ");
                } else if (i == inventory.size() - 2) {
                    printString.append(" and ");
                } else {
                    printString.append(".");
                }
            }
        } else printString.append("nothing currently.");
        return printString.toString();
    }
}