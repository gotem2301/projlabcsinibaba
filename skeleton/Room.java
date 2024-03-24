package skeleton;

import java.util.ArrayList;
import java.util.List;
import java.io.*;

public class Room {
	//enum helyett 2 bool értékeben tároljuk a szoba állapotát
	private int gassedRoom;
	private int clothedRoom;
	private int maxCapacity;
	private Boolean magical;
	private List<Door> doors;
	private List<Item> items;
	private List<Character> characters;
	
	/**
	 * Room konstruktora
	 * @param cap
	 */
	public Room(int cap, int gas, int cloth) {
		gassedRoom = 0;
		clothedRoom = 0;
		maxCapacity = cap;
		magical = false;
		doors = new ArrayList<Door>();
		items = new ArrayList<Item>();
		characters = new ArrayList<Character>();
	}
	
	/**
	 * getter
	 * @return MaxCapacity
	 */
	public int getMaxCapacity() {
		System.out.println("Room.getMaxCapacity");
		System.out.println("Return: " + maxCapacity);
		return maxCapacity;
	}
	
	/**
	 * getter
	 * @return clothedRoom
	 */
	public int getclothedRoom() {
		System.out.println("Room.getClothedRoom");
		System.out.println("Return: " + clothedRoom);
		return clothedRoom;
	}
	
	/**
	 * getter
	 * @return gassedRoom
	 */
	public int getgassedRoom() {
		System.out.println("Room.getGassedRoom");
		System.out.println("Return: " + gassedRoom);
		return gassedRoom;
	}
	
	/**
	 * getter
	 * @return characters
	 */
	public List<Character> getCharacters(){
		System.out.println("Room.getCharacters");
		System.out.println("Return: characters");
		return characters;
	}
	
	/**
	 * getter
	 * @return items
	 */
	public List<Item> getItems(){
		System.out.println("Room.getItems");
		System.out.println("Return: items");
		return items;
	}
	
	/**
	 * getter
	 * @return doors
	 */
	public List<Door> getDoors(){
		System.out.println("Room.getDoors");
		System.out.println("Return: Doors");
		return doors;
	}
	
	/**
	 * Kitörli a szoba nyilvántartásából az item-et
	 * @param i Törlendő tárgy
	 */
	public void removeItem(Item i){
		System.out.println("Room.removeItem()");
		if(items.contains(i)) {
			items.remove(i);
		}
	}
	/**
	 * Felveszi az item-et a szoba nyilvántartásába
	 * @param i Hozzáadandó tárgy
	 */
	public void addItem(Item i) {
		System.out.println("Room.addItem()");
		if(i != null) {
			items.add(i);
		}
	}
	/**
	 * A c karakter megpróbál belépni.
	 * @param c
	 * @return sikerült-e átlépni a másik szobába
	 * @throws IOException
	 */
	public Boolean characterEnters(Character c) throws IOException {
		System.out.println("Room.characterEnters");
		System.out.println("Kerem adja meg, hogy beferjen-e a karakter (1 - igen / minden mas karakter - nem)");
		//itt majd a maxCapacity-tól függ, hogy befér-e a karakter
		char decisionCapacity = (char)System.in.read();
		if(decisionCapacity == '1') {
			System.out.println("Kerem adja meg, hogy gazos legyen-e a szoba (1 - igen / minden mas karakter - nem)");
			char decisionGassed = (char)System.in.read();
			if(decisionGassed == '1') {
				c.setDazed(gassedRoom);
			}
			System.out.println("true");
			return true;
		}
		System.out.println("false");
		return false;
	}
	/**
	 *  Felveszi az karaktert a szoba nyilvántartásába.
	 * @param c Felvevendó karakter
	 */
	public void addCharacter(Character c) {
		System.out.println("Room.addCharacter");
		if(c != null) {
			characters.add(c);
		}
	}
	/**
	 * Kitörli a szoba nyilvántartásából a karaktert
	 * @param c Kitörlendó karakter
	 */
	public void removeCharacter(Character c) {
		System.out.println("Room.removeCharacter");
		if(characters.contains(c)) {
			characters.remove(c);
		}
	}
	/**
	 * Felvesz egy új ajtót a szoba nyilvántartásába
	 * @param d Hozzáadandó ajtó
	 */
	public void addDoor(Door d) {
		System.out.println("Room.addDoor");
		if(d != null) {
			doors.add(d);
		}
	}
	/**
	 *  Kitöröl egy ajtót a szoba nyilvántartásából.
	 * @param d Törlendó ajtó
	 */
	public void removeDoor(Door d) {
		System.out.println("Room.removeDoor");
		if(doors.contains(d)) {
			doors.remove(d);
		}
	}
	
	/**
	 * Kezdeményezi a szoba szétválását
	 */
	public void split() {
		System.out.println("Room.split");
		Room newRoom = new Room(this.maxCapacity, this.gassedRoom, this.clothedRoom);
		Door newDoor = new Door();
		newRoom.addDoor(newDoor);
		newDoor.setConnectedRooms(this, newRoom);
		//minden második ajtót az új szobába tesszük
		for(int i = 0; i < doors.size(); i++) {
			if(i % 2 == 0) {
				doors.get(i).replaceRoom(this, newRoom);
			}
		}
		
	}
	/**
	 * Minden karaktert megpróbál kibuktatni
	 * @throws IOException
	 */
	public void dropThemOut() throws IOException {
		System.out.println("Room.dropThemOut");
		for(int i = 0; i < characters.size(); i++) {
			int response = characters.get(i).dropOut();
			switch(response) {
				case 3 : {
					clothedRoom += 3;
				}
				//a szobával nem kell semmit se csinálni
				case 2 : {}
				//a szobával nem kell semmit se csinálni
				case 1 : {}
				case 0 : {
					characters.remove(characters.get(i));
					characters.get(i).dropEverything();
					///
				}
				//tanárral nem csinálunk semmit
				default : {}
			}
		}
	}
	
	/**
	 *  Minden karaktert megpróbál a nedves táblatörlő hatása alá vonni.
	 */
	public void clothThem() {
		System.out.println("Room.clothThem");
		for(int i = 0; i < characters.size(); i++) {
			characters.get(i).setClothed(clothedRoom);
		}
	}
	/**
	 * Kezdeményezi a szobák összeolvadását.
	 * @param r A másik szoba, amivel a jelenlegit összekéne vonni
	 * @throws IOException 
	 */
	public void mergeWithRoom(Room r) throws IOException {
		System.out.println("Room.mergeWithRoom");
		System.out.println("A nagyobbik szoba kapacitasa van-e akkora, mint a ket szoba osszes karaktereinek szama? (1 - igen / minden mas karakter - nem)");
		char decision = (char)System.in.read();
		if(decision == '1') {
			if(maxCapacity < r.getMaxCapacity()) {
				maxCapacity = r.getMaxCapacity();
			}
			if(clothedRoom < r.getclothedRoom()) {
				clothedRoom = r.getclothedRoom();
			}
			if(gassedRoom < r.getgassedRoom()) {
				gassedRoom = r.getgassedRoom();
			}
			
			for(int i = 0; i < r.getCharacters().size(); i++) {
				r.getCharacters().get(i).updateRoom(this);
			}
			characters.addAll(r.getCharacters());
			
			for(int i = 0; i < r.getItems().size(); i++) {
				r.getItems().get(i).updateRoom(this);
			}
			items.addAll(r.getItems());
			
			List<Door> newDoors = r.getDoors();
			for(int j = 0 ; j < r.getDoors().size(); j++) {
				newDoors.get(j).replaceRoom(r, this);
			}
			doors.addAll(newDoors);
		}
	}
}