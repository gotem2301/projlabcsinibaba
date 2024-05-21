package proto;

import proto.Drawers.DCharacter;
import proto.Drawers.DDoor;
import proto.Drawers.DItem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Controller {
    private Student currentPlayer;
    private View view;
    private Proto model;
    Random rand = new Random();
    int playerNumber;
    private int Time = Integer.MAX_VALUE;

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
        model.newGame(playerNumber);
        currentPlayer = model.getStudents().get(0);
        startTimer();
    }

    public void loadGame(String save){
        model.loadGraphicGame(save);
        currentPlayer = model.getStudents().get(0);
        startTimer();
    }

    public void setPlayerNumber(int i){
        playerNumber = i;
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
            if(pickedUpItem.getId().contains("sl")){
                for(Item i: model.getAllItems()){
                    if(i.getId().equals(pickedUpItem.getId())){
                        SlidingRuler igazie = (SlidingRuler) i;
                        if(!(igazie.getFake())){
                            view.gameOver(true);
                        }
                    }
                }
            }
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
                int r = rand.nextInt(t.currentRoom.getDoors().size());
                t.enterRoom(t.currentRoom.getDoors().get(r));
                if(!t.currentRoom.getItems().isEmpty()) t.pickUpItem(t.currentRoom.getItems().get(0));
            }
            for (Cleaner c: model.getCleaners()) {
                int r = rand.nextInt(c.currentRoom.getDoors().size());
                c.enterRoom(c.currentRoom.getDoors().get(r));
            }
            model.refreshStudents();
            if(model.getStudents().isEmpty()){
                view.gameOver(false);
            }
        }
        else{
            current++;
        }
        currentPlayer = model.getStudents().get(current);
    }

    private void gameTime(){
        Time--;
        if(Time <= 0){
            System.out.println("Game Over");
            view.gameOver(false);
        }

    }

    public void startTimer(){
        Time = 300;
        Thread timeThread = new Thread(() -> {
            while (!model.gameOver && Time > 0) {
                gameTime();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        });
        timeThread.start();
    }

    public void useItem(DItem picked){
        List<String> cmd = new ArrayList<>();
        cmd.add("Use");
        cmd.add(picked.getId());
        model.use(cmd);
        view.nullEverything();
        drawEverything();
    }
}
