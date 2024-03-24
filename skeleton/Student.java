package skeleton;

public class Student extends Character {
    public Student(Room r) {
        super(r);
        System.out.println("Student konstruktor hivas");
    }

    /**
     * Osszekoti a ket parameterkent kapott tranzisztort, ha egyiknek sincs parja
     *
     * @param t1 - egyik tranzisztor
     * @param t2 - masik tranzisztor
     */
    public void connect(Transistor t1, Transistor t2) {
        System.out.println("connect fuggveny hivas");

        if (t1.getPair() == null || t2.getPair() == null) {
            t1.setPair(t2);
            t2.setPair(t1);
            System.out.println("Parositas sikeres");
        } else {
            System.out.println("Parositas NEM sikeres");
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
        System.out.println("dropOut fuggveny hivas");

        int savedBy = 0;
        for (Item i : inventory) {
            if (i != null) {
                savedBy = i.saveMe();
                if (savedBy > 0) {
                    System.out.println("Visszateres: " + savedBy);
                    if(savedBy == 1) {
                        i.lowerRemainingUse();
                    }
                    return savedBy;
                }
            }
        }
        System.out.println("Visszateres: " + savedBy);
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
        System.out.println("pickUpItem fuggveny hivas");

        if (!this.Dazed) {
            for (int j = 0; j < 5; j++) {
                if (inventory[j] == null) {
                    inventory[j] = i;
                    this.currentRoom.removeItem(i);
                    break;
                }
            }

            System.out.println("Sikeres targyfelvetel");
            if (i.transfer(this, null) != false) {
                {
                    System.out.println("Jatek vege, mivel felvettek egy hallgato a logarlecet");
                }

            } else {
                System.out.println("Sikertelen targyfelvetel");
            }
        }
    }
}
