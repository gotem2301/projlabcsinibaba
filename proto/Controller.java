package proto;

import proto.Drawers.DCharacter;
import proto.Drawers.DDoor;
import proto.Drawers.DItem;

public class Controller {
    private Student currentPlayer;
    private View view;
    private Proto model;

    public Controller(){
        currentPlayer = null;
        view = null;
        model = new Proto();
    }

    public void setView(View newView){
        this.view = newView;
    }

    public void setCurrentPlayer(Student newCurrentPlayer){
        this.currentPlayer = newCurrentPlayer;
    }

    public void newGame(){
        Room r0 = new Room("r0", true, 0, 10, false, true, 0);
        Room r1 = new Room("r1", false, 0, 10, false, false, 0);
        Room r2 = new Room("r2", false, 0, 10, false, false, 0);

        Door r0r1 = new Door(r0, r1);
        Door r0r2 = new Door(r0, r2);

        Student s0 = new Student("s0", r0);
        Student s1 = new Student("s1", r1);

        Camembert camembert = new Camembert("ca1", r0, null);

        Cleaner c0 = new Cleaner("c0", r1);

        model.addRoom(r0);
        model.addRoom(r1);
        model.addRoom(r2);
        model.addDoor(r0r1);
        model.addDoor(r0r2);
        model.addCharacter(s0);
        model.addCharacter(s1);
        model.addCharacter(c0);
        currentPlayer = s0;
    }

    public void dropItem(DItem droppedItem){
        for (Item item : currentPlayer.inventory) {
            if (item.getId().equals(droppedItem.getId())) {
                currentPlayer.dropItem(item);
                view.dropItem(item.getId());
                view.repaint();
                return;
            }
        }
    }

    public void traverseDoor(DDoor chosenDoor){
        for (Door door : currentPlayer.currentRoom.getDoors()) {
            if (door.getId().equals(chosenDoor.getId())) {
                currentPlayer.enterRoom(door);
                switchPlayers();
                drawEverything();
                return;
            }
        }
    }

    public void pickUpItem(DItem pickedUpItem){
        for (Item item : currentPlayer.currentRoom.getItems()){
            if (pickedUpItem != null){
                if (item.getId().equals(pickedUpItem.getId())){
                    if (currentPlayer.getDazed()){
                        currentPlayer.pickUpItem(item);
                        view.pickUpItem(item.getId());
                        view.repaint();
                        return;
                    }
                    return;
                }
            }
        }
    }

    public void drawEverything(){
        view.nullEverything();

        for (Door door : currentPlayer.currentRoom.getDoors()){
            view.addDoor(new DDoor(door.getId(), door.getTraversableRoom(currentPlayer.currentRoom).getId()));
        }

        for (Character character : currentPlayer.currentRoom.getCharacters()){
            view.addCharacter(new DCharacter(character.getId()));
        }

        for (Item item : currentPlayer.currentRoom.getItems()){
            view.addItem(new DItem(item.id));
        }

        for (Item item : currentPlayer.inventory){
            if (item != null) {
                view.addInventoryItem(new DItem(item.id));
            }
        }

        view.isRoomSticky = currentPlayer.currentRoom.getSticky();
        view.isRoomGassed = currentPlayer.currentRoom.getgassedRoom();

        view.currentPlayerId = currentPlayer.ID;

        view.repaint();
    }

    public void switchPlayers(){
        for (Character character : model.getAllCharacters()){
            if (character.getId().substring(0, 1).equals("s")){
                Student student = (Student) character;

                if (!student.getId().equals(currentPlayer.getId())){
                    currentPlayer = student;
                }
            }
        }
    }
}
