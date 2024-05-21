package proto;

import proto.Drawers.DCharacter;
import proto.Drawers.DDoor;
import proto.Drawers.DItem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
        currentPlayer = model.getStudents().get(0);
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
        if(pickedUpItem != null && Arrays.stream(currentPlayer.inventory).filter(e -> e != null).count() < 5) {
            List<String> s = new ArrayList<>();
            s.add("PickUp");
            s.add(currentPlayer.getId());
            s.add(pickedUpItem.getId());
            model.pickUp(s);
            view.pickUpItem(pickedUpItem.getId());
            view.repaint();
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

    /*
    Először a tanulók kerülnek sorra, ha mindenki sorrakerült
    minden tanár szobát vált (az első ajtón) és felvesz egy tárgyat
    majd minden takatrító is (ugyanígy)
     */

    public void switchPlayers(){
        int current = model.getStudents().indexOf(currentPlayer);
        if(current == model.getStudents().size()-1) {
            current = 0;
            for (Teacher t: model.getTeachers()) {
                t.enterRoom(t.currentRoom.getDoors().get(0));
                if(!t.currentRoom.getItems().isEmpty()) t.pickUpItem(t.currentRoom.getItems().get(0));
            }
            for (Cleaner c: model.getCleaners()) {
                c.enterRoom(c.currentRoom.getDoors().get(0));
            }
            model.refreshStudents();
        }
        else current++;
        currentPlayer = model.getStudents().get(current);
    }
}
