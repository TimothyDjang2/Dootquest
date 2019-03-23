package dootquest.stuff;

public class Item {

    private String name;
    private String desc;
    private boolean hidden = false;
    private boolean takeable = true;
    private String deniedTakeMessage = "You can't take that!";

    /**
     * Superclass for "things you can acquire."
     */
    public Item(String name, String description) {
        this(name, description, true, "You can't take that!");
    }

    public Item(String name, String description, boolean takeable) {
        this(name, description, takeable, "You can't take that!");
    }

    public Item(String name, String description, boolean takeable, String deniedtake) {
        this.name = name;
        this.desc = description;
        this.takeable = takeable;
        this.deniedTakeMessage = deniedtake;
    }

    public boolean getHidden() {
        return hidden;
    }

    public String deniedTakeMsg() {
        return deniedTakeMessage;
    }

    public boolean canTake() {
        return takeable;
    }

    /**
     * If this item is in a room, do you want it to be included
     * in the description?
     */
    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }

    public String getDesc() {
        return desc;
    }

    public String getName() {
        return name;
    }
    
}