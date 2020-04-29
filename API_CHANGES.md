### Movement class
We completed redesigned our original Movement class. While we preserved the reflection and factory class paradigm we had planned upon, the core methods of the movement interface were changed. It was decided that each movement should be containerized in the sense that it has no knowledge of the grid or surrounding pieces; rather it should be up to the GameController and GridModel to decide on the allowed paths a piece can take given the standard paths defined by the movement. Thus, the movement class was refactored to only utilize one required method: getPaths, which returns all valid movement combinations from a specified index while leaving the logic of filtering out jumpable or enemy pieces to the controller.

### Gamecontroller
Originally we intended to have a "Player" class to hold each player's state and progress toward goals. However, upon developing the game through the second sprint, we realized that this was not necessary as "Players" are simply needless abstractions whose data is already stored within the GameController. Rather than manage two separate player classes, we simply toggled an integer state to represent which player was active in the GameController, allowing us the flexibility to switch to AI or double players mid game without having to worry about instantiating/deleting players. In place of a player, we created an AI package which provides an intelligent opposing "AI" which is a game controller which does not keep track of the state of the game, and simply returns the best move given an input grid when prompted.

### Front End Interaction with BackEND

In the implementation of the project, we made a slight change in the method 
through which the front end would communicate with the back end. This change 
came about as a result of designing a more abstract implementation of the Game Board. 
The board tiles and the pieces were made to be implementations of a component abstract class
which has a method that communicated with the backend. This eliminated the need 
for a specific method in the GameBoard Class to communicate user input as we had originally planned.   