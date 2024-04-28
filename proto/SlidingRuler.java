package proto;
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
    public SlidingRuler(String id, Room r, Character c, boolean b) {
        super(id, r, c);
        fake = b;
        if(fake) {
            System.out.println(id + " is fake");
        }
    }


    /**
     * Folulirva az eredetit mert logarlec eseteben a visszateres true.
     */
    @Override
    public boolean transfer(Character c, Room r) {
        heldBy = c;
        containedBy = r;
        return !fake;
    }
}
