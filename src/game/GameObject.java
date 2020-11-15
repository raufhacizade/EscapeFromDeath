package game;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.io.Serializable;

/**
 * An abstract class for realization of our Game Objects.
 * @author Rauf Hajiyev
 */
public abstract class GameObject implements Serializable {
    protected int x,y ;
    protected ID id;
    protected int velX,velY;
    protected int width,height;
    protected int gravity = 1;
    protected boolean special;
    protected boolean leftLooking;
    protected boolean movable;
    protected boolean frozen;

    public GameObject(int x, int y, ID id) {
        this.x = x;
        this.y = y;
        this.id = id;
        frozen=false;
        special=true;
    }

    public void setVelX(int velX) {
        this.velX = velX;
    }

    public void setVelY(int velY) {
        this.velY = velY;
    }
    
    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setId(ID id) {
        this.id = id;
    }
    public int getVelX() {
        return velX;
    }

    public int getVelY() {
        return velY;
    }

    public ID getId() {
        return id;
    }

    public int getX() {
        return x;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getY() {
        return y;
    }

    public boolean isMovable() {
        return movable;
    }

    public boolean isFrozen() {
        return frozen;
    }

    public void setFrozen(boolean frozen) {
        this.frozen = frozen;
    }
    
    
    
    public void setMovable(boolean movable) {
        this.movable = movable;
    }
    public Rectangle getCloseBounds(int xx, int yy) {
        return new Rectangle(x-xx,y-yy,width+xx*2,height+yy*2);
    }
    
    
    
    public abstract Rectangle getBounds();
    public abstract void tick();
    public abstract void render(Graphics g);
    
    
}
