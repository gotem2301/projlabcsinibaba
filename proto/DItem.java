package proto;
import java.awt.Graphics;

import javax.swing.*;


public class DItem extends JPanel implements IDraw
{
    //startPoint = bal felső sarok
    private int startPointX;
    private int startPointY;
    
    //becsomagolt item osztály
    Item item;
    private String id;

    //téglalap mérete
    private final int width;
    private final int height;

    
    /**
     * DItem konstuktora
     * alapértelmezett méret 50x50
     * alapértelmezetten nincs kirajzolva
     * @param startPX 
     * @param startPY
     * @param itemIn becsomagolt item
     */
    public DItem(int startPX, int startPY, Item itemIn){
        super();
        startPointX = startPX;
        startPointY = startPY;
        item = itemIn;
        id = item.getId();
        this.setVisible(false);

        width = 50;
        height = 50;
    }

    /**
     * Kirajzolja a négzetett benne a becsomagolt item-et id-jével
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