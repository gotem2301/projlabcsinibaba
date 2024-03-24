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
	public Door() {
		isOneWay = false;
		isClosed = false;
		rooms = new ArrayList<Room>();
	}
	/**
	 * Átrakja a karaktert a szoba párjába.
	 * @param c Átlépni akaró karakter
	 * @param r Az a szoba, ahova át akar lépni a karakter
	 * @throws IOException
	 */
	public void changeRoom(Character c, Room r) throws IOException {
		System.out.println("Door.changeRoom");
		//A megadott r szobának az ajtón keressztüli szomszédján hívja meg a characterEnters-t
		Boolean result;
		if(rooms.get(0).equals(r)) {
			result = rooms.get(1).characterEnters(c);
		}
		else {
			result = rooms.get(0).characterEnters(c);
		}
		
		if(result) {
			r.removeCharacter(c);
		}
	}
	
	/**
	 * Kicseréli a x-t a y-ra a nyilvántartásban.
	 * @param x A szoba, amit leakarunk cserélni
	 * @param y A szoba, amirre leakarjuk cserélni
	 */
	public void replaceRoom(Room x, Room y) {
		System.out.println("Door.replaceRoom");
		for(int i = 0; i < rooms.size(); i++) {
			if(rooms.get(i).equals(x)) {
				rooms.get(i).removeDoor(this);
				rooms.remove(i);
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
		System.out.println("Door.setConnectedRooms");
		rooms = new ArrayList<Room>();
		rooms.add(existing);
		rooms.add(_new);
	}
}
