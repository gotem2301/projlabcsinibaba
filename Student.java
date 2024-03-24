import java.util.Scanner;

public class Student extends Character {
    public Student(Room r) {
        super(r);
        System.out.println("Student konstruktor hivas");
    }

    /**
     * Osszekoti a ket parameterkent kapott tranzisztort, ha egyiknek sincs parja
     * @param t1 - egyik tranzisztor
     * @param t2 - masik tranzisztor
     */
    void connect(Transistor t1, Transistor t2){
        System.out.println("connect fuggveny hivas");



        String s;
        System.out.println("Valamelyik tranzisztor rendelkezik parral? \n" +
                "[0] Semelyik   " +
                "[1] Elso   " +
                "[2] Masodik    " +
                "[3] Mindketto\n");
        Scanner scanner = new Scanner(System.in);
        s = scanner.nextLine();
        switch (s){
            case "1":
                Transistor t3 = new Transistor();
                t1.setPair(t3);
                break;
            case "2":
                Transistor t3 = new Transistor();
                t2.setPair(t3);
                break;
            case "3":
                Transistor t3 = new Transistor();
                t1.setPair(t3);
                Transistor t4 = new Transistor();
                t2.setPair(t4);
                break;
            default:
                break;
        }


        if(t1.getPair == null || t2.getPair == null){
            t1.setPair(t2);
            t2.setPair(t1);
            System.out.println("Parositas sikeres");
        }else{
            System.out.println("Parositas NEM sikeres");
        }
    }

    /**
     * A hallgato kapott egy felszolitast a kibukasra, megvizsgalja hogy van-e nala vedelmet nyujto targy.
     * @return - visszateresi erteke meghatarozza az ot megvedo itemet
     * 0: nincs vedelem, 1: TVSZ (Book), 2: sor (Beer), 3: rongy (Cloth)
     */
    int dropOut(){
        System.out.println("dropOut fuggveny hivas");

        int savedBy = 0;
        for(Item i : inventory){
            savedBy = i.saveMe();
            if(savedBy > 0) {
                System.out.println("Visszateres: " + savedBy);
                return savedBy;
            }
        }
        System.out.println("Visszateres: " + savedBy);
        return savedBy;
    }

    /**
     * A student minden itemet kepes felvenni amig nincs tele az inventoryja
     * es nincs elkabitva
     * @param i - Az item amit fel akarunk venni
     */
    @Override
    public void pickUpItem(Item i) {
        System.out.println("pickUpItem fuggveny hivas");

        String s;
        System.out.println("A tarhely tele van? A hallgato el van kabulva?\n" +
                "[0] Nincs tele es nincs elkabulva  " +
                "[1] Tele van de nincs elkabulva    " +
                "[2] Nincs tele de el van kabulva   " +
                "[3] Tele van es el van kabulva    \n");
        Scanner scanner = new Scanner(System.in);
        s = scanner.nextLine();

        switch (s){
            case "1":
                Beer beer = new Beer();
                for(int i = 0; i < 5; i++){
                    this.inventory[i] = beer;
                }
                break;
            case "2":
                   this.setDazed(true);
                break;
            case "3":
                Beer beer = new Beer();
                for(int i = 0; i < 5; i++){
                    this.inventory[i] = beer;
                }
                this.setDazed(true);
                break;
            default:
                for(int i = 0; i < 5; i++){
                    this.inventory[i] = null;
                }
                this.setDazed(false);
                break;
        }

        if(this.inventory.length < 5 && !this.Dazed) {
            if (i.transfer(this, null) == false) {
                this.currentRoom.removeItem(i);
                this.addItem(i);
            } else {
                //Itt valakin megkene hivni az endgame fuggvenyt, de nem tudom hogy az kinek van?
            }
            System.out.println("Sikeres targyfelvetel");
        }else{
            System.out.println("Sikertelen targyfelvetel");
        }
    }
}
