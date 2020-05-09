/*
* [Name of class: Board2048]
* COMP 1020 SECTION [A03]
* INSTRUCTOR: [Robert Guderian]
* NAME: [Luo Jiehao]
* Student Number: [7852402]
* ASSIGNMENT: [Assignment 02]
* PURPOSE: [To implement the ¡°2048 Puzzle¡±]
*/
import java.util.Random;

public class Board2048 
{
	int[][] grid;												//To store the board of the ¡°2048 Puzzle¡±
	public static final int UP=1, DOWN=0, LEFT=3,RIGHT=5;		//To represent the four possible directions that the player want to shift the board. 
	
	/*
	* [Objective: To initialize the variable of grid with the desired size. It will be square.]
	* [Input: 1 parameter: int rowAndCol]
	* [Output: An initialized variable of grid.]
	* [Parameters: int rowAndCol]
	* [Value returned: None]
	*/
	public Board2048(int rowAndCol)
	{
		grid=new int[rowAndCol][rowAndCol];
	}
	
	/*
	* [Objective: To initialize the variable of grid by a particular grid.]
	* [Input: 1 parameter: int[][] particularGrid]
	* [Output: An initialized variable of grid.]
	* [Parameters: int[][] particularGrid]
	* [Value returned: None]
	*/
	public Board2048(int[][] particularGrid)
	{
		grid=particularGrid;
	}
	
	/*
	* [Objective: To return a multi-line String representation of the grid.]
	* [Input: None]
	* [Output: A multi-line String representation of the grid.]
	* [Parameters: None]
	* [Value returned: String printTheGrid]
	*/
	public String toString()
	{
		String printTheGrid="";													//To create an empty String.
		for(int row=0;row<grid.length;row++)									//Scan every row.
		{
			for(int col=0;col<grid[row].length;col++)								//Scan every column..
			{
				if(grid[row][col]==0)													//If a number in the grid is 0.
				{
					printTheGrid=printTheGrid+"-\t";										//Add "-" to the string to represent this number, and followed by a tab.
				}
				else																	//If a number in the grid is NOT 0.
					printTheGrid=printTheGrid+grid[row][col]+"\t";							//Add this number itself to the grid, and followed by a tab.
			}
			printTheGrid=printTheGrid+"\n";												//After a column is finished scanning, start a new line.
		}
		return printTheGrid;
	}
	
	/*
	* [Objective: Accept an int[] array representing one line of tiles in the grid, and alter the array according to the
				  rules of the game. It returns true if the array was changed in any way, and false if it remained
				  exactly the same as it was before.]
	* [Input: 1 parameter: int[] oneLine]
	* [Output: Alter the array according to the rules of the game, and return true if the array was changed in any way, 
	* 		   and false if it remained exactly the same as it was before.]
	* [Parameters: int[] oneLine]
	* [Value returned: boolean isChanged]
	*/
	public static boolean alterOneLine(int[] oneLine)
	{
		boolean isChanged=false;					// To identify if the array was changed.
		int orderOfnumber=0;						// The position in the array that should contain a non-zero value. We deal with it one by one, 
													// starting from position 0, and add its value by one after each dealing.
		boolean isLocked=true;						// To control if an operation of altering a number in this array needs to merge two numbers.
		for(int i=0;i<oneLine.length;i++)			// To scan every number in the array.
		{
			if(oneLine[i]!=0)							//If a number is not 0.
			{
				if(i!=orderOfnumber)						//And this number is at the wrong position.
				{
					isChanged=true;								//Under this circumstance, some changes must be done.
					if(!isLocked)								//Not locked, which means that we need to check if two number are the same then merge them.
					{
						if(oneLine[orderOfnumber-1]==oneLine[i])					// This number is equal to the non-zero number 
																					// that is at the previous position of the array.
						{
							oneLine[orderOfnumber-1]=oneLine[orderOfnumber-1]*2;		// To time the non-zero number 
																						// which is at the previous position of the array by 2.
							oneLine[i]=0;												// This number itself becomes 0.
							isLocked=true;												// Lock it! Next operation of altering a number in this array 
																						// doesn't need to merge two numbers.
						}
						else														// This number is NOT equal to the non-zero number 
																					// that is at the previous position of the array.
						{
							oneLine[orderOfnumber]=oneLine[i];							// Copy this number to the right position.
							oneLine[i]=0;												// This number itself becomes 0.
							orderOfnumber++;											// The position of the next non-zero number in this array 
																						// should be the next.
							isLocked=false;												// We did not merge two numbers this time, so we need to consider it next time.
						}
					}
					else										//It is locked, which means that we do not need to check if two number are the same then merge them.
					{
						oneLine[orderOfnumber]=oneLine[i];				// Copy this number to the right position.
						oneLine[i]=0;									// This number itself becomes 0.
						orderOfnumber++;								// The position of the next non-zero number in this array 
																		// should be the next.
						isLocked=false;									// We did not merge two numbers this time, so we need to consider it next time.
					}
				}
				else										//And this number is at the right position.
				{
					if(orderOfnumber>0)							//This number is NOT the first one in this array.
					{
						if(oneLine[orderOfnumber-1]==oneLine[i])	// This number is equal to the non-zero number 
																	// that is at the previous position of the array.
						{					
							isChanged=true;												// Under this circumstance, some changes must be done.
							oneLine[orderOfnumber-1]=oneLine[orderOfnumber-1]*2;		// To time the non-zero number 
																						// which is at the previous position of the array by 2.
							oneLine[i]=0;												// This number itself becomes 0.
							isLocked=true;												// Lock it! Next operation of altering a number in this array 
																						// doesn't need to merge two numbers.
						}
						else										// This number is NOT equal to the non-zero number 
																	// that is at the previous position of the array.
						{
							orderOfnumber++;							// The position of the next non-zero number in this array 
																		// should be the next.
							isLocked=false;								// We did not merge two numbers this time, so we need to consider it next time.
						}
					}
					else										//This number is the first one in this array.
					{
						orderOfnumber++;			// The position of the next non-zero number in this array 
													// should be the next.
						isLocked=false;				// We did not merge two numbers this time, so we need to consider it next time.
					}
				}				
			}
		}
		return isChanged;
	}
	
	/*
	* [Objective: To return an int[] array containing one particular row or column of the grid. If vertical is true it should return
				  column i. If vertical is false it should return row i. If reverse is false it should return the values in the usual
				  order (left to right or top to bottom), but if reverse is true it should return them in the reverse order.]
	* [Input: 3 parameters: int i, boolean vertical, boolean reverse.]
	* [Output: An int[] array containing one particular row or column of the grid.]
	* [Parameters: int i, boolean vertical, boolean reverse.]
	* [Value returned: int[] rowOrColWanted]
	*/
	public int[] extractLine(int i, boolean vertical, boolean reverse)
	{
		int[] rowOrColWanted; 					// Create an new int[] to hold the row or column extracted.			
		if(vertical)							// We need to extract a column. 
		{
			rowOrColWanted=new int[grid.length];	// Initialize the newly created int[], making its length equal to the number of grid's elements in one column. 
			if(reverse)								// Extract it in reverse order.	
			{
				for(int indexOfSource=grid.length-1,indexOfTatget=0;indexOfSource>=0;indexOfSource--,indexOfTatget++)	
														// The two int variables represent the subscript of the array that we extract values from, grid,
														// and the array we copy values into, rowOrColWanted, respectively. Because we do it in reverse order, 
														// the former is initialized by the position of the LAST element in one column in grid, 
														// and minus it by one every time we have done with one number.
					{
					rowOrColWanted[indexOfTatget]=grid[indexOfSource][i];
															// Copy grid's every row's No.i element to the newly created array of rowOrColWanted, in reverse order.
				}
			}
			else									// Extract it in usual order.	
			{
				for(int index=0;index<grid.length;index++)
				{
					rowOrColWanted[index]=grid[index][i];
															// Copy every row's No.i element of grid to the newly created array of rowOrColWanted, in usual order.
				}
			}
		}
		else									// We need to extract a row. 
		{											
			rowOrColWanted=new int[grid[i].length]; // Initialize the newly created int[], making its length equal to the number of grid's elements in one row. 
			if(reverse)								// Extract in reverse order.		
			{
				for(int indexOfSource=grid[i].length-1,indexOfTatget=0;indexOfSource>=0;indexOfSource--,indexOfTatget++)
														// The two int variables represent the subscript of the array that we extract values from, grid,
														// and the array we copy values into, rowOrColWanted, respectively. Because we do it in reverse order, 
														// the former is initialized by the position of the LAST element in one row in grid, 
														// and minus it by one every time we have done with one number.
				{
					rowOrColWanted[indexOfTatget]=grid[i][indexOfSource];
															// Copy grid's No.i row's every element to the newly created array of rowOrColWanted, in reverse order.	
				}
			}
			else									// Extract in usual order.	
			{
				for(int index=0;index<grid[i].length;index++)
				{
					rowOrColWanted[index]=grid[i][index];	// Copy grid's No.i row's every element to the newly created array of rowOrColWanted, in usual order.
				}
			}
		}
	return rowOrColWanted;
	}
	
	/*
	* [Objective: Work exactly like extractLine, but in reverse. The given int[] array will be copied into
				  the specified row or column of the grid, in normal or reverse order.]
	* [Input: 4 parameters: int[] line, int i, boolean vertical, boolean reverse]
	* [Output: An replaced row or col in grid.]
	* [Parameters: int[] line, int i, boolean vertical, boolean	reverse]
	* [Value returned: None]
	*/
	public void insertLine(int[] line, int i, boolean vertical, boolean	reverse)
	{
		if(vertical)						// Insert one column.
		{
			if(reverse)							// In reverse order.
			{
				for(int indexOfSource=line.length-1,indexOfTatget=0;indexOfSource>=0;indexOfSource--,indexOfTatget++)
													// The two int variables represent the subscript of the array that we insert values from, int[] line,
													// and the array we copy values into, grid, respectively. Because we do it in reverse order, 
													// the former is initialized by the position of the LAST element of int[] line, 
													// and minus it by one every time we have done with one number.
				{
					grid[indexOfTatget][i]=line[indexOfSource];
														// Copy every element of int[] line to the grid's every element of No.i column, in reverse order.
				}
			}
			else								// In usual order.
			{
				for(int index=0;index<line.length;index++)
				{
					grid[index][i]=line[index];			// Copy every element of int[] line to the grid's every element of No.i column, in usual order.
				}
			}
		}
		else								// Insert one row.
		{
			if(reverse)								// In reverse order.
			{
				for(int indexOfSource=line.length-1,indexOfTatget=0;indexOfSource>=0;indexOfSource--,indexOfTatget++)
														// The two int variables represent the subscript of the array that we insert values from, int[] line,
														// and the array we copy values into, grid, respectively. Because we do it in reverse order, 
														// the former is initialized by the position of the LAST element of int[] line, 
														// and minus it by one every time we have done with one number.
				{
					grid[i][indexOfTatget]=line[indexOfSource];
														// Copy every element of int[] line to the grid's every element of No.i row, in reverse order.
				}
			}
			else									// In usual order.
			{
				for(int index=0;index<line.length;index++)
				{
					grid[i][index]=line[index];
														// Copy every element of int[] line to the grid's every element of No.i row, in usual order.
				}
			}
		}
	}
	/*
	* [Objective: Shift the entire grid in the indicated direction, producing a new Board2048 object. The existing grid in the existing Board2048 object will not be
				  affected. This method uses extractLine, alterOneLine, and insertLine to do all of the work.]
	* [Input: 1 parameter: int direction]
	* [Output: A shifted Board2048.]
	* [Parameters: int direction]
	* [Value returned: Board2048]
	*/
	public Board2048 shift (int direction)
	{
		Board2048 shifted = new Board2048(grid.length);			// Create a new Board2048 with the same size of the original one. 
		
		for(int row=0;row<grid.length;row++)
		{
			for(int col=0;col<grid.length;col++)
			{
				shifted.grid[row][col]=grid[row][col];					// Copy every elements from the original grid to the newly created one.
			}
		}
		
		if(direction==UP)										// Shift upward
		{
			for(int i=0;i<shifted.grid.length;i++)
			{
				int[] container=shifted.extractLine(i, true, false);	// Copy one column to a "container" in usual order.
				alterOneLine(container);								// Alter the "container"
				shifted.insertLine(container, i ,true,false);   		// Copy the altered "container" back into the original column in usual order.
			}
		}
		else if (direction==DOWN)								// Shift downward
		{
			for(int i=0;i<shifted.grid.length;i++)
			{
				int[] container=shifted.extractLine(i, true, true);		// Copy one column to a "container" in reverse order.
				alterOneLine(container);								// Alter the "container".
				shifted.insertLine(container, i ,true, true);   		// Copy the altered "container" back into the original column in reverse order.
			}
		}
		else if (direction==LEFT)								//Shift leftward
		{
			for(int i=0;i<shifted.grid.length;i++)
			{
				alterOneLine(shifted.grid[i]);							// Alter every column of the newly created Board2048 object of shifted.
			}
		}
		else if (direction==RIGHT)								//Shift rightward
		{
			for(int i=0;i<shifted.grid.length;i++)
			{
				int[] container=shifted.extractLine(i,false,true);		// Copy one row to a "container" in reverse order.
				alterOneLine(container);								// Alter the "container".
				shifted.insertLine(container,i, false, true);			// Copy the altered "container" back into the original row in reverse order.
			}		
		}
		return shifted;
	}
	
	/*
	* [Objective: Find and return the number of empty spaces in grid.]
	* [Input: None]
	* [Output: The number of empty spaces in grid.]
	* [Parameters: None]
	* [Value returned: int sumOfSpaces]
	*/
	public int numEmpty()
	{
		int sumOfSpaces=0;							// To hold the number of spaces.
		for(int row=0;row<grid.length;row++)		
		{
			for(int col=0;col<grid.length;col++)		// Scan every elements in gird.
			{
				if(grid[row][col]==0)						
				{
					sumOfSpaces++;								
				}
			}
		}
		return sumOfSpaces;
	}
	
	/*
	* [Objective: Add a new tile to grid. Every empty space has an equal probability of being chosen as the place for the new tile.
	* 			  The value 2 is used for the new tile 90% of the time, and the value 4 is used 10% of the time.]
	* [Input: None]
	* [Output: Add a new tile to grid.]
	* [Parameters: None]
	* [Value returned: None]
	*/
	public void newTile()
	{
		Random ranGenerator=new Random();						
		int numOfNewTile=ranGenerator.nextInt(numEmpty())+1;	// Generate the serial number of the next new tile.
																// ranged from 1 to the number of empty spaces in grid. 
		
		int rowOfNewTile=0,colOfNewTile=0;						// To hold the row number and column number of the next new tile.
		int indexOfSpace=0;										// To hold the serial number of empty space we are checking. After each checking, we add it by 1.
		while(indexOfSpace<numOfNewTile)						// If the serial number of the empty space we are checking 
																// have not reached the position that the new tile should be. Otherwise, the rowOfNewTile and colOfNewTile
																// will be that we want;
		{
			if(grid[rowOfNewTile][colOfNewTile]==0)					// If the element in grid we are checking is 0;
			{
				indexOfSpace++;											// The serial number of empty space adds by 1.
			}
			if(indexOfSpace!=numOfNewTile)							// After the adding above, if the serial number of the empty space we are checking 
																	// still have not reached the position that the new tile should be, 
																	// we need to move on to check the next element in grid. 
			{
				if(colOfNewTile<grid.length-1)							// If we have not reached the end of a row.
					colOfNewTile++;											// we need to check the next element on the current row.
				else													// If we have already reached the end of a row.
				{
					if(rowOfNewTile<grid.length-1)							// If we have not reached the last row of grid.
						rowOfNewTile++;											
						colOfNewTile=0;											// We need to check the first element of the next row.
				}
			}
		}					
		int odd=ranGenerator.nextInt(10);						//Generate a number from 0 to 9.
		if(odd>=0 && odd<=8)									// The odd, 90%, for value of 2.
		{
			grid[rowOfNewTile][colOfNewTile]=2;						// Set the position of empty space we have spotted by 2.
		}
		else													// The odd, 10%, for value of 4.
		{
			grid[rowOfNewTile][colOfNewTile]=4;						// Set the position of empty space we have spotted by 4.
		}
	}
	
	/*
	* [Objective: Return true if two boards have identical grids.]
	* [Input: 1 parameter: Board2048 other]
	* [Output: A boolean showing if two boards have identical grids.]
	* [Parameters: Board2048 other]
	* [Value returned: boolean isIdentical]
	*/
	public boolean equals(Board2048 other)
	{
		boolean isIdentical=true;								//The boolean variable showing if two boards have identical grids.
		if(this.grid.length != other.grid.length)				// If the two girds have different size.
			isIdentical=false;
		for(int row=0;row<grid.length;row++)					
		{
			for(int col=0;col<grid.length;col++)					
			{
				if(this.grid[row][col] != other.grid[row][col])			// Check every element in the two grids to see if they have any different element.
					isIdentical=false;
			}
		}
		return isIdentical;
	}
	
	/*
	* [Objective: Return true if a shift in the given direction is a valid move. The move is valid if it will cause any change at all in the grid. 
	* 			  A move is invalid if the grid will remain unchanged.]
	* [Input: 1 parameter: int direction]
	* [Output: A boolean showing if a shift in the given direction is a valid move.]
	* [Parameters: int direction]
	* [Value returned: A boolean]
	*/
	public boolean validMove(int direction)
	{
		return !equals(shift(direction));								
	}
	
	/*
	* [Objective: Return true if the game is over. The game is over if there are no empty places in the grid, and there are no valid moves.]
	* [Input: None]
	* [Output: A boolean showing if the game is over.]
	* [Parameters: None]
	* [Value returned: boolean isOver]
	*/
	public boolean gameOver()
	{
		boolean isOver;
		if((numEmpty()==0) && (!validMove(1)) && (!validMove(0)) && (!validMove(3)) && (!validMove(5)))		
																		// If there are no empty places in the grid, and moves in all four directions are invalid.
		{
			isOver=true;
		}
		else															// If there is empty places in the grid or valid moves.
			isOver=false;
		return isOver;
	}
	
	/*
	* [Objective: Return grid for other classes to use.]
	* [Input: None]
	* [Output: grid]
	* [Parameters: None]
	* [Value returned: grid]
	*/
	public int[][] getMatrix()
	{
		return grid;
	}
}
