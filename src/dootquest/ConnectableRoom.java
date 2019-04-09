package dootquest;

//Class used when connecting two rooms, in case the connection needs to be locked.
public class ConnectableRoom {

    public Room room;
    public boolean locked;

    public ConnectableRoom(Room room, boolean locked) {
        this.room = room;
        this.locked = locked;
    }

    public Room getRoom() {
        return room;
    }

    public void unlock() {
        locked = false;
    }

    public void lock() {
        locked = true;
    }

}