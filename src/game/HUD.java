package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

/**
 * Creating a simple information panel for our game to display.
 *
 * @author Sayid Akhundov
 */
public class HUD {

    private Player player;
    private Camera camera;

    public HUD(Player player, Camera camera) {
        this.player = player;
        this.camera = camera;
    }

    public void tick() {
    }

    public void render(Graphics g) {
        int HEALTH = player.getHEALTH();
        int score = player.getScore();
        int level = player.getLevel();
        g.setColor(new Color(255 - HEALTH * 2, HEALTH * 2, 0));
        g.fillRect(player.x, player.getY() - 15, (player.getWidth() * HEALTH / 100), 7);
        g.setColor(Color.white);
        g.drawRect(player.x, player.getY() - 15, player.getWidth(), 7);
        
        Graphics2D g2d=(Graphics2D) g;
        g.setColor(Color.red);
        g2d.setFont(new Font( "SansSerif", Font.PLAIN, 30 ));
        g2d.drawString("Score : " + score, camera.getX() + 50, camera.getY() + 30);
        g2d.drawString("Level : " + level, camera.getX() + 300, camera.getY() + 30);
    }

}
