package dootquest;

import java.util.ArrayList;
import dootquest.stuff.Item;

public class Player {

    private double health = 100;
    private double maxHealth = 100;
    private Room position;
    private ArrayList<Item> inventory;

    public Player(Room position, double health) {
        this.health = health;
        this.maxHealth = health;
        this.position = position;
        inventory = new ArrayList<>();
    }

    public Room getPosition() {
        return position;
    }

    public void setPosition(Room room) {
        position = room;
    }

    public double getHealth() {
        return health;
    }

    public double getMaxHealth() {
        return maxHealth;
    }

    public void setHealth(double newHealth) {
        health = newHealth;
    }

    public String getDesc() {
        String desc;
        if (health >  maxHealth) {
            desc = "You feel almost like an immortal.";
        } else if (health > (.75 * maxHealth)) {
            desc = "Never felt better!";
        } else if (health <= (.75 * maxHealth) && health > (.5 * maxHealth)) {
            desc = "Looking sharp as always.";
        } else if (health <= (.5 * maxHealth) && health > (.25 * maxHealth)) {
            desc = "A bit tired.";
        } else {
            desc = "You've been better.";
        }
        return desc;
    }

    public void incrementHealth(double incr) {
        health += incr;
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