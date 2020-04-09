package game;

import game.Wolf;
import game.HUD;
import game.Handler;
import game.ID;
import game.KeyInput;
import game.Player;
import game.Window;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
//This game was created by Rauf Haciyev

public class Game extends Canvas implements Runnable {

    public static final int WIDTH = 1280, HEIGHT = WIDTH / 2; // Your game Canvas dimensions.
    private Window gameWindow;
    private Thread thread;
    private boolean running = false;
    private Random rand;
    private Handler handler;
    private HUD hud;
    private Player player;

    private boolean mouseState = false;

    private Camera camera;
    BufferedImageLoader imgUpload;
    private BufferedImage background;
    private static BufferedImage structureOfLevel = null;
    private static Fragment frag;

    private BufferedImage blockImage;

    private enum STATE {

        MENU,
        GAME,
        HELP,
        OPTIONS;
    };
    private STATE gameState = STATE.GAME;

    public Game() {
        rand = new Random();
        gameWindow = new Window(WIDTH, HEIGHT, "GD- Let's play ;)", this);

        imgUpload = new BufferedImageLoader();

        handler = new Handler();

        background = imgUpload.loadIamge("/back.png");
        blockImage = imgUpload.loadIamge("/block.png");
    }

    public static void main(String[] args) {
        new Game();
    }

    public synchronized void start() {
        thread = new Thread(this);
        thread.start();
        running = true;
    }

    public synchronized void stop() {
        try {
            thread.join();
            running = false;
        } catch (InterruptedException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void run() {
        UpLoadStructureOfLevel();

        this.requestFocus();
        long lastTime = System.nanoTime();
        double ammountOfTicks = 60.0;
        double ns = 1000000000 / ammountOfTicks;
        double delta = 0;

        long timer = System.currentTimeMillis();
        int frames = 0;

        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while (delta >= 1) {
                tick();
                delta--;
            }
            if (running) {
                render();
                frames++;
            }
            if ((System.currentTimeMillis() - timer) > 1000) {
                timer += 1000;
                frames = 0;
            }
        }
        stop();
    }

    private void tick() {

        handler.tick();

        camera.tick(player);

        if (gameState == STATE.GAME) {

            hud.tick();

        }
        if (player.y > structureOfLevel.getHeight() * 32 + 400) {
            System.out.println("Game Over!");
            JOptionPane.showMessageDialog(null, "Game Over!");
            System.exit(0);
        }

    }

    private void render() {
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            this.createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();
        g.drawImage(background, 0, 0, WIDTH, HEIGHT, null);

        Graphics2D g2d = (Graphics2D) g;

        g2d.translate(-camera.getX(), -camera.getY());
        handler.render(g);

        if (gameState == STATE.GAME) {
            hud.render(g);
        }
        g2d.translate(camera.getX(), camera.getY());
        g.dispose();
        bs.show();

    }

    private void UpLoadStructureOfLevel() {
        structureOfLevel = imgUpload.loadIamge("/1thLevel.png");
        constructLevel(structureOfLevel);

    }

    private void constructLevel(BufferedImage image) {
        frag = new Fragment();
        int w = image.getWidth();
        int h = image.getHeight();

        for (int xx = 0; xx < w; xx++) {
            for (int yy = 0; yy < h; yy++) {

                int pixel = image.getRGB(xx, yy);
                int red = (pixel >> 16) & 0x0ff;
                int green = (pixel >> 8) & 0x0ff;
                int blue = (pixel) & 0x0ff;

                if ((red == 136 && green == 0 && blue == 21)) {
                    handler.addObject(new Block(xx * 32, yy * 32, ID.Block, 0, 0, 0));
                }
                if ((red == 195 && green == 195 && blue == 195)) {
                    handler.addObject(new Block(xx * 32, yy * 32, ID.Block, 2, 0, 0));
                }
                if ((red == 0 && green == 0 && blue == 0)) {
                    handler.addObject(new Block(xx * 32, yy * 32, ID.Block, 1, 0, 0));
                }
                if ((red == 127 && green == 127 && blue == 127)) {
                    handler.addObject(new Block(xx * 32, yy * 32, ID.Block, 3, 0, 0));
                }
                if ((red == 63 && green == 72 && blue == 204)) {
                    handler.addObject(new Block(xx * 32, yy * 32, ID.Block, 0, 1, 0));
                }
                if ((red == 0 && green == 162 && blue == 232)) {
                    handler.addObject(new Block(xx * 32, yy * 32, ID.Block, 0, 0, 1));
                }
                if ((red == 34 && green == 177 && blue == 76)) {
                    player = new Player(xx * 32, yy * 64, ID.Player, handler);

                    if (gameState == STATE.GAME) {
                        handler.addObject(player);
                    }
                    camera = new Camera(0, 0, structureOfLevel.getWidth() * 32 + 500, structureOfLevel.getHeight() * 32 + 500);
                    hud = new HUD(player, camera);
                    this.addKeyListener(new KeyInput(this, player));
                    System.out.println("Player is created");

                }
                if ((red == 237 && green == 28 && blue == 36)) {
                    handler.addObject(new Fire(xx * 32, yy * 32, ID.Fire, handler));
                }
                if ((red == 244 && green == 232 && blue == 244)) {
                    handler.addObject(new Jacket(xx * 32, yy * 32, ID.Jacket));
                }
                if ((red == 239 && green == 228 && blue == 176)) {
                    handler.addObject(new Boss(xx * 32, yy * 32, ID.Boss, handler));
                }

                if ((red == 185 && green == 122 && blue == 87)) {
                    handler.addObject(new Wolf(xx * 32, yy * 32, ID.Enemy, handler));
                }

                if ((red == 255 && green == 242 && blue == 0)) {
                    handler.addObject(new Coin(xx * 32, yy * 32, ID.Coin));
                }

                if ((red == 163 && green == 73 && blue == 164)) {
                    handler.addObject(new Gate(xx * 32, yy * 32, ID.Gate));
                }
                if ((red == 54 && green == 79 && blue == 102)) {
                    handler.addObject(new Rock(xx * 32, yy * 32, ID.Rock, handler, player));
                }
                if ((red == 255 && green == 127 && blue == 39)) {
                    handler.addObject(new PressKey(xx * 32, yy * 32, ID.PressKey, handler));
                }
                if ((red == 181 && green == 230 && blue == 29)) {
                    handler.addObject(new Door(xx * 32, yy * 32, ID.Door, handler));
                }
                if ((red == 200 && green == 191 && blue == 231)) {
                    handler.addObject(new Snake(xx * 32, yy * 32, ID.Enemy, handler));
                }
                if ((red == 153 && green == 217 && blue == 234)) {
                    //System.out.println("buz");
                    handler.addObject(new Ice(xx * 32, yy * 32, ID.Ice, handler, 1));
                    handler.addObject(new Ice(xx * 32 + 15, yy * 32, ID.Ice, handler, 2));
                }
            }
        }
        handler.addObject(new Frost(0, 0, ID.Frost, handler));
    }

    public static BufferedImage getStructureOfLevel() {
        return structureOfLevel;
    }

    public static Fragment getFragment() {
        return frag;
    }
}
