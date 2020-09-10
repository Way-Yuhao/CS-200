/**
 * This file contains testing methods for the MineSweeper project.
 * These methods are intended to serve several objectives:
 * 1) provide an example of a way to incrementally test your code
 * 2) provide example method calls for the MineSweeper methods
 * 3) provide examples of creating, accessing and modifying arrays
 *    
 * Toward these objectives, the expectation is that part of the 
 * grade for the MineSweeper project is to write some tests and
 * write header comments summarizing the tests that have been written. 
 * Specific places are noted with FIXME but add any other comments 
 * you feel would be useful.
 * 
 * Some of the provided comments within this file explain
 * Java code as they are intended to help you learn Java.  However,
 * your comments and comments in professional code, should
 * summarize the purpose of the code, not explain the meaning
 * of the specific Java constructs.
 *    
 */

import java.util.Random;
import java.util.Scanner;


/**
 * This class contains a few methods for testing methods in the MineSweeper
 * class as they are developed. These methods are all private as they are only
 * intended for use within this class.
 * 
 * @author Jim Williams
 * @author FIXME add your name here when you add tests and comment the tests
 *
 */
public class TestMineSweeper {

    /**
     * This is the main method that runs the various tests. Uncomment the tests
     * when you are ready for them to run.
     * 
     * @param args  (unused)
     */
    public static void main(String [] args) {

        // Milestone 1
        //testing the main loop, promptUser and simplePrintMap, since they have
        //a variety of output, is probably easiest using a tool such as diffchecker.com
        //and comparing to the examples provided.
        //testEraseMap();
        
        // Milestone 2
        //testPlaceMines();
        //testNumNearbyMines();
        //testShowMines();
        //testAllSafeLocationsSwept();
        
        // Milestone 3
    		//testPrintMap();
        //testSweepLocation();
        testSweepAllNeighbors();
        //testing printMap, due to printed output is probably easiest using a 
        //tool such as diffchecker.com and comparing to the examples provided.
    }
    
    /**
     * This is intended to run some tests on the eraseMap method. 
     * 1. FIXME describe each test in your own words. 
     * 2.
     */
    private static void testEraseMap() {
        //Review the eraseMap method header carefully and write
        //tests to check whether the method is working correctly.
        boolean error = false;
        char[][]map = new char [3][4];
        MineSweeper.eraseMap(map);
        if (map[1][2] != Config.UNSWEPT ) {
        		error = true;
        	} else {
        		System.out.print("All tests passed");
        		}
        
    		System.out.println("Need to write testEraseMap tests.");
    }      
    
    /*
     * Calculate the number of elements in array1 with different values from 
     * those in array2
     */
    private static int getDiffCells(boolean[][] array1, boolean[][] array2) {
        int counter = 0;
        for(int i = 0 ; i < array1.length; i++){
            for(int j = 0 ; j < array1[i].length; j++){
                if(array1[i][j] != array2[i][j]) 
                    counter++;
            }
        }
        return counter;
    }    
    
    /**
     * This runs some tests on the placeMines method. 
     * 1. FIXME describe each test in your own words. 
     * 2.
     */
    private static void testPlaceMines() {
        boolean error = false;
        
        //These expected values were generated with a Random instance set with
        //seed of 123 and MINE_PROBABILITY is 0.2.
        boolean [][] expectedMap = new boolean[][]{
            {false,false,false,false,false},
            {false,false,false,false,false},
            {false,false,false,true,true},
            {false,false,false,false,false},
            {false,false,true,false,false}};
        int expectedNumMines = 3;
            
        Random studentRandGen = new Random( 123);
        boolean [][] studentMap = new boolean[5][5];
        int studentNumMines = MineSweeper.placeMines( studentMap, studentRandGen);
        
        if ( studentNumMines != expectedNumMines) {
            error = true;
            System.out.println("testPlaceMines 1: expectedNumMines=" + expectedNumMines + " studentNumMines=" + studentNumMines);
        }
        int diffCells = getDiffCells( expectedMap, studentMap);
        if ( diffCells != 0) {
            error = true;
            System.out.println("testPlaceMines 2: mine map differs.");
        }

        // Can you think of other tests that would make sure your method works correctly?
        // if so, add them.

        if (error) {
            System.out.println("testPlaceMines: failed");
        } else {
            System.out.println("testPlaceMines: passed");
        }        
        
    }
    
    /**This runs some tests on the PrintMap method
     * 1. FIXME describe this (*#I&($*
     * 
     */
    private static void testPrintMap() {
	    
      	char [][] map = new char [][] 
      			{{Config.UNSWEPT, Config.UNSWEPT, Config.UNSWEPT, Config.UNSWEPT, Config.UNSWEPT, Config.UNSWEPT, Config.UNSWEPT},
      		{Config.UNSWEPT, Config.UNSWEPT, Config.UNSWEPT, Config.UNSWEPT, Config.UNSWEPT, Config.UNSWEPT, Config.UNSWEPT},
      		{Config.UNSWEPT, Config.UNSWEPT, Config.UNSWEPT, Config.UNSWEPT, Config.UNSWEPT, Config.UNSWEPT, Config.UNSWEPT},
      		{Config.UNSWEPT, Config.UNSWEPT, Config.UNSWEPT, Config.UNSWEPT, Config.UNSWEPT, Config.UNSWEPT, Config.UNSWEPT},
      		{Config.UNSWEPT, Config.UNSWEPT, Config.UNSWEPT, Config.UNSWEPT, Config.UNSWEPT, Config.UNSWEPT, Config.UNSWEPT},
      		{Config.UNSWEPT, Config.UNSWEPT, Config.UNSWEPT, Config.UNSWEPT, Config.UNSWEPT, Config.UNSWEPT, Config.UNSWEPT},
      		{Config.UNSWEPT, Config.UNSWEPT, Config.UNSWEPT, Config.UNSWEPT, Config.UNSWEPT, Config.UNSWEPT, Config.UNSWEPT},
      		{Config.UNSWEPT, Config.UNSWEPT, Config.UNSWEPT, Config.UNSWEPT, Config.UNSWEPT, Config.UNSWEPT, Config.UNSWEPT}};
      	MineSweeper.printMap(map);
    }
    /**
     * 
     * This runs some tests on the numNearbyMines method. 
     * 1. FIXME describe each test in your own words. 
     * 2.
     */
    private static void testNumNearbyMines() {
        boolean error = false;

        boolean [][] mines = new boolean[][]{
            {false,false,true,false,false},
            {false,false,false,false,false},
            {false,true,false,true,true},
            {false,false,false,false,false},
            {false,false,true,false,false}};
        int numNearbyMines = MineSweeper.numNearbyMines( mines, 1, 1);
        
        if ( numNearbyMines != 2) {
            error = true;
            System.out.println("testNumNearbyMines 1: numNearbyMines=" + numNearbyMines + " expected mines=2");
        }
        
       numNearbyMines = MineSweeper.numNearbyMines( mines, 2, 1);
        
        if ( numNearbyMines != 0) {
            error = true;
            System.out.println("testNumNearbyMines 2: numNearbyMines=" + numNearbyMines + " expected mines=0");
        }        
        
        // Can you think of other tests that would make sure your method works correctly?
        // if so, add them.

        if (error) {
            System.out.println("testNumNearbyMines: failed");
        } else {
            System.out.println("testNumNearbyMines: passed");
        }
    }
    
    /**
     * This runs some tests on the showMines method. 
     * 1. FIXME describe each test in your own words. 
     * 2.
     */
    private static void testShowMines() {
        boolean error = false;
        

        boolean [][] mines = new boolean[][]{
            {false,false,true,false,false},
            {false,false,false,false,false},
            {false,true,false,false,false},
            {false,false,false,false,false},
            {false,false,true,false,false}};
            
        char [][] map = new char[mines.length][mines[0].length];
        map[0][2] = Config.UNSWEPT;
        map[2][1] = Config.UNSWEPT;
        map[4][2] = Config.UNSWEPT;
        
        MineSweeper.showMines( map, mines);
        if ( !(map[0][2] == Config.HIDDEN_MINE && map[2][1] == Config.HIDDEN_MINE && map[4][2] == Config.HIDDEN_MINE)) {
            error = true;
            System.out.println("testShowMines 1: a mine not mapped");
        }
        if ( map[0][0] == Config.HIDDEN_MINE || map[0][4] == Config.HIDDEN_MINE || map[4][4] == Config.HIDDEN_MINE) {
            error = true;
            System.out.println("testShowMines 2: unexpected showing of mine.");
        }
        
        // Can you think of other tests that would make sure your method works correctly?
        // if so, add them.

        if (error) {
            System.out.println("testShowMines: failed");
        } else {
            System.out.println("testShowMines: passed");
        }        
    }    
    
    /**
     * This is intended to run some tests on the allSafeLocationsSwept method.
     * 1. FIXME describe each test in your own words. 
     * 2.
     */
    private static void testAllSafeLocationsSwept() {
        //Review the allSafeLocationsSwept method header carefully and write
        //tests to check whether the method is working correctly.
        System.out.println("Need to write testAllSafeLocationsSwept tests.");
    }      

    
    /**
     * This is intended to run some tests on the sweepLocation method. 
     * 1. FIXME describe each test in your own words. 
     * 2.
     */
    private static void testSweepLocation() {
    		boolean error = false;
        boolean [][] mines= new boolean[][]
        		  {{false, true, false, false},
        			{false, false, true, false},
        			{true, false, false, false},
        			{false, false, false, false} };
        	char [][] map = new char [][] 
        			{{Config.UNSWEPT, Config.UNSWEPT, Config.UNSWEPT, '1'},
        			{Config.UNSWEPT, Config.UNSWEPT, Config.UNSWEPT, Config.UNSWEPT},
        			{Config.UNSWEPT, Config.UNSWEPT, Config.UNSWEPT, Config.UNSWEPT},
        			{Config.UNSWEPT, Config.UNSWEPT, Config.UNSWEPT, Config.NO_NEARBY_MINE}};
        			
        	int result1 = MineSweeper.sweepLocation(map, mines, 1, 1);
        	int result2 = MineSweeper.sweepLocation(map, mines, 3, 2);
        	int result3 = MineSweeper.sweepLocation(map, mines, 3, 3);
        	int result4 = MineSweeper.sweepLocation(map, mines, 4, 0);
        	if (result1 != 3) {
        		error = true;
            System.out.println("testSweepLocation1: index=" + result1 + " expected index = 0");
        	}
        	if (result2 != 0) {
        		error = true;
            System.out.println("testSweepLocation2: index=" + result2 + " expected index = 0");
        	}
        	if (result3 != -2) {
        		error = true;
            System.out.println("testSweepLocation3: index=" + result3 + " expected index = -2");
        	}
        	if (result4 != -3) {
        		error = true;
            System.out.println("testSweepLocation1: index=" + result4 + " expected index = -3");
        	}
        	if (error == false) {
        		System.out.println("All tests passed.");
        	}
        	MineSweeper.simplePrintMap(map);
    }
    
    /**
     * This is intended to run some tests on the sweepAllNeighbors method. 
     * 1. FIXME describe each test in your own words. 
     * 2.
     */
    private static void testSweepAllNeighbors() {
        boolean error = false;
        char [][] map = new char [][]
        		{{Config.UNSWEPT, Config.UNSWEPT, Config.UNSWEPT, Config.UNSWEPT},
        		{Config.UNSWEPT, Config.UNSWEPT, Config.UNSWEPT, Config.UNSWEPT},
        		{Config.UNSWEPT, Config.UNSWEPT, Config.UNSWEPT, Config.UNSWEPT}};
        	boolean mines[][] = new boolean [][] 
        			{{false, false, false, true},
        			{false, false, false, false},
        			{false, false, false, false}};
        			
        	MineSweeper.sweepAllNeighbors(map, mines, 2, 2);
        	MineSweeper.simplePrintMap(map);
        	
        	char [][] expectedMap = new char [][]
        			{{Config.UNSWEPT, Config.UNSWEPT, Config.UNSWEPT, Config.UNSWEPT},
        			{Config.UNSWEPT, Config.NO_NEARBY_MINE, '1', '1'},
        			{Config.UNSWEPT, Config.NO_NEARBY_MINE, Config.NO_NEARBY_MINE, Config.NO_NEARBY_MINE}};
        	
        	System.out.println(map);
        System.out.println(expectedMap);		
        	
        	return;
    }      
}