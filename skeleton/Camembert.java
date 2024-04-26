package skeleton;

public class Camembert extends Item implements Usable{
    
    /**
     * Konstruktor: letrehoz egy Camembert-et
     * @param r Melyik szobaban legyen letrehozva
     */
    public Camembert(String id, Room r, Character c){
        super(id, r, c);
    
        System.out.println("Camembert konstruktor hivas.");
    }

    /**
     * Elgazositja a szobat, amiben van, ha containedBy != null
     * Egyebkent azt a szobat, amelyikben van a karakter, akinel van a Camembert.
     */
    public void use(){
        System.out.println("Camembert use hivas.");
        if(containedBy != null){
            containedBy.setgassedRoom(true);
        }
        else{
            heldBy.getCurrentRoom().setgassedRoom(true);
        }
    }
}
