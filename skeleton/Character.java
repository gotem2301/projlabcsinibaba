
package skeleton;
public abstract class Character {
    protected String ID;

    /**
     * Meghatarozza hogy az adott character (Student/Teacher) el van-e kabitva gazos szoba altal
     */
    protected boolean Dazed;

    /**
     * Egy 5 hosszu tombben taroljuk a karakternel levo targyakat
     */
    protected Item[] inventory = new Item[5];

    /**
     * Az a szoba amiben a karakter jelenleg tartozkodik
     */
    protected Room currentRoom;

    public String getID() {
        return ID;
    }

    /**
     * Beallitja a Dazed valtozot a parameterkent kapott boolean ertekre
     * @param dazed - az az "allapot" amibe a karakter kerulni fog
     */
    public void setDazed(boolean dazed){
        if(dazed){
            Dazed = true;
            for(Item i : inventory) {
                if (i != null && Dazed) {
                    if (i.protectMe()) {
                        setDazed(false);
                    }
                }
            }
        }else{
            Dazed = false;
        }
    }

    /**
     *
     * @return - Megmondja hogy a karakter el van-e kabulva vagy sem
     */
    public boolean getDazed(){
        return Dazed;
    }

    /**
     * Beallitja a karakter szobajat
     * @param r - a karakter leendo szobaja
     */
    public void setCurrentRoom(Room r){
        currentRoom = r;
    }

    /**
     *
     * @return - Visszater a karakter aktualis szobajaval
     */
    public Room getCurrentRoom(){
        return currentRoom;
    }


    public Character(String id, Room r){
        this.Dazed = false;
        this.ID = id;
        this.currentRoom = r;
        currentRoom.addCharacter(this);
    }

    /**
     * Az ajton keresztul atmegy az ajot "masik szobajaba"
     * @param d - az ajto amit hasznal
     */
    public void enterRoom(Door d){
        d.changeRoom(this, currentRoom);
    }

    public void teacherDuty() {

    }

    /**
     *  A parameterkent kapott itemet elrakja az inventoryba, ha van hely
     *  a logarlec (SlidingRuler) mukodese miatt ezt a karakter leszarmazottjainal
     *  kulon kell definialni (A teacher nem veheti fel)
     * @param i - Az item amit fel akarunk venni
     */
    public abstract void pickUpItem(Item i);


    /**
     * A parameterkent atvett itemet eldobja a karakter
     * @param item - az eldobandó targy
     */
    public void dropItem(Item item){
        for(int i = 0; i < 5; i++){
            if(this.inventory[i] == item){
                this.inventory[i] = null;
            }
        }
        item.transfer(null, currentRoom);
        currentRoom.addItem(item);
    }

    /**
     * Kabulas eseten a karakter kidobja az osszes nala levo itemet
     */
    public void dropEverything(){
        for(Item i : inventory){
            if(i != null) {
                dropItem(i);
            }
        }
    }

    /**
     * Torli a parameterkent atvett itemet a jatekbol (pl. elhasznalodas eseten)
     * @param item - a torlendo targy
     */
    public void removeItem(Item item){
        item.transfer(null, null);
        for(int i = 0; i < 5; i++){
            if(inventory[i] == item){
                inventory[i] = null;
            }
        }
    }

    /**
     *  A parameterkent atvett targyat hasznalja
     * @param i - a hasznalando item
     */
    public void useItem(Item i){
        i.use();
    }

    public void updateRoom(Room r){
        currentRoom = r;
    }

    public void setClothed(int c){
    }
    
    public int dropOut(){
        return -1;
    }
}
