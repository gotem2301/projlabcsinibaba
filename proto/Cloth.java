package proto;
public class Cloth extends Item {

    /**
     * Meghatarozza, hogy a vedelembol mennyi ido van meg hatra.
     */
    private int remainingTime;

    /**
     * remainingTime getter, visszaadja, hogy mennyi ideig hasznalhato meg a Cloth.
     * @return Hatralevo hasznalati ido
     */
    public int getRemainingTime(){
        return remainingTime;
    }

    /**
     * remainingTime setter, beallitja, hogy mennyi ideig hasznalhato meg a Cloth.
     * @param newRemainingTime Uj hatralevo hasznalati ido
     */
    public void setRemainigTime(int newRemainingTime){
        remainingTime = newRemainingTime;
    }

    /**
     * Konstruktor: letrehoz egy rongyot.
     * @param r - Melyik szobaban legyen letrehozva.
     * @param t - Mennyi legyen a vedelem ideje.
     */
    public Cloth(String id, Room r, Character c) {
        super(id, r, c);
        remainingTime = 3;
    }


    /**
     * Megadja, hogy egy item megment-e oktatotol és ha igen akkor melyik.
     * Itt a rongy ment meg, ezt a 3 jelzi.
     */
    @Override
    public int saveMe() {
        return 3;
    }
}
