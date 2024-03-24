import java.util.Scanner;

public class Teacher extends Character{
    /**
     * Eltarolja hogy az adott tanar eppen le van-e rongyozva vagy sem
     */
    private boolean clothed;

    public Teacher(Room r) {
        super(r);

        System.out.println("Oktato letrehozva");
        clothed = false;
    }

    /**
     * A tanar egyedul a logarlecet nem kepes felvenni, igy ezt itt kezelem
     * ezen kivul minden mas itemet felvehet
     * @param i - Az item amit fel akarunk venni
     */
    @Override
    public void pickUpItem(Item i) {
        System.out.println("Tárgy felvétele");

        if(this.inventory.length < 5 && !this.Dazed) {
            if (i.transfer(this, null) == false) {
                this.currentRoom.removeItem(i);
                this.addItem(i);
                System.out.println("Sikeres targyfelvetel");
            } else {
                i.transfer(null, currentRoom);
                System.out.println("Sikertelen targyfelvetel");
            }
        }else{
            System.out.println("Sikertelen targyfelvetel");
        }
    }
}
