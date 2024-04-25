package skeleton;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Door {
	private Boolean isOneWay;
	private Boolean isClosed;
	private List<Room> rooms;
	
	/**
	 * Door konstruktora
	 */
	public Door(Room r1, Room r2) {
		isOneWay = false;
		isClosed = false;
		rooms = new ArrayList<Room>();
		rooms.add(r1);
		r1.addDoor(this);
		rooms.add(r2);
		r2.addDoor(this);
	}
	/**
	 * Átrakja a karaktert a szoba párjába.
	 * @param c Átlépni akaró karakter
	 * @param r Az a szoba, ahol van a karakter.
	 * @throws IOException
	 */
	public void changeRoom(Character c, Room r) {
		//A megadott r szobának az ajtón keressztüli szomszédján hívja meg a characterEnters-t
		Boolean result;
		Room other;
		if(rooms.get(0).equals(r)) {
			result = rooms.get(1).characterEnters(c);
			other = rooms.get(1);
		}
		else {
			result = rooms.get(0).characterEnters(c);
			other = rooms.get(0);
		}
		
		if(result) {
			c.setCurrentRoom(other);
			r.removeCharacter(c);
			other.addCharacter(c);
		}
	}
	
	/**
	 * Kicseréli a x-t a y-ra a nyilvántartásban.
	 * @param x A szoba, amit leakarunk cserélni
	 * @param y A szoba, amirre leakarjuk cserélni
	 */
	public void replaceRoom(Room x, Room y) {
		for(int i = 0; i < rooms.size(); i++) {
			if(rooms.get(i).equals(x)) {
				rooms.get(i).removeDoor(this);
				rooms.remove(i);
				y.addDoor(this);
				rooms.add(i, y);
			}
		}
	}
	
	/**
	 *  Beállítja hogy melyik két szoba között van.
	 * @param existing Egyik szoba
	 * @param _new Másik szoba
	 */
	public void setConnectedRooms(Room existing, Room _new) {
		for(int i = 0; i < rooms.size(); i++) {
			rooms.get(i).removeDoor(this);
		}
		rooms = new ArrayList<Room>();
		rooms.add(existing);
		existing.addDoor(this);
		rooms.add(_new);
		_new.addDoor(this);
	}
	
	/**
	 * Bezárja az ajtót
	 */
	public void close() {
		isClosed = true;
	}
	
	/**
	 * Kinyitja az ajtót
	 */
	public void open() {
		isClosed = false;
	}
}
