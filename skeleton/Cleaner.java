package skeleton;

public class Cleaner extends Character {

    public Cleaner(String id, Room r) {
        super(id, r);
    }


    @Override
    public void setCurrentRoom(Room r){
        currentRoom = r;
        System.out.println(ID + " entered " + currentRoom.getId());
        currentRoom.cleanRoom();
    }


    @Override
    public void pickUpItem(Item i) {}
}
