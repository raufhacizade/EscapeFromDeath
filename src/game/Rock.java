/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

/**
 *
 * @author HRauf
 */
public class Rock extends GameObject {

    private BufferedImageLoader imgUpload;
    private BufferedImage rock;
    private float friction;
    private boolean OnPlatform;
    private Handler handler;
    private Player player;
    private boolean movable;

    public Rock(int x, int y, ID id, Handler handler, Player player) {
        super(x, y, id);
        this.handler = handler;
        this.player = player;
        setVelX(0);
        setVelY(0);
        friction = 0.5f;
        width = 64;
        height = 64;
        movable = true;
        imgUpload = new BufferedImageLoader();

        rock = imgUpload.loadIamge("/rock.png");
    }

    @Override
    public void tick() {
        if (velX != 0) {
            velX += friction;
        }
        if (!OnPlatform) {
            velY += gravity;
        }else{
            velY=0;
        }

        y += velY;
        x += velX;
        collision();
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(rock, x, y, null);

//        Graphics2D g2d = (Graphics2D) g;
//        g.setColor(Color.white);
//        g2d.draw(rightBounds  ());
//        g2d.draw(leftBounds());
//        g2d.draw(upBounds());
    }

    private void collision() {
        for (int i = 0; i < handler.object.size(); i++) {

            GameObject tmpObj = handler.object.get(i);
            if (tmpObj.getId() == ID.Block) {
                if (downBounds().intersects(tmpObj.getBounds())) {
                    y = tmpObj.getY() - this.height;
                    velY = 0;
                    OnPlatform = true;
                }
                if (upBounds().intersects(tmpObj.getBounds())) {
                    y = tmpObj.getY() + 32;
                    velY = 0;
                }
                if (leftBounds().intersects(tmpObj.getBounds())) {
                    movable = false;
                    x = tmpObj.getX() + 32;
                    velX = 0;
                } else {
                    movable = true;
                }
                if (rightBounds().intersects(tmpObj.getBounds())) {
                    movable = false;
                    x = tmpObj.getX() - this.width;
                    velX = 0;
                } else {
                    movable = true;
                }
            }else {
                    OnPlatform = false;
            }

            if (tmpObj.getId() == ID.Player) {
                if (downBounds().intersects(tmpObj.getBounds())) {
                    tmpObj.setY(this.getY() + this.getHeight());
                    tmpObj.setVelY(0);
                }
                if (upBounds().intersects(tmpObj.getBounds())) {
                    tmpObj.setY(this.getY() - 65);
                    Player.setOnPlatform(true);
                    Player.setJumping(false);
                    tmpObj.setVelY(0);
                }
                if (leftBounds().intersects(tmpObj.getBounds())) {
                    if (movable) {
                        velX = tmpObj.getVelX();
                        tmpObj.setX(this.getX() - 41);
                        if (friction > 0) {
                            friction = -friction;
                        }
                    }
                }
                if (rightBounds().intersects(tmpObj.getBounds())) {
                    if (movable) {
                        velX = tmpObj.getVelX();
                        tmpObj.setX(this.getX() + this.width);
                        tmpObj.setVelX(0);
                        if (friction < 0) {
                            friction = -friction;
                        }
                    }
                }
            }
            
            if (tmpObj.getId() == ID.PressKey) {
                if (getBounds().intersects(tmpObj.getBounds())) {
                    PressKey pK = (PressKey) tmpObj;
                    y--;
                    velY = 0;
                    OnPlatform = true;
                    pK.setPressed(true);
                    tmpObj = pK;
                }
            }
            if (tmpObj.getId() == ID.Enemy) {
                if (downBounds().intersects(tmpObj.getBounds())) {
                    if (OnPlatform == false) {
                        tmpObj.id=ID.Died;
                    }
                }
            }    
        }
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }

    public Rectangle upBounds() {
        return new Rectangle(x + 5 + width / 6, y, width - 2 * width / 6 - 10, height / 4);
    }

    public Rectangle downBounds() {
        return new Rectangle(x + 5 + width / 6, y + 3 * height / 4, width - 2 * width / 6 - 10, height / 4);
    }

    public Rectangle leftBounds() {
        return new Rectangle(x, y + height / 6, width / 4 + 5, height - height / 3);
    }

    public Rectangle rightBounds() {
        return new Rectangle(x - 5 + width - width / 4, y + height / 6, width / 4 + 5, height - height / 3);
    }

}
