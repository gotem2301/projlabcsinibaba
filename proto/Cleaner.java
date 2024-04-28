package proto;

public class Cleaner extends Character {

    public Cleaner(String id, Room r) {
        super(id, r);
    }


    @Override
    public void setCurrentRoom(Room r){
        currentRoom = r;
        currentRoom.cleanRoom();
    }


    @Override
    public void pickUpItem(Item i) {}

    @Override
    public void setDazed(boolean dazed) {}
}
