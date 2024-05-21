package proto.Drawers;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.nio.BufferOverflowException;

public class DItem implements IDraw {
    private String Id;

    private int x0;
    private int y0;
    private int x1;
    private int y1;

    public DItem (String Id){
        this.Id = Id;
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
            BufferedImage img = null;

            switch (Id.substring(0, 2)){
                case "bl":
                    img = ImageIO.read(new File("proto/Images/BlankInventorySpace.png"));
                    break;
                case "ai":
                    img = ImageIO.read(new File("proto/Images/AirFreshener.png"));
                    break;
                case "be":
                    img = ImageIO.read(new File("proto/Images/Beer.png"));
                    break;
                case "bo":
                    img = ImageIO.read(new File("proto/Images/Book.png"));
                    break;
                case "ca":
                    img = ImageIO.read(new File("proto/Images/Camembert.png"));
                    break;
                case "cl":
                    img = ImageIO.read(new File("proto/Images/Cloth.png"));
                    break;
                case "ma":
                    img = ImageIO.read(new File("proto/Images/Mask.png"));
                    break;
                case "sl":
                    img = ImageIO.read(new File("proto/Images/SlidingRuler.png"));
                    break;
                case "tr":
                    img = ImageIO.read(new File("proto/Images/Transistor.png"));
                    break;
                default:
                    return;
            }

            renderer.drawImage(img, x, y, imageObserver);

            x0 = x;
            y0 = y;
            x1 = x + 50;
            y1 = y + 50;
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
