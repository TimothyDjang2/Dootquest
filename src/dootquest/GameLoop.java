package dootquest;

import javax.lang.model.util.ElementScanner6;
import dootquest.stuff.Item;

public class GameLoop {

    String gameState;
    Parser parser;
    WorldMap worldMap;
    public static Player player;

    public static void main(String[] args) {
        new GameLoop();
    }

    public GameLoop() {
        gameState = "menuinit";

        worldMap = new WorldMap();
        parser = new Parser();

        mainLoop();
    }

    public void mainLoop() {
        switch(gameState) {
            case "menuinit":
                System.out.println("#########################");
                System.out.println("#       DOOTQUEST       #");
                System.out.println("#  Type Start To Begin  #");
                System.out.println("#########################");
                gameState = "menu";
                break;
            case "menu":
                System.out.print("> ");
                menu();
                break;
            case "newGame":
                examine();
                gameState = "inGame";
                break;
            case "inGame":
                System.out.print("> ");
                normalTurn();
            break;
            default:
                System.out.println("I done happened a big oopsie!");
                gameState = "quit";
                break;
        }
        mainLoop();
    }

    public void menu() {
        String[] cmds = parser.getInput();
        switch (cmds[0]) {
            case "start":
                System.out.println("is does start");
                player = new Player(worldMap.main, 100);
                gameState = "newGame";
                break;
            default:
                break;
        } 
    }

    public void normalTurn() {

        String[] cmds = parser.getInput();
        switch (cmds[0]) {
            case "examine":
            case "x":
                if (cmds.length == 1) {
                    examine();
                } else if (cmds[1].equals("me") && cmds.length == 2) {
                    System.out.println(player.getDesc());
                    System.out.println("Looks like you have " + (int)Math.round(player.getHealth()) + "/" + (int)Math.round(player.getMaxHealth()) + " health left. Excellent.");
                } else {
                    Item item = player.getPosition().findItem(parser.combine(1, cmds.length, cmds));
                    if (item != null) {
                        examine(item);
                        break;
                    }
                    item = player.findItem(parser.combine(1, cmds.length, cmds));
                    if (item != null) {
                        examine(item);
                        break;
                    }
                    System.out.println("They don't seem to have any of those.");
                }
            break;
            case "go":
            case "move":
                int direction = Direction.convertToInt(cmds[1]);
                if (direction == -1) {
                    System.out.println("That's not a direction you can go!");
                } else if (!player.getPosition().connectedRooms.containsKey(direction)) {
                    System.out.println("You can't go that way.");
                } else if (player.getPosition().connectedRooms.get(direction).locked) {
                    System.out.println("You can't go that way.");
                } else {
                    player.setPosition(player.getPosition().connectedRooms.get(direction).getRoom());
                    examine();
                }
            break;
            case "take":
            case "get":
                if (cmds.length == 1) {
                    System.out.println("Acquire the what now?");
                } else {
                    //System.out.println("Tried to get [" + parser.combine(1, cmds.length, cmds) + "]");
                    Item item = player.getPosition().findItem(parser.combine(1, cmds.length, cmds));
                    if (item != null) {
                        if (item.canTake()) {
                        player.giveItem(item);
                        player.getPosition().removeItem(item);
                        System.out.println("You got the " + item.getName().toLowerCase() + ".");
                        break;
                        } else {
                            System.out.println(item.deniedTakeMsg());
                            break;
                        }
                    }
                    System.out.println("They don't seem to have any of those.");
                }
            break;
            case "inventory":
            case "i":
                System.out.println(player.getInventory());
                break;
            case "drop":
                if (cmds.length == 1) {
                    System.out.println("Drop the what now?");
                } else if (cmds[1].equals("the beat") && cmds.length == 2) {

                } else {
                    Item item = player.findItem(parser.combine(1, cmds.length, cmds));
                    if (item != null) {
                        player.removeItem(item);
                        player.getPosition().addItem(item);
                        System.out.println("You dropped the " + item.getName().toLowerCase() + ".");
                    } else {
                        System.out.println("You don't have any of those!");
                    }
                }
                break;
            default:
            break;
        }
    }

    public void examine() {
        System.out.println("[" + player.getPosition().name + "]\n");
        System.out.println(player.getPosition().desc + "\n");
        
        String items = player.getPosition().getItems();
        if (!items.equals("There is ")) { 
            System.out.println(player.getPosition().getItems() + "\n"); 
        }

        System.out.println(player.getPosition().getPossibleDirections());
    }

    public void examine(Item item) {
        System.out.println(item.getDesc());
    }
}