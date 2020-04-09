package game;

import java.awt.Graphics;
import java.io.Serializable;
import java.util.ArrayList;


/**
 * Our best friend in Game which will handle updating and rendering all GameObjects existing in the game scene and bunch of other functions. 
 * @author Sayid Akhundov
 */
public class Handler implements Serializable{
    ArrayList<GameObject> object = new ArrayList();
  
    public void tick(){
        for (int i = 0; i < object.size(); i++){
            GameObject tempObject = object.get(i);
            if(tempObject != null) tempObject.tick();
        }
    }
    
    public void render(Graphics g){
        for (int i = 0; i < object.size(); i++){
            GameObject tempObject = object.get(i);
             if(tempObject != null)tempObject.render(g);
        }
    }
    
    public void addObject(GameObject object){
        this.object.add(object);
    }
    public void removeObject(GameObject object){
        this.object.remove(object);
    }
    
}
