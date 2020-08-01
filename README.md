# UTD Pledge of Honor
“As a Comet, I pledge honesty, integrity, and service in all that I do.”

# How to Use the International Draughts Application

## Basic Information

The user interface for this program is console driven.  

The International Draughts (ID) board is a 10 by 10 square, 2-dimensional play surface. Each player has a total of 20 pieces at their disposal, for a total of 40 game pieces on the board at the start of the game (20 white pieces and 20 black pieces). A sample print out of the board in its initial state is provided below. Please note that the "Rows" and "Columns" labels are shown in-game for the players help.

            0  1  2  3  4  5  6  7  8  9  <-- Columns
      0     -  W  -  W  -  W  -  W  -  W    
      1     W  -  W  -  W  -  W  -  W  -   
      2     -  W  -  W  -  W  -  W  -  W 
      3     W  -  W  -  W  -  W  -  W  -    
      4     -  -  -  -  -  -  -  -  -  -    
      5     -  -  -  -  -  -  -  -  -  -    
      6     -  B  -  B  -  B  -  B  -  B    
      7     B  -  B  -  B  -  B  -  B  -    
      8     -  B  -  B  -  B  -  B  -  B    
      9     B  -  B  -  B  -  B  -  B  -  
      Rows
          
          
It is assumed that the user knows how to play International Draughts, so the game rules have not been provided in the README. If you would like to review the rules, please visit the following link: https://en.wikipedia.org/wiki/International_draughts

## Initial Game Setup

Player 0 is pre-selected as a Human Player; this is the designated player for the user.  Player 1's player type is selected at runtime. The user is prompted to select a Player type from the following:
  1. Another Human Player
  2. Random Strategy Computer Player
  
Player 0 moves the white game pieces and makes the opening move.
Player 1 moves the black game pieces and makes the next move.

When the player(s) are ready to start the game, type "S" or "s" to start the game. Then, the initial game board state is printed to the console. After the board has been printed, Player 0 (the user) can make the opening move.

## How to Move Your Pieces

A piece is moved by providing 2 sets of coordinates. The first set contains the row and column information for the piece that you would like to move, and the second set contains the row and column information for where you would like to move that piece to.

It is important to note that rows and columns are both indexed from 0. Valid row numbers are 0 through 9 (10 rows), and valid column numbers are 0 through 9 (10 columns).

# Issues faced during implementation/ Design Decisions

Please visit the Wiki page to read about this: https://github.com/UTDClassroom/cs6359003-project-cs6359003-team4/wiki/Issues-faced-during-implementation-and-solutions

# Game Design

* Most of the checkers rule will be implemented.
* The king piece logic will be ignored for this implementation.
* The pieces will be able to move in all directions(Right, Left, Front, Back).
* Score for both players will be stored and be used for deciding the winner.
* When the number of games cross 100 moves a winner will be decided, if the scores are same it will be declared a draw.

# Random Strategy

Both RandomMoveStrategy and RandomValidMoveStrategy have been combined into one. Random Strategy used by the computer player will always generate valid values.

# GameFramework

Design Patterns for Board Games

Development of an OO board game system. The students easily see the power, utility, flexibility and scalability of the OO Design. The lesson is that generic concepts should lead to generic applicability, not single-use, “throw away” code. The students learn “programming-in-the-large” while studying a system that is still small enough to manage.

For simplicity, we will focus on two-dimensional game boards (Tic-Tac-Toe, Othello, Checkers, Go,...), with only two players.

This project is not about Tic-Tac-Toe nor InternationalDraughts. It uses a 2-person game design as a vehicle to learn BIGGER concepts in computing:

- Abstraction
- Design Process
- Fundamental Principles.

In this project, you will be given a big chunk of a 2-person board game framework and asked to write a few of its components, plug them in and obtain a program that can run Tic-Tac-Toe, Othello, and .. with different types of players, human and/or computer, using a variety of strategies to compute the next move while playing the games.

The given game framework abstracts and decouples the different components in a game and specifies them in terms of interfaces with only pure abstract behaviors.
For example, the rules of a game is abstracted and encapsulated in an interface called IBoardModel. ABoardModel is a specific implementation of this interface using the state pattern. Playing a particular board game is a matter of writing a concrete subclass of ABoardModel and plug it into the framework. Nothing in the framework is changed!

Among the source files is GameModel.java which is the class that encapsulates:

- the rules of a game,
- the strategies to compute the next move, and
- the management of players.

- *GameModel* does not contain any code specific to Tic-Tac-Toe. It merely moderates the interactions between the board, IBoardModel and the strategy to compute the next move, INextMoveStrategy. 
- *GameModel.getRequestor()* is the factory method to instantiate the IRequestor for the view. GameModel directly implements the IModelManager interface.
- GameModel.java has a method called getPlayers(). In this method, the code to add players playing the strategies.

For more info please review the following paper: https://github.com/UTDClassroom/GameFramework/blob/master/DesignPatternForGames.pdf

Be sure to document (in javadoc style) all the code you write.
