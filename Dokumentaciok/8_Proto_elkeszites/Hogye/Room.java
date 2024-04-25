package skeleton;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.*;

public class Room {
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
	
	/**
	 * Room konstruktora
	 * @param cap
	 */
	public Room(int cap, boolean gas, int cloth, int visit, boolean magik, boolean _sticky) {
		gassedRoom = gas;
		clothedRoom = cloth;
		maxCapacity = cap;
		visitors = visit;
		magical = magik;
		sticky = _sticky;
		doors = new ArrayList<Door>();
		items = new ArrayList<Item>();
		characters = new ArrayList<Character>();
	}

	public Room(int cap) {
		this(cap, false, 0, 0, false, false);
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
			this.increaseVisitors();
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
		Room newRoom = new Room(this.maxCapacity, this.gassedRoom, this.clothedRoom, this.visitors, this.magical, this.sticky);
		Door newDoor = new Door(this, newRoom);
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
	public void dropThemOut() {
		List<Character> tmp = new ArrayList<Character>();
		for(int i = 0; i < characters.size(); i++) {
			int response = characters.get(i).dropOut();
			switch(response) {
				case 3 : 
					clothedRoom += 3;
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
			return;
		}
		
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
		
		for(int i = 0; i < r.getCharacters().size(); i++) {
			r.getCharacters().get(i).updateRoom(this);

		}
		List<Character> tmp = new ArrayList<>();
		tmp.addAll(r.getCharacters());
		for(Character ch : tmp){

			this.addCharacter(ch);
		}
		
		for(int i = 0; i < r.getItems().size(); i++) {
			r.getItems().get(i).updateRoom(r);
		}
		for(Item it : r.getItems()){
			this.addItem(it);
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
		for(Door door : r.getDoors()){
			this.addDoor(door);
		}	
	}
	
	/**
	 * Bezárja a szoba összes ajtaját
	 */
	public void closeDoors() {
		if (doors != null) {
			for (Door d : doors) {
				d.close();
			}
		}
	}
	
	/**
	 * Kinyitja a szoba összes ajtaját
	 */
	public void openDoors() {
		if(doors != null) {
			for(Door d : doors) {
				d.open();
			}
		}
	}
	
	/**
	 * setter
	 * @param b boolean
	 */
	public void setSticky(boolean b) {
		sticky = b;
	}
	
	/**
	 * Minden olyan karaktert aki nem bénult/ájult kidob a szobából
	 */
	public void cleanRoom() {
		for(Character c : characters) {
			if(c.getDazed()) {
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
	}
	
	/**
	 * A szoba visitors attribútumát növeli eggyel.
	 */
	public void increaseVisitors() {
		visitors++;
	}
}