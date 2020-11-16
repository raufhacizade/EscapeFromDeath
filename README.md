<h1 align="center"> EscapeFromDeath </h1>

<p align="center">
  <img src="forReadME/1.gif">
</p>

<h2 align="center"> This game is fully written in java. If you want to play this game, just download the source code and run. </h2>
<b/>

The aim of the player in this game is to reach the main door before frost kills him. Also, he needs to escape from enemies and collect the required amount of coins.For exploration and visualization of the game, there some gifs.

From the first image, you can see some type of objects. Player is one of the movable object and he can be move along the both of x and y axises.Due to the Physics rules, player will be fallen down by gravity if he  jumps or he is not on surface of blocks.([For more information click here](https://en.wikipedia.org/wiki/Equations_for_a_falling_body)) In the game, there are movable and static blocks which are designed to player can move on surface of their.Also fire and coins are static object, it means they don't have ability to move along the x and y axes. The blue expanning rectangle is represent incoming frost. The simple enemies in the shape of snake just have ability to  move along the x-axis. Also these enemies can decrease the health score of the player at the time of the collision of their boundaries of themself and player.It seems that through the fire is moving, but actually it doesn't change its coordinates and boundaries. Rendering each time different bufferedimage create an appearance that fire is moving.If boundaries of player and fire collide, then health score of player will decrease. If frost and fire collide, then fire will be terminated.

<p align="center">
  <img src="forReadME/2.gif">
</p>

From the above image, you can see that another movable objects are mid level enemy (Wolf), stones and doors. Mid level enemy is a little bit smarter than simple enemy. If boundaries of player collide with angle of vision of the Wolf, then wolf starts to follow the player  along the x-axis until blocks stops it. There are two type of stone. The stone ,which is in the shpe of circle, can be moved by the player along the x-axis and player can open the door only  by pressing another appropriate stone with this one. If player push the circle stone forward to the Wolf, then he can be kill Wolf.

<p align="center">
  <img src="forReadME/3.gif">
</p>

One of the other movable object is Ice Dams.If player passes under the  Ice Dams, then they will fall down until be stuck in the blocks. If wolf or player will collides with these  Ice Dams, then they will be die immediatly.
  
<p align="center">
  <img src="forReadME/4.gif">
</p>

In the final part of first level, there is Monster which is much smarter and stronger than other enemies. If player enters in angle of vision of Monster, then it starts to follow player and nothing can damage this enemy. If player has enough coins and gets closer to the Main Door, then door will be opened and player can pass to second level.
<b/>

<h1 align="center"> The Main Concepts of the Application. </h1>

<p align="center">
  <img src="forReadME/main_flow.png">
</p>

First of all, classes of the movable and static objects are extended from the **GameObject** abstract class.GameObject class holds positions and speeds (according to arrows of the X and Y), gravity value, info of being movable, ID number,  boundaries and also tick(), render() methods.The ID actually is an Enum type and it is used to differentiate objects types.

<h2 align="center">The Game Map</h2>
<b/>

<p align="center">
  <img src="Images/1thLevel.png">
</p>
I designed an image which represents the game map. Namely, it keeps initial coordinates of each objects. For example : player, movable and static blocks, enemies, fires, coins and etc. How does it work? After loading  the map image file as BufferedImage, program started  Loop along pixels and obtain RGB channels for each pixel.For example, if RGB color of pixel is RGB(34 ,177,76) - like green, program will initiliaze new instance of Player class. After loop, now you should have all information which you can use in object creation.Pixsel coordinates of referred code (key or barcode) will give you the position of your object on the level/world. (If you want, you can check constructLevel() method in Game Class).

<h2 align="center">tick() and render() Methods</h2>
<b/>

The **tick()** and **render()** methods are the most important methods of our program. Both of these methods are called 60 times each second. The main purpose of the **tick()** method is to update data of each object (for example its position, x and y speeds, acceleration and etc). 

**Updating (ticking) Flow :
Game.run() ->  Game.tick() -> handler.tick() -> All objects -> tick()**

**Rendering Flow :
Game.run() ->  Game.render() -> handler.render(g) -> All objects -> render(g)**

The **render()** method is used to draw our shape of objects. For creating animation illusion, It renders the window continuously by drawing the new positions of each object.

For more information:
[12 Principles of Animation](https://www.youtube.com/watch?v=uDqjIdI4bF4&feature=youtu.be&ab_channel=AlanBeckerTutorials)

[Graphics2D Class in Java](https://docs.oracle.com/javase/7/docs/api/java/awt/Graphics2D.html)

[BufferedImage Class in Java](https://docs.oracle.com/javase/7/docs/api/java/awt/image/BufferedImage.html)
<b/>

<h2 align="center">Contact and Collision of Objects</h2>
To check collision of two or more object, we use their boundaries which are in shape of rectangle. For example : following figure and Java code shows an example of new get bounds method which returns several related areas of representing boundaries.

<p align="center">
  <img src="forReadME/player.jpg">
</p>

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
    
Using these rectangles and also adding some conditions it’s possible to track the behavior of the collision. For example: If an object area have been crossed with main getBound() rectangle (Pink rectangle). It means there was a collision between these two objects. Now it’s time to check the nature of this collision. - If it crossed with upBound (Green rectangle) and the velocity along Y (velY) of crossed object is positive (downward), You can say that this object is falling to players head and collision behavior has to be done for this situation
<b/>

<h2 align="center">Camera</h2>
Camera represents a realization which helps on following objects in motion creating a simple following Cameraman effect. When you have Large world game which runs out from your screen canvas, you should have a system which follows your object of interest (often Player) and transfers world as this object or region always be rendered on your canvas/screen.

Although, it looks like we transfer screen/view towards to region of interest, we can’t change pixel coordinates of our screen. And our created ‘g’ (Graphics ) will be always rendered from its (0,0) beginning from the top left of our display. To simulate such result you can use following Java code where you render the world objects.

        Graphics2D g2d = (Graphics2D) g;

        g2d.translate(-camera.getX(), -camera.getY()); //FIRST LINE
        
        handler.render(g);
        if (gameState == STATE.GAME)
            hud.render(g);
            
        g2d.translate(camera.getX(), camera.getY());  //SECOND LINE
        g.dispose();

Every code written inside of this 2 translating line will be transferred with world. It means if we want some drawings to not be affected by camera movement and always be rendered at the same place/screen coordinate, we should put them outside of this code. The translate coordinates/Vector represented as “camera.getX(), camera.getY()” are calculated displacement of object of interest in game world. As you see first we translate world enough to render objects in such manner that interest region falls to our screen size/view. 

<h2 align="center"> And this is the end. Hope you enjoy this game.</h2>
