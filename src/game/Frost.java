package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 * GameObject Example for Coin.
 *
 * @author Sayid Akhundov
 */
public class Frost extends GameObject {

    private Handler handler;
    public static Rectangle frost;
    public Frost(int x, int y, ID id, Handler handler) {
        super(x, y, id);
        frost=this.getBounds();
        this.handler = handler;
        
        width=0;
        height = Game.getStructureOfLevel().getHeight()*32;
    }

    @Override
    public void tick() {
        if (0<=width && width<=Game.getStructureOfLevel().getWidth()*8) {
            width+=2;
        }else if(width>Game.getStructureOfLevel().getWidth()*8 && width<=Game.getStructureOfLevel().getWidth()*16){
            width+=4;
        }
        else if(width>Game.getStructureOfLevel().getWidth()*16 && width<=Game.getStructureOfLevel().getWidth()*24){
            width+=6;
        }
        frost=this.getBounds();
    }

    @Override
    public void render(Graphics g) {
//        g.setColor(Color.red);
//        g.drawRect(x, y, width, height);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }

}
