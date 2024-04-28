package proto;

import java.util.ArrayList;
import java.util.List;
import java.io.*;

public class Room implements ID {
	private String id;
	//enum helyett 2 bool értékeben tároljuk a szoba állapotát
	private boolean gassedRoom;
	private int clothedRoom;
	private int visitors;
	private int maxCapacity;
	private boolean magical;
	private boolean sticky;
	private List<Door> doors;
	private List<Item> items;
	private List<Character> characters;
	private int numberOfSplitsFromThis;
	
	/**
	 * Room kontruktora
	 * @param cap kapacitás
	 * @param gas gázos-e
	 * @param cloth törlőrongyos-e
	 * @param visit hányan látogaták meg a legutobbi takarítás óta
	 * @param magik mágikus-e
	 * @param _sticky ragados-e
	 * @param _id id
	 */
	public Room(String _id, boolean gas, int cloth, int cap, boolean magik, boolean _sticky, int visit) {
		id = _id;
		gassedRoom = gas;
		clothedRoom = cloth;
		maxCapacity = cap;
		visitors = visit;
		magical = magik;
		sticky = _sticky;
		doors = new ArrayList<Door>();
		items = new ArrayList<Item>();
		characters = new ArrayList<Character>();
		numberOfSplitsFromThis = 0;
		System.out.println(id + " created");
		if(sticky) {
			System.out.println(id + " is sticky");
		}
		if(gassedRoom) {
			System.out.println(id + " is gassed");
		}
	}

	/**
	 * Room rövidebb konstruktora
	 * @param cap kapacitas
	 * @param _id id
	 */
	public Room(int cap, String _id) {
		this(_id, false, 0, cap, false, false, 0);
	}

	
	/**
	 * getter
	 * @return id
	 */
	public String getId() {
		return id;
	}
	
	/**
	 * getter
	 * @return MaxCapacity
	 */
	public int getMaxCapacity() {
		return maxCapacity;
	}
	
	/**
	 * getter
	 * @return clothedRoom
	 */
	public int getclothedRoom() {
		return clothedRoom;
	}
	
	/**
	 * getter
	 * @return gassedRoom
	 */
	public boolean getgassedRoom() {
		return gassedRoom;
	}

	public void setgassedRoom(boolean b) {
		gassedRoom = b;
		if(gassedRoom) {
			System.out.println(id + " is gassed");
			for(Character c : characters) {
				c.setDazed(true);
			}
		}
		
	}
	
	/**
	 * getter
	 * @return characters
	 */
	public List<Character> getCharacters(){
		return characters;
	}
	
	/**
	 * getter
	 * @return items
	 */
	public List<Item> getItems(){
		return items;
	}
	
	/**
	 * getter
	 * @return doors
	 */
	public List<Door> getDoors(){
		return doors;
	}
	/**
	 * getter
	 * @return visitors
	 */
	public int getVisitors() {
		return visitors;
	}
	
	/**
	 * getter
	 * @return magical
	 */
	public boolean getMagical() {
		return magical;
	}
	
	/**
	 * getter
	 * @return sticky
	 */
	public boolean getSticky() {
		return sticky;
	}
	
	/**
	 * Kitörli a szoba nyilvántartásából az item-et
	 * @param i Törlendő tárgy
	 */
	public void removeItem(Item i){
		if(items.contains(i)) {
			items.remove(i);
		}
	}
	/**
	 * Felveszi az item-et a szoba nyilvántartásába
	 * @param i Hozzáadandó tárgy
	 */
	public void addItem(Item i) {
		if(i != null) {
			items.add(i);
			System.out.println(i.getId() + " added to " + id);
		}
	}
	/**
	 * A c karakter megpróbál belépni.
	 * @param c
	 * @return sikerült-e átlépni a másik szobába
	 */
	public Boolean characterEnters(Character c) {
		if(maxCapacity > characters.size()) {
			return true;
		}
		return false;
	}
	/**
	 *  Felveszi az karaktert a szoba nyilvántartásába.
	 * @param c Felvevendó karakter
	 */
	public void addCharacter(Character c) {
		if(c != null) {
			characters.add(c);
			if(visitors >= 0){
				this.increaseVisitors();
			}
			if(visitors >= 5 && !(this.getSticky())){
				this.setSticky(true);
			}
			if(gassedRoom) {
				c.setDazed(gassedRoom);
			}
			List<Character> tmp = new ArrayList<>();
			tmp.addAll(characters);
			for(Character character : tmp) {
				///cleaner SetCurrentRoom-ot itt kéne???
				
				character.teacherDuty();
			}
			
		}
	}
	/**
	 * Kitörli a szoba nyilvántartásából a karaktert
	 * @param c Kitörlendó karakter
	 */
	public void removeCharacter(Character c) {
		if(characters.contains(c)) {
			characters.remove(c);
		}
	}
	/**
	 * Felvesz egy új ajtót a szoba nyilvántartásába
	 * @param d Hozzáadandó ajtó
	 */
	public void addDoor(Door d) {
		if(d != null) {
			doors.add(d);
		}
	}
	/**
	 *  Kitöröl egy ajtót a szoba nyilvántartásából.
	 * @param d Törlendó ajtó
	 */
	public void removeDoor(Door d) {
		if(doors.contains(d)) {
			doors.remove(d);
		}
	}
	
	/**
	 * Kezdeményezi a szoba szétválását
	 */
	public void split() {
		System.out.println(id + " split");
		String newSplitId = this.getId() + "."  + this.numberOfSplitsFromThis;
		numberOfSplitsFromThis++;
		Room newRoom = new Room(newSplitId, this.gassedRoom, this.clothedRoom, this.maxCapacity, this.magical, this.sticky, this.visitors);
		Door newDoor = new Door(this, newRoom);
		newRoom.addDoor(newDoor);
		newDoor.setConnectedRooms(this, newRoom);
		//minden második ajtót az új szobába tesszük
		for(int i = 0; i < doors.size(); i++) {
			if(i % 2 == 0) {
				doors.get(i).replaceRoom(this, newRoom);
			}
		}
		// minden masodik karaktert az uj szobaba tesszuk
		for(int i = 0; i < characters.size(); i++) {
			if(i % 2 == 0) {
				characters.get(i).enterRoom(newDoor);
			}
		}
		// minden masodik itemet az uj szobaba tesszuk
		for(int i = 0; i < items.size(); i++) {
			if(i % 2 == 0) {
				newRoom.addItem(items.get(i));
				removeItem(items.get(i));
			}
		}
	}
	/**
	 * Minden karaktert megpróbál kibuktatni
	 * @throws IOException
	 */
	public void dropThemOut() {
		List<Character> tmp = new ArrayList<Character>();
		for(int i = 0; i < characters.size(); i++) {
			int response = characters.get(i).dropOut();
			switch(response) {
				case 3 : 
					clothedRoom += 3;
					clothThem();
					break;
				
				//a szobával nem kell semmit se csinálni
				case 2 : break;
				//a szobával nem kell semmit se csinálni
				case 1 : break;
				case 0 :
					tmp.add(characters.get(i));
					break;
				
				//tanárral nem csinálunk semmit
				default :
					break;
			}
		}
		
		if(clothedRoom > 0) {return;}
		
		for(Character ch : tmp) {
			System.out.println(ch.getId() + " failed");
			characters.remove(ch);
			ch.dropEverything();
		}
	}
	
	/**
	 *  Minden karaktert megpróbál a nedves táblatörlő hatása alá vonni.
	 */
	public void clothThem() {
		for(int i = 0; i < characters.size(); i++) {
			characters.get(i).setClothed(clothedRoom);
		}
	}
	/**
	 * Kezdeményezi a szobák összeolvadását.
	 * @param r A másik szoba, amivel a jelenlegit összekéne vonni 
	 */
	public void mergeWithRoom(Room r) {
		if(Math.max(maxCapacity, r.getMaxCapacity()) < (this.characters.size() + r.getCharacters().size())) {
			System.out.println(id + " and " + r.getId() + " failed to merge");
			return;
		}
		
		System.out.println(id + " and " + r.getId() + " merged into " + id);
		if(maxCapacity < r.getMaxCapacity()) {
			maxCapacity = r.getMaxCapacity();
		}
		if(clothedRoom < r.getclothedRoom()) {
			clothedRoom = r.getclothedRoom();
		}
		if(gassedRoom || r.getgassedRoom()) {
			gassedRoom = true;
		}
		if(visitors < r.getVisitors()) {
			visitors = r.visitors;
		}
		if(magical || r.getMagical()) {
			magical = true;
		}
		
		if(sticky || r.getSticky()) {
			sticky = r.sticky;
		}
		
		//karakterek átpakolása
		for(int i = 0; i < r.getCharacters().size(); i++) {
			System.out.println(r.getCharacters().get(i).getId() + " entered " + id);
			r.getCharacters().get(i).setCurrentRoom(this);
		}
		List<Character> tmp = new ArrayList<>();
		tmp.addAll(r.getCharacters());
		for(Character ch : tmp){
			this.addCharacter(ch);
			r.removeCharacter(ch);
		}
		
		//tárgyak átpakolása
		for(int i = 0; i < r.getItems().size(); i++) {
			r.getItems().get(i).updateRoom(r);
		}
		List<Item> tmpItem = new ArrayList<>();
		tmpItem.addAll(r.getItems());
		for(Item it : tmpItem){
			this.addItem(it);
			r.removeItem(it);
		}
		//azaz ajtó amely a this és r kötti össze törölni kell
		for(int i = 0; i < doors.size(); i++) {
			for(int j = 0; j <= r.getDoors().size(); j++) {
				if(doors.get(i).equals(r.getDoors().get(j))) {
					r.removeDoor(doors.get(i));
					this.removeDoor(doors.get(i));
				}
			}
		}
		
		
		List<Door> newDoors = r.getDoors();
		for(int i = 0 ; i < r.getDoors().size(); i++) {
			newDoors.get(i).replaceRoom(r, this);
		}
		for(Door door : newDoors){
			this.addDoor(door);
			r.removeDoor(door);
		}
	}
	
	/**
	 * Bezárja a szoba összes ajtaját
	 */
	public void closeDoors() {
		if (doors != null) {
			for (Door d : doors) {
				d.setIsClosed(true);;
			}
		}
	}
	
	/**
	 * Kinyitja a szoba összes ajtaját
	 */
	public void openDoors() {
		if(doors != null) {
			for(Door d : doors) {
				d.setIsClosed(false);;
			}
		}
	}
	
	/**
	 * setter
	 * @param b boolean
	 */
	public void setSticky(boolean b) {
		if(b) { System.out.println(id + " is sticky");}
		sticky = b;
	}
	
	/**
	 * Minden olyan karaktert aki nem bénult/ájult kidob a szobából
	 */
	public void cleanRoom() {
		List<Character> list = new ArrayList<>();
		list.addAll(characters);

		for(Character c : list) {
			if(!(c.getDazed())) {
				int randomSzam = (int)Math.random();
				if(randomSzam >= doors.size()) {
					c.enterRoom(doors.get(randomSzam));
				}
				else {
					c.enterRoom(doors.get(doors.size() - 1));
				}
			}
		}
		visitors = 0;
		sticky = false;
		gassedRoom = false;
		System.out.println(id + " is clean");
	}
	
	/**
	 * A szoba visitors attribútumát növeli eggyel.
	 */
	public void increaseVisitors() {
		visitors++;
	}
}