## Implementation Plan
* Game Genre:
    * Turn-Based Strategy Games
    * Commonalities: 
        * Game Pieces
        * Grid style board setups
        * Capturing/neutralizing of opponent pieces
    * Differences:
        * Goal to win game
        * Rules of how pieces can move
* Extensions:
    * Dark Mode
    * Level of computer AI
    * Preferences
    * Load/Save Games
    
* Sprint 1:
    * Ability to read and parse game file
    * Create some game parts from ^^
    * Basic GUI display
    
* Sprint 2:
    * More advanced GUI
    * Error Handling
    * Ability to run to a simple degree certain games

* Sprint 3:
    * Complete Game
    * Advanced AI
    
## API Discussion

* See DESIGN_PLAN.md and UML_Diagram.PNG

* Use Cases: 
    * Creating a Piece
    * Move Piece
    
* Alternative Design: 
    * Having only specific possible movement classes 
        * Pros:
            * Easy to Implement
            * Limits the possible movements our code has to account for
        * Cons:
             * Not very flexible
             * limits ability of user to create random piece movements 
                for their games



