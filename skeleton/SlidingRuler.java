package skeleton;
public class SlidingRuler extends Item {

    /**
     * Konstruktor: letrehoz egy logarlecet.
     * @param r - Melyik szobaban legyen letrehozva.
     */
    public SlidingRuler(Room r) {
        super(r);
        System.out.println("SlidingRuler konstruktor hivas.");
    }


    /**
     * Folulirva az eredetit mert logarlec eseteben a visszateres true.
     */
    @Override
    public boolean transfer(Character c, Room r) {
        System.out.println("Transfer fuggveny hivas.");
        heldBy = c;
        containedBy = r;
        System.out.println("Visszateres: true.");
        return true;
    }
}
