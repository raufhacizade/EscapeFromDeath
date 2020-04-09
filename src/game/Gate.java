package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Gate extends GameObject {

    private int width;
    private int height;
    private boolean open;
    public static int distance = 300;

    private BufferedImage gate;
    Fragment frag = Game.getFragment();

    public Gate(int x, int y, ID id) {
        super(x, y, id);
        width = 96;
        height = 96;

        open = false;

//        openGate = new Animation(5, frag.getGate()[0], frag.getGate()[1],
//                frag.getGate()[2], frag.getGate()[3],
//                frag.getGate()[4], frag.getGate()[5],
//                frag.getGate()[6], frag.getGate()[7]);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }

    @Override
    public Rectangle getCloseBounds(int xx, int yy) {
        return new Rectangle(x, y, width+xx, height+yy);
    }
    
    

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {

        if ((300 > distance) && (distance <= 200)) {
            g.drawImage(frag.getGate()[1], x, y, null);
        } else if (200 > distance && distance <= 100) {
            g.drawImage(frag.getGate()[2], x, y, null);
        } else if (100 > distance && distance <= 0) {
            g.drawImage(frag.getGate()[3], x, y, null);
        } else {
            g.drawImage(frag.getGate()[0], x, y, null);
        }
    }

}
