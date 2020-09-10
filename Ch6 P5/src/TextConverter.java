//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:           Text Converter
// Files:           TextConverter.java
// Course:          CS 200
//
// Author:          Yuhao Liu
// Email:           liu697@wisc.edu
// Lecturer's Name: Jim Williams
//
///////////////////////////// CREDIT OUTSIDE HELP /////////////////////////////
//
// Students who get help from sources other than their partner must fully 
// acknowledge and credit those sources of help here.  Instructors and TAs do 
// not need to be credited here, but tutors, friends, relatives, room mates 
// strangers, etc do.  If you received no outside help from either type of 
// source, then please explicitly indicate NONE.
//
// Persons:         (identify each person and describe their help in detail)
// Online Sources:  (identify each URL and describe their assistance in detail)
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////
import java.util.Scanner;

public class TextConverter {

	/**
     * 1337 — convert the string to leet-speak:
     *   Replace each L or l with a 1 (numeral one)
     *   Replace each E or e with a 3 (numeral three)
     *   Replace each T or t with a 7 (numeral seven)
     *   Replace each O or o with a 0 (numeral zero)
     *   Replace each S or s with a $ (dollar sign)
     *    
     * @param current Original string
     * @return string converted to leet-speak.
     */
    public static String action1337(String current) {
            String [] oriChar1 = {"L", "E", "T", "O", "S"};
            String [] oriChar2 = {"l", "e", "t", "o", "s"};
            String [] convChar = {"1", "3", "7", "0", "$"};
            
            for (int i = 0; i < oriChar1.length; i++) {
            		while ((current.contains(oriChar1[i])) || (current.contains(oriChar2[i]))) {
            			current = current.replace(oriChar1[i], convChar[i]);
            			current = current.replace(oriChar2[i], convChar[i]);
            		}
            }
            return current;
    }

    /**
     * This reverses the order of characters in the current string. 
     * @param current  Original string to be reversed.
     * @return  The string in reversed order.
     */
    public static String actionReverse(String current) {
    			//char[] oriStr = new char[current.length()];
    			//char[] revStr = new char[current.length()];
    			
    			int j = 0;
    			String revStr = "";
    			for (int i = 0; i < current.length(); i++) {
    				j = current.length() - 1 - i;
    				revStr += current.charAt(j);
    			}
    			
            return revStr;
    }

    /**
     * Provides the main menu for the text converter and calls methods based
     * on menu options chosen.
     * @param args
     */
    
    
    public static void main(String[] args) {
    		/*Show Welcome message and menu
    		Prompt for text string
    		Loop until "quit"
        Prompt for action
        Call appropriate action method
    		End Loop */
    		Scanner scan = new Scanner(System.in);
    		System.out.println("Welcome to the Text Converter.\n" +
    							"Available Actions:\n" +
    							"  1337) convert to 1337-speak\n" +
    							"  rev) reverse the string\n" + 
    							"  quit) exit the program\n");
    		
    		System.out.print("Please enter a string: ");
    		String userStr = scan.nextLine();
    		
    		String userCmd = "";
    		 do {
    			System.out.print("Action (1337, rev, quit): ");
    			userCmd = scan.nextLine();
    			if (userCmd.equals("rev")) {
    				userStr = actionReverse(userStr);
    				System.out.println(userStr);
    			} else if (userCmd.equals("1337")) {
    				userStr = action1337(userStr);
    				System.out.println(userStr);
    			} else if (userCmd.contains("quit")) {
    				break;
    			} else {
    				System.out.println("Unrecognized action.");
    				System.out.println(userStr);
    			} 
    		} while (!userCmd.equals("quit"));
    		System.out.println("See you next time!");
    }
}