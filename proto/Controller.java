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
        model.newGame(3);
        currentPlayer = (Student)model.getAllCharacters().get(0);
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
