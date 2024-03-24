package skeleton;
public abstract class Character {
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

    /**
     * Beallitja a Dazed valtozot a parameterkent kapott boolean ertekre
     * @param dazed - az az "allapot" amibe a karakter kerulni fog
     */
    public void setDazed(boolean dazed){
        System.out.println("setDazed fuggveny hivas");
        if(dazed){
            Dazed = true;
            for(Item i : inventory) {
                if (i != null && Dazed) {
                    if (i.saveMe() == 1) {
                        Dazed = false;
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
        System.out.println("getDazed fuggveny hivas");
        System.out.println("Visszateres: " + Dazed);
        return Dazed;
    }

    /**
     * Beallitja a karakter szobajat
     * @param r - a karakter leendo szobaja
     */
    public void setCurrentRoom(Room r){
        System.out.println("setCurrentRoom fuggveny hivas");
        currentRoom = r;

    }

    /**
     *
     * @return - Visszater a karakter aktualis szobajaval
     */
    public Room getCurrentRoom(){
        System.out.println("getCurrentRoom fuggveny hivas");
        System.out.println("Visszateres: " + "currentRoom");
        return currentRoom;
    }


    public Character(Room r){
        this.Dazed = false;
        this.currentRoom = r;
    }

    /**
     * Az ajton keresztul atmegy az ajot "masik szobajaba"
     * @param d - az ajto amit hasznal
     */
    public void enterRoom(Door d){
        System.out.println("enterRoom fuggveny hivas");
        d.changeRoom(this, currentRoom);
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
     * @param i - az eldobandó item
     */
    public void dropItem(Item item){
        System.out.println("dropItem fuggveny hivas");

        for(int i = 0; i < 5; i++){
            if(this.inventory[i] == item){
                this.inventory[i] = null;
            }
        }
        item.transfer(null, r);
        currentRoom.addItem(item);
    }

    /**
     * Kabulas eseten a karakter kidobja az osszes nala levo itemet
     */
    public void dropEverything(){
        System.out.println("dropEverything fuggveny hivas");
        for(Item i : inventory){
            dropItem(i);
        }
    }

    /**
     * Torli a parameterkent atvett itemet a jatekbol (pl. elhasznalodas eseten)
     * @param i
     */
    public void removeItem(Item i){
        System.out.println("removeItem fuggveny hivas");
        i.transfer(null, null);
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
        System.out.println("useItem fuggveny hivas");
        i.use();
    }

    public void updateRoom(Room r){
        System.out.println("updateRoom fuggveny hivas");
        currentRoom = r;
    }


}
