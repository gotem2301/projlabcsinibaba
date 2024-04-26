package skeleton;
public abstract class Item implements ID {

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
     * Itemek azonositasara szolgalo Id.
     */
    protected String id;

    /**
     * heldBy getter, visszaadja azt a Charactert, akinel van az Item.
     * @return Itemet hordozo Character
     */
    public Character getHeldBy(){
        return heldBy;
    }

    /**
     * heldBy setter, beallitja azt a Charactert, aki az Item uj hordozoja. 
     * @param newHolder Uj hordozo Character
     */
    public void setHeldBy(Character newHolder){
        heldBy = newHolder;
    }

    /**
     * containedBy getter, visszaadja azt a Room-ot, amelyikben van az Item.
     * @return Az a szoba, ahol az Item talalhato
     */
    public Room getContainedBy(){
        return containedBy;
    }

    /**
     * containedBy setter, beallitja azt a Room-ot, ahol az Item megtalalhato lesz.
     * @param newContainer Az a szoba, ahol az Item megtalalhato lesz
     */
    public void setContainedBy(Room newContainer){
        containedBy = newContainer;
    }

    /**
     * id getter, visszaadja az Item azonositojat.
     * @return Item azonositoja
     */
    public String getId(){
        return id;
    }

    /**
     * id setter, beallitja az Item azonositojat.
     * @param newId Item uj azonositoja
     */
    public void setId(String newId){
        id = newId;
    }

    /**
     * Atallitja mindket tagvaltozot.
     * @param c - Az uj karakter akinel lesz az item.
     * @param r - Az uj szoba ahol lesz az item.
     * @return - Ha nem a logarlecrol van szo akkor false.
     */
    public boolean transfer(Character c, Room r) {
        heldBy = c;
        containedBy = r;
        return false;
    }

    /**
     * Beallitja a szoba tagvaltozot.
     * @param r - Az uj szoba ahol lesz az item.
     */
    public void updateRoom(Room r) {
        containedBy = r;
    }

    /**
     * Megadja, hogy egy item megment-e oktatotol és ha igen akkor melyik.
     * @return - Alapertelmezetten nem ment meg egy item, ezt a 0 jelzi.
     */
    public int saveMe() {
        return 0;
    }

    /**
     * Megadja, hogy egy item megved-e a gaztol.
     * @return - Alapertelmezetten nem ved meg egy item, ezt a false jelzi.
     */
    public boolean protectMe() {
        return false;
    }

    /**
     * Absztrakt fuggveny, leszarmazo osztalyok implemetaljak.
     */
    public void lowerRemainingUse() {}

    /**
     * Fuggveny definicio, nem tud semmit.
     */
    public void use() {}

    /**
     * Konstruktor.
     * @param r - Melyik szobaban legyen letrehozva.
     */
    public Item(String id, Room r, Character c) {
        this.id = id;
        heldBy = c;
        containedBy = r;
        if(containedBy != null){
            System.out.println(id + " created in " + containedBy.getId());
            containedBy.addItem(this);
        }
        else{
            System.out.println(id + " created in " + heldBy.getId());
            heldBy.pickUpItem(this);
        }
    }
}
