package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

/**
 * GameObject Object Example for an Enemy.
 *
 * @author Sayid Akhundov
 */
public class Snake extends GameObject {

    private Handler handler;
    private int width;
    private int height;
    private boolean test;
    private double velX;

    Fragment frag = Game.getFragment();
    Animation leftSnake;
    Animation rightSnake;

    public Snake(int x, int y, ID id, Handler handler) {
        super(x, y, id);
        this.handler = handler;
        test = false;
        
        width = 64;
        height = 30;
        velX = 3;

        rightSnake = new Animation(4, frag.getSnake()[0], frag.getSnake()[1], frag.getSnake()[2], frag.getSnake()[3]);
        leftSnake = new Animation(4, frag.getSnake()[4], frag.getSnake()[5], frag.getSnake()[6], frag.getSnake()[7]);
    }

    @Override
    public void tick() {
        x += velX;

        collision();
    }

    private void collision() {
        for (int i = 0; i < handler.object.size(); i++) {

            GameObject tmpObj = handler.object.get(i);
            if (tmpObj.getId() == ID.Block) {
                if (leftBounds().intersects(tmpObj.getBounds())) {
                    x = tmpObj.getX() + 32;
                    velX = -velX;
                }
                if (rightBounds().intersects(tmpObj.getBounds())) {
                    x = tmpObj.getX() - this.width;
                    velX = -velX;
                }
            }
        }
    }

    @Override
    public void render(Graphics g) {

        if (velX > 0) {
            rightSnake.drawAnimation(g, this.x, this.y - 9);
            rightSnake.runAnimation();
        } else {
            leftSnake.drawAnimation(g, this.x, this.y - 9);
            leftSnake.runAnimation();
        }

//        Graphics2D g2d=(Graphics2D)g;
//        g.setColor(Color.red);
//        g2d.draw(getBounds());
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }

    public Rectangle upBounds() {
        return new Rectangle(x + width / 6, y, width - 2 * width / 6, height / 4);
    }

    public Rectangle downBounds() {
        return new Rectangle(x + width / 6, y + 3 * height / 4, width - 2 * width / 6, height / 4);
    }

    public Rectangle leftBounds() {
        return new Rectangle(x, y + height / 6, width / 4, height - height / 3);
    }

    public Rectangle rightBounds() {
        return new Rectangle(x + width - width / 4, y + height / 6, width / 4, height - height / 3);
    }
}
