package game;

import static javax.swing.Spring.height;
import static javax.swing.Spring.width;

public class Camera {

    private int x;
    private int y;
    private int height;
    private int width;
    //private GameObject Dimensions;

    public Camera(int x, int y, int width ,int height) {
        this.x = x;
        this.y = y;
        this.height = height+50;
        this.width = width+50;
    }
    
    public void tick(GameObject object){
        
       // System.out.println(object.getBounds().height);  
     
      x += ((object.getX()+object.getBounds().width-x)-Game.WIDTH/2)*0.05f;
      y += ((object.getY()+object.getBounds().height-y)-Game.HEIGHT/2)*0.05f;
      
      
      if(x <= 0)x=0;
      if(y <= 0)y=0;
      if(x >= (width-Game.WIDTH))x= width-Game.WIDTH;
      if(y >= (height-Game.HEIGHT))y=height-Game.HEIGHT;
      
     
    } 

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

}
