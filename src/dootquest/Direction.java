package dootquest;

public final class Direction {
    public static final int NORTH = 0;
    public static final int EAST = 1;
    public static final int WEST = 2;
    public static final int SOUTH = 3;
    public static final int UP = 4;
    public static final int DOWN = 5;

    public static String convertToString(int direction) {
        switch(direction) {
            case 0:
                return "north";
            case 1:
                return "east";
            case 2:
                return "west";
            case 3:
                return "south";
            case 4:
                return "up";
            case 5:
                return "down";
            default:
                return "error";
        }
    }

    public static int convertToInt(String direction) {
        switch(direction) {
            case "n":
            case "north":
                return NORTH;
            case "e":
            case "east":
                return EAST;
            case "w":
            case "west":
                return WEST;
            case "s":
            case "south":
                return SOUTH;
            case "u":
            case "up":
                return UP;
            case "d":
            case "down":
                return DOWN;
            default:
                return -1;
        }
    }
}