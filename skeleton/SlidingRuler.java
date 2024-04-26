package skeleton;
public class SlidingRuler extends Item {

    /**
     * Azt jeloli, hogy az item hamis verzioja-e.
     */
    private boolean fake;

    /**
     * fake getter, visszaadja, hogy a SlidingRuler hamis-e.
     * @return Hamis-e a SlidingRuler
     */
    public boolean getFake(){
        return fake;
    }

    /**
     * fake setter, beallitja, hogy a SlidingRuler hamis-e.
     * @param newFake Uj hamissag
     */
    public void setFake(boolean newFake){
        fake = newFake;
    }

    /**
     * Konstruktor: letrehoz egy logarlecet.
     * @param r - Melyik szobaban legyen letrehozva.
     */
    public SlidingRuler(Room r, boolean b) {
        super(r);
        fake = b;
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
