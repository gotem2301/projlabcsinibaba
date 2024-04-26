package skeleton;
public class Beer extends Item {

    /**
     * Meghatarozza, hogy a vedelembol mennyi ido van meg hatra.
     */
    private int remainingTime;

    /**
     * remainingTime getter, visszaadja, hogy mennnyi ideig hasznalhato meg a Beer.
     * @return Fennmarado ido a hasznalatbol
     */
    public int getRemainingTime(){
        return remainingTime;
    }

    /**
     * remainingTime setter, beallitja az uj hatralevo idot a hasznalatbol.
     * @param newRemainingTime Uj fennmarado ido
     */
    public void setRemainigTime(int newRemainingTime){
        remainingTime = newRemainingTime;
    }

    /**
     * Konstruktor: letrehoz egy sort.
     * @param r - Melyik szobaban legyen letrehozva.
     * @param t - Mennyi legyen a vedelem ideje.
     */
    public Beer(String id, Room r, Character c, int i) {
        super(id, r, c);
        remainingTime = i;
    }


    /**
     * Csokkenti a vedelembol hatralevo idot.
     * Ha ez eleri a 0-at akkor torli a jatekbol.
     */
    public void lowerRemainingTime() {
        remainingTime = remainingTime - 1;
        System.out.println(heldBy.getId() + " used " + id);
        if(remainingTime == 0) {
            heldBy.removeItem(this);
        }
    }

    /**
     * Megadja, hogy egy item megment-e oktatotol és ha igen akkor melyik.
     * Itt a sor ment meg, ezt a 2 jelzi.
     */
    @Override
    public int saveMe() {
        return 2;
    }
}
