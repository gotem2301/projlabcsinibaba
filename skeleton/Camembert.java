package skeleton;

public class Camembert extends Item implements Usable{
    
    /**
     * Konstruktor: letrehoz egy Camembert-et
     * @param r Melyik szobaban legyen letrehozva
     */
    public Camembert(Room r){
        super(r);
    
        System.out.println("Camembert konstruktor hivas.");
    }

    /**
     * Elgazositja a szobat (amiben eppen van)
     */
    public void use(){
        System.out.println("Camembert use hivas.");
        containedBy.setState(Room.GASSED);
    }
}
