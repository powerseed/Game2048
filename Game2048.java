/*
* [Name of class: Game2048]
* COMP 1020 SECTION [A03]
* INSTRUCTOR: [Robert Guderian]
* NAME: [Luo Jiehao]
* Student Number: [7852402]
* ASSIGNMENT: [Assignment 02]
* PURPOSE: [To play the ¡°2048 Puzzle¡± game]
*/
import java.util.ArrayList;

public class Game2048 {
   
   private GraphicsWindow2048 myWindow;
   
   //A list of all of the boards in the game.
   //....your code here
   private ArrayList<Board2048> list = new ArrayList<Board2048>(0);
  
   /*
	* [Objective: To start a game and display it within a window.]
	* [Input: 2 parameters: GraphicsWindow2048 theWindow, int size]
	* [Output: Initialized variable of myWindow, a new Board2048 object of newBoard, add a new tile to the grid of newBoard,
	* 		   add the newBoard to list and display the grid within a window.]
	* [Parameters: GraphicsWindow2048 theWindow, int size]
	* [Value returned: None]
	*/
   public Game2048(GraphicsWindow2048 theWindow, int size) {
      myWindow = theWindow;
      //....your code here
      Board2048 newBoard=new Board2048(size);				// Create a new Board2048 object.
      newBoard.newTile();									// Add the first tile.
      list.add(newBoard);									// Add this Board2048 object to list.
      myWindow.displayBoard(newBoard.getMatrix());			// Display the newBoard
   }//Game2048
   
   /*
	* [Objective: Attempt to make the indicated move. If it is an invalid move, it do nothing. If it is a valid move, it makes the move, add a new
				  tile to the board, and display the new board. It keeps track of the new board in list. If the game is now over, 
				  it displays a ¡°Game Over¡± message in the window.]
	* [Input: 1 parameter: int direction]
	* [Output: A shifted grid with a new tile, which is added into list, and display it within a window.]
	* [Parameters: int direction]
	* [Value returned: None]
	*/
   public void tryMove(int direction){
      //....your code here
      if(list.get(list.size()-1).validMove(direction))				//If the newest board of the game can move in the specified direction validly.
      {
    	  list.add(list.get(list.size()-1).shift(direction));			// Shift the newest board in the specified direction, then add the newly shifted board to list.
    	  list.get(list.size()-1).newTile();							// Add a new tile to the the newly shifted board.
    	  myWindow.displayBoard(list.get(list.size()-1).getMatrix());	// Display the newly shifted board.
    	  if(list.get(list.size()-1).gameOver())						// If the newly shifted board can not be shifted anymore.
    	  {
    		  myWindow.displayMessage("Game Over");
    	  }
      }
   }//tryMove
   
   /*
	* [Objective: Undo the previous move, going back to the previous board. This works any number of times, so that every move in the entire game could be undone. 
	* 			  But not be able to go beyond the initial position created in the constructor.]
	* [Input: None]
	* [Output: Remove the last element of list and display the one before it.]
	* [Parameters: None]
	* [Value returned: None]
	*/
   public void undo(){
      //....your code here
	   if(list.size()>1)													// If this is not the initial board.
	   {
		   list.remove(list.size()-1);											// Remove the newest board from list.
		   myWindow.displayBoard(list.get(list.size()-1).getMatrix());			// Display the last board after removing.
	   }
   }//undo
}//Game2048 class
   