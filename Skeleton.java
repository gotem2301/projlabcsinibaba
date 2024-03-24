package skeleton;
import java.util.Scanner;

public class Skeleton {

    // a parancsok beolvasásához
    static Scanner scanner = new Scanner(System.in);

    // gy.i.k.
    private static final String WARNING_1 = "Nincs ilyen opcio! Kerlek valassz a megadottak kozul!";
    private static final String QUESTION_1 = "Tele van a celszoba? i/n";
    private static final String QUESTION_2 = """
            Milyen vedelem van a tanulonal?
            0 -> semmi
            1 -> tvsz
            2 -> sor
            3 -> rongy""";
    private static final String QUESTION_3 = "Van maszkja? i/n";

    // tanuló átlép biztonságos szobába
    public static void roomChange1(){
        kerdes:
        while (true){
            System.out.println(QUESTION_1);
            Room r1 = new Room();
            Student s = new Student(r1);

            switch (Skeleton.scanner.nextLine()) {
                case "i":
                    // teli szoba
                    System.out.println("igen tele");
                    Room r2 = new Room();
                    Door d12 = new Door(r1, r2);
                    s.enterRoom(d12);
                    break kerdes;
                case "n":
                    // nincs tele
                    System.out.println("nincs tele");
                    Room r3 = new Room();
                    Door d13 = new Door(r1, r3);
                    s.enterRoom(d13);
                    break kerdes;
                default:
                    System.out.println(WARNING_1);
            }
        }
    }

    // tanuló átlép oktatós szobába
    public static void roomChange2(){
        kerdes:
        while (true){
            System.out.println(QUESTION_2);
            Room r1 = new Room();
            Room r2 = new Room();
            Door d = new Door(r1, r2);
            Student s = new Student(r1);
            Teacher t = new Teacher(r2);

            switch (Skeleton.scanner.nextLine()) {
                case "0":
                    // tanuló kibukik
                    System.out.println("kampec");
                    s.enterRoom(d);
                    break kerdes;
                case "1":
                    // tvsz megvéd
                    System.out.println("TVSZ");
                    Book tvsz = new Book();
                    s.inventory.add(tvsz);
                    s.enterRoom(d);
                    break kerdes;
                case "2":
                    // sör megvéd
                    System.out.println("SER");
                    Beer b = new Beer();
                    s.inventory.add(b);
                    s.enterRoom(d);
                    break kerdes;
                case "3":
                    // rongy megvéd
                    System.out.println("NEDV");
                    Cloth c = new Cloth();
                    s.inventory.add(c);
                    s.enterRoom(d);
                    break kerdes;
                default:
                    System.out.println(WARNING_1);
            }
        }
    }

    // szobák összevonása
    public static void mergeRooms(){
        System.out.println("ket szobabol egy lesz ha lehet");
        Room r1 = new Room();
        Room r2 = new Room();
        Door d = new Door(r1, r2);
        r1.mergeWith(r2);
    }

    // szoba szétválása
    public static void splitRooms(){
        System.out.println("egy szobabol ketto lesz");
        Room r1 = new Room();
        r1.split();
    }

    // tanuló átlép gázos szobába
    public static void roomChange3(){
        kerdes:
        while (true){
            System.out.println(QUESTION_3);
            Room r1 = new Room();
            Room r2 = new Room();
            Door d = new Door(r1, r2);
            Student s = new Student(r1);

            switch (Skeleton.scanner.nextLine()) {
                case "i":
                    // használja a maszkot
                    Mask m = new Mask();
                    s.inventory.add(m);
                    s.enterRoom(d);
                    System.out.println("MASZK");
                    break kerdes;
                case "n":
                    // tanuló lebénul
                    s.enterRoom(d);
                    System.out.println("kampec");
                    break kerdes;
                default:
                    System.out.println(WARNING_1);
            }
        }
    }

    // oktató átlép rongyos szobába
    public static void roomChange4(){
        Room r1 = new Room();
        Room r2 = new Room();
        Door d = new Door(r1, r2);
        Teacher t = new Teacher(r1);
        t.enterRoom(d);
        System.out.println("Lebenult az oktato");
    }

    // tanuló felvesz tárgyat
    public static void itemPickup(){
        System.out.println("tied lesz a targy");
        Room r1 = new Room();
        Student s = new Student(r1);
        Beer b = new Beer(r1);
        s.pickUpItem(b);
    }

    // tanuló eldob tárgyat
    public static void itemDrop(){
        System.out.println("eldobtad a targyat");
        Room r1 = new Room();
        Student s = new Student(r1);
        Beer b = new Beer(r1);
        b.transfer(s, r1);
        s.dropItem(b);
    }

    // tranzisztorok összekapcsolása
    public static void transistor1(){
        System.out.println("tranzisztorok osszekapcsolva");
        Room r1 = new Room();
        Student s = new Student(r1);
        Transistor t1 = new Transistor(r1);
        Transistor t2 = new Transistor(r1);
        s.pickUpItem(t1);
        s.pickUpItem(t2);
        s.connect(t1, t2);
    }

    // tranzisztor rendeltetés szerű használata
    public static void transistor2(){
        System.out.println("utazas a masik szobaba");
        Room r1 = new Room();
        Room r2 = new Room();
        Student s = new Student(r1);
        Transistor t1 = new Transistor(r1);
        Transistor t2 = new Transistor(r2);
        t1.transfer(s, r1);
        t1.pair = t2;
        t2.pair = t1;
        t1.use;
    }

    // tranzisztor hasznaláta úgy, hogy más játékos "ellopta" a párját
    public static void transistor3(){
        System.out.println("utazas a masik jatekoshoz");
        Room r1 = new Room();
        Room r2 = new Room();
        Student s1 = new Student(r1);
        Student s2 = new Student(r2);
        Transistor t1 = new Transistor(r1);
        Transistor t2 = new Transistor(r2);
        t1.transfer(s1, r1);
        t2.transfer(s2, r2);
        t1.pair = t2;
        t2.pair = t1;
        t1.use;
    }

    public static void main(String[] args) {
        // a parancsok tárolásához, string, hogy a hibás bemenetre is tudjunk válaszolni
        String choice;

        // lehetőségek kilistázása
        menu:
        while (true) {
            System.out.println("""
                    Tesztesetek:
                    1. Tanulo atlep sima szobaba
                    2. Tanulo atlep tanaros szobaba
                    3. Ket szoba osszeolvad
                    4. Egy szoba osztodik
                    5. Tanuló atlep gazos szobaba
                    6. Oktato atlep rongyos szobaba
                    7. Tanulo felvesz targyat
                    8. Tanulo eldob targyat
                    9. Tanulo osszekapcsol ket tranzisztort
                    10. Tanulo visszautazik szobaba tranzisztorral
                    11. Tanulo visszautazik jatekoshoz tranzisztorral
                    0. Kilepes""");
            choice = Skeleton.scanner.nextLine();

            try{
                // mivel string ezért castoljuk
                int testCase = Integer.parseInt(choice);
                switch (testCase) {
                    case 1:
                        roomChange1();
                        break;
                    case 2:
                        roomChange2();
                        break;
                    case 3:
                        mergeRooms();
                        break;
                    case 4:
                        splitRooms();
                        break;
                    case 5:
                        roomChange3();
                        break;
                    case 6:
                        roomChange4();
                        break;
                    case 7:
                        itemPickup();
                        break;
                    case 8:
                        itemDrop();
                        break;
                    case 9:
                        transistor1();
                        break;
                    case 10:
                        transistor2();
                        break;
                    case 11:
                        transistor3();
                        break;
                    case 0:
                        break menu;
                    default:
                        System.out.println(WARNING_1);
                }
            } catch (NumberFormatException e) {
                System.out.println("Ervenytelen valasztas! Kerlek, adj meg egy szamot a teszteset kivalasztasahoz.");
            }
        }
    }
}