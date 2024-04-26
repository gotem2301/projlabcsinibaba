package skeleton;

import java.util.Arrays;

public class Teacher extends Character{

    /**
     * Eltarolja hogy az adott tanar eppen le van-e rongyozva vagy sem
     */
    private int clothed;


    public Teacher(String id, Room r) {
        super(id, r);
        clothed = 0;
        System.out.println(ID + " created in " + currentRoom.getId());
    }


    public int getClothed() {
        return clothed;
    }

    /**
     *  Beallitja a clothed tagvaltozot
     * @param bool - az allapot amive valtozik
     */
    @Override
    public void setClothed(int c){
        clothed = c;
        if(clothed != 0) {
            System.out.println(ID + " is clothed");
        }
    }

    
    /**
     * A tanar egyedul a logarlecet nem kepes felvenni, igy ezt itt kezelem
     * ezen kivul minden mas itemet felvehet
     * @param i - Az item amit fel akarunk venni
     */
    @Override
    public void pickUpItem(Item i) {
        if (!this.Dazed && !this.currentRoom.getSticky() && Arrays.stream(inventory).filter(e -> e != null).count() < 5) {
            if (i.transfer(this, null)) {
                i.transfer(null, currentRoom);
                System.out.println(ID + "  cant pick up " + i.getId());
                return;
            }
            for (int j = 0; j < 5; j++) {
                if (inventory[j] == null) {
                    inventory[j] = i;
                    this.currentRoom.removeItem(i);
                    System.out.println(ID + " picked up " + i.getId());
                    break;
                }
            }
        } 
        else {
            System.out.println(ID + "  cant pick up " + i.getId());
        }
    }


    @Override
    public void teacherDuty() {
        currentRoom.dropThemOut();
    }
}
