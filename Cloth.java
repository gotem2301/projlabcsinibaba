public class Cloth extends Item {

    /**
     * Meghatarozza, hogy a vedelembol mennyi ido van meg hatra.
     */
    private int remainingTime;


    /**
     * Konstruktor: letrehoz egy rongyot.
     * @param r - Melyik szobaban legyen letrehozva.
     * @param t - Mennyi legyen a vedelem ideje.
     */
    public Cloth(Room r) {
        super(r);
        remainingTime = 3;
        System.out.println("Cloth konstruktor hivas.");
    }


    /**
     * Megadja, hogy egy item megment-e oktatotol és ha igen akkor melyik.
     * Itt a rongy ment meg, ezt a 3 jelzi.
     */
    @Override
    public int saveMe() {
        System.out.println("SaveMe fuggveny hivas.");
        System.out.println("Visszateres: 3.");
        return 3;
    }
}
