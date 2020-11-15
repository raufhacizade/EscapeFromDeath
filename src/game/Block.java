/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

/**
 *
 * @author Rauf Hajiyev
 */
public class Block extends GameObject {

    private int width = 32;
    private int height = 32;
    private int velX;
    private int velY;
    private BufferedImage image;
    private boolean movable;
    private int type;
    private int distance = -width * 6;
    private int tempX = distance;
    private int tempY = distance;

    public Block(int x, int y, ID id, int type, int signOfvelX, int signOfvelY) {
        super(x, y, id);
        this.type = type;
        this.velX = 2 * signOfvelX;
        this.velY = 2 * signOfvelY;

        if (signOfvelX == 0 && signOfvelY == 0) {
            movable = false;
        } else {
            movable = true;
        }

        switch (type) {
            case 0:
                special=true;
                image = Game.getFragment().getBlock()[0];
                break;
            case 1:
                image = Game.getFragment().getBlock()[1];
                break;
            case 2:
                image = Game.getFragment().getBlock()[2];
                break;
            case 3:
                image = Game.getFragment().getBlock()[3];
                break;
            case 4:
                image = Game.getFragment().getBlock()[4];
                break;
        }

    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void render(Graphics g) {
        g.drawImage(image, x, y, null);
        
        if (getBounds().intersects(Frost.frost)) {
            Color c = new Color(0f, 0f, 1f, .5f);
            g.setColor(c);
            g.fillRect(x, y, width, height);
        }

    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);

    }

    @Override
    public void tick() {
        if (velX != 0) {
            if (tempX >= 0) {
                velX = -2;
                tempX = 0;
            } else if (tempX <= distance) {
                velX = 2;
                tempX = distance;
            }
            tempX += velX;
            x += velX;
            setVelX(velX);
        }
        
       
        if (velY != 0) {
            if (tempY >= 0) {
                velY = -2;
                tempY = 0;
            } else if (tempY <= distance) {
                velY = 2;
                tempY = distance;
            }
            setVelY(velY);
            tempY += velY;
            y += velY;
        }
    }

    @Override
    public boolean isMovable() {
        return movable;
    }

}
