package skeleton;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Proto {
    private Scanner scanner = new Scanner(System.in);

    private List<Room> allRooms = new ArrayList<>();
    private List<Door> allDoors = new ArrayList<>();
    private List<Character> allCharacters = new ArrayList<>();
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


    public void Run(){

        while(isRunning) {
            System.out.print(">  ");
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

    public void newRoom(List<String> args) throws IllegalArgumentException {
        if(args.size() != 7){
            throw new IllegalArgumentException();
        }


        boolean gassed, magical, sticky;
        gassed = converter(args.get(1));
        magical = converter(args.get(4));
        sticky = converter(args.get(5));
        String id = "r";
        id.concat(Integer.toString(allRooms.size()-1));

        Room room = new Room(id, gassed, args.get(2), args.get(3), magical, sticky, args.get(6));
        allRooms.add(room);

        commands.add(args);

    }
    public void newStudent(List<String> args){

    }
    public void newTeacher(List<String> args){
        commands.add(args);
    }
    public void newCleaner(List<String> args){
        commands.add(args);
    }

    public void newItem(List<String> args){
        commands.add(args);
    }
    public void newDoor(List<String> args){
        commands.add(args);
    }
    public void setTime(List<String> args){
        commands.add(args);
    }
    public void split(List<String> args){
        commands.add(args);
    }
    public void merge(List<String> args){
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

                }
        }
    }
    public void listRoom(List<String> args){
        commands.add(args);
    }
    public void listChar(List<String> args){
         System.out.println("listChar");
        commands.add(args);
    }
    public void listItem (List<String> args){
        commands.add(args);
    }
    public void listDoor(List<String> args){
        commands.add(args);
    }
    public void enter(List<String> args){
        commands.add(args);
    }
    public void pickUp(List<String> args){
        commands.add(args);
    }
    public void drop(List<String> args){
        commands.add(args);
    }
    public void use(List<String> args){
        commands.add(args);
    }
    public void connect(List<String> args){
        commands.add(args);
    }
    public void loadGame(List<String> args){
        commands.add(args);
    }
    public void saveGame(List<String> args){
        commands.add(args);
    }
    public void newGame(List<String> args){
        commands.add(args);
    }
}
