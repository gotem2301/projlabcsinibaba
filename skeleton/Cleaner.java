package skeleton;

public class Cleaner extends Character {

    public Cleaner(String id, Room r) {
        super(id, r);
        System.out.println(ID + " created in " + currentRoom.getId());
    }


    @Override
    public void setCurrentRoom(Room r){
        currentRoom = r;
        currentRoom.cleanRoom();
    }


    @Override
    public void pickUpItem(Item i) {}
}