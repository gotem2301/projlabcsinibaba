package skeleton;
import java.lang.invoke.ClassSpecializer.Factory;
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
     * Meghatarozza, hogy a targy hamis verzioja-e.
     */
    private boolean fake;

    /**
     * remainingTime getter, visszaadja, hogy mennnyi ideig hasznalhato meg a Mask.
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
     * usedTimes getter, visszaadja, hogy hanyszor volt eddig hasznalva a Mask.
     * @return Hasznalatok szama
     */
    public int getUsedTimes(){
        return usedTimes;
    }

    /**
     * usedTimes setter, beallitja, hogy hanyszor volt eddig hasznalva a Mask.
     * @param newUsedTimes (Uj) hasznalatok szama
     */
    public void setUsedTimes(int newUsedTimes){
        usedTimes = newUsedTimes;
    }

    /**
     * fake getter, visszaadja, hogy a Mask hamis-e.
     * @return Hamis-e a Mask
     */
    public boolean getFake(){
        return fake;
    }

    /**
     * fake setter, beallitja, hogy a Mask hamis-e.
     * @param newFake Uj hamissag
     */
    public void setFake(boolean newFake){
        fake = newFake;
    }
    
    /**
     * Konstruktor: letrehoz egy maszkot.
     * @param r - Melyik szobaban legyen letrehozva.
     * @param t - Mennyi legyen a vedelem ideje.
     */
    public Mask(Room r, boolean b) {
        super(r);
        remainingTime = 3;
        usedTimes = 0;
        fake = b;
        System.out.println("Mask konstruktor hivas.");
    }


    /**
     * Noveli a hasznalat szamat.
     */
    public void increaseUsedTimes() {
        System.out.println("IncreaseUsedTimes fuggveny hivas.");
        if (fake) {
            return;
        }
        usedTimes = usedTimes + 1;
    }

    /**
     * Csokkenti a vedelembol hatralevo idot.
     * Ha ez eleri a 0-at akkor torli a jatekbol.
     */
    public void lowerRemainingTime() {
        System.out.println("LowerRemainingTime fuggveny hivas.");
        if (fake) {
            return;
        }
        System.out.println("Utolso hasznalat legyen?\n" + "[0] Igen    [Barmi mas] Nem\n");
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        if(s.equals("0")) {
            remainingTime = 1;
        }
        remainingTime = remainingTime - 1;
        if(remainingTime == 0) {
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
