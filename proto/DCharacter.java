package proto;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.*;


public class DCharacter extends JPanel implements IDraw
{
    //startPoint = bal felső sarok
    private int startPointX;
    private int startPointY;
    private String id;
    Character ch;

    private final int height;
    private final int width;

    public DCharacter(int startPX, int startPY, /*string idIn,*/ Character c){
        super();
        startPointX = startPX;
        startPointY = startPY;
        ch = c;
        id = ch.getId();

        height = 120;
        width = 30;
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