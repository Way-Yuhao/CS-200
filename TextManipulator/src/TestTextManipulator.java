/**
 * This file contains some testing methods for BP2 TextManipulator
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.io.PrintWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.*;

/**
 * This class contains a few methods for testing methods in the TextManipulator
 * class as they are developed. 
 * 
 * @author Yuhao Liu
 *
 */
public class TestTextManipulator {

	/**
    * This is the main method that runs the various tests. Uncomment the tests
    * when ready for them to run.
    * 
    * @param args  (unused)
    */
	public static void main(String[] args) {
		
		//testReverseWords();
		//testReverseLine();
		//testPigLatin();
		//testReadInputFile();
		//testReadDictFile();
		//testUpdateFileName();
		//testMatchCase();
		//testTranslate();
		//testPromptMenu();
		//testDisplay();
		//testPrintLine();
		//testManipulate();
		testSaveToFile();
		
	}
	
	/**
     * This is intended to run some tests on the reverse method. 
     * 1. Create an arrayList that consists of originals, 
     * 	  results, and expected results
     * 2. Use a for loop to run tests for each circumstances
     * 3. Compare results
     * 
     */
	private static void testReverseWords() {
		/*
		 * For each test String[],
		 * * element in [0] is the template (not used)
		 * * element in [1] is the original word
		 * * element in [2] is the result generated by calling reverse()
		 * * element is [3] is the expected result
		 */
		ArrayList<String[]> tests = new ArrayList<String[]>();
		String [] test1 = new String [4];
		String [] test2 = new String [4];
		String [] test3 = new String [4];
		String [] test4 = new String [4];
		String original, result, expectedResult;
		boolean allPassed = true;
		test1[1] = "Abc";
		test1[2] = "Cba";
		
		test2[1] = "123";
		test2[2] = "321";
		
		test3[1] = "A a";
		test3[2] = "A a";
		
		test4[1] = "bb!bbb";
		test4[2] = "bbb!bb";
		
		tests.add(test1);
		tests.add(test2);
		tests.add(test3);
		tests.add(test4);
		
		for (int i = 0; i < 4; i++) {
			original = tests.get(i)[1];
			expectedResult = tests.get(i)[2];
			result = TextManipulator.reverse(original);
			//check for mismatches
			if (!result.equals(expectedResult)) {
				System.out.println("Error found when running reverse()\n"
						+ "Expected: \"" + expectedResult + "\" got: \"" + result);
				allPassed = false;
			}
		}
		if (allPassed) {
			System.out.println("All tests passed.");
		}
	}
	
	/**
     * This is intended to run some tests on the reverse method (for lines). 
     * 1. Prepare multiple ArrayLists for testing
     * 2. Pass each arrayLists into reverser() and store the returned arrayList
     * 3. Print out each returned arrayList
     */
	private static void testReverseLine() {
		ArrayList<String> oriAL1 = new ArrayList<String>();
		ArrayList<String> revAL1 = new ArrayList<String>();
		ArrayList<String> oriAL2 = new ArrayList<String>();
		ArrayList<String> revAL2 = new ArrayList<String>();
		ArrayList<String> oriAL3 = new ArrayList<String>();
		ArrayList<String> revAL3 = new ArrayList<String>();
		ArrayList<String> oriAL4 = new ArrayList<String>();
		ArrayList<String> revAL4 = new ArrayList<String>();
		
		String[] str1 = {"1", "2"};
		oriAL1.addAll(Arrays.asList(str1));
		revAL1 = TextManipulator.reverse(oriAL1);
		
		String[] str2 = {"Aaa", "Bbb", "Ccc"};
		oriAL2.addAll(Arrays.asList(str2));
		revAL2 = TextManipulator.reverse(oriAL2);
		
		String[] str3 = {"!", "."};
		oriAL3.addAll(Arrays.asList(str3));
		revAL3 = TextManipulator.reverse(oriAL3);
		
		String[] str4 = {"Derrick", "Rose", "will", "return", "."};
		oriAL4.addAll(Arrays.asList(str4));
		revAL4 = TextManipulator.reverse(oriAL4);
		
		System.out.println(revAL1);
		System.out.println(revAL2);
		System.out.println(revAL3);
		System.out.println(revAL4);
	}
	
	/**
     * This is intended to run some tests on the pigLatin method. 
     * 1. 1. Create an arrayList that consists of originals, 
     * 	  results, and expected results
     * 2. Use a for loop to run tests for each circumstances
     * 3. Compare results
     * 
     */
	public static void testPigLatin() {
		/*
		 * For each test String[],
		 * * element in [0] is the template (not used)
		 * * element in [1] is the original word
		 * * element in [2] is the result generated by calling reverse()
		 * * element is [3] is the expected result
		 */
		ArrayList<String[]> tests = new ArrayList<String[]>();
		String [] test1 = new String [4];
		String [] test2 = new String [4];
		String [] test3 = new String [4];
		String [] test4 = new String [4];
		String original, result, expectedResult;
		boolean allPassed = true;
		test1[1] = "Abc";
		test1[2] = "Abcway";
		
		test2[1] = "123";
		test2[2] = "123";
		
		test3[1] = "A a";
		test3[2] = "A away";
		
		test4[1] = "'hello world'";
		test4[2] = "ello world''hay";
		
		tests.add(test1);
		tests.add(test2);
		tests.add(test3);
		tests.add(test4);
		
		for (int i = 0; i < 4; i++) {
			if (i==3) {
				System.out.print("");
			}
			original = tests.get(i)[1];
			expectedResult = tests.get(i)[2];
			result = TextManipulator.pigLatin(original);
			//check for mismatches
			if (!result.equals(expectedResult)) {
				
				System.out.println("Error found when running pigLatin()\n"
						+ "Expected: \"" + expectedResult + "\" got: \"" + result);
				allPassed = false;
			}
		}
		if (allPassed) {
			System.out.println("All tests passed.");
		}
	}
	
	/**
     * This is intended to run some tests on the readDict method. 
     * 1. Read a file named "OneFishDict.txt"
     * 2. Print out the file and manually assess if the read file process is complete
     * 
     */
    private static void testReadDictFile() {
    		ArrayList<String[]> wordListTest1 = new ArrayList<>();
    		String fileName = "OneFishDict.txt";
    		try {
    			TextManipulator.readDictFile(fileName, wordListTest1);
    			for (int i = 0; i < wordListTest1.size(); i++) {
    				for (int j = 0; j < 2; j++) {
    					System.out.print(wordListTest1.get(i)[j] + " ");
    				}
    				System.out.println();
    			}
    		} catch (IOException e) {
    			System.out.println(e.getMessage());
    		} 	
    }
    
    /**
     * This is intended to run some tests on the readInputFile method. 
     * 1. Read a file named "OneFish.txt"
     * 2. Print out the file and manually assess if the read file process is complete
     * 
     */
    private static void testReadInputFile() {
    		ArrayList<String[]> wordListTest1 = new ArrayList<>();
    		ArrayList<ArrayList<String>> fileByLineTest = 
    				new ArrayList<ArrayList<String>>();
    		String fileName = "OneFish.txt";
    		try {
    			TextManipulator.readInputFile(fileName, fileByLineTest);
    				System.out.println(fileByLineTest);
    			
    		} catch (IOException e) {
    			System.out.println(e.getMessage());
    		} 	
    }
	
    /**
     * This is intended to run some tests on the matchCase method. 
     * 1. 
     * 2. 
     * 
     */
    private static void testUpdateFileName() {
    		String [] filesStr = new String[Config.NUM_FILES];
    		Scanner sc = new Scanner(System.in);
    		//Input file name
    		filesStr[Config.FILE_IN] = TextManipulator.updateFileName(sc, filesStr[Config.FILE_IN]);
    		//Output file name
    		filesStr[Config.FILE_OUT] = TextManipulator.updateFileName(sc, filesStr[Config.FILE_OUT]);
    		//Dictionary file name
    		filesStr[Config.FILE_DICT] = TextManipulator.updateFileName(sc, filesStr[Config.FILE_DICT]);
    		
    		for (int i = 0; i < Config.NUM_FILES; i++) {
    			System.out.println(filesStr[i]);
    		}
    }
    
    /**
     * This is intended to run some tests on the matchCase method. 
     * 1. Create an arrayList that consists of templates, originals, 
     * 	  results, and expected results
     * 2. Use a for loop to run tests for each circumstances
     * 3. Compare results
     * 
     */
    private static void testMatchCase() {
    		/*
    		 * For each test String[],
    		 * * element in [0] is the template
    		 * * element in [1] is the original word
    		 * * element in [2] is the result generated by calling mathCase()
    		 * * element is [3] is the expected result
    		 */
    		ArrayList<String[]> tests = new ArrayList<String[]>();
    		String [] test1 = new String [4];
    		String [] test2 = new String [4];
    		String [] test3 = new String [4];
    		String [] test4 = new String [4];
    		String template, original, result, expectedResult;
    		boolean allPassed = true;
    		test1[0] = "LeBron";
    		test1[1] = "James";
    		test1[2] = "JaMes";
    		
    		test2[0] = ".";
    		test2[1] = ".";
    		test2[2] = ".";
    		
    		test3[0] = "A";
    		test3[1] = "bbb";
    		test3[2] = "Bbb";
    		
    		test4[0] = "A!a!A";
    		test4[1] = "bb!bbb";
    		test4[2] = "Bb!bBb";
    		
    		tests.add(test1);
    		tests.add(test2);
    		tests.add(test3);
    		tests.add(test4);
    		
    		for (int i = 0; i < 4; i++) {
    			template = tests.get(i)[0];
    			original = tests.get(i)[1];
    			expectedResult = tests.get(i)[2];
    			result = TextManipulator.matchCase(template, original);
    			//check for mismatches
    			if (!result.equals(expectedResult)) {
    				System.out.println("Error found when running matchCase()\n"
    						+ "Expected: \"" + expectedResult + "\" got: \"" + result);
    				allPassed = false;
    			}
    		}
    		if (allPassed) {
    			System.out.println("All tests passed.");
    		}
    		
    }
    
    /**
     * This is intended to run some tests on the translate method. 
     * 1. Open Dictionary file
     * 2. Create an arrayList that consists of originals, 
     * 	  results, and expected results
     * 3. Use a for loop to run tests for each circumstances
     * 4. Compare results
     * 
     */
    private static void testTranslate() {
    		ArrayList<String[]> wordListTest = new ArrayList<>();
		String fileName = "OneFishDict.txt";
		
		try {
			TextManipulator.readDictFile(fileName, wordListTest);
			/*
			 * For each test String[],
			 * * element in [0] is the template (not used)
			 * * element in [1] is the original word
			 * * element in [2] is the result generated by calling translate()
			 * * element is [3] is the expected result
			 */
			ArrayList<String[]> tests = new ArrayList<String[]>();
			String [] test1 = new String [4];
			String [] test2 = new String [4];
			String [] test3 = new String [4];
			String [] test4 = new String [4];
			String original, result, expectedResult;
			boolean allPassed = true;
			test1[1] = "a";
			test1[2] = "un(e)";
			
			test2[1] = "arE";
			test2[2] = "ãªTre";
			
			test3[1] = ".";
			test3[2] = ".";
			
			test4[1] = "Kyrie";
			test4[2] = "-----";
			
			tests.add(test1);
			tests.add(test2);
			tests.add(test3);
			tests.add(test4);
			
			for (int i = 0; i < 4; i++) {
				original = tests.get(i)[1];
				expectedResult = tests.get(i)[2];
				result = TextManipulator.translate(original, wordListTest);
				//check for mismatches
				if (!result.equals(expectedResult)) {
					System.out.println("Error found when running translate()\n"
							+ "Expected: \"" + expectedResult + "\" got: \"" + result);
					allPassed = false;
				}
			}
			if (allPassed) {
				System.out.println("All tests passed.");
			}
			
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
    }
    
    /**
     * This is intended to run some tests on the promptMenu method. 
     * 1. Initialize each argument needed to pass into promtMenu
     * 2. Call promptMenu
     */
    private static void testPromptMenu() {
    		//(Scanner sc, String[] files, boolean[] modFlags, boolean modeBoth, boolean showMenu)
    		Scanner sc = new Scanner(System.in);
    		String [] filesStr = new String[Config.NUM_FILES];
    		boolean [] modFlags = new boolean [Config.NUM_MODS];
    		boolean modeBoth = false;
    		boolean showMenu = true;
    		char userCmd;
    		
    		modFlags[Config.MOD_PIG] = true;
    		modFlags[Config.MOD_REV_LINE] = true;
    		userCmd = TextManipulator.promptMenu(sc, filesStr, modFlags, modeBoth, showMenu);
    		
    }
    
    /**
     * This is intended to run some tests on the display method. 
     * 1. As testers, create a specified oriStr and modStr
     * 2. Pass the two String[] into the display() method
     * 
     */
    private static void testDisplay() {
    		ArrayList<ArrayList<String>> fileByLineTest = 
    				new ArrayList<ArrayList<String>>();
    		ArrayList<ArrayList<String>>modFileByLineTest = 
    				new ArrayList<ArrayList<String>>();
    		
    		fileByLineTest.add(new ArrayList<String>());
    		modFileByLineTest.add(new ArrayList<String>());
    		String[] oriStr = {"Aaa", "Bbbb", "Cccc"};
    		String[] modStr = {"Dddd", "Eee", "Fff"};
    		fileByLineTest.get(0).addAll(Arrays.asList(oriStr));
    		modFileByLineTest.get(0).addAll(Arrays.asList(modStr));
    		
    		try {
    			TextManipulator.display(fileByLineTest, modFileByLineTest, true);
    		} catch (Exception e) {
    			System.out.println(e.getMessage());
    		}
    }
    
    /**
     * This is intended to run some tests on the manipulate method. 
     * 1. Read in OneFishDict.txt
     * 2. Arbitrarily set the value of FileByLine
     * 3. Arbitrarily create different sets of modFlags 
     * 4. Run manipulate and assess results
     * 
     */
    public static void testManipulate() {
    		ArrayList<ArrayList<String>> fileByLineTest = 
				new ArrayList<ArrayList<String>>();
		ArrayList<ArrayList<String>>modFileByLineTest = 
				new ArrayList<ArrayList<String>>();
		
		//open and read dictionary file
		ArrayList<String[]> wordListTest1 = new ArrayList<>();
		String fileName = "OneFishDict.txt";
		try {
			TextManipulator.readDictFile(fileName, wordListTest1);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		} 	
		
		fileByLineTest.add(new ArrayList<String>());
		String[] oriStr = {"One", "Fish", "Two", "Fish", "."};
		
		 boolean [] modFlags1 = new boolean [Config.NUM_MODS];
		 //Mode T
		 modFlags1[Config.MOD_TRANS] = !modFlags1[Config.MOD_TRANS];
		 
		 boolean [] modFlags2 = new boolean [Config.NUM_MODS];
		 //Mode TP
		 modFlags2[Config.MOD_TRANS] = !modFlags2[Config.MOD_TRANS];
		 modFlags2[Config.MOD_PIG] = !modFlags2[Config.MOD_PIG];
		 
		 boolean [] modFlags3 = new boolean [Config.NUM_MODS];
		 //Mode L
		 modFlags3[Config.MOD_REV_LINE] = !modFlags3[Config.MOD_REV_LINE];
		 
		 boolean [] modFlags4 = new boolean [Config.NUM_MODS];
		 //Mode PR
		 modFlags4[Config.MOD_PIG] = !modFlags4[Config.MOD_PIG];
		 modFlags4[Config.MOD_REV_WORD] = !modFlags4[Config.MOD_REV_WORD];
		
		
		fileByLineTest.get(0).addAll(Arrays.asList(oriStr));
		//testing phrase 
		System.out.println("Original content: ");
		System.out.println(fileByLineTest);
		
		System.out.println("\nMode T");
		modFileByLineTest= TextManipulator.manipulate(fileByLineTest,  wordListTest1, modFlags1);
		System.out.println(modFileByLineTest);
		
		System.out.println("\nMode TP");
		modFileByLineTest= TextManipulator.manipulate(fileByLineTest,  wordListTest1, modFlags2);
		System.out.println(modFileByLineTest);
		
		System.out.println("\nMode L");
		modFileByLineTest= TextManipulator.manipulate(fileByLineTest,  wordListTest1, modFlags3);
		System.out.println(modFileByLineTest);
		
		System.out.println("\nMode PR");
		modFileByLineTest= TextManipulator.manipulate(fileByLineTest,  wordListTest1, modFlags4);
		System.out.println(modFileByLineTest);
		
		
    }
    
    /**
     * This is intended to run some tests on the saveToFile method. 
     * 1. Have the tester manually create and popularize an arrayList
     * 2. Call savetoFile method to save such arrayList to file
     */
    private static void testSaveToFile() {
    		ArrayList<ArrayList<String>> modFileByLineTest = new ArrayList<ArrayList<String>>();
    		modFileByLineTest.add(new ArrayList<String>());
    		modFileByLineTest.get(0).add("LeBron");
    		modFileByLineTest.get(0).add("\"King");
    		modFileByLineTest.get(0).add("James\"");
    		modFileByLineTest.get(0).add(">");
    		modFileByLineTest.get(0).add("KD");
    		try {
    			TextManipulator.saveToFile("OneFishTT.txt", modFileByLineTest);
    		} catch (IOException e) {
    			System.out.println(e.getMessage());
    		}
    }
   
    /**
     * This is intended to run some tests on the display method. 
     * 1. Test if printLine() method prints the line of desired length
     * 
     */
    public static void testPrintLine() {
	    	System.out.println( "--------------------------------------"
	    			+ "------------------------------------------");
	    	TextManipulator.printLine();
	    	
    }
}