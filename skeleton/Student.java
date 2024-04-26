package skeleton;

import java.util.Arrays;

public class Student extends Character {

    public Student(String id, Room r) {
        super(id, r);
        System.out.println(ID + " created in " + currentRoom.getID());
    }


    /**
     * Osszekoti a ket parameterkent kapott tranzisztort, ha egyiknek sincs parja
     *
     * @param t1 - egyik tranzisztor
     * @param t2 - masik tranzisztor
     */
    public void connect(Transistor t1, Transistor t2) {
        if (t1.getPair() == null && t2.getPair() == null) {
            t1.setPair(t2);
            t2.setPair(t1);
            System.out.println(t1.getID() + " " + t2.getID() + " connected");
        } else {
            System.out.println(t1.getID() + " " + t2.getID() + " cant connect");
        }
    }


    /**
     * A hallgato kapott egy felszolitast a kibukasra, megvizsgalja hogy van-e nala vedelmet nyujto targy.
     *
     * @return - visszateresi erteke meghatarozza az ot megvedo itemet
     * 0: nincs vedelem, 1: TVSZ (Book), 2: sor (Beer), 3: rongy (Cloth)
     */
    @Override
    public int dropOut() {
        int savedBy = 0;
        for (Item i : inventory) {
            if (i != null) {
                savedBy = i.saveMe();
                if (savedBy > 0) {
                    if(savedBy == 1) {
                        i.lowerRemainingUse();
                    }
                    return savedBy;
                }
            }
        }
        return savedBy;
    }


    /**
     * A student minden itemet kepes felvenni amig nincs tele az inventoryja
     * es nincs elkabitva
     *
     * @param i - Az item amit fel akarunk venni
     */
    @Override
    public void pickUpItem(Item i) {
        if (!this.Dazed && !this.currentRoom.getSticky() && Arrays.stream(inventory).filter(e -> e != null).count() < 5) {
            for (int j = 0; j < 5; j++) {
                if (inventory[j] == null) {
                    inventory[j] = i;
                    this.currentRoom.removeItem(i);
                    System.out.println(ID + " picked up " + i.getID());

                    if (i.transfer(this, null)) {
                        System.out.println("YOU WIN");
                    }
                    break;
                }
            }
        } 
        else {
            System.out.println(ID + "  cant pick up " + i.getID());
        }
    }
}
