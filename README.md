# textRPG

Side project developed from roughly 2015-present used to learn Python, then ported to Java with more features. 
The game is feature complete, but still contains bugs. The game features a simple turn based battle system, 
with items and abilities that can be unlocked or bought with the very original currency: "money".  
Towns and dungeons can be discovered and explored, and the player can fight bosses and level up. 

The game is run by compiling all the classes, then running the compiled Game.java from the command line.
The various java classes are outlined in more detail below:

Game.java
Contains the main code for running the game. Controls all of the actions of the user.

Boss.java
Contains code for all Boss monsters the player encounters.

Dungeon.java
Contains code for the dungeons the player can explore. The dungeon consists of 5 rooms with randomly generated
events for the player, and a locked boss room. The boss room is unlocked by solving a Sudoko-style puzzle
using numbers found in the rooms.

Item.java


Monster.java
-

Move.java
-

Other.java
-

Player.java
-

Puzzle.java
-

Room.java
-

Town.java
-
