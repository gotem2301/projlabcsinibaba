package proto;

public class Transistor extends Item implements Usable {

    private Transistor pair;



    /**
     * Konstruktor: letrehoz egy Transistor-t
     * @param r Melyik szobaban legyen letrehozva
     */
    public Transistor(String id, Room r, Character c){
        super(id, r, c);
    }

    /**
     * Visszaadja a Transistor parjat
     * @return A Transistor parja
     */
    public Transistor getPair(){
        return pair;
    }

    /**
     * Beallitja a Transistor parjat
     * @param t A Transistor uj parja
     */
    public void setPair(Transistor t){
        this.pair = t;
    }

    /**
     * Visszaadja a szobat, ahol van a Transistor
     * @return A szoba, ahol van a Transistor
     */
    public Room getRoom(){
        return containedBy;
    }

    /**
     * Bekapcsol egy tranzisztort
     */
    public void use(){
        System.out.println(heldBy.getId() + " used " + id);
        Character s = this.heldBy;
        Room t1Room = s.getCurrentRoom();
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
            System.out.println(s.getId() + " entered " + t2Room.getId());
            s.setCurrentRoom(t2Room);
            t2Room.addCharacter(s);
            t1Room.removeCharacter(s);
            this.transfer(null, t1Room);
            return;
        }
    }
}
