package proto.Drawers;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Dimension2D;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;

public class DDoor implements IDraw {
    private int x0;
    private int y0;
    private int x1;
    private int y1;

    private String Id;
    private String otherRoomsId;

    public DDoor(String Id, String otherRoomsId){
        this.Id = Id;
        this.otherRoomsId = otherRoomsId;
    }

    public String getId() {
        return Id;
    }

    public int getX0() {
        return x0;
    }

    public int getX1() {
        return x1;
    }

    public int getY0() {
        return y0;
    }

    public int getY1() {
        return y1;
    }

    @Override
    public void draw(Graphics2D renderer, int x, int y, ImageObserver imageObserver) {
        try {
            BufferedImage img = ImageIO.read(new File("proto/Images/Door.png"));
            renderer.drawImage(img, x, y, imageObserver);

            renderer.setColor(Color.WHITE);
            renderer.setFont(new Font("Harrington", Font.PLAIN, 15));
            String doorTitle = "Room" + otherRoomsId.substring(1);
            renderer.drawString(doorTitle, x, y - 10);


            x0 = x;
            y0 = y;
            x1 = x + 54;
            y1 = y + 152;
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
