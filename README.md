final
====

This project implements a player for multiple related games.

Names:

Alexander Uzochukwu

Eric Jiang

Turner Jordan

Muthu Arivoli

### Timeline

Start Date: March 31, 2020

Finish Date: April 25, 2020

Hours Spent:

Alexander Uzochukwu: 60

Eric Jiang: 60

Turner Jordan:

Muthu Arivoli:

### Primary Roles
Alexander Uzochukwu:
* Responsible for entire front-end UI design
    * Creating and implementing the resource package into the project
        * Languages
        * Stylesheets
    * Creating and implementing objects that the user can interact with
    * Providing the user ability to make design changes in the game
    * Updating display based on user decisions

Eric Jiang
* Responsible for full stack design
    * Worked on parsers and JSON game configuration file design
    * Developed controller and game/grid/piece models to interface with frontend
    * Implemented frontend features such as handling user clicks to move and place items

Turner Jordan

Muthu Arivoli

### ooga.games Used
* Chess
* Connect 4
* Tic tac toe

### Running the Program
Simply run the GameGuiController from src/ooga/controller. This will boot up the application from which the user can load the desired game configuration file.

#####Main class: 

GameGuiController in the controller package. This is the sole class which the user needs to interact with to start the program.

#####Data files needed: 

Game files will be loaded from the "data/gameFiles" directory. This is the sole directory which the user must interact with; each game corresponds to a single JSON configuration file stored within this directory.

#####Features implemented:
* Dark Mode
* Game Area Editor
* Intelligent MinMax AI and greedy AI
* Ability to fully customize tile/piece shapes and colors

### Notes/Assumptions

Assumptions or Simplifications:
The user will need to have some degree of understanding about how to set up the configuration file in particular. We assume that a piece which can move infinitely until hitting another piece (e.g. a rook in chess) will have this undiscretized movement pattern specified by a "-1" rather than a standard natural number representing the allowed movements.
Furthermore, we assume that the user will always include an "goals" parameter within the configuration file to define when the game has ended. If these goals are not defined, the game will continue without ending, even if all pieces have been captured. 

Interesting data files:

Known Bugs:
* if the user changes the tiles to be circular and the pieces to be 
rectangular, it creates an unwanted overlap that prevents the user from 
seeing the tile.
* while most exceptions from the parser due to bad configuration files are thrown and an error message is displayed to the user in the frontend, there may be an index out of bounds exception if the user creates an ascii grid whose dimensions does not match that specified by the width/height parameters.

### Impressions
The project was a great learning tool. It forced us to truly think about how APIs can be implemented so that they can handle different usages. All in all, we felt that this project allowed us to showcase many of the advanced Java design principles we developed throughout the course of the semester; from factory classes to MVC separation to inheritance to interfaces and resources, we managed to tie in much of our experience working on parser and simulation in particular to aid us in the development of this project.  
