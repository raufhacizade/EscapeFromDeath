package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import javax.swing.JOptionPane;

/**
 * Realization of Player GameObject
 *
 * @author Sayid Akhundov
 */
public class Player extends GameObject {

    private Handler handler;
    public static int HEALTH;
    private int Score = 0;
    private int LEVEL = 1;

    boolean[] keyPressed = new boolean[5];

    private int width = 43;
    private int height = 64;

    private static boolean OnPlatform;
    private static boolean Jumping;
    private int MaxYspeed = 12;

    private boolean isGate;

    Fragment frag = Game.getFragment();

    Animation playerRightWalk;
    Animation playerLeftWalk;

    private boolean leftLooking;
    private boolean jacket;

    public Player(int x, int y, ID id, Handler handler) {
        super(x, y, id);
        this.handler = handler;
        keyPressed[0] = false;
        keyPressed[1] = false;
        keyPressed[2] = false;
        keyPressed[3] = false;
        keyPressed[4] = false;
        HEALTH = 100;
        OnPlatform = true;
        Jumping = true;
        leftLooking = false;
        isGate = false;



        playerRightWalk = new Animation(5, frag.getRightPlayer()[1], frag.getRightPlayer()[2], frag.getRightPlayer()[3], frag.getRightPlayer()[4],
                frag.getRightPlayer()[5], frag.getRightPlayer()[6], frag.getRightPlayer()[7], frag.getRightPlayer()[8]);

        playerLeftWalk = new Animation(5, frag.getLeftPlayer()[8], frag.getLeftPlayer()[7], frag.getLeftPlayer()[6], frag.getLeftPlayer()[5],
                frag.getLeftPlayer()[4], frag.getLeftPlayer()[3], frag.getLeftPlayer()[2], frag.getLeftPlayer()[1]);
    }

    public void keyboard() {
        if (keyPressed[1]) {
            setVelX(-7);
            leftLooking = true;
            playerLeftWalk.runAnimation();
        } else if (keyPressed[2]) {
            setVelX(7);
            leftLooking = false;
            playerRightWalk.runAnimation();
        } else {
            velX = 0;
        }
        if (keyPressed[3]) {
            if (OnPlatform && !Jumping) {
                setVelY(-MaxYspeed);
                Jumping = true;

                OnPlatform = false;
            }
        } else if (keyPressed[4]) {

        }
    }

    public void eat() {
        setHEALTH(getHEALTH() + 2);

    }

    @Override
    public void tick() {
        if (OnPlatform || Jumping == true) {
            velY += gravity;

            if (velY > MaxYspeed) {
                velY = MaxYspeed;
            }
        }

        keyboard();
        x += velX;
        y += velY;

        collision();
//        jacket.setJumping(Jumping);
//        jacket.setLeftLooking(leftLooking);
//        jacket.setVelX(velX);
//        jacket.setVelY(velY);

        if (HEALTH <= 0) {
            System.out.println("Game Over!");
            JOptionPane.showMessageDialog(null, "Game Over!");
            System.exit(0);
        }
    }

    private void collision() {
        for (int i = 0; i < handler.object.size(); i++) {

            GameObject tmpObj = handler.object.get(i);
            if (tmpObj.getId() == ID.Block) {
                if (getBounds().intersects(tmpObj.getBounds())) {
                    if (downBounds().intersects(tmpObj.getBounds())) {
                        y = tmpObj.getY() - this.height;
                        velY = 0;
                        OnPlatform = true;
                        Jumping = false;

                        if (tmpObj.isMovable()) {
                            x += tmpObj.getVelX();
                            y += tmpObj.getVelY();
                        }
                    }
                    if (upBounds().intersects(tmpObj.getBounds())) {
                        y = tmpObj.getY() + 32;
                        velY = 0;
                    }
                    if (leftBounds().intersects(tmpObj.getBounds())) {
                        x = tmpObj.getX() + 32;
                        velX = 0;
                    }
                    if (rightBounds().intersects(tmpObj.getBounds())) {
                        x = tmpObj.getX() - this.width;
                        velX = 0;
                    }
                }
            }

            if (tmpObj.getId() == ID.Enemy || tmpObj.getId() == ID.Fire || tmpObj.getId() == ID.Frost) {
                if (getBounds().intersects(tmpObj.getBounds())) {
                    HEALTH--;
                }
            }

            if (tmpObj.getId() == ID.Coin) {
                if (getBounds().intersects(tmpObj.getBounds())) {
                    handler.removeObject(tmpObj);
                    Score += 5;
                }
            }

            if (tmpObj.getId() == ID.Gate) {
                if (getBounds().intersects(tmpObj.getCloseBounds(300, 300))) {
                    Gate.distance = tmpObj.getX() - x;
                    if (getBounds().intersects(tmpObj.getBounds())) {
                        Gate.distance = 0;
                    }
                }
            }
            if (tmpObj.getId() == ID.PressKey) {
                if (getBounds().intersects(tmpObj.getBounds())) {
                    PressKey pK = (PressKey) tmpObj;
                    y--;
                    velY = 0;
                    OnPlatform = true;
                    Jumping = false;
                    pK.setPressed(true);
                    tmpObj = pK;
                } else {
                    PressKey pK = (PressKey) tmpObj;
                    pK.setPressed(false);
                    tmpObj = pK;
                }
            }

            if (tmpObj.getId() == ID.Door) {

                if (rightBounds().intersects(tmpObj.getBounds())) {
                    x = tmpObj.getX() - this.width;
                    velX = 0;
                }
                if (upBounds().intersects(tmpObj.getBounds())) {
                    y = tmpObj.getY() + 96;
                    velY = 0;
                }
            }
            
            if (tmpObj.getId() == ID.Ice) {
                if (upBounds().intersects(tmpObj.getBounds())) {
                    this.HEALTH=0;
                }
            }

        }
    }

    @Override
    public void render(Graphics g) {
//        g.setColor(Color.red);
//        Graphics2D g2d = (Graphics2D) g;
//        g2d.draw(rightBounds());
//        g2d.draw(leftBounds());

        if (Jumping) {
            if (velY < 0) {
                if (!leftLooking) {
                    g.drawImage(frag.getPlayerJumping()[0], x - 10, y - 3, null);
                } else {
                    g.drawImage(frag.getPlayerJumping()[1], x - 10, y - 3, null);
                }
            } else {
                if (!leftLooking) {
                    g.drawImage(frag.getPlayerFalling()[0], x - 10, y - 3, null);
                } else {
                    g.drawImage(frag.getPlayerFalling()[1], x - 10, y - 3, null);
                }
            }
        } else {
            if (velX != 0) {
                if (!leftLooking) {
                    playerRightWalk.drawAnimation(g, x - 10, y - 3);
                } else {
                    playerLeftWalk.drawAnimation(g, x - 10, y - 3);
                }
            } else {
                if (!leftLooking) {
                    g.drawImage(frag.getRightPlayer()[0], x - 10, y - 3, null);
                } else {
                    g.drawImage(frag.getLeftPlayer()[0], x - 10, y - 3, null);
                }
            }
        }
    }

    int getLevel() {
        return LEVEL;
    }

    void setLevel(int level) {
        this.LEVEL = level;
    }

    @Override
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

    public int getScore() {
        return Score;
    }

    public int getHEALTH() {
        return HEALTH;
    }

    public void setScore(int Score) {
        this.Score = Score;
    }

    public void setHEALTH(int HEALTH) {

        //this.HEALTH = Game.clamp(HEALTH, 0, 100);
    }

    public int getLEVEL() {
        return LEVEL;
    }

    public void setLEVEL(int LEVEL) {
        this.LEVEL = LEVEL;
    }

    public static boolean isOnPlatform() {
        return OnPlatform;
    }

    public static void setOnPlatform(boolean OnPlatform) {
        Player.OnPlatform = OnPlatform;
    }

    public static boolean isJumping() {
        return Jumping;
    }

    public static void setJumping(boolean Jumping) {
        Player.Jumping = Jumping;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

//    public static int getHeight() {
//        return height;
//    }
//
//    public static void setHeight(int height) {
//        this.height = height;
//    }
    
    

}
