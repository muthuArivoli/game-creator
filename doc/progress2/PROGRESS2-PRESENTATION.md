# Team 9 - Sprint 2 Progress Presentation

## Part 1 - Project Overview
### Demo:
!! ask the team about this one !!

### Current Structure:
#### Package 1 - game_view (front-end):

- **GameGUIController:** This class initializes the GUI and all the JavaFX methods when the program is run. It contains the JavaFX start method, and begins the animation loop, sets up the border pane, and creates all the buttons in the initial UI. 
- **GameBoard:** This holds the visual representation of the game board once it has begun. It is initialized in the GameGUIController class when a new game is started.
- **Tiles:** The tile classes represent each "space" on the game board, and are initialized and contained in a group structure that within the GameBoard class.
#### Package 2 - model (back-end):

- **GridModel:** The GridModel class is a back-end representation of the game board. It handles adding and removing pieces, calculating valid moves, and in general controlling the logical flow of the game's operations. It is initialized within the controller and communicates with the controller directly. 
- **Piece:** The piece classes represent the actual pieces that occupy spaces in the grid board. These classes contain the potential movement of each piece, its position, and its interactions. 
- **Movement:** The Movement classes handle all piece movement operations. We utilize the MovementFactory class to generate the proper type of movement class when they are needed. 

#### Package 3 - controller:

- **GameController:** This is the main interface between the front-end and back-end packages. The game controller class initializes the parsers that handle data file input. It also initializes the GridModel (back-end) class, and receives information from it which it communicates to the front-end. 
- **Parser:** The controller is also responsible for parsing information from data files, especially the gameFiles configurations. This is handled in Parser classes within the controller package. 

### Data Files:
- **gameFiles (chess.json):** Go over the file. 


## Part 2 - Sprint Reflection  
- During this sprint, we continued to focus on establishing the core mechanics necessary for games to run in our environment. As of now, we are still focused on bringing the chess example to full functionality. The piece movement has been fleshed out extensively, and the three main components of our design are working together as intended. The UI elements were also cleaned up and expanded upon during this sprint.
- Specific Event
- Improvement: This sprint, we all worked relatively well together. However, as we are approaching the end of the project, we recognize that we need to be more clear in our goals and better with communicating with one another. For the next sprint, we want to establish specific action items each time we meet, and deadlines for when these will be done. We will also increase our overall communication with each other and help out when needed.
- Future plan: Overall, we want our application to deliver a solid environment for strategy game play. For the next sprint, we will continue to work on implementing the fundamentals of the genre. We also hope to implement more games, once chess is up and running sufficiently well.
    1. Eric -
    2. Muthu -
    3. Turner - 
    4. Alexander - 