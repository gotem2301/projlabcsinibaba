package skeleton;
public abstract class Item {

    /**
     * Meghatarozza, hogy melyik karakternel van az item.
     * Ha szobaban van akkor null.
     */
    protected Character heldBy;

    /**
     * Meghatarozza, hogy melyik szobaban van az item.
     * Ha karakternel van akkor null.
     */
    protected Room containedBy;


    /**
     * Konstruktor.
     * @param r - Melyik szobaban legyen letrehozva.
     */
    public Item(Room r) {
        heldBy = null;
        containedBy = r;
    }


    /**
     * Atallitja mindket tagvaltozot.
     * @param c - Az uj karakter akinel lesz az item.
     * @param r - Az uj szoba ahol lesz az item.
     * @return - Ha nem a logarlecrol van szo akkor false.
     */
    public boolean transfer(Character c, Room r) {
        System.out.println("Transfer fuggveny hivas.");
        heldBy = c;
        containedBy = r;
        System.out.println("Visszateres: false.");
        return false;
    }

    /**
     * Beallitja a szoba tagvaltozot.
     * @param r - Az uj szoba ahol lesz az item.
     */
    public void updateRoom(Room r) {
        System.out.println("UpdateRoom fuggveny hivas.");
        containedBy = r;
    }

    /**
     * Megadja, hogy egy item megment-e oktatotol és ha igen akkor melyik.
     * @return - Alapertelmezetten nem ment meg egy item, ezt a 0 jelzi.
     */
    public int saveMe() {
        System.out.println("SaveMe fuggveny hivas.");
        System.out.println("Visszateres: 0.");
        return 0;
    }

    /**
     * Megadja, hogy egy item megved-e a gaztol.
     * @return - Alapertelmezetten nem ved meg egy item, ezt a false jelzi.
     */
    public boolean protectMe() {
        System.out.println("ProtectMe fuggveny hivas.");
        System.out.println("Visszateres: false.");
        return false;
    }
}
