package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Boss extends GameObject {

    private Handler handler;
    private int width;
    private int height;
    private int HEALTH;

    private Fragment frag = Game.getFragment();
    private Animation leftBoss;
    private Animation rightBoss;
    private Animation leftBossRun;
    private Animation rightBossRun;

    private Animation leftBossAttack;
    private Animation rightBossAttack;

    private boolean running;
    private int runSpeed;
    private int tempY;
    private int tempWidth;
    private int tempHeight;
    private boolean attack;

    private static boolean OnPlatform;
    private static boolean Jumping;
    private int MaxYspeed = 12;

    private boolean left;

    public Boss(int x, int y, ID id, Handler handler) {
        super(x, y, id);
        this.handler = handler;
        HEALTH = 100;

        width = 96;
        height = 96;
        tempWidth = (int) (128 * 1.2);
        tempHeight = (int) (72 * 1.2);
        velX = 2;
        runSpeed = 6;
        tempY = y + 20;

        running = false;
        OnPlatform = false;
        attack = false;
        left = false;

        rightBoss = new Animation(3, frag.getBoss()[0], frag.getBoss()[1], frag.getBoss()[2], frag.getBoss()[3], frag.getBoss()[4],
                frag.getBoss()[5], frag.getBoss()[6], frag.getBoss()[7], frag.getBoss()[8], frag.getBoss()[9], frag.getBoss()[10]);

        leftBoss = new Animation(3, frag.getBoss()[11], frag.getBoss()[12], frag.getBoss()[13], frag.getBoss()[14], frag.getBoss()[15],
                frag.getBoss()[16], frag.getBoss()[17], frag.getBoss()[18], frag.getBoss()[19], frag.getBoss()[20], frag.getBoss()[21]);

        rightBossRun = new Animation(1, frag.getBoss()[22], frag.getBoss()[23], frag.getBoss()[24], frag.getBoss()[25], frag.getBoss()[26], frag.getBoss()[27],
                frag.getBoss()[28], frag.getBoss()[29], frag.getBoss()[30], frag.getBoss()[31], frag.getBoss()[32], frag.getBoss()[33], frag.getBoss()[34]);

        leftBossRun = new Animation(1, frag.getBoss()[35], frag.getBoss()[36], frag.getBoss()[37], frag.getBoss()[38], frag.getBoss()[39], frag.getBoss()[40],
                frag.getBoss()[41], frag.getBoss()[42], frag.getBoss()[43], frag.getBoss()[44], frag.getBoss()[45], frag.getBoss()[46], frag.getBoss()[47]);

        rightBossAttack = new Animation(2,
                //frag.getBoss()[48], frag.getBoss()[49], frag.getBoss()[50], frag.getBoss()[51], frag.getBoss()[52], frag.getBoss()[53],
                //frag.getBoss()[54], frag.getBoss()[55], frag.getBoss()[56],
                frag.getBoss()[57], frag.getBoss()[58], frag.getBoss()[59], frag.getBoss()[60],
                frag.getBoss()[61], frag.getBoss()[62], frag.getBoss()[63], frag.getBoss()[64], frag.getBoss()[65], frag.getBoss()[66], frag.getBoss()[67]);

        leftBossAttack = new Animation(2,
                //frag.getBoss()[68], frag.getBoss()[69], frag.getBoss()[70], frag.getBoss()[71], frag.getBoss()[72], frag.getBoss()[73],
                //frag.getBoss()[74], frag.getBoss()[75], frag.getBoss()[76], 
                frag.getBoss()[77], frag.getBoss()[78], frag.getBoss()[79], frag.getBoss()[80],
                frag.getBoss()[81], frag.getBoss()[82], frag.getBoss()[83], frag.getBoss()[84], frag.getBoss()[85], frag.getBoss()[86], frag.getBoss()[87]);
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
                } else {
                    running = false;
                }
                if (getBounds().intersects(tmpObj.getBounds())) {
                    if (!attack) {
                        Player.HEALTH = Player.HEALTH - 25;
                        if (velX > 0) {
                            left = true;
                        } else {
                            left = false;
                        }
                        attack = true;
                        running = false;
                    }
                    velX = 0;
                } else {
                    attack = false;
                }
            }
            
            if (tmpObj.getId() == ID.Ice) {
                if (upBounds().intersects(tmpObj.getBounds())) {
                    this.HEALTH=this.HEALTH-10;
                    System.out.println(this.HEALTH);
                    if (this.HEALTH<=0) {
                        this.id=ID.Died;
                    }
                }
            }
        }
    }

    @Override
    public void render(Graphics g) {
        if (this.id != ID.Died) {
            if (!running) {
                if (velX > 0) {
                    rightBoss.drawAnimation(g, this.x, this.y, width, height);
                    rightBoss.runAnimation();
                } else if (velX < 0) {
                    leftBoss.drawAnimation(g, this.x, this.y, width, height);
                    leftBoss.runAnimation();
                }
            } else {
                if (velX > 0) {
                    rightBossRun.drawAnimation(g, this.x, tempY, tempWidth, tempHeight);
                    //rightBossRun.drawAnimation(g, this.x, this.y,width ,height);
                    rightBossRun.runAnimation();
                } else if (velX < 0) {
                    leftBossRun.drawAnimation(g, this.x, tempY, tempWidth, tempHeight);
                    //leftBossRun.drawAnimation(g, this.x, this.y,width ,height);
                    leftBossRun.runAnimation();
                }
            }
        }else{
            
        }

        if (attack) {
            if (!left) {
                leftBossAttack.drawAnimation(g, this.x - 20, tempY - 20, (int) (tempWidth * 1.2), (int) (tempHeight * 1.2));
                leftBossAttack.runAnimation();
            } else {
                rightBossAttack.drawAnimation(g, this.x - 20, tempY - 20, (int) (tempWidth * 1.2), (int) (tempHeight * 1.2));
                rightBossAttack.runAnimation();
            }
        }
//        g.setColor(Color.red);
//        Graphics2D g2d = (Graphics2D) g;
//        g2d.draw(getBounds());

//        if (!running) {
//            g.setColor(new Color(255 - HEALTH * 2, HEALTH * 2, 0));
//            g.fillRect(x, getY() - 15, (getWidth() * HEALTH / 100), 7);
//            g.setColor(Color.white);
//            g.drawRect(x, getY() - 15, getWidth(), 7);
//        }else{
//            g.setColor(new Color(255 - HEALTH * 2, HEALTH * 2, 0));
//            g.fillRect( this.x, tempY, (getWidth() * HEALTH / 100), 7);
//            g.setColor(Color.white);
//            g.drawRect(this.x, tempY, getWidth(), 7);
//        }
    }

    public Rectangle getBounds() {
        if (!running) {
            return new Rectangle(x, y, width, height);
        } else {
            return new Rectangle(x, tempY, tempWidth, tempHeight);
        }

    }

    public Rectangle upBounds() {
        return new Rectangle(x + width / 6, y, width - 2 * width / 6, height / 4);
    }

    public Rectangle downBounds() {
        return new Rectangle(x, y, width - 20, 67);
    }

    public Rectangle leftBounds() {
        return new Rectangle(x, y + height / 6, width / 4, height - height / 3);
    }

    public Rectangle rightBounds() {
        return new Rectangle(x + width - width / 4, y + height / 6, width / 4, height - height / 3);
    }

}
