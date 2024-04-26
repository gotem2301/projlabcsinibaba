package skeleton;
public class AirFreshener extends Item implements Usable{
    /**
     * Konstruktor: letrehoz egy legfrissitot.
     * @param r - Melyik szobaban legyen letrehozva
     */
    public AirFreshener(Room r) {
        super(r);
        System.out.println("AirFreshener konstruktor hivas.");
    }

    /**
     * Ha containedBy != null, akkor a szoba ahol van, az nem gazos.
     * Egyebkent az a szoba nem lesz gazos, amelyikben az a karakter van, akinel van a legfrissito.
     */
    public void use(){
        System.out.println("AirFreshener use hivas.");
        if (containedBy != null){
            containedBy.setgassedRoom(false);
        }
        else{
            heldBy.getCurrentRoom().setgassedRoom(false);
        }
    }
}