# textRPG

Side project developed from roughly 2015-present used to learn Python, then ported to Java with more features. 
The game is essentially feature complete, but still contains bugs. The game features a simple turn based battle system, 
with items and abilities that can be unlocked or bought with the very original currency: "money".  
Towns and dungeons can be discovered and explored, and the player can fight bosses and level up. 

To run, enter the following commands in the terminal:

    javac *.java
    java Game

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
Contains code that keeps track of the items in the player's inventory (health potion, attack potion, experience potion,
money potion, town map, dungeon map, compass, binoculars, puzzle, charm).

Monster.java
Contains code for the randomly generated monsters the player encounters. 

Move.java
Contains code for the moves the player has unlocked, and their cooldowns (charge, sap, steal, stun).

Other.java
Contains code that keeps track of miscellaneous locations the player has discovered (altar, bank).

Player.java
Contains code for the player object and the player statistics. Town messages are included as well, for some reason. 

Puzzle.java
Contains code for the Sudoko puzzles used in the dungeons. Each puzzle is randomly selected out of the 10 or so solutions
hard coded into the game.

Room.java
Contains code for the rooms randomly generated within the dungeons. Each room has 5 subrooms that randomly generate an
event for the player. (Usually a monster battle).

Town.java
Contains code for the towns the player can discover and explore. The town can heal the player, sell the player items, or
give the player various hints and tips for playing the game.

Note that this project was never intended for public release.
