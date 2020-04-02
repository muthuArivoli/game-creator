### Use Case 1:  Setting the game to dark mode

* User clicks the following buttons : settings -> change background -> dark mode
* Display Controller executes the event handler that is the changeBackground method. 

## Use Case 2: Creating Queen piece

* In the configuration file
    * add image file/Shape 
    * Name
    * movements
* Parser Controller parses and creates Piece object with those parameters

## Use Case 3: Adding a Goal to the engine
* Create a class that specifies the settings for which the game ends
* The goal is specified in the configuration file
* Parser Controller creates the goal object as part of the game 

## Use Case 4: Select a piece
* User clicks on piece
* Game Controller executes choosePiece method

## Use Case 5: Move a piece
* User Clicks on piece and then clicks on destination
* Game Controller executes choosePiece method
* Game Controller executes isPieceMoveable method
* Piece is moved to destination if result of above is true

## Use Case 6: Load a new game
* Click "new game button"
* LoadFile method is executed
* Select new configuration file

## Use Case 7: 



