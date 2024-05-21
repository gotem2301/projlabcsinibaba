package proto;
import java.awt.Graphics;

import javax.swing.*;


public class DCharacter extends JPanel implements IDraw
{
    //startPoint = bal felső sarok
    private int startPointX;
    private int startPointY;
    
  //becsomagolt character osztály
    Character ch;
    private String id;

    //téglalap mérete
    private final int height;
    private final int width;

    
    /**
     * DCharacter konstuktora
     * alapértelmezett méret 30x120
     * alapértelmezetten nincs kirajzolva
     * @param startPX 
     * @param startPY
     * @param c becsomagolt character
     */
    public DCharacter(int startPX, int startPY, Character c){
        super();
        startPointX = startPX;
        startPointY = startPY;
        ch = c;
        id = ch.getId();
        this.setVisible(false);

        height = 120;
        width = 30;
    }

    /**
     * Kirajzolja a négzetett benne a becsomagolt character-t id-jével
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