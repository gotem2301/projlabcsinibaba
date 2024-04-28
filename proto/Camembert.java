package proto;

public class Camembert extends Item implements Usable{
    
    /**
     * Konstruktor: letrehoz egy Camembert-et
     * @param r Melyik szobaban legyen letrehozva
     */
    public Camembert(String id, Room r, Character c){
        super(id, r, c);
    }

    /**
     * Elgazositja a szobat, amiben van, ha containedBy != null
     * Egyebkent azt a szobat, amelyikben van a karakter, akinel van a Camembert.
     */
    public void use(){
        if(containedBy != null){
            containedBy.setgassedRoom(true);
        }
        else{
            System.out.println(heldBy.getId() + " gassed " + heldBy.getCurrentRoom().getId());
            Room r = heldBy.getCurrentRoom();
            heldBy.removeItem(this);
            r.setgassedRoom(true);
        }
    }
}
