import java.util.Scanner;

public class Beer extends Item {

    /**
     * Meghatarozza, hogy a vedelembol mennyi ido van meg hatra.
     */
    private int remainingTime;


    /**
     * Konstruktor: letrehoz egy sort.
     * @param r - Melyik szobaban legyen letrehozva.
     * @param t - Mennyi legyen a vedelem ideje.
     */
    public Beer(Room r, int t) {
        super(r);
        remainingTime = t;
        System.out.println("Beer konstruktor hívás.");
    }


    /**
     * Csokkenti a vedelembol hatralevo idot.
     * Ha ez eleri a 0-at akkor torli a jatekbol.
     */
    public void lowerRemainingTime() {
        System.out.println("LowerRemainingTime függvény hívás.");
        System.out.println("Utolso hasznalat legyen?\n" + "[0] Igen    [Barmi mas] Nem\n");
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        if(s.equals("0")) {
            remainingTime = 1;
        }
        remainingTime = remainingTime - 1;
        if(remainingTime == 0) {
            System.out.println("RemoveItem függvény hívás.");
            heldBy.removeItem(this);
        }
    }

    /**
     * Megadja, hogy egy item megment-e oktatotol és ha igen akkor melyk.
     * Itt a sor ment meg, ezt a 2 jelzi.
     */
    @Override
    public int saveMe() {
        System.out.println("SaveMe függvény hívás.");
        System.out.println("Visszatérés: 2.");
        return 2;
    }
}
