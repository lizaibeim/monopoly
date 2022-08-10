# monopoly
Class project for comp2021 object oriented programming

## Introduction
Monopoly is a board game that originated in the United States in 1903 as a way to demonstrate that an economy that rewards wealth creation is better than one in which monopolists work under few constraints and to promote the economic theories of Henry George and in particular his ideas about taxation. The current version was first published by Parker Brothers in 1935. Subtitled "The Fast-Dealing Property Trading Game", the game is named after the economic concept of monopoly—the domination of a market by a single entity. It is now owned and produced by the American game and toy company Hasbro. Players move around the game-board buying or trading properties, developing their properties with houses and hotels, and collecting rent from their opponents, with the goal being to drive them all into bankruptcy leaving one monopolist in control of the entire economy. Since the board game was first commercially sold in the 1930s, it has become a part of popular world culture, having been locally licensed in more than 103 countries and printed in more than thirty-seven languages.  
**This project is to simulate the monopoly game written in Java which fulfils the following functionalities**:  
```
  a. support a command line user interface.  
  b. support both human players and computer players.  
  c. ask for a command from a human player at each step, including Continue, Report, Auto, and Retire.  
  d. let a human player take his/her turn once receiving the command Continue.  
  e. print out the information of each square and each player’s location on board once receiving the command Report.  
  f. switch a human player to be auto and let it control by a computer which always continues until the game ends and 
     makes random decisions once receiving the command Auto.  
  g. switch a human player to be retired (leaves games with all his/her properties unowned) once receiving the command Retire.  
  h. ask for input from human players on the condition that whether to buy a property or pay the fine to get out of jail.  
```

## Program structure
![image](https://user-images.githubusercontent.com/38242437/184028757-3d6ee400-1f80-495c-ba34-a6ed2dfc5af0.png)
### Game class
The ***Game*** class controls the process of game and initializes the squares and the players. ***runGame()*** method control the process that every player takes his turn and check whether that the game finished (only one player left or more than 100 rounds).  
```
a. in each turn, the askforCommand() method will ask for an input from the cmd, and call the corresponding method (continueCommand(),
   reportCommand(), retireCommand()).  
b. continueCommand(): if the player is in jail, call the injail.dosomething() and if after the call, the player get out of the jail,
   call dosomething() of the square the player located. If the player is not in jail, move the player and then call dosomething() 
   of the square the player located. Then check whether the player pass the go square and whether the player has to retire.  
c. reportCommand(): Report Command will print the information of squares and players. After printing, call the askforCommand().  
d. retireCommand(): it will change the player into Retire and reset the owner of properties.  
```
### Player class
The ***Player*** class store the information of the player and has serval method for interaction. The field name and the method name can indicate its functionality so here only give the description of some special method.  
```
a. move(): the method will return a Boolean value telling whether the player passes the go squares so that the continueCommand can know.  
b. rollDice(): the method will return an integer array containing the two random integers which are both in [1, 4].
```
### Square class
The ***Square*** class is an abstract class that is extended by different types of squares.  
```
a. doSomething(): the method is an abstract method and it is overridden in subclass so that different squares can realize different functionalities.
b. printInfo(): the method is to print the information about it for the reportCommand.
```

## Game flow
### The basic design of our game is that the players will be asked for commands from the console and according to their input, the corresponding output will be shown. When the game is finished, the winner will be shown.
![image](https://user-images.githubusercontent.com/38242437/184029706-546ae400-5c72-459d-8cd2-2753477fdd12.png)

## Quick start guide
This monopoly project has a lot of commands to make the monopoly game go smoothly.  
Those commands are as follows:
1. First, when the game starts, the console will ask a command from users to input a number ***n***, which is used to initialize the ***n*** number of players.  
2. Then, when the number of players has been input, the console will initialize the player one by one. The user will be asked for a command to input 0 or 1, of which 0 represents that the player(n) will be controlled by humans during the game and 1 represents that the player(n) will be controlled by the computer during the game.  
3. If all the players are controlled by the computer, then the console will no longer need to ask for a command from users.  
4. If a player(n) is controlled by humans, then the console will ask for a command when it is up to player(n)’s turn. The commands include Continue, Report, Auto and Retire. The function of each command are as follows:  
      - Continue: Two dice (tetrahedron) are rolled simultaneously. The sum of the two dice (from 2 to 8) is the step count for the player stepping forward.  
      - Report: All information about squares and players will be printed out, including the type of square, the property name of the square (for property only), the owner of the square (for property only), the price of the square (for property only), the rent of square (for property only), the type of player, the location of the player, and the money player owns.  
      - Auto: The player controlled by humans is switched to automatically controlled by the computer.  
      - Retire: The player controlled by humans is switched to be retired and leaves the game.  

## Authors:
  [Zaibei Li](https://www.linkedin.com/in/zaibei-eric-li/)  
  [Yanbin Cao](https://www.linkedin.com/in/%E9%9B%81%E5%BD%AC-%E6%9B%B9-0112a711b/)  
  [Ziyi Wen](https://www.linkedin.com/in/ziyi-wen-896309168/)
