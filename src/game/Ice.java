package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Ice extends GameObject {

    private int width;
    private int height;
    private boolean above;
    private boolean below;
    public static int distance = 300;
    private int type;

    private Handler handler;
    private BufferedImage ice;
    private BufferedImageLoader imgUpload;
    private int maxYspeed;

    public Ice(int x, int y, ID id, Handler handler, int type) {
        super(x, y, id);
        this.handler = handler;
        this.type = type;
        maxYspeed=18;
        if (type == 1) {
            width = 16;
            height = 64;
        } else if (type == 2) {
            width = 16;
            height = 96;
        }

        above = true;
        below = false;

        ice =  Game.getFragment().getIce();

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
        if (!above && !below) {
            velY++;
            if (velY > maxYspeed) {
                velY = maxYspeed;
            }
            y += velY;
        }
        collision();
    }

    private void collision() {
        if (this.id != ID.Died) {
            for (int i = 0; i < handler.object.size(); i++) {

                GameObject tmpObj = handler.object.get(i);
                if (above) {
                    if (tmpObj.getId() == ID.Player) {
                        if (getCloseBounds(-6, 256).intersects(tmpObj.getBounds())) {
                            above = false;
                        }
                    }
                }

                if (!above && !below) {
                    if (tmpObj.getId() == ID.Block) {
                        if (getBounds().intersects(tmpObj.getBounds())) {
                            if (y <= tmpObj.y - 20) {
                                y = tmpObj.y - 20;
                                velY = 3;
                                below = true;
                                this.id = ID.Died;
                            }

                        }
                    }
                }
                if (tmpObj.getId() == ID.Enemy) {
                    if (getBounds().intersects(tmpObj.getBounds())) {
                        tmpObj.id = ID.Died;
                    }
                }
            }
        }
    }

    @Override
    public void render(Graphics g) {
        
        g.drawImage(ice, x, y, width, height, null);
//        Graphics2D g2d = (Graphics2D) g;
//        g.setColor(Color.red);
//        g2d.draw(getCloseBounds(-6, 256));
    }

}
