package dootquest;

import javax.lang.model.util.ElementScanner6;

public class GameLoop {

    String gameState;
    Parser parser;
    WorldMap worldMap;
    Player player;

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
                player = new Player(worldMap.main);
                gameState = "inGame";
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
                examine();
            break;
            case "go":
            case "move":
                int direction = Direction.convertToInt(cmds[1]);
                if (direction == -1) {
                    System.out.println("That's not a direction you can go!");
                } else if (!getPosition().connectedRooms.containsKey(direction)) {
                    System.out.println("You can't go that way.");
                } else if (getPosition().connectedRooms.get(direction).locked) {
                    System.out.println("You can't go that way.");
                } else {
                    player.position = getPosition().connectedRooms.get(direction).getRoom();
                    examine();
                }
                break;
        }
    }

    public void examine() {
        System.out.println("[" + getPosition().name + "]\n");
        System.out.println(getPosition().desc + "\n");
        System.out.println(getPosition().getPossibleDirections());
    }

    public Room getPosition() {
        return player.position;
    }
}