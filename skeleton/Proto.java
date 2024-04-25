package skeleton;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Proto {
    private Scanner scanner;

    private List<Room> allRooms = new ArrayList<>();
    private List<Door> allDoors = new ArrayList<>();
    private List<Character> allCharacters = new ArrayList<>();
    private int nOfStudents = 0;
    private int nOfTeachers = 0;
    private int nOfCleaners = 0;
    private List<Item> allItems = new ArrayList<>();
    private List<List<String>> commands = new ArrayList<>();
    private boolean isRunning = true;
    private int Time = Integer.MAX_VALUE;

    private void gameTime(){
        Time--;
        try {
            Thread.sleep(1000);
        }catch (InterruptedException IE){
            System.out.println(IE.toString());
        }

    }


    public void Run(InputStream inputStream){
        scanner = new Scanner(inputStream);
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

                    case "SaveGame":
                        saveGame(arguments);
                        break;

                    case "NewGame":
                        newGame(arguments);
                        break;

                    case "Exit":
                        isRunning = false;
                        for(List<String> ls : commands){
                            for(String s : ls){
                                System.out.print(s + " ");
                            }
                            System.out.println("");
                        }
                        break;

                    default:
                        System.out.println("Ilyen parancs nem letezik!");
                        break;
                }
            }catch (IllegalArgumentException IAE){
                System.out.println("A parancs nincs jol parameterezve!");
            }
        }
    }

    private boolean converter(String s) throws IllegalArgumentException {
        if (s.equals("+"))
            return true;

        if(s.equals("-"))
            return false;

        throw new IllegalArgumentException();
    }

    private <T> T findID(List<T> list, String id) throws IllegalArgumentException {
        for(T element : list) {
            if(element.getID() == id)
                return element;
        }
        throw new IllegalArgumentException();
    }

    public void newRoom(List<String> args) throws IllegalArgumentException {
        if(args.size() != 7){
            throw new IllegalArgumentException();
        }


        boolean gassed, magical, sticky;
        gassed = converter(args.get(1));
        magical = converter(args.get(4));
        sticky = converter(args.get(5));
        String id = "r";
        id = id.concat(Integer.toString(allRooms.size()-1));

        Room room = new Room(id, gassed, Integer.parseInt(args.get(2)),
                Integer.parseInt(args.get(3)), magical, sticky, Integer.parseInt(args.get(6)));
        allRooms.add(room);

        commands.add(args);

    }
    public void newStudent(List<String> args) throws IllegalArgumentException {
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
    public void newTeacher(List<String> args) throws IllegalArgumentException {
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
    public void newCleaner(List<String> args) throws IllegalArgumentException {
        if(args.size() != 3){
            throw new IllegalArgumentException();
        }

        boolean dazed = converter(args.get(1));
        String id = "c";
        id = id.concat(Integer.toString(nOfCleaners));

        Room r = findID(allRooms, args.get(2));
        Cleaner cleaner = new Cleaner(id, r);
        if(dazed){
            cleaner.setDazed(true);
        }
        allCharacters.add(cleaner);
        commands.add(args);
    }

    public void newItem(List<String> args){

        commands.add(args);
    }
    public void newDoor(List<String> args){
        if(args.size() != 5){
            throw new IllegalArgumentException();
        }

        Room r1 = findID(allRooms, args.get(1));
        Room r2 = findID(allRooms, args.get(2));
        boolean isOneWay = converter(args.get(3));
        boolean isClosed = converter(args.get(4));
        String id = "d";
        id = id.concat(Integer.toString(allDoors.size()));

        Door door = new Door(r1, r2);

        door.setIsOneWay(isOneWay);
        door.setIsClosed(isClosed);

        commands.add(args);
    }
    public void setTime(List<String> args){
        Time = Integer.parseInt(args.get(1));
        commands.add(args);
    }
    public void split(List<String> args){
        Room room = findID(allRooms, args.get(1));
        commands.add(args);
    }
    public void merge(List<String> args){

        Room r1 = findID(allRooms, args.get(1));
        Room r2 = findID(allRooms, args.get(2));
        r1.mergeWithRoom(r2);

        commands.add(args);
    }
    public void listAll(List<String> args){
        if(args.size() != 2)
            throw new IllegalArgumentException();

        switch (args.get(1)){
            case "Rooms":
                System.out.println("Minden szoba:");
                for(Room r : allRooms){
                    System.out.println("Azonosito: " + r.getID());
                    System.out.println("State: " + r.getgassedRoom() + " " + r.getclothedRoom() + " " +
                            r.getmagicalRoom() + " " + r.getstickyRoom());
                    System.out.println("Capacity: " + r.getMaxCapacity());

                }
                break;

            case "Characters":
                System.out.println("Minden karakter:");
                for(Character character : allCharacters){
                    System.out.println("Azonosito: " + character.getID() );
                    System.out.println("Room: " + character.getCurrentRoom().getID());
                }
                break;

            case "Items":
                System.out.println("Minden targy: ");
                for(Item item : allItems){
                    System.out.println("Azonosito: " + item.getID());
                    System.out.println("Room: " + item.getContainedBy().getID());
                    System.out.println("Character: " + item.getHeldBy().getID());
                }
                break;

            case "Doors":
                System.out.println("Minden ajto: ");
                for(Door door : allDoors){
                    System.out.println("Azonosito: " + door.getID());
                    List<Room> rooms = door.getRooms();
                    System.out.println("Rooms: " + rooms.get(0).getID() + " " + rooms.get(1).getID());
                }
                break;
        }
    }
    public void listRoom(List<String> args){
        Room room = findID(allRooms, args.get(1));
        System.out.println("State: " + room.getgassedRoom() + " " + room.getclothedRoom() + " " +
                room.getmagicalRoom() + " " + room.getstickyRoom());
        System.out.println("Items: ");
        for(Item i : room.getItems()){
            System.out.print(i.getID() + " ");
        }
        System.out.println("\nCharacter: ");
        for(Character c : room.getCharacters()){
            System.out.print(c.getID() + " ");
        }
        System.out.println("\nDoors: ");
        for(Door d : room.getDoors()){
            System.out.print(d.getID() + " ");
        }


    }
    public void listChar(List<String> args){
        Character character = findID(allCharacters, args.get(1));
        System.out.println("Dazed: " + character.getDazed());
        System.out.println("Items: ");
        for(int i = 0; i < 5; i++){
            if(character.getInventory[i] != null){
                System.out.print(character.getInventory[i].getID);
            }
        }
        System.out.println("Room: " + character.getCurrentRoom().getID());

    }
    public void listItem (List<String> args){
        Item item = findID(allItems, args.get(1));
        System.out.println("Room: " + item.getContainedBy().getID());
        System.out.println("Character: " + item.getHeldBy().getID());

    }
    public void listDoor(List<String> args){
        Door door = findID(allDoors, args.get(1));
        System.out.println("isOneWay: " + door.getIsOneWay() );
        System.out.println("isClosed: " + door.getIsClosed() );
        List<Room> rooms = door.getRooms();
        System.out.println("Rooms: " + rooms.get(0).getID() + " " + rooms.get(1).getID());
    }
    public void enter(List<String> args){
        Character character = findID(allCharacters, args.get(1));
        Door door = findID(allDoors, args.get(2));

        character.enterRoom(door);
        commands.add(args);
    }
    public void pickUp(List<String> args){
        Character character = findID(allCharacters, args.get(1));
        Item item = findID(allItems, args.get(2));

        character.pickUpItem(item);
        commands.add(args);
    }
    public void drop(List<String> args){
        Character character = findID(allCharacters, args.get(1));
        Item item = findID(allItems, args.get(2));

        character.dropItem(item);
        commands.add(args);
    }
    public void use(List<String> args){
        Item item = findID(allItems, args.get(2));

        item.use();
        commands.add(args);
    }
    public void connect(List<String> args){
        Student student = (Student) findID(allCharacters, args.get(1));
        Transistor t1 = (Transistor) findID(allItems, args.get(2));
        Transistor t2 = (Transistor) findID(allItems, args.get(3));

        student.connect(t1, t2);


        commands.add(args);
    }
    public void loadGame(List<String> args){
        File file = new File("/../saves/" + args.get(1) + ".txt");
        try {
            InputStream inputStream = new FileInputStream(file);
            this.Run(inputStream);
        } catch (FileNotFoundException FNFE){
            System.out.println("Nem letezik a megadott nevvel mentes!" );
        }
        commands.add(args);
    }
    public void saveGame(List<String> args){

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("/../saves/" + args.get(1) + ".txt"))) {
            for (List<String> list : commands) {
                StringBuilder line = new StringBuilder();
                for (String str : list) {
                    line.append(str).append(" ");
                }
                // Remove the trailing space before writing to the file
                if (line.length() > 0) {
                    line.setLength(line.length() - 1);
                }
                writer.write(line.toString());
                writer.newLine();
            }
            System.out.println("Az " + args.get(0) + " mentest sikeresen felulirta");
        } catch (IOException e) {
            System.out.println("Hiba tortent!");
        }

    }
    public void newGame(List<String> args){
        commands.add(args);
    }
}
