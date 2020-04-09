package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 * GameObject Example for Coin.
 * @author Sayid Akhundov
 */
public class Coin extends GameObject{

 private int width=25;
 private int height=25;
 private Animation coin;
 Fragment frag = Game.getFragment();
 public Coin(int x, int y, ID id) {
        super(x, y, id);
        
        coin=new Animation(10,frag.getCoin()[0],frag.getCoin()[1],frag.getCoin()[2],
                             frag.getCoin()[3],frag.getCoin()[4],frag.getCoin()[5]);
    }

    @Override
    public void tick() {
    }

    @Override
    public void render(Graphics g) {
       coin.drawAnimation(g, x, y);
       coin.runAnimation();
    }

    @Override
    public Rectangle getBounds() {
      return new Rectangle (x,y,width,height);
    }
    
}
