package proto;
import java.awt.Graphics;

import javax.swing.*;


public class DDoor extends JPanel implements IDraw
{
    //startPoint = bal felső sarok
    private int startPointX;
    private int startPointY;
    
    //becsomagolt door osztály
    Door door;
    private String id;

    ////téglalap mérete
    private final int width;
    private final int height;

    /**
     * DDoor konstuktora
     * alapértelmezett méret 50x100
     * alapértelmezetten nincs kirajzolva
     * @param startPX
     * @param startPY
     * @param d becsomagolt door
     */
    public DDoor(int startPX, int startPY, Door d){
        super();
        startPointX = startPX;
        startPointY = startPY;
        door = d;
        id = d.getId();
        this.setVisible(false);

        width = 50;
        height = 100;
    }

    /**
     * Kirajzolja a négzetett benne a becsomagolt door-t id-jével
     */
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

    /**
     *  @param b kirajzoljuk-e
     */
    public void draw(boolean b){
    	this.setVisible(b);
    }
}