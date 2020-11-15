
package game;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


/**
 * Simplest Keyboard representation which takes keyboard actions and sending integer type information 
 * to the referenced Player Objects public keyPressed array.   
 * @author Rauf Hajiyev
 */
public class KeyInput extends KeyAdapter {
    private Game game;
    
    private Player player;
    private boolean[] keyPressed = new boolean[4];
    public KeyInput(Game game,Player player) {
        this.player = player;
        this.game = game;
    }
    
    
    @Override
    public void keyPressed(KeyEvent e){
    int key = e.getKeyCode();
    if( key == KeyEvent.VK_ESCAPE) System.exit(0);
    
        
        if(player.getId() == ID.Player){
            if(key == KeyEvent.VK_W) { player.keyPressed[3] = true;}
            if(key == KeyEvent.VK_A) {  player.keyPressed[1] = true;}
            if(key == KeyEvent.VK_S) {   player.keyPressed[4] = true;} 
            if(key == KeyEvent.VK_D) {  player.keyPressed[2] = true;}
       
            if(key == KeyEvent.VK_UP) { player.keyPressed[3] = true;}
            if(key == KeyEvent.VK_LEFT){ player.keyPressed[1] = true;}
            if(key == KeyEvent.VK_DOWN) { player.keyPressed[4] = true;}
            if(key == KeyEvent.VK_RIGHT) { player.keyPressed[2] = true;}
            if(key == KeyEvent.VK_SPACE) {
               
            }
            
        }
    }
    
    @Override
    public void keyReleased(KeyEvent e){
    int key = e.getKeyCode();
    
     
        
        if(player.getId() == ID.Player){
            if(key == KeyEvent.VK_W) player.keyPressed[3] = false;
            if(key == KeyEvent.VK_A) player.keyPressed[1] = false;
            if(key == KeyEvent.VK_S) player.keyPressed[4] = false;
            if(key == KeyEvent.VK_D) player.keyPressed[2] = false;
            if(key == KeyEvent.VK_UP) player.keyPressed[3] = false;
            if(key == KeyEvent.VK_LEFT) player.keyPressed[1] = false;
            if(key == KeyEvent.VK_DOWN) player.keyPressed[4] = false;
            if(key == KeyEvent.VK_RIGHT) player.keyPressed[2] = false;
            
            
        }
       
    }
    
}
