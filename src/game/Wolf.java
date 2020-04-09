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
public class Wolf extends GameObject {

    private Handler handler;
    private int width;
    private int height;

    private Fragment frag = Game.getFragment();
    private Animation leftWolf;
    private Animation rightWolf;
    private Animation leftWolfRun;
    private Animation rightWolfRun;

    private boolean running;
    private int runSpeed;

    public Wolf(int x, int y, ID id, Handler handler) {
        super(x, y, id);
        this.handler = handler;

        width = 76;
        height = 32;
        velX = 2;
        runSpeed = 6;

        running = false;
        rightWolf = new Animation(2, frag.getEnemyWolf()[0], frag.getEnemyWolf()[1], frag.getEnemyWolf()[2],
                frag.getEnemyWolf()[3], frag.getEnemyWolf()[4], frag.getEnemyWolf()[5]);

        leftWolf = new Animation(2, frag.getEnemyWolf()[6], frag.getEnemyWolf()[7], frag.getEnemyWolf()[8],
                frag.getEnemyWolf()[9], frag.getEnemyWolf()[10], frag.getEnemyWolf()[11]);

        rightWolfRun = new Animation(4, frag.getEnemyWolf()[17], frag.getEnemyWolf()[18],
                frag.getEnemyWolf()[19], frag.getEnemyWolf()[20], frag.getEnemyWolf()[21]);

        leftWolfRun = new Animation(4, frag.getEnemyWolf()[12], frag.getEnemyWolf()[13],
                frag.getEnemyWolf()[14], frag.getEnemyWolf()[15], frag.getEnemyWolf()[16]);

    }

    @Override
    public void tick() {
        if (this.id != ID.Died) {
            x += velX;
            collision();
        }
    }

    private void collision() {
        for (int i = 0; i < handler.object.size(); i++) {

            GameObject tmpObj = handler.object.get(i);
            if (tmpObj.getId() == ID.Block) {
                if (leftBounds().intersects(tmpObj.getBounds())) {
                    x = tmpObj.getX() + 32;
                    velX = 2;
                }
                if (rightBounds().intersects(tmpObj.getBounds())) {
                    x = tmpObj.getX() - this.width;
                    velX = -2;
                }
            }

            if (tmpObj.getId() == ID.Player) {
                if (getCloseBounds(350, 50).intersects(tmpObj.getBounds())) {
                    if (running) {
                        if (tmpObj.x < x) {

                            velX = -runSpeed;
                        }
                        if (tmpObj.x > x) {

                            velX = runSpeed;
                        }
                    }
                    running = true;
                    System.out.println(running);
                } else {
                    running = false;
                }
            }
        }
    }

    @Override
    public void render(Graphics g) {
        if (this.id != ID.Died) {
            if (!running) {
                if (velX > 0) {
                    rightWolf.drawAnimation(g, this.x, this.y - 13);
                    rightWolf.runAnimation();
                } else {
                    leftWolf.drawAnimation(g, this.x, this.y - 13);
                    leftWolf.runAnimation();
                }
            } else {
                if (velX > 0) {
                    rightWolfRun.drawAnimation(g, this.x, this.y - 8);
                    rightWolfRun.runAnimation();
                } else {
                    leftWolfRun.drawAnimation(g, this.x, this.y - 8);
                    leftWolfRun.runAnimation();
                }
            }
        } else {
            if (velX > 0) {
                g.drawImage(frag.getEnemyWolf()[0], x, y, null);
            } else {
                g.drawImage(frag.getEnemyWolf()[6], x, y, null);
            }

        }
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
