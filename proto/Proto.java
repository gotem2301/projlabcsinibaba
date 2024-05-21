package proto;

import java.io.*;
import java.util.*;

public class Proto {

    /**
     * Az osszes szobat eltarolom ami letrejon a futas soran
     */
    private List<Room> allRooms = new ArrayList<>();
    /**
     * Az osszes ajtot eltarolom ami letrejon a futas soran
     */
    private List<Door> allDoors = new ArrayList<>();
    /**
     * Az osszes karaktert eltarolom ami letrejon a futas soran
     */
    private List<Character> allCharacters = new ArrayList<>();
    /**
     * Mivel az osszes karakter egy kozos listaban van, de az id-juk kulonbozo, ezert
     * mindegyiknek kulon tarolom a db szamat. Ez a hallgatok szama.
     */
    private int nOfStudents = 0;
    /**
     * Oktatok szama
     */
    private int nOfTeachers = 0;
    /**
     * Takaritok szama
     */
    private int nOfCleaners = 0;
    /**
     * Az osszes targyat eltarolom ami letrejon a futas soran
     */
    private List<Item> allItems = new ArrayList<>();

    private List<Integer> nOfEachItem = new ArrayList<>(Collections.nCopies(8, 0));

    /**
     * A futas soran beirt parancsokat itt eltarolom, igy kesobb a mentesnel csak ezt kell kiirni
     * a .txt fileba, es a betolteskor a jo allapot toltodik vissza.
     */
    private List<List<String>> commands = new ArrayList<>();
    /**
     * Itt tarolodik hogy a jatek fut-e
     */
    private boolean isRunning = true;
    /**
     * A jatekbol hatralevo ido
     */
    private int Time = Integer.MAX_VALUE;
    private boolean gameOver = false;

    public List<Room> getAllRooms(){
        return allRooms;
    }
    public void removeRoom(Room r){
        allRooms.remove(r);
    }
    public List<Character> getAllCharacters(){
        return allCharacters;
    }
    public void removeCharacter(Character c){
        allCharacters.remove(c);
    }

    public List<Student> getStudents(){
        List<Student> studentList = new ArrayList<>();
        for(Character c : allCharacters){
            if(c.getId().contains("s")){
                studentList.add((Student) c);
            }
        }
        return studentList;
    }
    public List<Teacher> getTeachers(){
        List<Teacher> teacherList = new ArrayList<>();
        for(Character c : allCharacters){
            if(c.getId().contains("t")){
                teacherList.add((Teacher) c);
            }
        }
        return teacherList;
    }
    public List<Cleaner> getCleaners(){
        List<Cleaner> cleanerList = new ArrayList<>();
        for(Character c : allCharacters){
            if(c.getId().contains("c")){
                cleanerList.add((Cleaner) c);
            }
        }
        return cleanerList;
    }

    public void refreshStudents(){
        List<Student> aliveStudents = new ArrayList<>();
        for(Room r : allRooms){
            if(!r.getCharacters().isEmpty()){
                for(Character c : r.getCharacters()){
                    if(c.getId().contains("s")){
                        aliveStudents.add((Student) c);
                    }
                }
            }
        }
        List<Student> students = this.getStudents();
        students.removeAll(aliveStudents);
        allCharacters.removeAll(students);
    }

    public List<Item> getAllItems(){
        return allItems;
    }
    public void removeItem(Item i){
        allItems.remove(i);
    }
    public List<Door> getAllDoors(){
        return allDoors;
    }
    public void removeDoor(Door d){
        allDoors.remove(d);
    }
    /**
     * Ez a fuggveny oldja meg az ido csokkenteset, KULON THREADEN KELL INDITANI, ez legyen a
     * belepesi pont
     */
    private void gameTime(){
        Time--;
        if(Time <= 0){
            System.out.println("Game Over");
            System.exit(0);
        }

    }

    /**
     * A parancsertelmezo fuggveny, kap egy InputStream tipusu valtozot, es abban megprobalja
     * kikeresni a parancsokat, vegigmeny a soron es a szokozok menten felbontja a sort tobb stringre.
     * System.In-el pseudo parancs sorkent hasznalhato, de fajlbol beolvasott sorokat is ezzel
     * tudunk lefuttatni, miutan InputStreamet csinaltunk belole. Addig olvas amig a felhasznalo ki
     * nem lep, vagy elfogytak a sorok
     * @param inputStream - Ebben az InputStreamben fogja keresni a parancsokat.
     */
    public void Run(InputStream inputStream){
        Scanner scanner = new Scanner(inputStream);
        Thread timeThread = new Thread(() -> {
            while (!gameOver) {
                gameTime();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        });
        timeThread.start();

        while(isRunning && scanner.hasNext()) {
            String command = scanner.nextLine();

            List<String> arguments = List.of(command.split(" "));

            try {
                switch (arguments.get(0)) {

                    case "NewRoom":
                        newRoom(arguments);
                        break;

                    case "NewStudent":
                        newStudent(arguments);
                        break;

                    case "NewTeacher":
                        newTeacher(arguments);
                        break;

                    case "NewCleaner":
                        newCleaner(arguments);
                        break;

                    case "NewItem":
                        newItem(arguments);
                        break;

                    case "NewDoor":
                        newDoor(arguments);
                        break;

                    case "SetTime":
                        setTime(arguments);
                        break;

                    case "Split":
                        split(arguments);
                        break;

                    case "Merge":
                        merge(arguments);
                        break;

                    case "ListAll":
                        listAll(arguments);
                        break;

                    case "ListRoom":
                        listRoom(arguments);
                        break;

                    case "ListChar":
                        listChar(arguments);
                        break;

                    case "ListItem":
                        listItem(arguments);
                        break;

                    case "ListDoor":
                        listDoor(arguments);
                        break;

                    case "Enter":
                        enter(arguments);
                        break;

                    case "PickUp":
                        pickUp(arguments);
                        break;

                    case "Drop":
                        drop(arguments);
                        break;

                    case "Use":
                        use(arguments);
                        break;

                    case "Connect":
                        connect(arguments);
                        break;

                    case "LoadGame":
                        loadGame(arguments);
                        break;

                    case "Test":
                        test(arguments);
                        break;

                    case "SaveGame":
                        saveGame(arguments);
                        break;


                    case "Exit":
                        isRunning = false;
                        break;

                    default:
                        System.out.println("Ilyen parancs nem letezik!");
                        break;
                }
            }catch (IllegalArgumentException IAE){
                System.out.println("A parancs nincs jol parameterezve!" + IAE.toString());
            }
        }
    }

    /**
     * A parancsaink a boolean ertekeket '+' ha true '-' ha false formaban varjak, ez a
     * fuggveny az ilyen stringek boolean erteket adja vissza, ha van
     * @param s - A konvertalando string
     * @return - Ha a string "+" akkor true, ha "-" akkor false a visszateresi ertek
     * @throws IllegalArgumentException - Ezt akkor dobja ha a string nem "+" vagy "-", tehat rosszul
     * lett parameterezve a beirt parancs
     */
    private boolean converter(String s) throws IllegalArgumentException {
        if (s.equals("+"))
            return true;

        if(s.equals("-"))
            return false;

        throw new IllegalArgumentException();
    }


    /**
     * Egy generikus fuggveny, ami kap egy listat meg egy stringet, es megkeresi a listaban
     * azt az elemet aminek az id-je megegyezik a kapott stringgel
     * @param list - A lista amiben a keresest vegezzuk
     * @param id - Az id amit meg akarunk keresni a listaban
     * @return - Visszaadja az elemet aminek id-je megegyezett a keresettel
     * @param <T> - Olyan tipusokat varunk amik megvalostitjak az ID interface-t, azaz van getId()
     *           fuggvenyuk
     * @throws IllegalArgumentException - Ezt a kivetelt akkor dobja ha nem talalta meg a listaban a
     *     keresett id-t, azaz a parancsnak a parameterekent rossz objektumot adott meg a felhasznalo
     */
    private <T extends ID> T findID(List<T> list, String id) throws IllegalArgumentException {
        for(T element : list) {
            if(element.getId().equals(id))
                return element;
        }
        throw new IllegalArgumentException(id + " nem jó");
    }

    /**
     * Letrehoz egy szoba objektumot a parancsban megadott parameterekket
     * @param args - a parancsban megadott parameterek, a parameterei elottuk az args listaban
     *             levo indexukkel:
     *             1 Gazos-e a szoba
     *             2 A szobaban van-e "rongyos oktato"
     *             3 A szoba maximalis kapacitasa
     *             4 Magikus-e a szoba, azaz hogy az ajtoi eltunnek-e adott idonkent
     *             5 ragacsos-e a szoba
     *             6 a legutobbi takaritas ota belepo karakterek szama
     */
    public void newRoom(List<String> args){
        if(args.size() != 7){
            throw new IllegalArgumentException();
        }


        boolean gassed, magical, sticky;
        gassed = converter(args.get(1));
        magical = converter(args.get(4));
        sticky = converter(args.get(5));
        String id = "r";
        id = id.concat(Integer.toString(allRooms.size()));
        int capacity = Integer.parseInt(args.get(3));
        if(capacity == -23){
            capacity = Integer.MAX_VALUE;
        }

        Room room = new Room(id, gassed, Integer.parseInt(args.get(2)),
                capacity, magical, sticky, Integer.parseInt(args.get(6)));
        allRooms.add(room);

        commands.add(args);

    }
    /**
     * Letrehoz egy Hallgato objektumot a parancsban megadott parameterekket
     * @param args - a parancsban megadott parameterek, parameterei elottuk az args listaban
     *             levo indexukkel:
     *             1 El van-e kabulva
     *             2 A szoba objektum amiben tartozkodik
     */
    public void newStudent(List<String> args){
        if(args.size() != 3){
            throw new IllegalArgumentException();
        }
        boolean dazed;
        dazed = converter(args.get(1));
        String id = "s";
        id = id.concat(Integer.toString(nOfStudents++));

        Room r = findID(allRooms, args.get(2));
        Student student = new Student(id, r);
        if(dazed){
            student.setDazed(true);
        }
        allCharacters.add(student);
        commands.add(args);
    }
    /**
     * Letrehoz egy Oktato objektumot a parancsban megadott parameterekket
     * @param args - a parancsban megadott parameterek, parameterei elottuk az args listaban
     *             levo indexukkel:
     *             1 Kabult allapot
     *             2 Rongyos allapot
     *             3 A szoba amiben jelenleg tartozkodik
     */
    public void newTeacher(List<String> args){
        if(args.size() != 4){
            throw new IllegalArgumentException();
        }

        boolean dazed = converter(args.get(1));
        int clothed = Integer.parseInt(args.get(2));
        String id = "t";
        id = id.concat(Integer.toString(nOfTeachers++));

        Room r = findID(allRooms, args.get(3));
        Teacher teacher = new Teacher(id, r);
        if(dazed){
            teacher.setDazed(true);
        }
        teacher.setClothed(clothed);
        allCharacters.add(teacher);
        commands.add(args);
    }
    /**
     * Letrehoz egy Takarito objektumot a parancsban megadott parameterekket
     * @param args - a parancsban megadott parameterek, parameterei elottuk az args listaban
     *             levo indexukkel:
     *             1 El van-e kabulva
     *             2 A szoba objektum amiben tartozkodik
     */
    public void newCleaner(List<String> args){
        if(args.size() != 3){
            throw new IllegalArgumentException();
        }

        boolean dazed = converter(args.get(1));
        String id = "c";
        id = id.concat(Integer.toString(nOfCleaners++));

        Room r = findID(allRooms, args.get(2));
        Cleaner cleaner = new Cleaner(id, r);
        if(dazed){
            cleaner.setDazed(true);
        }
        allCharacters.add(cleaner);
        commands.add(args);
    }
    /**
     * Letrehoz egy Targybol szarmaztatott objektumot a parancsban megadott parameterekket
     * @param args - a parancsban megadott parameterek, parameterei elottuk az args listaban
     *             levo indexukkel:
     *             1 A targy tipusa (a tipusokat lasd lentebb)
     *             2 A szoba amiben jelenleg van, ha karakternel van es nem szobaban akkor ez '-'
     *             3 A karakter akinel jelenleg van, ha szobaban van akkor ez '-'
     *             4 Ha a targy csak parszor hasznalhato, akkor hasznalatok szama
     *             5 Ha a targynak van hamis verzioja, akkor az itt adhato meg
     *             A 4-es es 5-os indexu parameterek targy fuggok, de csak akkor vizsgalja oket
     *             a program ha olyan tipusu targyat hozunk letre, de minden esetben meg kell adni
     *             ezt az 5 argumentumot.
     * Targyak tipusa:
     *             1 tranzisztor, 2 rongy, 3 maszk, 4 camembert, 5 tvsz, 6 sor,
     *             7 legfrissito, 8 logarlec
     */
    public void newItem(List<String> args){
        Room room;
        Character character;
        Item item;
        if(args.get(2).equals("-")){
            room = null;
            character = findID(allCharacters, args.get(3));
        }else{
            character = null;
            room = findID(allRooms, args.get(2));
        }

        String id;

        int remaining;
        boolean fake;

        switch (args.get(1)){
            case "1":
                id = "tr";
                id = id.concat(Integer.toString(nOfEachItem.get(0)));
                nOfEachItem.set(0, nOfEachItem.get(0) + 1);
                item = new Transistor(id, room, character);
                break;
            case "2":
                id = "cl";
                id = id.concat(Integer.toString(nOfEachItem.get(1)));
                nOfEachItem.set(1, nOfEachItem.get(1) + 1);
                item = new Cloth(id, room, character);
                break;
            case "3":
                remaining = Integer.parseInt(args.get(4));
                fake = converter(args.get(5));
                id = "ma";
                id = id.concat(Integer.toString(nOfEachItem.get(2)));
                nOfEachItem.set(2, nOfEachItem.get(2) + 1);
                item = new Mask(id, room, character, remaining, fake);
                break;
            case "4":
                id = "ca";
                id = id.concat(Integer.toString(nOfEachItem.get(3)));
                nOfEachItem.set(3, nOfEachItem.get(3) + 1);
                item = new Camembert(id, room, character);
                break;
            case "5":
                remaining = Integer.parseInt(args.get(4));
                fake = converter(args.get(5));
                id = "bo";
                id = id.concat(Integer.toString(nOfEachItem.get(4)));
                nOfEachItem.set(4, nOfEachItem.get(4) + 1);
                item = new Book(id, room, character, remaining, fake);
                break;
            case "6":
                id = "be";
                id = id.concat(Integer.toString(nOfEachItem.get(5)));
                nOfEachItem.set(5, nOfEachItem.get(5) + 1);
                remaining = Integer.parseInt(args.get(4));
                item = new Beer(id, room, character, remaining);

                break;
            case "7":
                id = "ai";
                id = id.concat(Integer.toString(nOfEachItem.get(6)));
                nOfEachItem.set(6, nOfEachItem.get(6) + 1);
                item = new AirFreshener(id, room, character);
                break;
            case "8":
                fake = converter(args.get(4));
                id = "sl";
                id = id.concat(Integer.toString(nOfEachItem.get(7)));
                nOfEachItem.set(7, nOfEachItem.get(7) + 1);
                item = new SlidingRuler(id, room, character, fake);
                break;
            default:
                throw new IllegalArgumentException();
        }
        allItems.add(item);
        commands.add(args);
    }
    /**
     * Letrehoz egy Ajto objektumot a parancsban megadott parameterekket
     * @param args - a parancsban megadott parameterek, parameterei elottuk az args listaban
     *             levo indexukkel:
     *             1 A kisebb sorszamu szoba
     *             2 A nagyobb sorszamu szoba
     *             3 egyiranyu-e az ajto
     *             4 Az ajto lathatosaga
     */
    public void newDoor(List<String> args){
        if(args.size() != 5){
            throw new IllegalArgumentException();
        }

        Room r1 = findID(allRooms, args.get(1));
        Room r2 = findID(allRooms, args.get(2));
        boolean isOneWay = converter(args.get(3));
        boolean isClosed = converter(args.get(4));
        

        Door door = new Door(r1, r2);

        door.setIsOneWay(isOneWay);
        door.setIsClosed(isClosed);

        allDoors.add(door);
        commands.add(args);
    }

    /**
     * Be lehet allitani a hatralevo idot
     * @param args - a parancsban megadott parameterek, parameterei elottuk az args listaban
     *             levo indexukkel:
     *             1 Hatralevo ido
     */
    public void setTime(List<String> args){
        if(args.size() != 2)
            throw new IllegalArgumentException();
        Time = Integer.parseInt(args.get(1));
        commands.add(args);
    }

    /**
     * A kapott szobat kette bontja
     * @param args - a parancsban megadott parameterek, parameterei elottuk az args listaban
     *             levo indexukkel:
     *             1 A szoba ami szet lesz szedve
     */
    public void split(List<String> args){
        if(args.size() != 2)
            throw new IllegalArgumentException();
        Room room = findID(allRooms, args.get(1));
        room.split();
        commands.add(args);
    }

    /**
     * A ket kapott szobat osszeolvasztja
     * @param args - a parancsban megadott parameterek, parameterei elottuk az args listaban
     *             levo indexukkel:
     *             1 Az egyik szoba
     *             2 A masik szoba
     */
    public void merge(List<String> args){
        if(args.size() != 3)
            throw new IllegalArgumentException();

        Room r1 = findID(allRooms, args.get(1));
        Room r2 = findID(allRooms, args.get(2));
        r1.mergeWithRoom(r2);

        commands.add(args);
    }

    /**
     * Kilistazza az osszes parameternek megfelelo objektumot ami jelenleg letezik
     * @param args - A parancs parameterei,
     *             1-es indexen az osztaly amiket kiirunk
     */
    public void listAll(List<String> args){
        if(args.size() != 2)
            throw new IllegalArgumentException();

        switch (args.get(1)){
            case "Rooms":
                System.out.println("Minden szoba:");
                for(Room r : allRooms){
                    System.out.println("Azonosito: " + r.getId());
                    System.out.println("State: " + r.getgassedRoom() + " " + r.getclothedRoom() + " " +
                            r.getMagical() + " " + r.getSticky());
                    System.out.println("Capacity: " + r.getMaxCapacity());

                }
                break;

            case "Characters":
                System.out.println("Minden karakter:");
                for(Character character : allCharacters){
                    System.out.println("Azonosito: " + character.getId() );
                    System.out.println("Room: " + character.getCurrentRoom().getId());
                }
                break;

            case "Items":
                System.out.println("Minden targy: ");
                for(Item item : allItems){
                    System.out.println("Azonosito: " + item.getId());
                    if(item.getContainedBy() != null)
                        System.out.println("Room: " + item.getContainedBy().getId());
                    if(item.getHeldBy() != null)
                        System.out.println("Character: " + item.getHeldBy().getId());
                }
                break;

            case "Doors":
                System.out.println("Minden ajto: ");
                for(Door door : allDoors){
                    System.out.println("Azonosito: " + door.getId());
                    List<Room> rooms = door.getRooms();
                    System.out.println("Rooms: " + rooms.get(0).getId() + " " + rooms.get(1).getId());
                }
                break;
        }
        System.out.println();
    }

    /**
     * A parameterkent megadott szobanak osszes adatat kiirja, pl.: Allapot, kik vannak benne.
     * @param args - A parancs parameterei,
     *             benne 1-es indexen a szoba
     */
    public void listRoom(List<String> args){
        if(args.size() != 2)
            throw new IllegalArgumentException();

        Room room = findID(allRooms, args.get(1));
        System.out.println("State: " + room.getgassedRoom() + " " + room.getclothedRoom() + " " +
                room.getMagical() + " " + room.getSticky());
        System.out.println("Items: ");
        for(Item i : room.getItems()){
            System.out.print(i.getId() + " ");
        }
        System.out.println("\nCharacter: ");
        for(Character c : room.getCharacters()){
            System.out.print(c.getId() + " ");
        }
        System.out.println("\nDoors: ");
        for(Door d : room.getDoors()){
            System.out.print(d.getId() + " ");
        }


    }

    /**
     * A paramerkent megadott karakter objektum osszes adatat kiirja
     * @param args - a parancs parameterei,
     *             1-es indexen a karakter.
     */
    public void listChar(List<String> args){
        if(args.size() != 2)
            throw new IllegalArgumentException();

        Character character = findID(allCharacters, args.get(1));
        System.out.println("Dazed: " + character.getDazed());
        System.out.println("Items: ");
        for(int i = 0; i < 5; i++){
            if(character.getInventory()[i] != null){
                System.out.print(character.getInventory()[i].getId());
            }
        }
        System.out.println("Room: " + character.getCurrentRoom().getId());

    }

    /**
     * A paramerkent megadott targy objektum osszes adatat kiirja
     * @param args - a parancs parameterei,
     *             1-es indexen a targy
     */
    public void listItem (List<String> args){
        if(args.size() != 2)
            throw new IllegalArgumentException();

        Item item = findID(allItems, args.get(1));
        if(item.getContainedBy() != null)
            System.out.println("Room: " + item.getContainedBy().getId());
        if(item.getHeldBy() != null)
            System.out.println("Character: " + item.getHeldBy().getId());

    }

    /**
     * A paramerkent megadott ajto objektum osszes adatat kiirja
     * @param args - a parancs parameterei,
     *             1-es indexen az ajto
     */
    public void listDoor(List<String> args){
        if(args.size() != 2)
            throw new IllegalArgumentException();

        Door door = findID(allDoors, args.get(1));
        System.out.println("isOneWay: " + door.getIsOneWay() );
        System.out.println("isClosed: " + door.getIsClosed() );
        List<Room> rooms = door.getRooms();
        System.out.println("Rooms: " + rooms.get(0).getId() + " " + rooms.get(1).getId());
    }

    /**
     * A parancs belepteti a kapott karaktert a kapott ajton.
     * @param args - a parancs parameterei,
     *             1-es indexen karakter,
     *             2-es indexen az ajto
     */
    public void enter(List<String> args){
        if(args.size() != 3)
            throw new IllegalArgumentException();

        Character character = findID(allCharacters, args.get(1));
        Door door = findID(allDoors, args.get(2));

        character.enterRoom(door);
        commands.add(args);
    }

    /**
     * A parancs felveteti a kapott karakterrel a kapott targyat
     * @param args - A parancs parameterei,
     *             1-es index karakter,
     *             2-es index targy
     */
    public void pickUp(List<String> args){
        if(args.size() != 3)
            throw new IllegalArgumentException();

        Character character = findID(allCharacters, args.get(1));
        Item item = findID(allItems, args.get(2));

        character.pickUpItem(item);
        commands.add(args);
    }

    /**
     * A parancs hatasara a kapott karakter eldobja a kapott targyat
     * @param args - A parancs parameterei
     *             1-es index karakter
     *             2-es index targy
     */
    public void drop(List<String> args){
        if(args.size() != 3)
            throw new IllegalArgumentException();

        Character character = findID(allCharacters, args.get(1));
        Item item = findID(allItems, args.get(2));

        character.dropItem(item);
        commands.add(args);
    }
    public void use(List<String> args){
        if(args.size() != 2)
            throw new IllegalArgumentException();

        Item item = findID(allItems, args.get(1));

        item.use();
        commands.add(args);
    }

    /**
     * A parancs hatasara a kapott karakter osszekapcsolja a ket kapott tranzisztort
     * @param args - a parancs parameterei,
     *             1-es index tanulo,
     *             2-es index egyik tranzisztor
     *             3-as index masik tranzisztor
     */
    public void connect(List<String> args){
        if(args.size() != 4)
            throw new IllegalArgumentException();

        Student student = (Student) findID(allCharacters, args.get(1));
        Transistor t1 = (Transistor) findID(allItems, args.get(2));
        Transistor t2 = (Transistor) findID(allItems, args.get(3));

        student.connect(t1, t2);


        commands.add(args);
    }

    /**
     * Betolti a kapott sorszamu teszt fajlt, es lefuttatja a benne levo parancsokat
     * a parancsertelmezoben.
     * @param args - A parancs parameterei,
     *             1-es indexen a betoltendo fajl neve
     */
    public void test(List<String> args){

        File currentDirectory = new File("proto");
        File testDirectory = new File(currentDirectory, "test");
        File inputDirectory = new File(testDirectory, "be");
        selectFile(args, inputDirectory);

    }

    /**
     * Betolti a kapott sorszamu mentes fajlt, es lefuttatja a benne levo parancsokat
     * a parancsertelmezoben.
     * @param args - A parancs parameterei,
     *             1-es indexen a betoltendo fajl neve
     */
    public void loadGame(List<String> args){
        File currentDirectory = new File("proto");
        File testDirectory = new File(currentDirectory, "test");
        File inputDirectory = new File(testDirectory, "saves");
        selectFile(args, inputDirectory);

    }

    public void loadGraphicGame(String save){
        List<String> cmd = new ArrayList<>();
        cmd.add("LoadGame");
        cmd.add(save);
        loadGame(cmd);
    }

    /**
     * Mivel a teszt es a mentes betoltese nagyon hasonlo, igy a tenyleges beolvasas itt tortenik
     * @param args - A parancs parameterei,
     *             1-es indexen a betoltendo fajl neve
     * @param inputDirectory - a mentes/teszt mappa kell
     */
    private void selectFile(List<String> args, File inputDirectory) {
        if(args.size() != 2)
            throw new IllegalArgumentException();

        File txt = new File(inputDirectory, args.get(1) + ".txt");
        try {
            InputStream inputStream = new FileInputStream(txt);
            this.Run(inputStream);
        } catch (FileNotFoundException FNFE){
            System.out.println("Nem letezik a megadott nevvel mentes!" + FNFE.toString());
        }
    }

    /**
     * A jatek jelen allasat kimenti egy fajlba
     * @param args - a parancs parameterei,
     *             1-es index a fajl neve
     */
    public void saveGame(List<String> args){
        if(args.size() != 2)
            throw new IllegalArgumentException();

        File currentDirectory = new File("proto");
        File testDirectory = new File(currentDirectory, "test");
        File inputDirectory = new File(testDirectory, "saves");
        File txt = new File(inputDirectory, args.get(1) + ".txt");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(txt))) {
            for (List<String> list : commands) {
                StringBuilder line = new StringBuilder();
                for (String str : list) {
                    line.append(str).append(" ");
                }
                if (line.length() > 0) {
                    line.setLength(line.length() - 1);
                }
                writer.write(line.toString());
                writer.newLine();
            }
            System.out.println("Az " + args.get(0) + " mentest sikeresen felulirta");
        } catch (IOException e) {
            System.out.println("Hiba tortent!" + e.toString());
        }

    }

    public void newGame(int playerNumber){
        List<String> cmd = new ArrayList<>();
        cmd.add("LoadGame");
        cmd.add("defaultStartingMap");
        loadGame(cmd);
        cmd = new ArrayList<>();
        cmd.add("NewStudent");
        cmd.add("-");
        cmd.add("r0");
        for(int i = 0; i < playerNumber; i++){
            newStudent(cmd);
        }



        Random random = new Random();
        for(int i = 0; i < 26; i++){
            for(int j = 1; j <= 8; j++){
                cmd = new ArrayList<>();
                cmd.add("NewItem");
                String roomID = "r".concat(Integer.toString(i));
                int k = random.nextInt(99);
                int fakeChance = random.nextInt(2);
                String fake;
                if(fakeChance % 3 == 0) {
                    fake = "-";
                }else {
                    fake = "+";
                }
                cmd.add(Integer.toString(j));
                cmd.add(roomID);
                cmd.add("-");

                if(k % 5 == 0) {
                    switch (j) {
                        case 3, 5:
                            cmd.add(Integer.toString(3));
                            cmd.add(fake);
                            break;
                        case 6:
                            cmd.add(Integer.toString(3));
                            break;
                        case 8:
                            cmd.add("+");
                            break;
                    }
                    newItem(cmd);
                }
            }
        }
    }
}
