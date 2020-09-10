//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:           TextManipulator 
// Files:           TextManipulator.ajava, Config.java
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
// Persons:         
// Online Sources:  
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.io.PrintWriter;
//import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
//import java.math.*;

/*
 * This class contains a text-menu-driven program that reads text files and manipulates 
 * the input as directed by the user via the text menu. In addition, the program will 
 * both display the modifications on the screen and save them to a file.
 */

public class TextManipulator {

    /**
     * Matches the case of the original string to that of the template as follows. 
     *
     * 1) If the length of template is the same or longer than the length of the original, the 
     *    returned string will match the case of the prefix of the template up to the length of the
     *    original string. 
     *    For example:
     *         template: XXxxxXxX
     *         original: azertYU
     *         returns:  AZertYu
     *
     * 2) If the length of the template is shorter than the length of the original, the prefix of the
     *    returned string up to the length of the template will match the case of the template. The 
     *    remaining characters should all be lower case.
     *    For example: 
     *         template: WxCv
     *         original: azertYU
     *         returns:  AzErtyu
     *
     * @param template Template of which characters should be which case.
     * @param original String to change the case of, based on the template.
     * @return A string containing the characters of original with the case matching that of 
     *         template.
     */
    public static String matchCase(String template, String original) {
        //Begin match case process
    		boolean oriIsUpperCase = false;
    		boolean tempIsUpperCase = false;
    		boolean caseMatch = false;
    		String tempStr = "";
    		int startIndex, minLenth;
    		
    			minLenth = Math.min(template.length(), original.length());
	    		for (int i = 0; i < minLenth; i++) {
	    			if (Character.isUpperCase(template.charAt(i)))
	    				tempIsUpperCase = true;
	    			if (Character.isUpperCase(original.charAt(i)))
	    				oriIsUpperCase = true;
	    			if (tempIsUpperCase == oriIsUpperCase) 
	    				caseMatch = true;
	    			//if a certain char needs converting to lower case
	    			if ((!caseMatch) && oriIsUpperCase)
	    				tempStr += Character.toLowerCase(original.charAt(i));
	    			else if ((!caseMatch) && (!oriIsUpperCase))
	    			//if a certain char needs converting to upper case
	    				tempStr += Character.toUpperCase(original.charAt(i));
	    			//else, add the original char to the String as it
	    			else if (caseMatch)
	    				tempStr += original.charAt(i);
	    			//reset variables
	    			tempIsUpperCase = false;
	    			oriIsUpperCase = false;
	    			caseMatch = false;
	    		}
	    		//add the remaining string as lower case letters
	    		if (original.length() > template.length()) {
	    			startIndex = template.length();
	    			tempStr += original.substring(startIndex).toLowerCase();
    		}
	        return tempStr;
    }

    /**
     * Translates a word according to the data in wordList then matches the case. The parameter 
     * wordList contains the mappings for the translation. The data is organized in an ArrayList 
     * containing String arrays of length 2. The first cell (index 0) contains the word in the 
     * original language, called the key, and the second cell (index 1) contains the translation.
     *
     * It is assumed that the items in the wordList are sorted in ascending order according to 
     * the keys in the first cell. 
     *
     * @param word The word to translate.
     * @param wordList An ArrayList containing the translation mappings.          
     * @return The mapping in the wordList with the same case as the original. If no match is found
     *         in wordList, it returns a string of Config.LINE_CHAR of the same length as word.
     */
    public static String translate(String word, ArrayList<String[]> wordList) {
    		int index = 0;
    		String transWord = "";
    		boolean keyFound = false;
    		
    		//neglect punctuation
    		if (!Character.isLetter(word.charAt(0)))
    			return word;
        //search for key
    		do {
    			if (index >= wordList.size())
    				break;
    			if (word.toLowerCase().equals(wordList.get(index)[0]))
    				keyFound = true;
    			else 
    				index++;
    		} while (!keyFound);
    		
    		//when found
    		if (keyFound) {
    			transWord = wordList.get(index)[1];
    			//match the case of translated word to that of the original word
    			transWord = matchCase(word, transWord);
    		} else {//if translation is missing 
    			for (int i = 0; i < word.length(); i++) {
    				transWord += Config.LINE_CHAR;
    			}
    		}
        return transWord;
    }

    /**
     * Converts a string to simplified Pig Latin then matching the case. The rules for simplified Pig 
     * Latin are as follows:
     * 1) If the word begins with a vowel (a, e, i, o, u, or y), then the string "way" is appended. 
     * 2) If the word begins with a consonant (any letter that is not a vowel as defined above), then
     *    the group of consonants at the beginning of the word are moved to the end of the string,
     *    and then the string "ay" is appended.
     * 3) If the word begins with any other character, the original string is returned.
     * Note 1: 'y' is always considered a vowel.
     * Note 2: An apostrophe that is not the first character of a word is treated as a consonant. 
     *
     * Some examples:
     *   Pig -> Igpay
     *   Latin -> Atinlay
     *   Scram -> Amscray
     *   I'd -> I'dway
     *   you -> youway
     *   crypt -> yptcray 
     *
     * @param word The word to covert to simplified Pig Latin.
     * @return The simplified Pig Latin of the parameter word with matching case.
     */
    public static String pigLatin(String word) {
        char firstChar;
        boolean firstCharisVowel = false;
        String modWord = word;
        //such index is to prevent infinite loop
        int index = 1;
        //read the first character and convert it to lower case.
        firstChar = Character.toLowerCase(word.charAt(0));
        switch (firstChar) {
        case 'a':
        case 'e':
        case 'i':
        case 'o':
        case 'u':
        case 'y':
        		//if the word begins with a vowel
        		modWord += "way";
        		break;
        	default:
        		//if the word begins with a consonant
        		if (Character.isAlphabetic(firstChar) || (firstChar == '\'')) {
        			do {
        				//if having iterated though the entire word
        				if (index == word.length())
        					//break out of the loop
        					firstCharisVowel = true;
        				else 
        					index++;
        				
        				switch (firstChar) {
        		        case 'a':
        		        case 'e':
        		        case 'i':
        		        case 'o':
        		        case 'u':
        		        case 'y':
        		        		//when encounters an vowel, loop terminates
        		        		firstCharisVowel = true;
        		        		break;
        		        	default:
        		        		//else, move the first letter to the end
        		        		modWord = modWord.substring(1) + firstChar;
        		        		//reset the first char
        		        		firstChar = modWord.charAt(0);
        				}
        			} while (!firstCharisVowel);
        			modWord += "ay";
	        		modWord = matchCase(word, modWord);
        		} else {
        			modWord = word;
        		}
        }
        return modWord;
    }

    /**
     * Reverses a String, then matches the case.
     * For example: aZErty returns yTReza
     *
     * @param word The String to reverse.
     * @return The reverse of word with matching case.
     */
    public static String reverse(String word) {
        String revWord = "";
        for (int i = 0; i < word.length(); i++) {
        		//add each character within word to revWord in reversed order
        		revWord += word.charAt(word.length() - 1 - i);
        }
        revWord = matchCase(word, revWord);
        return revWord;
    }

    /**
     * Builds a new ArrayList of Strings that contains the items of the ArrayList passed in, arrL,
     * but in reverse order.
     *
     * @param arrL The ArrayList of Strings to reverse.
     * @return A new ArraList of Strings that is the reverse of arrL.
     */
    public static ArrayList<String> reverse(ArrayList<String> arrL) {
        ArrayList<String> revArrL = new ArrayList<String>();
        String revWord;
        
        //add all elements in arrL into revArrL in reversed order.
        	for(int i = 0; i < arrL.size(); i++) {
        		revWord = arrL.get(arrL.size() - 1 - i);
        		revArrL.add(revWord);
        	}
        	
        return revArrL; 
    }

    /**
     * The method that displays the main program menu and reads the user's menu choice.
     * The full menu has the following format where the (assuming that Config.MISSING_CHAR is '-'):
     * 
     * --------------------------------------------------------------------------------
     * Text Manipulator Program
     * --------------------------------------------------------------------------------
     * Current input file: ---
     * Current output file: ---
     * Current dictionary: ---
     * Current mode: Interleaved
     * Current mods: TPWL
     * --------------------------------------------------------------------------------
     * Main menu:
     * 1) Display Output
     * 2) Save Output
     * Manipulations:
     *        T)ranslate
     *        P)ig latin
     *        W)ord reverse
     *        L)ine reverse
     * Configuration:
     *        I)nput file.
     *        O)utput file.
     *        D)ictionary file.
     *        M)ode Toggle.
     *        H)ide/show Menu.
     * Q)uit
     * Enter action: 
     *
     * Notes:
     *   - The lines consist of 80 Config.LINE_CHAR characters.
     *   - The input file, output file and dictionary names are 3 Config.LINE_CHAR characters if 
     *     appropriate value in files is null, otherwise display the appropriate value in files.
     *   - The Current mode displays "Interleaved" when modeBoth is true and "Modified" when false.
     *   - The Current mods displays (in the following order) 'T' if translate is toggled, 'P' if
     *     Pig Latin is toggled, 'W' if word reverse is toggled, and 'L' if line reverse is toggled.
     *   - The manipulation and configuration options are preceded by a single tab.
     *   - There is no new line following the final "Enter action: " prompt.
     *
     * @param sc The reference to the Scanner object for reading input from the user.
     * @param files A string array containing the input file name, the output file name, and the
     *              dictionary file name in that exact order. The entries may be null. The length
     *              of the array is Config.NUM_FILES. The input file name is at index 
     *              Config.FILE_IN, the output file name is at index Config.FILE_OUT, and the
     *              dictionary file name is at index Config.FILE_DICT.
     * @param modFlags A boolean array where the values are true if the given modification is set
     *                 to be applied. 
     * @param modeBoth True if the display command will alternate lines from the modified text and
     *                 the original text.
     * @param showMenu If true the entire menu is shown, otherwise only the "Enter Action: " line is
                       shown.
     * @return The first character of the line inputed by the user.
     */
    public static char promptMenu(Scanner sc, String[] files, boolean[] modFlags, boolean modeBoth,
                                  boolean showMenu) {
    		if (showMenu) {
	    		String currentModeStatus = null;
	    		String currentModsStatus = "";
	    		String currentInputFile = "", currentOutputFile = "", currentDictionary = "";
	    		String stringEmptyFiles = "" + Config.LINE_CHAR ;
	    		
	    		for (int j = 0; j < 2; j++) {
	    			stringEmptyFiles += Config.LINE_CHAR;
	    		}
	    		
	    		//assigning values to currentModesStatus
	    		currentModeStatus = modeBoth? "Interleaved" : "Modified";
	    		//assigning values to currentModsStatus
	    		if (modFlags[Config.MOD_TRANS])
	    			currentModsStatus += "T";
	    		if (modFlags[Config.MOD_PIG])
	    			currentModsStatus += "P";
	    		if (modFlags[Config.MOD_REV_WORD])
	    			currentModsStatus += "W";
	    		if (modFlags[Config.MOD_REV_LINE]) 
	    			currentModsStatus += "L";
	        
	    		//assign file names
	    		if (files == null) {
	    			currentInputFile = stringEmptyFiles;
	    			currentOutputFile = stringEmptyFiles;
	    			currentDictionary = stringEmptyFiles;
	    		} else {
		    		if (files[Config.FILE_IN] == null) { 
		    			currentInputFile = stringEmptyFiles;}
		    		else {
		    			currentInputFile = files[Config.FILE_IN];}
		    		if (files[Config.FILE_OUT] == null) {
		    			currentOutputFile = stringEmptyFiles;}
		    		else {
		    			currentOutputFile = files[Config.FILE_OUT];}
		    		if (files[Config.FILE_DICT] == null) {
		    			currentDictionary = stringEmptyFiles;}
		    		else {
		    			currentDictionary = files[Config.FILE_DICT];}
	    		}
	    		
	    		//printing out the menu
	    		printLine(); //employ supporting method to print a line of Config.LINE_CHAR
	    		System.out.println("Text Manipulator Program");
	    		printLine();
	    		System.out.println("Current input file: " + currentInputFile);
	    		System.out.println("Current output file: " + currentOutputFile);
	    		System.out.println("Current dictionary: " + currentDictionary);
	    		System.out.println("Current mode: " + currentModeStatus);
	    		System.out.println("Current mods: " + currentModsStatus);
	    		printLine();
	    		System.out.println("Main menu:\n"
	    				+ "1) Display Output\n"
	    				+ "2) Save Output\n"
	    				+ "Manipulations:\n"
	    					+ "\tT)ranslate\n"
	    					+ "\tP)ig latin\n"
	    					+ "\tW)ord reverse\n"
	    					+ "\tL)ine reverse\n"
	    				+ "Configuration:\n"
	    					+ "\tI)nput file.\n"
	    					+ "\tO)utput file.\n"
	    					+ "\tD)ictionary file.\n"
	    					+ "\tM)ode Toggle.\n"
	    					+ "\tH)ide/show Menu.\n"
	    				+ "Q)uit");
    		}
    		System.out.print("Enter action: ");
    		char userInput = Character.toUpperCase(sc.nextLine().charAt(0));
        return userInput;
    }

    /**
     * Prompts the user for a new file name. The prompt should be:
     * "Enter file name [curFileName]: ", where curFileName is the value from the parameter of the
     * same name. There should not be a new line following the prompt.
     *
     * @param sc The reference to the Scanner object for reading input from the user.
     * @param curFileName The current file name.
     * @return The value input by the user excluding any leading or trailing white space. If the
     *         input is an empty string, then curFileName is returned.  
     */
    public static String updateFileName(Scanner sc, String curFileName) {
    		String newFileName;
	    System.out.print("Enter file name [" + curFileName+ "]: ");
	    newFileName = sc.nextLine().trim();
	    //check if the new file name is empty
	    if (newFileName == null || newFileName.isEmpty()) {
	    		newFileName = curFileName;
	    } 
        return newFileName;
    }

    /**
     * Opens and reads the contents of the dictionary file specified in fileName. The file is 
     * assumed to be a text file encoded in UTF-8. It is assumed that there is one translation 
     * mapping per line. Each line contains a key and its translation separated by a tab.
     * Note: The dictionary file is assumed to be sorted by the keys in ascending order. 
     *
     * For each line in the dictionary file, an entry is added into wordList. The entry is a String
     * array of length 2, where the value of index 0 is the key and the value of index 1 is the
     * translation.
     *
     * When opening the file, any FileNotFoundException is caught and the error message 
     * "Exception: File 'fileName' not found." followed by a new line is output,
     * where fileName is the name of the file that the method attempted to open.
     *
     * @param fileName
     * @param wordList Reference to ArrayList to contain the translation mappings.
     * @throws IOException if an I/O error occurs when closing the file. FileNotFoundException is
     *                     caught when opening the file.
     */
    public static void readDictFile(String fileName,
                                    ArrayList<String[]> wordList) throws IOException {
    		FileInputStream dictFile = null;
    		Scanner sc = null;
    		String ori, trans;
    		//if fileName is null, return null;
        if (fileName == null)
        		return;
        else {
        		try {
        			//attempt to open dictionary file
        			dictFile = new FileInputStream(fileName);
        			sc = new Scanner(dictFile);
        			//if the file contains no elements
        			if (!sc.hasNext()) {
        				System.out.println("Dictionary File is empty.");
        			} else {
        				do {
        					//read from the dictionary file
        					ori = sc.next();
        					trans = sc.next();
        					//store each pair of key/translation into arrayList
        					wordList.add(new String[] {ori, trans});
        				} while (sc.hasNext());
        			}
        		} catch (FileNotFoundException e) {
        			throw new IOException("Exception: File '" + fileName + "' not found.");
        		} finally {
        			if (dictFile != null)
	        			dictFile.close();
	        		if (sc != null)
	        			sc.close();
        		}
        }
    }

    /**
     * Opens and reads the contents of the input file specified in fileName. The input file is read
     * line by line. Each line is split into words and punction (excluding the apostrophe) and
     * stored in an ArrayList of Strings. These ArrayLists representing the line are stored in an
     * ArrayList of ArrayLists of Strings. Specifically, they are put in the ArrayList fileByLine
     * that is passed in as a parameter.
     *
     * For example, a file containing the following: 
     * Lorem ipsum dolor sit amet, consectetur adipiscing elit. Don'ec elementum tortor in mauris 
     * consequat vulputate.
     *
     * Would produce an ArrayList of ArrayLists containing 2 ArrayLists of Strings.
     * The first ArrayList would contain:
     *   "Lorem", "ipsum", "dolor", "sit", "amet", ",", "consectetur", "adipiscing", "elit", ".", 
     *   "Don'ec", "elementum", "tortor", "in", "mauris"
     * The second Arraylist would contain:
     *   "consequat", "vulputate", "."
     *
     * Note 1: The text file is assumed to be UTF-8.
     * Note 2: There are no assumption about the length of the file or the length of the lines.
     * Note 3: All single quotes (') are assumed to be apostrophes.
     *
     * When opening the file, any FileNotFoundException is caught and the error message 
     * "Exception: File 'fileName' not found." followed by a new line is output,
     * where fileName is the name of the file that the method attempted to open.
     *
     * @param fileName The name of the input text file to parse.
     * @param fileByLine Reference to ArrayList to contain the contents of the file line by line, 
     *                   where each line is an ArrayList of Strings.
     * @throws IOException if an I/O error occurs when closing the file. FileNotFoundException is
     *                     caught when opening the file.
     */
    public static void readInputFile(String fileName,
                                     ArrayList<ArrayList<String> > fileByLine) throws IOException {
    		String currentLine = null;
    		int i = 0; //denoting the sequence of lines
        FileInputStream inputFile = null;
        Scanner sc = null;
        String currStr;
        
        if (fileName == null) {
        		return;
        } else {
	        try {
	        		inputFile = new FileInputStream(fileName);
	        		sc = new Scanner(inputFile);
	        		currentLine = sc.nextLine(); 
	        		while (!currentLine.isEmpty()) { //if file contains more lines
	        			//split the line by spaces and store each section in a array of Strings
	        			String [] tempContent = currentLine.split(" ");
	        			
	        			//add the collection of Strings to the arrayList
	        			fileByLine.add(new ArrayList<String>());
	        			fileByLine.get(i).addAll(Arrays.asList(tempContent));
	        			
	        			for (int j = 0; j < fileByLine.get(i).size(); j++) {
	        				currStr = fileByLine.get(i).get(j);
	        				
	        				//check for lest quotation marks 
	        				if (currStr.charAt(0) == '\"') {
	        					fileByLine.get(i).set(j, "\"");
	        					fileByLine.get(i).add(j + 1, currStr.substring(1, currStr.length()));
	        					//reset currStr for future use
	        					currStr = currStr.substring(1, currStr.length());
	        					j++;
	        				}
	        				//FIX_ME may need additional list
	        				//check all punctuation and appending each word
	        				if (currStr.contains(".") || currStr.contains("?") || currStr.contains("!")
	        						|| currStr.contains(",") || (currStr.contains("\""))
	        						|| currStr.contains(":")) { 
	        					fileByLine.get(i).set(j, currStr.substring(0, currStr.length() - 1));
	        					fileByLine.get(i).add(j + 1, currStr.substring(currStr.length()-1));
	        					j++;
	        				}
	        			}
	        			//adding space in front of punctuation
	        			if (sc.hasNext())
	        				currentLine = sc.nextLine();
	        			else 
	        				break;
	        			i++;
	        		}
	        		
	        } catch (FileNotFoundException e) {
	        		throw new FileNotFoundException("Exception: File '"+ fileName + "' not found.");
	        } finally {
	        		//close FileInputStream and Scanner
	        		if (inputFile != null)
	        			inputFile.close();
	        		if (sc != null)
	        			sc.close();
	        }
        }
        return;
    }

    /**
     * Opens and writes (overwrites if the file already exits) the modified contents of the input 
     * file specified contained in modFileByLine to a filecalled filename. medFileByLine is an 
     * ArrayList containing one ArrayList of String for each output line. 
     *
     * When producing the output file, each line should be terminated by a new line. Each 
     * non-punctuation should be Moreover, the
     * spacing around the punctuation should be as follows:
     * - Excluding, double quotes ("), no space between the preceding string and the punctuation and
     *   a space following.
     * - Double quotes (") will be treated as pairs:
     *    - the first double quote will be preceded by a space and will not have a space following.
     *    - the next double quote will not be preceded by space and will have a space following.  
     *
     * If modFileByLine is an ArrayList of ArrayLists containt 2 ArrayLists of Strings, such that:
     * - The first ArrayList contains:
     *   "Lorem", "ipsum", "\"", "dolor", "sit", "\"", "amet", ",", "consectetur", "adipiscing", 
     *   "elit", ".", "Don'ec", "elementum", "tortor", "in", "mauris"
     * - The second Arraylist contains:
     *   "consequat", "vulputate", "."
     *
     * The output to the file would be:
     * Lorem ipsum "dolor sit" amet consectetur adipiscing elit. Don'ec elementum tortor in mauris
     * consequat vulputate.
     *
     * Note 1: The output to the file is UTF-8.
     *
     * When opening the file, any FileNotFoundException is caught and the error message 
     * "Exception: File 'fileName' not found." followed by a new line is output,
     * where fileName is the name of the file that the method attempted to open.
     *
     * @param fileName The name of the input text file to parse.
     * @param modFileByLine Reference to ArrayList to contain the modified contents of the file line
     *                      by line, where each line is an ArrayList of Strings.
     * @throws IOException if an I/O error occurs when closing the file. FileNotFoundException is
     *                     caught when opening the file.
     */
    public static void saveToFile(String fileName,
                                  ArrayList<ArrayList<String> > modFileByLine) throws IOException {
        	FileOutputStream outputFile = null;
        	PrintWriter outFS = null;
        	try {
        		outputFile = new FileOutputStream(fileName);
        		outFS = new PrintWriter(outputFile);
        		String curLine = "";
        		char firstChar;
        		boolean isFirstQuote = true;
        		//iterate through the whole ArrayList
        		for (int i = 0; i < modFileByLine.size(); i++) {
        			//iterate through the whole line
	        		for (int j = 0; j < modFileByLine.get(i).size(); j++ ) {
	        			firstChar =  modFileByLine.get(i).get(j).charAt(0);
	        			//if encounters a letter, which suggests that it pertains to a word
	        			if (Character.isAlphabetic(firstChar))
	        				curLine += modFileByLine.get(i).get(j) + " ";
	        			//if such char is not a letter
	        			else {
	        				//specifically, if encounters a left quotation mark
	        				if (firstChar == '\"' && isFirstQuote) {
	        					curLine += modFileByLine.get(i).get(j);
	        					isFirstQuote = !isFirstQuote;
	        				}
	        				//if encounters other punctuation
	        				else {
	        					curLine = curLine.substring(0, curLine.length() - 1);
	        					curLine += modFileByLine.get(i).get(j) + " ";
	        				}
	        			}		
	        		}
	        		//remove the last space within the line
	        		curLine = curLine.substring(0, curLine.length() - 1);
	        		//output the current line
	        		outFS.println(curLine);
	        		//reset current line
	        		curLine = "";
	        		outFS.flush(); 
        		}
        	} catch (IOException e) {
        		throw new IOException("Exception: File " + fileName + " not found.");
        	} finally {
        		if (outputFile != null)
        			outputFile.close();
        		if (outFS != null) 
        			outFS.close();
        	}
    }

    /**
     * Prints out the modified file (and possibly interleaved with the original file) to the screen.
     *
     * If modeBoth is false, then the contents of modFileByLine is output line by line. Each word is
     * output followed by a space except for the last word. Each line is terminated with a new line
     * character.
     *
     * For the non-interleaved mode, consider the following example:
     * modFileByLine contains 2 ArrayList of Strings: 
     *          1: "OÃ¹", "est", "la", "bibliothÃ¨que", "?"
     *          2: "Aucune", "idÃ©e", "."
     *
     * The output would be:
     * OÃ¹ est la bibliothÃ¨que ?
     * Aucune idÃ©e .
     *
     * Otherwise, modeBoth is true, then the contents of modFileByLine is interleaved with fileByline. 
     * Lines are printed out in order. First, a line from modFileByLine is output followed by a new
     * line character followed by the corresponding line in fileByLine. In order to better match up
     * the corresponding words in the corresponding lines, the short word is appended with spaces to
     * the length of the longer word. Between each word adjusted for length is an additional space.
     *
     * For the interleaved mode, consider the following example:
     * 
     * modFileByLine contains 2 ArrayList of Strings: 
     *          1: "OÃ¹", "est", "la", "bibliothÃ¨que", "?"
     *          2: "Aucune", "idÃ©e", "."
     * fileByLine contains 1 ArrayList of Strings: 
     *          1: "Where", "is", "the", "library", "?"
     *          2: "No", "idea", "."
     *
     * The output would be:
     * OÃ¹    est la  bibliothÃ¨que ?
     * Where is  the library      ?   
     * Aucune idÃ©e .
     * No     idea . 
     * 
     * @param fileByLine An ArrayList of ArrayList of Strings containing the original content.
     * @param modFileByLine An ArrayList of ArrayList of Strings containing the modified content.
     * @param modeBoth Flag to indicate if the original file should be interleaved.
     * @throws Exception Throws an Exception with the message "Number of lines in modified version
     *                   differs from original." if the size of fileByLine differes from 
     *                   modFileByLine.
     * @throws Exception Throws an Exception with the message "Number of words on line i in modified
     *                   version differs from original.", where i should be the 0-based line index
     *                   where the size of the ArrayList at index i in fileByLine differes from the
     *                   ArrayList at index i in modFileByLine. ;
     */
    public static void display(ArrayList<ArrayList<String> > fileByLine,
                               ArrayList<ArrayList<String> > modFileByLine,
                               boolean modeBoth) throws Exception {
        //scanning for Exceptions
        if (fileByLine.size() != modFileByLine.size()) //if number of lines differ
        		throw new Exception("Number of lines in modified version differs from original.");
        else { //if number of words per line differ
        		for (int i = 0; i < fileByLine.size(); i++) {
        			if (fileByLine.get(i).size() != modFileByLine.get(i).size()) 
        				throw new Exception("Number of words on line " + i + " in modified version differs from original.");        			
        		}
        }
        
        //handling output
        if (!modeBoth) { //mode Modified 
	        for (int i = 0; i < modFileByLine.size(); i++) {
	        		for (int j = 0; j < modFileByLine.get(i).size(); j++ ) {
	        			if (j < modFileByLine.get(i).size() - 1) 
	        				System.out.print(modFileByLine.get(i).get(j) + " "); 
	        			else //print the last word/symbol of the line without appending space
	        				System.out.print(modFileByLine.get(i).get(j));
	        		}
	        		System.out.println();
	        }
        } else { //Mode Interleaved
        		String oriWord, modWord;
        		String curOriLine = "";
        		String curModLine = "";
        		String spaceToAppend = "";
        		String addSpace = " ";
        		int lengthDiff; //denotes the difference in length between oriWord and ModWord
        		for (int i = 0; i < modFileByLine.size(); i++) {
	        		for (int j = 0; j < modFileByLine.get(i).size(); j++ ) {
	        			oriWord =  fileByLine.get(i).get(j);
	        			modWord =  modFileByLine.get(i).get(j);
	        			lengthDiff = modWord.length() - oriWord.length();
	        			
	        			for (int k = Math.abs(lengthDiff); k > 0; k--) {
	        				spaceToAppend += " "; //append space based on lengthDiff
	        			}
	        			if (j == modFileByLine.get(i).size() - 1) { 
	        				//do not add any space behind the last word
	        				addSpace = ""; 
	        				spaceToAppend = "";
	        			}
	        			if (lengthDiff > 0) { //if modWord is longer than oriWord
	        				curOriLine += oriWord + addSpace + spaceToAppend;
	        				curModLine += modWord + addSpace;
	        			} else if (lengthDiff < 0) { //if oriWord is longer than modWord
	        				curOriLine += oriWord + addSpace;
	        				curModLine += modWord + addSpace + spaceToAppend;
	        			} else { //if they are equal in length
	        				curOriLine += oriWord + addSpace;
	        				curModLine += modWord + addSpace;
	        			}
	        			//resetting variables 
		        		spaceToAppend = "";
		        		addSpace = " ";
	        		}
	        		System.out.println(curModLine);
	        		System.out.println(curOriLine);
	        		System.out.println();
	        		curOriLine = "";
	        		curModLine = "";
	        		
        		}
        }
        return;
    }

    /**
     * Performs the actions specified by the modFlags to the input file stored in the ArrayList of
     * ArrayLists of Strings fileByLine. This method stores the modified string in a new ArrayList
     * of ArrayLists of Strings which it returns.
     *
     * There are 4 modifications that may be performed. They are to be process in the following 
     * order if indicated in modFlags:
     *   1 - Translation
     *   2 - To Pig Latin
     *   3 - Reverse the letters in each word
     *   4 - Reverse the words in each line
     *
     * @param fileByLine The original file stored as an ArrayList of ArrayLists of Strings.
     * @param dict An ArrayList of String arrays of length two. The ArrayList is assumed to be
     *             sorted in ascending order accordings to the strings at index 0. This will be the
     *             second argument for the translate method.
     * @param modFlags A boolean area of length Config.NUM_MODS that indicates which translation 
     *                 to perform by having a value of true in the appropriate cell as follows:
     *                 Index                  Modification
     *                 -------------------    --------------------------------
     *                 Config.MOD_TRANS       Translation
     *                 Config.MOD_PIG         To Pig Latin
     *                 Config.MOD_REV_WORD    Reverse the letters in each word
     *                 Config.MOD_REV_LINE    Reverse the words in each line
     *@return An ArrayList of ArrayLists of Strings, where each internal ArrayList is a line 
     *        which corresponds to the data in fileByLine but with the transformations applied in 
     *        the order specified above.
     */
    public static ArrayList<ArrayList<String> > manipulate(ArrayList<ArrayList<String> > fileByLine,
                                                           ArrayList<String[]> dict,
                                                           boolean[] modFlags) {
        //For Milestone 2, you will need to build a new ArrayList<ArrayList<String>> that will be
        //returned. Go through the each string in fileByLine and, if the boolean at
        //Config.MOD_TRANS in modFlags is true, then call the translate method, storing the modified
        //strings line by line as they are organized in fileByLine.
    	
    		//For Milestone 3, you will need to build a new ArrayList<ArrayList<String>> that will be
        //returned. For each string in fileByLine, you will need to check the booleans at
        //Config.MOD_TRANS, Config.MOD_PIG, and Config.MOD_REV_WORD in modFlags in that order. For
        //each one that is true, apply the appropriate transformation, storing them as in
        //Milestone 2. After having processed each string, if the boolean at Config.MOD_REV_LINE
        //in modFlags is true, reverse each line in the returning ArrayList<ArrayList<String>>.
    	
    		ArrayList<ArrayList<String>> modFileByLine = new ArrayList<ArrayList<String>> ();
    		String oriWord, modWord;
    		
    		for (int i = 0; i < fileByLine.size(); i++) {
    			modFileByLine.add(new ArrayList<String>(fileByLine.get(i)));
    		}
    		
    		//perform manipulations
    		if (modFlags[Config.MOD_TRANS]) {
    			for (int i = 0; i < fileByLine.size(); i++) {
	        		for (int j = 0; j < fileByLine.get(i).size(); j++ ) {
	        			oriWord = modFileByLine.get(i).get(j);
	        			modWord = translate(oriWord, dict);
	        			modFileByLine.get(i).set(j, modWord);
	        		}
	        	}
    		} if (modFlags[Config.MOD_PIG]) {
    			for (int i = 0; i < fileByLine.size(); i++) {
	        		for (int j = 0; j < fileByLine.get(i).size(); j++ ) {
	        			oriWord = modFileByLine.get(i).get(j);
	        			modWord = pigLatin(oriWord);
	        			modFileByLine.get(i).set(j, modWord);
	        		}
	        	}
    		} if (modFlags[Config.MOD_REV_WORD]) {
    			for (int i = 0; i < fileByLine.size(); i++) {
	        		for (int j = 0; j < fileByLine.get(i).size(); j++ ) {
	        			oriWord = modFileByLine.get(i).get(j);
	        			modWord = reverse(oriWord);
	        			modFileByLine.get(i).set(j, modWord);
	        		}
	        	}
    		} if (modFlags[Config.MOD_REV_LINE]) {
    			for (int i = 0; i < fileByLine.size(); i++) {
    				modFileByLine.set(i, reverse(modFileByLine.get(i)));
	        	}
    		}
    		return modFileByLine;
    }
    
    /**
     * This is a supporting method to print out a line consisting of 80 Config.LINE_CHAR, followed
     * by line new line character.
     */
    public static void printLine() {
    		for (int i = 0; i < 80; i++) {
    			System.out.print(Config.LINE_CHAR);
    		}
    		System.out.println();
    		return;
    }

    /**
     * This is the main method for the TextManipulator program. This method contains the loop that
     * runs the prompt. It handles the user response and calls the methods that are necessary in
     * order to perform the actions requested by the user.
     *
     * The initial default behavior of the program is to show the full menu, to be in the "Modified"
     * mode, to have no modifications selected, and to have no values for the various file names.
     *
     * @param args (unused)
     */
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        ArrayList<ArrayList<String>> fileByLine = new ArrayList<ArrayList<String>> ();
        ArrayList<ArrayList<String>> modFileByLine = new ArrayList<ArrayList<String>> ();
        ArrayList<String[]> wordList = new ArrayList<String[]>();
        String [] filesStr = new String[Config.NUM_FILES];
        boolean [] modFlags = new boolean [Config.NUM_MODS];
        boolean modeBoth = false;
        boolean showMenu = true;
        char userCmd = ' ';
        
        //initialize modFlags
        	for (int i = 0; i < modFlags.length; i++)
        		modFlags[i]= false;
        //menu loop
        do {
        		try {
        			userCmd = promptMenu(sc, filesStr, modFlags, modeBoth, showMenu);
        			switch (userCmd) {
        			//Manipulations
        			case '1': //Display output
        				modFileByLine = manipulate(fileByLine, wordList, modFlags);
        				printLine();
        				display(fileByLine, modFileByLine, modeBoth); 
        				printLine();
        				break;
        			case '2':  //Save to file
        				modFileByLine = manipulate(fileByLine, wordList, modFlags);
        				saveToFile(filesStr[Config.FILE_OUT], modFileByLine);
        			case 'T': //Translation
        				modFlags[Config.MOD_TRANS] = !modFlags[Config.MOD_TRANS];
        				break;
        			case 'P': //Pig Latin
        				modFlags[Config.MOD_PIG] = !modFlags[Config.MOD_PIG];
        				break;
        			case 'W': //Word Reverse
        				modFlags[Config.MOD_REV_WORD] = !modFlags[Config.MOD_REV_WORD];
        				break;
        			case 'L': //Line Reverse
        				modFlags[Config.MOD_REV_LINE] = !modFlags[Config.MOD_REV_LINE];
        				break;
        			//Configuration
        			case 'I': //Input file
        				filesStr[Config.FILE_IN] = updateFileName(sc, filesStr[Config.FILE_IN]);
        				readInputFile(filesStr[Config.FILE_IN], fileByLine);
        				break;
        			case 'O': //Output file
        				filesStr[Config.FILE_OUT] = updateFileName(sc, filesStr[Config.FILE_OUT]);
        				break;
        			case 'D': //Dictionary file
        				filesStr[Config.FILE_DICT] = updateFileName(sc, filesStr[Config.FILE_DICT]);
        				readDictFile(filesStr[Config.FILE_DICT], wordList);
        				break;
        			case 'M': //Mode Toggle
        				modeBoth = !modeBoth;
        				break;
        			case 'H': //Hide or Show Menu
        				showMenu = !showMenu;
        				break;
        			case 'Q':
        				break;
        			default:
        				System.out.print("Unknown option.\n");
        			}
        		} catch (Exception e) {
        			System.out.println(e.getMessage());
        		}
        		
        } while ( userCmd != 'Q');
    }    
}