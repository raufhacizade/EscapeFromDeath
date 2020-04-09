package game;

import java.awt.image.BufferedImage;

public class SpriteSheet {
    private BufferedImage image;

    public SpriteSheet(BufferedImage image) {
        this.image = image;
    }
    
    public BufferedImage captureImage(int column,int row, double width, double height){
        return image.getSubimage((int)((column*width)-width),
                                 (int)((row*height)-height),
                                 (int)  width,
                                 (int)  height);
    }    
}
