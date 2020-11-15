# EscapeFromDeath
![](forReadME/1.gif)
<center>Centered text</center>
## This game is fully written in java. If you want to play this game, just download the source code and run.

This Game is a 2D Java platform game. For the explanation of the game, I divide the Game into 4 parts and show them in gif files

First of all, classes of the movable and static objects are extended from the **GameObject** abstract class.GameObject class holds positions and speeds (according to arrows of the X and Y), gravity value, info of being movable, ID number,  bounds and also tick(), render() methods. 

The **tick()** and **render()** methods are the most important methods of our program. Both of these methods are called 60 times each second. The main purpose of the tick method is to update data of each object (for example its position, x and y speeds, acceleration and etc). The render method is used to draw our shape of objects.

Updating (ticking) Flow :
Game.run() ->  Game.tick() -> handler.tick() -> All objects -> tick()

Rendering Flow :
Game.run() ->  Game.render() -> handler.render(g) -> All objects -> render(g)

## 1. First part
![](forReadME/1.gif)

## 2. First part
![](forReadME/2.gif)

## 3. First part
![](forReadME/3.gif)

## 4. First part
![](forReadME/4.gif)
 
