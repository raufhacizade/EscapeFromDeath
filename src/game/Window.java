package game;

import java.awt.*;
import javax.swing.JFrame;

/**
 * Since, our course is not Java programming language course this Class represents a simple game display area.
 * For this simple example, you create frame using java.swing.JFrame class and later in constructor of this Window class
 * you are going to display your game Canvas in bound of this created frame.
 * @author Sayid Akhundov
 */
public class Window {

    public Window(int width,int height,String title, Game game) {
     JFrame frame = new JFrame(title);
     
     frame.setPreferredSize(new Dimension(width,height));
     frame.setMaximumSize(new Dimension(width,height));
     frame.setMinimumSize(new Dimension(width,height));
     
     frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     frame.setResizable(true);
     frame.setLocationRelativeTo(null);
     frame.add(game);
     
     frame.setVisible(true);
     game.start();
    }
    
}
