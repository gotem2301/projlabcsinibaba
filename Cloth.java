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
    public Cloth(Room r, int t) {
        super(r);
        remainingTime = t;
        System.out.println("Cloth konstruktor hívás.");
    }


    /**
     * Megadja, hogy egy item megment-e oktatotol és ha igen akkor melyk.
     * Itt a rongy ment meg, ezt a 3 jelzi.
     */
    @Override
    public int saveMe() {
        System.out.println("SaveMe függvény hívás.");
        System.out.println("Visszatérés: 3.");
        return 3;
    }
}
