package skeleton;

public class Cleaner extends Character {

    public Cleaner(String id, Room r) {
        super(id, r);
        System.out.println(ID + " created in " + currentRoom.getID());
    }

    @Override
    public void setCurrentRoom(Room r){
        currentRoom = r;
        currentRoom.cleanRoom();
    }
}
