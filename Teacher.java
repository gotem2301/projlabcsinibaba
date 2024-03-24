import java.util.Scanner;

public class Teacher extends Character{
    /**
     * Eltarolja hogy az adott tanar eppen le van-e rongyozva vagy sem
     */
    private boolean clothed;

    public Teacher(Room r) {
        super(r);

        System.out.println("Oktato letrehozva");
        clothed = false;
    }

    /**
     * A tanar egyedul a logarlecet nem kepes felvenni, igy ezt itt kezelem
     * ezen kivul minden mas itemet felvehet
     * @param i - Az item amit fel akarunk venni
     */
    @Override
    public void pickUpItem(Item i) {
        System.out.println("Tárgy felvétele");

        String s;
        System.out.println("A tarhely tele van? Az oktato el van kabulva?\n" +
                "[0] Nincs tele es nincs elkabulva  " +
                "[1] Tele van de nincs elkabulva    " +
                "[2] Nincs tele de el van kabulva   " +
                "[3] Tele van es el van kabulva    \n");
        Scanner scanner = new Scanner(System.in);
        s = scanner.nextLine();

        switch (s){
            case "1":
                Beer beer = new Beer();
                for(int i = 0; i < 5; i++){
                    this.inventory[i] = beer;
                }
                break;
            case "2":
                this.setDazed(true);
                break;
            case "3":
                Beer beer = new Beer();
                for(int i = 0; i < 5; i++){
                    this.inventory[i] = beer;
                }
                this.setDazed(true);
                break;
            default:
                for(int i = 0; i < 5; i++){
                    this.inventory[i] = null;
                }
                this.setDazed(false);
                break;
        }

        if(this.inventory.length < 5 && !this.Dazed) {
            if (i.transfer(this, null) == false) {
                this.currentRoom.removeItem(i);
                this.addItem(i);
            } else {
                //Itt valakin megkene hivni az endgame fuggvenyt, de nem tudom hogy az kinek van?
            }
            System.out.println("Sikeres targyfelvetel");
        }else{
            System.out.println("Sikertelen targyfelvetel");
        }
    }
}
