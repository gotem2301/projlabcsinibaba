import java.util.Scanner;

public class Mask extends Item {

    /**
     * Meghatarozza, hogy a vedelembol mennyi ido van meg hatra.
     */
    private int remainingTime;

    /**
     * Meghatarozza, hogy hanyszor volt hasznalva.
     */
    private int usedTimes;

    
    /**
     * Konstruktor: letrehoz egy maszkot.
     * @param r - Melyik szobaban legyen letrehozva.
     * @param t - Mennyi legyen a vedelem ideje.
     */
    public Mask(Room r, int t) {
        super(r);
        remainingTime = t;
        usedTimes = 0;
        System.out.println("Mask konstruktor hívás.");
    }


    /**
     * Noveli a hasznalat szamat.
     */
    public void increaseUsedTimes() {
        System.out.println("IncreaseUsedTimes függvény hívás.");
        usedTimes = usedTimes + 1;
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
     * Megadja, hogy egy item megved-e a gaztol.
     * A mask megved, ezt a true jelzi.
     */
    @Override
    public boolean protectMe() {
        System.out.println("ProtectMe fuggveny hivas.");
        System.out.println("Visszateres: true.");
        return true;
    }
}
