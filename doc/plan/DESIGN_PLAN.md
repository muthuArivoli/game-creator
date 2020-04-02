# Team 9 Final Project - Design Document
## Introduction

## Overview
With this being the final project, our main goal is to come full circle, effectively bringing together all of the design elements and experience garnered from the previous projects into one cohesive product. To be able to put together everything we have learned, from interfaces/inheritance and reflection to Java design paradigms, we hope to be able to utilize the principles of OOP to create a project that is as scalable and extensible as possible.

The game category we have decided to tackle are turn-based strategy board games (e.g. chess, connect 4, etc). We found this category particularly interesting due to the fact that while the game logic may vary significantly between games, there are core components which make up any strategy game which we can abstractify.

Our end goal is to allow the user to be able to mix and match between various configurations of any strategy game by specifying parameters within a configuration file. This means that our principle design will adhere to the paradigm that there will NOT be a class for each specific game type (such as what we saw in the simulation project). Rather, in an effort to give more freedom to the user and follow through on abstraction, a completely new game variant can be generated simply by creating a single configuration file with the desired parameters (specifying the starting configuration, pieces, winning configurations, etc). This way, a user could theoretically mix and match with multiple pieces and game rules, perhaps creating chess-style connect-4 or any other mutated variant of the base game given that they can explicitly define every single piece/movement parameter withint the configuration file without touching any of the Java classes.

## Design Details
### Backend
The backend is the meat and potatoes of this project. Given our core design tenets of abstraction (having the user be able to create a new game by editing only the configuration file) and scalability (ability to handle any number of new game variants with differing combinations of pieces and rules), we felt it important to modularize the physical components of any strategy game into its individual classes.

1. Grid Class: We begin with a Grid class which contains a 2D ArrayList of Piece objects which will represent the board. Given that all strategy games detailed in the following section are physically played on a 2D grid, we felt this implementation choice would allow for enough scalibility for our uses. The Grid class will contain the standard public getter and setter methods to access/update/remove Piece objects specified by the (x, y) index passed through.
2. Piece Class: This is quite possibly the most important class in the entire project as it represents the individual physical piece that a player will place/move on the board. All Piece objects will have the following instance variables which will set by the configuration file: 
    - String name (e.g. "Queen")
    - String image (image to represent the piece in frontend)
    - String shape (shape of the Piece if image file not specified)
    - Boolean capturable (ability to be captured by another piece)
    - Boolean placeable (ability to be placed on the board by a user)
    - String movementDirection (specifies the limits upon the degrees of freedom that the piece has. For instance, a Piece with "forward" movement direction will not be allowed to move backward while a Piece with "linear" movement direction can move unrestricted in all directions)
    - ArrayList <> Moves(represents all the possible movements that the piece can have)
3. Move Class: All Pieces objects are associated with a "movability" which specifies the direction and amount in which they are allowed to be moved by both the user and the AI. This ability to move is defined within classes which implement the Move class interface. The Move class will always take in a parameter within the constructor which specifies the number of units allowed to move (for instance, a checker would be allowed to move 1 unit while a rook in chess would be allowed to move -1 units (which means infinite units)).
    - Each possible movement will have a corresponding class which implements this base movement class. For instance, there will be a Left, Right, Forward, Backward, Diagonal classes which each specify the corresopnding movement in a particular direction. 
4. Goal Class: This is a top level class component which abstractifies what it will take to end the game. Underneath the Goal class will be many smaller subcomponents to represent the condition under which the game will end. For instance, fourInRowGoal will be a class which represents the winning state of having four in a row in Connect 4, while allPiecesCapturedGoal will represent the game end state when all pieces have been captured; while pieceCapturedGoal will represent the game end state when a specific piece has been captured.
5. Player Class: The player class will be used as a model representation of the player and the AI. For instance, each Player will have instance variables which are updated on each move, such as int totalScore, Boolean isMyTurn, myMove (which represents the interaction between the user and the gui to place or move a piece or the AI's decision to do the same).
6. Controller Class: This is responsible for containing both Player classes and the associated Grid model. It will pass these models and the associated state of the game  to be rendered by the View Class.
7. View Class: Quite simple implementation which will simply take in the Grid Model and contain event listeners for each of the Piece objects represented within the frontend Grid. On click of a particular cell or Piece object, the corresponding event handler logic within that View will in turn trigger a change the most likely the Player class (where the myMove variable will be updated and the controller will execute this change via the Executioner class).
8. Executioner Class: Responsible for executing a specific movement or action by the user: such as, placing a Piece by clicking on an empty cell or moving a Piece by clicking on the Piece and then clicking on an open cell.
9. Parser Class: Responsible for parsing the configuration file for each game and populating the corresponding model with the data (e.g. populating the Grid Model with the correct placement of Piece objects each instantiated as specified within the JSON configuration file).

## Example Games
The three examples which we will cover in this Plan document to highlight differences are Checkers, Chess, and Connect 4.

These 3 examples are all similar in that they each feature the same core components which are abstractified in our design: Pieces which can be placed in open cells in a grid and have some degree of interactability with other cells. This degree of interactability can mean that a piece may have the ability to "capture" another Piece if its capture movement allows the piece to land on another Piece (such as in Checkers or Chess). It can also mean that a Piece may not capture other pieces and simply other pieces serve as an impedance to the allowed motion (as in the case of Connect 4). Furthermore, there are two main capabilities of pieces: the ability to be placed on the board and generated at will or preset pieces which can only be moved around but not created. In order to allow for this degree of variability, we simply set the corresponding instance variables (some of which are booleans to account for this):

Pieces have the following attributes:
- belong to one particular side/player
- ability to interact with other pieces
 
Pieces Have Move Classes
- ability to move in a specific direction (for instace in chess you cannot move backward)
- allowed degree of movement when not taking a piece (linear, diagonal) including number of units it can move
    - Even have an "L" move class for chess

- ability to take another piece
    - Connect 4: no ability to take piece
    - Chess: ability to take piece if move
- direction the piece needs to move in order to take a piece
    - Chess: needs to move diagonally in order to capture piece
 
- a corresponding image/shape/color
- ability to be placed on board
    - Connect 4, tic tac toe: can be placed
    - Chess: pieces cannot be placed

Displaying the three games on a frontend is trivial, as the frontend simply needs to read in the Grid model and generate a representation of each piece within the Grid based on its image as specified in the configuration file. 

## Design Considerations
Since the focus of this project is scalability, the team spent extensive time thinking about how best to promote the modularity and scalability aspects. Initially, we proposed a similar design to the previous Cellular Automata project in which we would have all the different Game variations and the corresponding logic for these games each stored in its own class which would inherit from a base Game parent class. Likewise, we would have a parent Piece class and we would create the corresponding classes for each piece for the game we wanted to implement (for instance, for chess, we would create a Rook, Knight, Pawn, Queen, etc. class which extend/implement the Piece class). However, we realized that this design would be highly unscalabe, as adding a new game would not only reuire the user to create a configuration file but also create a new class and associated variables. Instead, we decided that to solve the problem of figuring out how to abstractify each game's logic, we would break down the game logic into small modularized components in Class form such that it would be on the onus of the user to build a game from the core logic class we provide. What this means is that our API essentially provides building blocks/LEGOS which the user can utilize to mix and match as many different Pieces with differing movement capabilities/placement/interactability with other pieces as they wanted. This means they could quite easily create a Connect 6 rather than Connect 4, or a Chess variant which doesn't have pawns and knights, or a Checkers variant where the pieces can only move in L shaped directions (as in Knights). Essentially we would provide the modular building blocks by creating classes to represent these building blocks and utilize reflection to achieve what the user's tailored game specifications are. Some further brainstorming we came up with for possible games are:

Possible Games:
- Chess
- Checkers
- Connect Four
- Tic Tac Toe
- Othello
- Reversi
- Mancala