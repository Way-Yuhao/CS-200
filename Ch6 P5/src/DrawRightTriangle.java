//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:           Drawing a right triangle
// Files:           DrawRightTriangle.java
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

public class DrawRightTriangle {
	
	/*TODO: Extra new line before the triangle begins
	The last line of the triangle ends with a new line
	Program header
*/
	//method that prints the triangle 
	public static void buildTriangle(char triangleChar, int triangleHeight) {
		System.out.print("\n");
		int height = 1;
		int i = 0;
		while (height<= triangleHeight) {  //determining height
			for (i = 1; i<= height; i++) { //determining how many characters to print 
				System.out.print(triangleChar);
				System.out.print(" ");
				if (i == height)
					System.out.print("\n");
			}
			i = 1;
			height++;
		}
	}
	
	//methods that scans the height and check if the height is valid
	/*public static int scanHeight() {
		Scanner scnr = new Scanner(System.in);
		System.out.println("Enter triangle height (1-10): "); 
		int userHeight = scnr.nextInt(); 
		
		while (!((userHeight >= 1) && (userHeight <= 10))) {
			System.out.println("Please enter height between 1 and 10.");
			userHeight = scnr.nextInt();
		}
		scnr.close();
		return userHeight;
	}
	
	public static char scanChar() {
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter a character: ");
		char userChar = scan.next().charAt(0);
		scan.close();
		return userChar;
	} */
	
	
	public static void main(String[] args) {
		//char userChar;
		//int userHeight;
		
		//userChar = scanChar();
		//userHeight = scanHeight();
		//buildTriangle(userChar, userHeight);
		
		Scanner scan = new Scanner(System.in);
		System.out.print("Enter a character: ");
		char userChar = scan.next().charAt(0);
		
		System.out.print("Enter triangle height (1-10): "); 
		int userHeight = scan.nextInt(); 
		
		while (!((userHeight >= 1) && (userHeight <= 10))) {
			System.out.println("Please enter height between 1 and 10.");
			userHeight = scan.nextInt();
		}
		scan.close();
		
		buildTriangle(userChar, userHeight);
		return;
		
	}

}
