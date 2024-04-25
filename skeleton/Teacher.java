package skeleton;

public class Teacher extends Character{
    /**
     * Eltarolja hogy az adott tanar eppen le van-e rongyozva vagy sem
     */
    private int clothed;

    /**
     *  Beallitja a clothed tagvaltozot
     * @param bool - az allapot amive valtozik
     */
    @Override
    public void setClothed(int c){
        clothed = c;
    }

    public Teacher(Room r) {
        super(r);

        System.out.println("Oktato letrehozva");
        clothed = 0;
    }

    /**
     * A tanar egyedul a logarlecet nem kepes felvenni, igy ezt itt kezelem
     * ezen kivul minden mas itemet felvehet
     * @param i - Az item amit fel akarunk venni
     */
    @Override
    public void pickUpItem(Item i) {
        System.out.println("pickUpItem fuggveny hivas");

        if(this.inventory.length < 5 && !this.Dazed) {
            if (i.transfer(this, null) == false) {
                for(int j = 0; j < 5; j++){
                    if(inventory[j] == null){
                        inventory[j] = i;
                    }
                }
                this.currentRoom.removeItem(i);
                System.out.println("Sikeres targyfelvetel");
            } else {
                i.transfer(null, currentRoom);
                System.out.println("Sikertelen targyfelvetel");
            }
        }else{
            System.out.println("Sikertelen targyfelvetel");
        }
    }

    @Override
    public void teacherDuty() {
        System.out.println("TeacherDuty fuggveny hivas");
        currentRoom.dropThemOut();
    }
}
