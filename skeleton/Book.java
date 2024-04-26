package skeleton;
import java.util.Scanner;

public class Book extends Item {

    /**
     * Meghatarozza, hogy a vedelembol hany alkalom van meg hatra.
     */
    private int remainingUse;

    /**
     * Azt jeloli, hogy a targy hamis verzioja-e.
     */
    private boolean fake;

    /**
     * remainingUse getter, visszaadja, hogy hanyszor lehet meg hasznalni a Book-ot.
     * @return Hatralevo hasznalat
     */
    public int getRemainingUse(){
        return remainingUse;
    }

    /**
     * remainingUse setter, beallitja, hogy hanyszor lehet meg hasznalni a Book-ot.
     * @param newRemainingUse Uj hatralevo hasznalat
     */
    public void setRemainingUse(int newRemainingUse){
        remainingUse = newRemainingUse;
    }

    /**
     * fake getter, visszaadja, hogy a Book hamis-e.
     * @return Hamis-e a Book
     */
    public boolean getFake(){
        return fake;
    }

    /**
     * fake setter, beallitja, hogy a Book hamis-e.
     * @param newFake Uj hamissag.
     */
    public void setFake(boolean newFake){
        fake = newFake;
    }

    /**
     * Konstruktor: letrehoz egy TVSZ-t.
     * @param r - Melyik szobaban legyen letrehozva.
     * @param b - Hamis verzio-e.
     */
    public Book(String id, Room r, Character c, int i, boolean b) {
        super(id, r, c);
        remainingUse = i;
        fake = b;
        if(fake) {
            System.out.println(id + " is fake");
        }
    }

    /**
     * Csokkenti a hatralevo vedelmek szamat.
     * Ha ez eleri a 0-at akkor torli a jatekbol.
     */
    @Override
    public void lowerRemainingUse() {
        if(fake){
            return;
        }
        remainingUse = remainingUse - 1;
        System.out.println(heldBy.getId() + " used " + id);
        if(remainingUse == 0) {
            heldBy.removeItem(this);
        }
    }

    /**
     * Megadja, hogy egy item megment-e oktatotol és ha igen akkor melyik.
     * Itt a TVSZ ment meg, ezt az 1 jelzi. 
     */
    @Override
    public int saveMe() {
        return 1;
    }
}
