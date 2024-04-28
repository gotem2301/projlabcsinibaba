package proto;

public abstract class Character implements ID {

    protected String ID;

    /**
     * Meghatarozza hogy az adott character (Student/Teacher) el van-e kabitva gazos szoba altal
     */
    protected boolean Dazed;

    /**
     * Az a szoba amiben a karakter jelenleg tartozkodik
     */
    protected Room currentRoom;

    /**
     * Egy 5 hosszu tombben taroljuk a karakternel levo targyakat
     */
    protected Item[] inventory = new Item[5];


    public Character(String id, Room r){
        ID = id;
        Dazed = false;
        currentRoom = r;
        System.out.println(ID + " created in " + currentRoom.getId());
        currentRoom.addCharacter(this);
    }


    public String getId() {
        return ID;
    }

    /**
     *
     * @return - Megmondja hogy a karakter el van-e kabulva vagy sem
     */
    public boolean getDazed(){
        return Dazed;
    }

    /**
     * Beallitja a Dazed valtozot a parameterkent kapott boolean ertekre
     * @param dazed - az az "allapot" amibe a karakter kerulni fog
     */
    public void setDazed(boolean dazed){
        if(dazed){
            for(Item i : inventory) {
                if (i != null) {
                    if (i.protectMe()) {
                        i.lowerRemainingTime();
                        return;
                    }
                }
            }
            Dazed = true;
            dropEverything();
            System.out.println(ID + " is dazed");
        }else{
            Dazed = false;
        }
    }

 
    /**
     *
     * @return - Visszater a karakter aktualis szobajaval
     */
    public Room getCurrentRoom(){
        return currentRoom;
    }

    /**
     * Beallitja a karakter szobajat
     * @param r - a karakter leendo szobaja
     */
    public void setCurrentRoom(Room r){
        currentRoom = r;
    }


    public Item[] getInventory() {
        return inventory;
    }


    /**
     * Az ajton keresztul atmegy az ajot "masik szobajaba"
     * @param d - az ajto amit hasznal
     */
    public void enterRoom(Door d){
        d.changeRoom(this, currentRoom);
    }

    
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


    public int dropOut(){
        return -1;
    }


    /**
     *  A parameterkent kapott itemet elrakja az inventoryba, ha van hely
     *  a logarlec (SlidingRuler) mukodese miatt ezt a karakter leszarmazottjainal
     *  kulon kell definialni (A teacher nem veheti fel)
     * @param i - Az item amit fel akarunk venni
     */
    public void pickUpItem(Item i) {};

    public void teacherDuty() {}

    public void setClothed(int c){}
}
