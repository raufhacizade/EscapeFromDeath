package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Door extends GameObject {

    public  int width;
    private int height;
    private boolean open;
    private int newY;
    private BufferedImage image = null;
    private Handler handler;

    public Door(int x, int y, ID id, Handler handler) {
        super(x, y, id);
        width = 64;
        height = 96;
        newY = y - height;
        this.handler = handler;

        
        image = Game.getFragment().getDoor();
        open = false;

    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }

    @Override
    public Rectangle getCloseBounds(int xx, int yy) {
        return new Rectangle(x, y, width + xx, height + yy);
    }

    @Override
    public void tick() {
        if (open) {
            if (y >= newY) {
                y--;
            }
        } else {
            if (y <= (newY + height)) {
                y++;
            }
        }
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(image, x, y, width, height, null);
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

}
