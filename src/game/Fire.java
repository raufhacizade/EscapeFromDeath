package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Fire extends GameObject{

 private int width;
 private int height;
 private Handler handler;
 private Animation fire;
 Fragment frag =  Game.getFragment();
 
 public Fire(int x, int y, ID id,Handler handler) {
        super(x, y, id);
        this.width=32;
        this.height=60;
        this.handler=handler;
        fire=new Animation(3,frag.getFire()[0],frag.getFire()[1],frag.getFire()[2],frag.getFire()[3]);
    }

    @Override
    public void tick() {
        if (getBounds().intersects(Frost.frost)) {
            handler.removeObject(this);
        }
        fire.runAnimation();
    }

    @Override
    public void render(Graphics g) {
       fire.drawAnimation(g, x-21, y-21);
    }

    @Override
    public Rectangle getBounds() {
      return new Rectangle (x,y,width,height);
    }
    
}
