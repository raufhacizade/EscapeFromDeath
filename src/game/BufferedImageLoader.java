package game;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public class BufferedImageLoader {
 private BufferedImage image;  
 
 public BufferedImage loadIamge(String path){
     try {
         Path path1 = Paths.get(System.getProperty("user.dir")+"/Images"+path);
         image =ImageIO.read(path1.toFile());
     } catch (IOException ex) {
         Logger.getLogger(BufferedImageLoader.class.getName()).log(Level.SEVERE, null, ex);
     }
     return image;
 }
}
