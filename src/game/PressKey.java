package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class PressKey extends GameObject {

    private int width;
    private int height;
    private boolean pressed;
    private int newY;
    private Handler handler;

    private BufferedImageLoader imgUpload;
    private BufferedImage image = null;

    public PressKey(int x, int y, ID id, Handler handler) {
        super(x, y, id);
        width = 64;
        height = 32;
        newY = y + height*2;
        this.handler = handler;

        imgUpload = new BufferedImageLoader();
        image = imgUpload.loadIamge("/rock2.png");
        pressed = false;

    }

    @Override
    public void tick() {
        if (pressed) {
            if (y <= newY) {
                y++;
            }
        } else {
            if (y >= (newY - height*2)) {
                y--;
            }
        }
        
        for (int i = 0; i < handler.object.size(); i++) {

            GameObject tmpObj = handler.object.get(i);
            if (tmpObj.getId() == ID.Door) {
                if (getCloseBounds(10*32,10*32).intersects(tmpObj.getBounds())) {
                    Door tempDoor=(Door) tmpObj;
                    tempDoor.setOpen(pressed);
                    tmpObj=tempDoor;
                }
            }
        }
    }
    
    @Override
    public void render(Graphics g) {
        g.drawImage(image, x-8, y,80,38, null);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }

    public boolean isPressed() {
        return pressed;
    }

    public void setPressed(boolean pressed) {
        this.pressed = pressed;
    } 
}
