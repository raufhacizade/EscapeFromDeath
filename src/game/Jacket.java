/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

/**
 *
 * @author Rauf Hajiyev
 */
public class Jacket extends GameObject {
    
    private Fragment frag=Game.getFragment();
    private BufferedImage image;

    public Jacket(int x, int y, ID id) {
        super(x, y, id);
        width=32;
        height=64;
        image=frag.getRightJacket()[0];
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }

    @Override
    public void tick() {
        
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(image, x, y, null);
    }
}