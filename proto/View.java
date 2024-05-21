package proto;

import proto.Drawers.DCharacter;
import proto.Drawers.DDoor;
import proto.Drawers.DItem;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class View extends JPanel implements MouseListener {

    private Graphics2D renderer;
    private List<DDoor> doors = new ArrayList<>();
    private List<DCharacter> characters = new ArrayList<>();
    private List<DItem> items = new ArrayList<>();
    private List<DItem> inventory = new ArrayList<>();
    public boolean isRoomSticky = false;
    public boolean isRoomGassed = false;
    public String currentPlayerId = null;
    private DItem pickedItemFromInventory = null;

    private Controller controller = null;

    public View(){
        addMouseListener(this);
    }

    public void setController(Controller controller){
        this.controller = controller;
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        renderer = (Graphics2D) g;

        try {
            BufferedImage img = ImageIO.read(new File("proto/Images/RoomBackground.png"));
            renderer.drawImage(img, 0, 0, this);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }

        if (isRoomSticky){
            try {
                BufferedImage img = ImageIO.read(new File("proto/Images/Sticky.png"));
                renderer.drawImage(img, 473, 142, this);
            }
            catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
        if (isRoomGassed){
            try {
                BufferedImage img = ImageIO.read(new File("proto/Images/Gassed.png"));
                renderer.drawImage(img, 545, 142, this);
            }
            catch (Exception e){
                System.out.println(e.getMessage());
            }
        }

        drawDoors();
        drawCharacters();
        drawItems();
        drawInventory();
        drawCurrentPlayerTitle();

        if (pickedItemFromInventory != null){
            drawItemOptions();
        }

        repaint();
    }

    public void nullEverything(){
        doors = new ArrayList<>();
        items = new ArrayList<>();
        characters = new ArrayList<>();
        inventory = new ArrayList<>();
        isRoomSticky = false;
        isRoomGassed = false;
        currentPlayerId = null;
        pickedItemFromInventory = null;
    }

    public void drawItemOptions(){
        try{
            BufferedImage img = null;
            if (pickedItemFromInventory.getId().substring(0, 2).equals("tr")){
                img = ImageIO.read(new File("proto/Images/InventoryItemOptionsTransistor.png"));
            }
            else {
                img = ImageIO.read(new File("proto/Images/InventoryItemOptions.png"));
            }

            renderer.drawImage(img, pickedItemFromInventory.getX0(), pickedItemFromInventory.getY1(), this);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void drawCurrentPlayerTitle(){
        renderer.setColor(Color.WHITE);
        renderer.setFont(new Font("Harrington", Font.BOLD, 20));
        renderer.drawString("Current player: Student" + currentPlayerId.substring(1), 500, 40);
    }

    public void drawDoors(){
        int doorX = 405, doorY = 305;

        for (DDoor door : doors){
            door.draw(renderer, doorX, doorY, this);

            doorX -= 70;
        }
    }

    public void drawCharacters(){
        int characterX = 405, characterY = 305;

        for (DCharacter dCharacter : characters){
            dCharacter.draw(renderer, characterX, characterY, this);

            characterX += 70;
        }
    }

    public void drawItems(){
        int itemX = 195, itemY = 475;

        for (DItem dItem : items){
            dItem.draw(renderer, itemX, itemY, this);

            itemX += 70;
        }
    }

    public void drawInventory(){
        try {
            BufferedImage img = ImageIO.read(new File("proto/Images/InventoryTitle.png"));
            renderer.drawImage(img, 20, 20, this);

            int inventoryX = 20, inventoryY = 40;
            int itemCtr = 0;

            for (DItem dItem : inventory){
                dItem.draw(renderer, inventoryX, inventoryY, this);

                itemCtr++;
                inventoryX += 50;
            }

            for (int i = itemCtr; i < 5; i++){
                DItem dItem = new DItem("bl0");
                dItem.draw(renderer, inventoryX, inventoryY, this);

                inventoryX += 50;
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void addInventoryItem(DItem item){
        if(inventory.size() < 5){
            inventory.add(item);
        }
    }

    public void addDoor(DDoor door){
        doors.add(door);
    }

    public void addCharacter(DCharacter character){
        characters.add(character);
    }

    public void addItem(DItem item){
        items.add(item);
    }
    public void pickUpItem(String Id){
        for (DItem item : items){
            if (item.getId().equals(Id)){
                items.remove(item);
                inventory.add(item);
                return;
            }
        }
    }

    public void dropItem(String Id){
        for (DItem item : inventory){
            if (item.getId().equals(Id)){
                items.add(item);
                inventory.remove(item);
                return;
            }
        }
    }

    public void gameOver(boolean b){
        Window endFrame = (Window) SwingUtilities.getWindowAncestor(this);
        endFrame.gameOver(b);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        for (DItem dItem : inventory){
            if (dItem.getX0() < e.getX() && e.getX() < dItem.getX1() && dItem.getY0() < e.getY() && e.getY() < dItem.getY1()){
                pickedItemFromInventory = dItem;
                repaint();
                return;
            }
        }

        if (pickedItemFromInventory != null){
            if (pickedItemFromInventory.getX0() < e.getX() && e.getX() < (pickedItemFromInventory.getX0() + 100) && pickedItemFromInventory.getY1() < e.getY() && e.getY() < (pickedItemFromInventory.getY1() + 20)){
                controller.dropItem(pickedItemFromInventory);
            }
            else if (pickedItemFromInventory.getX0() < e.getX() && e.getX() < (pickedItemFromInventory.getX0() + 100) && pickedItemFromInventory.getY1() + 20 < e.getY() && e.getY() < (pickedItemFromInventory.getY1() + 40)){
                controller.useItem(pickedItemFromInventory);
            }
            else if (pickedItemFromInventory.getX0() < e.getX() && e.getX() < (pickedItemFromInventory.getX0() + 100) && pickedItemFromInventory.getY1() + 40 < e.getY() && e.getY() < (pickedItemFromInventory.getY1() + 60)){
                /// Ide kéne a Transistor Connect
                System.out.println("Transistor CONNECT");
                controller.connectTransistor(pickedItemFromInventory);
            }
        }

        pickedItemFromInventory = null;

        for (DDoor door : doors){
            if (door.getX0() < e.getX() && e.getX() < door.getX1() && door.getY0() < e.getY() && e.getY() < door.getY1()){
                controller.traverseDoor(door);
            }
        }

        DItem pickedUpItem = null;
        for (DItem dItem : items){
            if (dItem.getX0() < e.getX() && e.getX() < dItem.getX1() && dItem.getY0() < e.getY() && e.getY() < dItem.getY1()){
                pickedUpItem = dItem;
            }
        }
        if (!isRoomSticky){
            controller.pickUpItem(pickedUpItem);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
