import java.util.Scanner;

public class Book extends Item {

    /**
     * Meghatarozza, hogy a vedelembol hany alkalom van meg hatra.
     */
    private int remainingUse;


    /**
     * Konstruktor: letrehoz egy TVSZ-t.
     * @param r - Melyik szobaban legyen letrehozva.
     */
    public Book(Room r) {
        super(r);
        remainingUse = 3;
        System.out.println("Book konstruktor hívás.");
    }


    /**
     * Csokkenti a hatralevo vedelmek szamat.
     * Ha ez eleri a 0-at akkor torli a jatekbol.
     */
    public void lowerRemainingUse() {
        System.out.println("LowerRemainingUse függvény hívás.");
        System.out.println("Utolso hasznalat legyen?\n" + "[0] Igen    [Barmi mas] Nem\n");
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        if(s.equals("0")) {
            remainingUse = 1;
        }
        remainingUse = remainingUse - 1;
        if(remainingUse == 0) {
            System.out.println("RemoveItem függvény hívás.");
            heldBy.removeItem(this);
        }
    }

    /**
     * Megadja, hogy egy item megment-e oktatotol és ha igen akkor melyk.
     * Itt a TVSZ ment meg, ezt az 1 jelzi. 
     */
    @Override
    public int saveMe() {
        System.out.println("SaveMe függvény hívás.");
        System.out.println("Visszatérés: 1.");
        return 1;
    }
}
