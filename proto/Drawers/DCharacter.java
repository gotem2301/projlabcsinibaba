package proto.Drawers;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;

public class DCharacter implements IDraw {
    private String Id;
    private int x0;
    private int y0;
    private int x1;
    private int y1;
    public DCharacter(String Id){
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
            String characterTitle = null;
            switch (Id.substring(0, 1)){
                case "s":
                    characterTitle = "PLayer" + Id.substring(1);
                   img = ImageIO.read(new File("proto/Images/Student.png"));
                   break;
                case "t":
                    characterTitle = "Teacher" + Id.substring(1);
                    img = ImageIO.read(new File("proto/Images/Teacher.png"));
                    break;
                case "c":
                    characterTitle = "Cleaner" + Id.substring(1);
                    img = ImageIO.read(new File("proto/Images/Cleaner.png"));
                    break;
                default:
                    return;
            }

            renderer.drawImage(img, x, y, imageObserver);
            renderer.setColor(Color.WHITE);
            renderer.setFont(new Font("Harrington", Font.PLAIN, 15));
            renderer.drawString(characterTitle, x, y - 10);

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
