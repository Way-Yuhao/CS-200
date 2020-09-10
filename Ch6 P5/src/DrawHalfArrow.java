//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:           Draw Half Arrow
// Files:           DrawHalfArrow.java
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

public class DrawHalfArrow {
   public static void main(String[] args) {
      Scanner scnr = new Scanner(System.in);
      int arrowBaseHeight = 0;
      int arrowBaseWidth  = 0;
      int arrowHeadWidth = 0;

      int i = 0;
      int j = 0;

      System.out.print("Enter arrow base height: ");
      arrowBaseHeight = scnr.nextInt();

      System.out.print("Enter arrow base width: ");
      arrowBaseWidth = scnr.nextInt();

      System.out.print("Enter arrow head width: ");
      arrowHeadWidth = scnr.nextInt();

      //make sure arrow head width is larger than base width
      
      while (arrowHeadWidth <= arrowBaseWidth) {
    	  	System.out.print("Enter arrow head width: ");
    	  	arrowHeadWidth = scnr.nextInt();
      }

      //draw arrow base 
      System.out.print("\n");
      for (i = 1; i <= arrowBaseHeight; i++) {
    	  	for (j = 1; j <= arrowBaseWidth; j++) {
    	  		System.out.print("*");
    	  	}
    	  	System.out.print("\n");
      }

      //draw arrow head
      for (i = arrowHeadWidth; i >= 1; i--) {
    	  	for (j = i; j >= 1; j--) {
    	  		System.out.print("*");
    	  	}
    	  	System.out.print("\n");
      }

      scnr.close();
      return;
   }
}