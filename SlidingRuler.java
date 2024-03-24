public class SlidingRuler extends Item {

    /**
     * Konstruktor: letrehoz egy logarlecet.
     * @param r - Melyik szobaban legyen letrehozva.
     */
    public SlidingRuler(Room r) {
        super(r);
        System.out.println("SlidingRuler konstruktor hívás.");
    }


    /**
     * Folulirva az eredetit mert logarlec eseteben a visszateres true.
     */
    @Override
    public boolean transfer(Character c, Room r) {
        System.out.println("Transfer függvény hívás.");
        heldBy = c;
        containedBy = r;
        System.out.println("Visszatérés: true.");
        return true;
    }
}
