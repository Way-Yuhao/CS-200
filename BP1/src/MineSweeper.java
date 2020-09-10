//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:           BIG PROGRAM 1 
// Files:           MineSweeper.java, Config.java, TestMineSweeper.java
// Course:          CS 200
//
// Author:          Yuhao Liu
// Email:           liu697@wisc.edu
// Lecturer's Name: Jim Williams
//
////////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name:    Huifeng Su
// Partner Email:   hsu56@wisc.edu
// Lecturer's Name: Jim Williams
// 
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
//   x___ Write-up states that pair programming is allowed for this assignment.
//   x___ We have both read and understand the course Pair Programming Policy.
//   x___ We have registered our team prior to the team registration deadline.
//
///////////////////////////// CREDIT OUTSIDE HELP /////////////////////////////
//
// Students who get help from sources other than their partner must fully 
// acknowledge and credit those sources of help here.  Instructors and TAs do 
// not need to be credited here, but tutors, friends, relatives, room mates 
// strangers, etc do.  If you received no outside help from either type of 
// source, then please explicitly indicate NONE.
//
// Persons:         None
// Online Sources:  None
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////

import java.util.Random;
import java.util.Scanner;


	/* This class contains MineSweeper game whose goal is to uncover (sweep) 
	 * all the locations that do not contain a mine. To win the game,
	 * sweep all locations and leave only the mines unswept. If you sweep a
	 * location that contains a mine, then you lose the game. The location 
	 * of the mines is discovered using logic. Selecting a location will 
	 * sweep that location showing what was hidden. Locations contain blanks,
	 * number 1-8 or mines. A blank means no neighboring (8 touching locations)
	 * contains a mine. A number indicates the number of the neighboring
	 * locations that contain mines.
	 */

public class MineSweeper {

    /**
     * This is the main method for Mine Sweeper game!
     * This method contains the within game and play again loops and calls
     * the various supporting methods.
     *  
     * @param args (unused)
     */
	
    public static void main(String[] args) {
    		Scanner scnr = new Scanner(System.in);
    		Random randGen = new Random(Config.SEED);
    		String mapWidthBoundryPrompt = "What width of map would you like (" + Config.MIN_SIZE +
    				" - " + Config.MAX_SIZE + "): ";
    		String mapHeightBoundryPrompt = "What height of map would you like (" + Config.MIN_SIZE +
    				" - " + Config.MAX_SIZE + "): ";
    		String rowPrompt = "row: ";
    		String columnPrompt = "column: ";
    		int nearbyMine;
    		char userResponse;
    		String userResponseStr;
    		boolean playAgain = false;
    		boolean allMinesSwept = false;
    		boolean gameLost = false;
    		
    		//Print welcome message
    		System.out.println("Welcome to Mine Sweeper!");
    		do {
	    		//Call promptUser to get the width and height of the map
	    		int mapWidth = promptUser(scnr, mapWidthBoundryPrompt, 
	    				Config.MIN_SIZE, Config.MAX_SIZE); 
	    		int mapHeight = promptUser(scnr, mapHeightBoundryPrompt, 
	    				Config.MIN_SIZE, Config.MAX_SIZE);
	    		int numMines;
	    			
	    		//create a 2 dimensional array of chat for the map.
    			char [][] mapArray = new char [mapHeight][mapWidth];
    			//create a 2 dimensional array to store the locations of mines;
    			boolean [][] mineArray = new boolean [mapHeight][mapWidth];
    		
    			//call method eraseMap() to initialize the map
    			eraseMap(mapArray);
    			//place mines
    			numMines = placeMines(mineArray, randGen);
   		
    			//game loop
    			do {
    				gameLost = false;
    				/*call method to place mines within the map, and then assign 
    				 * the total number of mines to numMines. Then print out numMines.
        			 */
        			System.out.println("\nMines: " + numMines);
        			//call method simplePrintMap() to print out the map
        			printMap(mapArray);
        			
	    			//call promptUser() to ask the user for the location (row & column) to sweep
	    			int row_to_sweep = promptUser(scnr, rowPrompt, 1, mapHeight) - 1;
	    			int column_to_sweep = promptUser(scnr, columnPrompt, 1, mapWidth) - 1;
	    			//sweep this location
	    			nearbyMine = sweepLocation(mapArray, mineArray, row_to_sweep, column_to_sweep);
	    			switch (nearbyMine) {
		    			case -3:
		    				break;
		    			case -2:
		    				break;
		    			case -1:
		    				gameLost = true; 
		    				break;
		    			case 0:
		    				sweepAllNeighbors(mapArray, mineArray, row_to_sweep, column_to_sweep);
		    				break;
		    			default:
		    				break;
	    			}
	    			
	    			//check if there are any unswept mines; 
	    			//if there are none, assign true to allMinesSwpet 
	    			allMinesSwept = allSafeLocationsSwept(mapArray, mineArray);		
    			} while (!(allMinesSwept || gameLost)); 
    			
    			//call showMines to  show all the mines on the map.
			showMines(mapArray, mineArray);
    			printMap(mapArray);
    			if (allMinesSwept) {
    				System.out.println("You Win!");
    			} else {
    				System.out.println("Sorry, you lost.");
    			}
    			
    			//continue new game loop while the user wants to keep playing
    			System.out.print("Would you like to play again (y/n)? ");
    			userResponseStr = scnr.nextLine().trim();
    			if (userResponseStr.length() == 0) {
    				playAgain = false;
    			} else {
    				userResponse = userResponseStr.charAt(0);
    				if (userResponse == 'y' || userResponse == 'Y') {
    					playAgain = true;
    				} else {
    				playAgain = false;
    				}
    			}
    		} while (playAgain); //if true, generate a new game
    		//when done playing, show the Thank you message.
    		System.out.println("Thank you for playing Mine Sweeper!");
    }


    /**
     * This method prompts the user for a number, verifies that it is between min
     * and max, inclusive, before returning the number.  
     * 
     * If the number entered is not between min and max then the user is shown 
     * an error message and given another opportunity to enter a number.
     * If min is 1 and max is 5 the error message is:
     *      Expected a number from 1 to 5.  
     * 
     * If the user enters characters, words or anything other than a valid int then 
     * the user is shown the same message.  The entering of characters other
     * than a valid int is detected using Scanner's methods (hasNextInt) and
     * does not use exception handling.
     * 
     * Do not use constants in this method, only use the min and max passed
     * in parameters for all comparisons and messages.
     * Do not create an instance of Scanner in this method, pass the reference
     * to the Scanner in main, to this method.
     * The entire prompt should be passed in and printed out.
     *
     * @param in  The reference to the instance of Scanner created in main.
     * @param prompt  The text prompt that is shown once to the user.
     * @param min  The minimum value that the user must enter.
     * @param max  The maximum value that the user must enter.
     * @return The integer that the user entered that is between min and max, 
     *          inclusive.
     */
    public static int promptUser(Scanner in, String prompt, int min, int max) {
    		//printing out prompt
    		System.out.print(prompt);
    		int userVal;
    		
    		do {
    			if (in.hasNextInt()) {
    				userVal = in.nextInt();
    				//check if userVal is within the desired boundary
    				if ((userVal < min) || (userVal > max)) {
    					//if number is invalid, prompt the user the enter a valid number
        				System.out.println("Expected a number from " + min + " to " + max + ".");
        				in.nextLine();
        			} else {
        				in.nextLine();
        				break;
        			}
    			} else { 
    				//if encountered illegal characters, prompt the user the enter a valid number
    				System.out.println("Expected a number from " + min + " to " + max + ".");
    				in.nextLine();
    			}
    		} while (3 > 2); //infinite loop until user enters a valid number
    		
        return userVal; 
    }

    
    /**
     * This initializes the map char array passed in such that all
     * elements have the Config.UNSWEPT character.
     * Within this method only use the actual size of the array. Don't
     * assume the size of the array.
     * This method does not print out anything. This method does not
     * return anything.
     * 
     * @param map An allocated array. After this method call all elements
     *      in the array have the same character, Config.UNSWEPT. 
     */
    public static void eraseMap(char[][] map) {
    		int i = 0;
    		int j = 0;
    		for (i = 0; i < map.length; ++i) {
    			for (j = 0; j < map[i].length; ++j) {
    				//fill in each cell with character '.'
    				map[i][j] = Config.UNSWEPT;
    			}
    		}
    	return;
    }    

    
    /**
     * This prints out a version of the map without the row and column numbers.
     * A map with width 4 and height 6 would look like the following: 
     *  . . . .
     *  . . . .
     *  . . . .
     *  . . . .
     *  . . . .
     *  . . . .
     * For each location in the map a space is printed followed by the 
     * character in the map location.
     * @param map The map to print out.
     */
    public static void simplePrintMap(char[][] map) {
    		int i = 0;
		int j = 0;
		for (i = 0; i < map.length; ++i) {
			for (j = 0; j < map[i].length; ++j) {
				//using nested loops to print out the entire map
				System.out.print(" " + map[i][j]);
			}
			System.out.println("");
		}
        return; 
    }

    
    /**
     * This prints out the map. This shows numbers of the columns
     * and rows on the top and left side, respectively. 
     * map[0][0] is row 1, column 1 when shown to the user.
     * The first column, last column and every multiple of 5 are shown.
     * 
     * To print out a 2 digit number with a leading space if the number
     * is less than 10, you may use:
     *     System.out.printf("%2d", 1); 
     * 
     * @param map The map to print out.
     */
    public static void printMap(char[][] map) {
    		int row = 0;
		int col = 0;
		int rowLength = map[0].length;
		for (row = -1; row < map.length; ++row) {
			
			for (col = -1; col < rowLength; ++col) {
				if ((row==-1) && (col == -1)) {
					//Printing out upper left corner
					System.out.print("  ");
				} else if (row == -1)  {
				//printing out horizontal boundary
					if ((((col + 1) % 5) == 0) || col == 0){
						//printing out numbers within the horizontal boundary
						System.out.printf("%2d", (col + 1)); 
					} else if (col + 1== rowLength){
						//printing out the last number of columns
						System.out.printf("%2d", (col + 1)); 
					} else {
						//printing out dashes within the horizontal boundary
						System.out.print("--");
					}
				} else if (col == -1) {
					//printing out left boundary
					if ((((row + 1) % 5) == 0) || row == 0) {
						System.out.printf("%2d", (row + 1)); 
					} else if (row + 1 == map.length) {
						//printing out the last number of rows
						System.out.printf("%2d", (row + 1)); 
					} else {
						//printing out seperators
						System.out.print(" |");
					}
				} else {
					//printing out map
					System.out.print(" " + map[row][col]);
				}
			}
			System.out.println("");
		}
        return; 
    }

    
    /**
     * This method initializes the boolean mines array passed in. A true value for
     * an element in the mines array means that location has a mine, false means
     * the location does not have a mine. The MINE_PROBABILITY is used to determine
     * whether a particular location has a mine. The randGen parameter contains the
     * reference to the instance of Random created in the main method.
     * 
     * Access the elements in the mines array with row then column (e.g., mines[row][col]).
     * 
     * Access the elements in the array solely using the actual size of the mines
     * array passed in, do not use constants. 
     * 
     * A MINE_PROBABILITY of 0.3 indicates that a particular location has a
     * 30% chance of having a mine.  For each location the result of
     *      randGen.nextFloat() < Config.MINE_PROBABILITY 
     * determines whether that location has a mine.
     * 
     * This method does not print out anything.
     *  
     * @param mines  The array of boolean that tracks the locations of the mines.
     * @param randGen The reference to the instance of the Random number generator
     *      created in the main method.
     * @return The number of mines in the mines array.
     */
    public static int placeMines(boolean[][] mines, Random randGen) {
    		int mineCount = 0;
    		for (int i = 0; i < mines.length; i++) {
    			for (int j = 0; j < mines[i].length; j++) {
    				//using nested loops to proceed on each cell
    				//determines whether to place a mine in this cell based on 20% probability
    				if (randGen.nextFloat() < Config.MINE_PROBABILITY) {
    					mines[i][j] = true;
    					mineCount++; //if a mine is placed, increase mineCount by 1
    				} else {
    					mines[i][j] = false;
    				}
    			}
    		}
        return mineCount;
    }

    
    /**
     * This method returns the number of mines in the 8 neighboring locations.
     * For locations along an edge of the array, neighboring locations outside of
     * the mines array do not contain mines. 
     * This method does not print out anything.
     * 
     * If the row or col arguments are outside the mines array, then return -1.
     * This method (or any part of this program) should not use exception handling.
     * 
     * @param mines The array showing where the mines are located.
     * @param row The row, 0-based, of a location.
     * @param col The col, 0-based, of a location.
     * @return The number of mines in the 8 surrounding locations or -1 if row or col
     *      are invalid.
     */
    public static int numNearbyMines( boolean [][]mines, int row, int col) {
    	
    	//If the row or col arguments are outside the mines array, then return -1;
    	if (row >= mines.length || row < 0 || col > mines[0].length - 1 || col < 0) {
    		return -1;
    	}
    	
    	//Declare variables that would be used in the checking process;
    int minesCount = 0;   
    int rowToCheck;
    int colToCheck;
    
    //Declare and popularize all coordinates that need to be checked;
    	int[][] coordinateToCheck = {   {row - 1, col - 1}, 
    		   							{row - 1, col    },
    		   							{row - 1, col + 1},
    		   							{row    , col - 1},
    		   							{row    , col + 1},
    		   							{row + 1, col - 1},
    		   							{row + 1, col    },
    		   							{row + 1, col + 1}};
    	
    	//Use a FOR loop to examine if there is mine at each location;
    	for (int i = 0; i < coordinateToCheck.length; ++i ) {
    		 rowToCheck = coordinateToCheck[i][0];
    		 colToCheck = coordinateToCheck[i][1];
    		 if ((rowToCheck < 0)|| (colToCheck < 0) || 
    				 (rowToCheck > mines.length - 1) || (colToCheck > mines[0].length - 1)) {
    			 continue; //if such location is outside of the boundary, continue;
    		 } else {
    			if (mines[rowToCheck][colToCheck])
    				minesCount++; //if a mine is present, add 1 to mineCount; 
    		}
    	}
    	return minesCount;
    }

    
    /**
     * This updates the map with each unswept mine shown with the Config.HIDDEN_MINE
     * character. Swept mines will already be mapped and so should not be changed.
     * This method does not print out anything.
     * 
     * @param map  An array containing the map. On return the map shows unswept mines.
     * @param mines An array indicating which locations have mines.  No changes
     *      are made to the mines array.
     */
    
    public static void showMines(char[][] map, boolean[][] mines) {
        for (int i = 0; i < map.length; ++i) {
        		for (int j = 0; j < map[i].length; ++j) {
        			/*if the coordinate is both unswept and has mine, 
        			 *mark the coordinate as an unswept mine using Config.Unswept. */
        			if (mines[i][j] && map[i][j] == Config.UNSWEPT) { 
        				map[i][j] = Config.HIDDEN_MINE;        				
        			}
        		}        	
        }    	
    		return; 
    }

    
    /**
     * Returns whether all the safe (non-mine) locations have been swept. In 
     * other words, whether all unswept locations have mines. 
     * This method does not print out anything.
     * 
     * @param map The map showing touched locations that is unchanged by this method.
     * @param mines The mines array that is unchanged by this method.
     * @return whether all non-mine locations are swept.
     */
    public static boolean allSafeLocationsSwept(char[][] map, boolean[][] mines) {
    		boolean mineIndicator = true;
    		for (int i = 0; i < map.length; i++) {
    			for (int j = 0; j < map[i].length; j++) {
    				if((!mines[i][j]) && (map[i][j] == Config.UNSWEPT)){
    					//if there is any location where it has unswept mine, 
    					//assign false to indicator
    					mineIndicator = false;
    				}
    			}
    		}
        return mineIndicator; 
    }

    
    /**
     * This method sweeps the specified row and col.
     *   - If the row and col specify a location outside the map array then 
     *     return -3 without changing the map.
     *   - If the location has already been swept then return -2 without changing
     *     the map.
     *   - If there is a mine in the location then the map for the corresponding
     *     location is updated with Config.SWEPT_MINE and return -1.
     *   - If there is not a mine then the number of nearby mines is determined 
     *     by calling the numNearbyMines method. 
     *        - If there are 1 to 8 nearby mines then the map is updated with 
     *          the characters '1'..'8' indicating the number of nearby mines.
     *        - If the location has 0 nearby mines then the map is updated with
     *          the Config.NO_NEARBY_MINE character.
     *        - Return the number of nearbyMines.
     *        
     * @param map The map showing swept locations.
     * @param mines The array showing where the mines are located.
     * @param row The row, 0-based, of a location.
     * @param col The col, 0-based, of a location.
     * @return the number of nearby mines, -1 if the location is a mine, -2 if 
     * the location has already been swept, -3 if the location is off the map.
     */
    public static int sweepLocation(char[][] map, boolean[][] mines, int row, int col) {
       int index = 0;
    		//return -3 if row and col specify a location outside the map array.
       if ((row < 0) || (row > (map.length -1)) ||
    		   		(col < 0) || (col > (map[row].length - 1))) {
    	   		index = -3;
       }
       //If the location has already been swept then return -2
       else if ((!mines[row][col]) && (map[row][col] != Config.UNSWEPT)) {
    			index = -2;
    		}
    		//If there is a mine, return -1
       else if(mines[row][col]) {
    	   		//update the map
    	   		map[row][col] = Config.SWEPT_MINE; 
    			index = -1;
    		}
    		//if no mines are present at that location
       else {
    	   		index = numNearbyMines(mines, row, col);
    	   		if (index == 0){ //if there are no mines nearby
    	   			//Update the map with Config.No_NEARBY_MINE character
    	   			map[row][col] = Config.NO_NEARBY_MINE; 
    	   		} else { //if there is one or more nearby mines
    	   			//Update the map to indicate the number of nearby mines
    	   			map[row][col] = (char) (index + 48); 
    	   		}
       }
    		
    		return index;
    } 
    	

    /**
     * This method iterates through all 8 neighboring locations and calls sweepLocation
     * for each. It does not call sweepLocation for its own location, just the neighboring
     * locations.
     * @param map The map showing touched locations.
     * @param mines The array showing where the mines are located.
     * @param row The row, 0-based, of a location.
     * @param col The col, 0-based, of a location.
     */
    public static void sweepAllNeighbors(char [][]map, boolean[][] mines, int row, int col) {
    		int rowToCheck;
    		int colToCheck;
    		int numMinesNearby = -99;
    		//declare a array containing the coordinates of 8 neighboring locations
	    	int[][] coordinateToCheck = {{row - 1, col - 1}, 
									{row - 1, col    },
									{row - 1, col + 1},
									{row    , col - 1},
									{row    , col + 1},
									{row + 1, col - 1},
									{row + 1, col    },
									{row + 1, col + 1}};
	    	
	    	for (int i = 0; i < coordinateToCheck.length; ++i ) {
	    		 rowToCheck = coordinateToCheck[i][0];
	    		 colToCheck = coordinateToCheck[i][1];
	    		 if ((rowToCheck < 0)|| (colToCheck < 0) || 
	    				 (rowToCheck > mines.length - 1) || (colToCheck > mines[0].length - 1)) {
	    			 continue; //if such location is outside of the boundary, continue;
	    		 } else {
	    			numMinesNearby = sweepLocation(map, mines, rowToCheck, colToCheck );
	    			if (numMinesNearby == 0) {
	    				//To activate function for the optional assignment, uncomment this line
	    				sweepAllNeighbors(map, mines, rowToCheck, colToCheck); 
	    			}
	    		}
	    	}
        return; 
    } 
} 