package skeleton;

public class Transistor extends Item implements Usable {

    private Transistor pair;



    /**
     * Konstruktor: letrehoz egy Transistor-t
     * @param r Melyik szobaban legyen letrehozva
     */
    public Transistor(String id, Room r, Character c){
        super(id, r, c);

        System.out.println("Transistor konstruktor hivas.");
    }

    /**
     * Visszaadja a Transistor parjat
     * @return A Transistor parja
     */
    public Transistor getPair(){
        System.out.println("Transistor getPair() hivas.");
        return pair;
    }

    /**
     * Beallitja a Transistor parjat
     * @param t A Transistor uj parja
     */
    public void setPair(Transistor t){
        System.out.println("Transistor setPair() hivas.");
        this.pair = t;
    }

    /**
     * Visszaadja a szobat, ahol van a Transistor
     * @return A szoba, ahol van a Transistor
     */
    public Room getRoom(){
        System.out.println("Transistor getRoom() hivas.");
        return containedBy;
    }

    /**
     * Bekapcsol egy tranzisztort
     */
    public void use(){
        System.out.println("Transistor use hivas.");
        Character s = this.heldBy;
        Room t1Room = this.getRoom();
        Transistor t1 = this;
        Transistor t2 = this.pair;
        Room t2Room;
        if(t2.heldBy != null){
            t2Room = t2.heldBy.getCurrentRoom();
        }
        else{
            t2Room = pair.containedBy;
        }

        
        if (t2Room.characterEnters(s)) {
            s.dropItem(t1);
            s.setCurrentRoom(t2Room);
            t2Room.addCharacter(s);
            t1Room.removeCharacter(s);
            t1Room.addItem(t1);
            this.transfer(null, t1Room);
            System.out.println("Transistor use sikeres.");
            return;
        }
        System.out.println("Transistor use sikertelen.");
    }
}
