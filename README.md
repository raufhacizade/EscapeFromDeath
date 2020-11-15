<h1 align="center"> EscapeFromDeath </h1>

<p align="center">
  <img src="forReadME/1.gif">
</p>

<h2 align="center"> This game is fully written in java. If you want to play this game, just download the source code and run. </h2>

The aim of the player in this game is to reach the main door before frost kills him. Also, he needs to escape from enemies and collect the required amount of coins.For exploration and visualization of the game, there some gifs.

From the first image, you can see some type of objects, enemy and player. In the game, there are movable and static blocks which are designed to player can move on surface of their.Also fire and coins are static object, it means they don't have ability to move according to the x and y axis. The blue expanning rectangle is represent incoming frost. The simple enemies in the shape of snake just have ability to  move according to the axises of x and y. Also these enemies can decrease the health score of the player at the time of the collision of their boundaries of themself and player.It seems that through the fire is moving, but actually it doesn't change its coordinates and boundaries. Rendering each time different bufferedimage create an appearance that fire is moving.If boundaries of player and fire collide, then health score of player will decrease. If frost and fire collide, then fire will be terminated.

<p align="center">
  <img src="forReadME/2.gif">
</p>

<p align="center">
  <img src="forReadME/3.gif">
</p>
  
<p align="center">
  <img src="forReadME/main_flow.png">
</p>

<h1 align="center"> The main concepts of the application. </h1>

<p align="center">
  <img src="forReadME/1.gif">
</p>

First of all, classes of the movable and static objects are extended from the **GameObject** abstract class.GameObject class holds positions and speeds (according to arrows of the X and Y), gravity value, info of being movable, ID number,  boundaries and also tick(), render() methods. 

The **tick()** and **render()** methods are the most important methods of our program. Both of these methods are called 60 times each second. The main purpose of the tick method is to update data of each object (for example its position, x and y speeds, acceleration and etc). The render method is used to draw our shape of objects.

Updating (ticking) Flow :
Game.run() ->  Game.tick() -> handler.tick() -> All objects -> tick()

Rendering Flow :
Game.run() ->  Game.render() -> handler.render(g) -> All objects -> render(g)
 
