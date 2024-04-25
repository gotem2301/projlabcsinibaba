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
     * Konstruktor: letrehoz egy TVSZ-t.
     * @param r - Melyik szobaban legyen letrehozva.
     * @param b - Hamis verzio-e.
     */
    public Book(Room r, boolean b) {
        super(r);
        remainingUse = 3;
        fake = b;
        System.out.println("Book konstruktor hivas.");
    }

    /**
     * Csokkenti a hatralevo vedelmek szamat.
     * Ha ez eleri a 0-at akkor torli a jatekbol.
     */
    @Override
    public void lowerRemainingUse() {
        System.out.println("LowerRemainingUse fuggvény hivas.");
        if(fake){
            return;
        }
        System.out.println("Utolso hasznalat legyen?\n" + "[0] Igen    [Barmi mas] Nem\n");

        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        if(s.equals("0")) {
            remainingUse = 1;
        }
        remainingUse = remainingUse - 1;
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
        System.out.println("SaveMe fuggveny hivas.");
        System.out.println("Visszateres: 1.");
        return 1;
    }
}
