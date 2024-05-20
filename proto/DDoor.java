package proto;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.*;


public class DDoor extends JPanel implements IDraw
{
    //startPoint = bal felső sarok
    private int startPointX;
    private int startPointY;
    private String id;
    Door door;

    private final int height;
    private final int width;

    public DDoor(int startPX, int startPY, /*string idIn,*/ Door d){
        super();
        startPointX = startPX;
        startPointY = startPY;
        door = d;
        id = d.getId();

        height = 100;
        width = 50;
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawRect(
        		startPointX, 
        		startPointY, 
        		width + g.getFontMetrics().charsWidth(id.toCharArray(), 0, id.length()),
        		height - g.getFontMetrics().getHeight() / 2);
        g.drawString(id, startPointX  + (width/2), startPointY + (height/2));
    }

    public void draw(Graphics2D g2d, String idIn){}
}