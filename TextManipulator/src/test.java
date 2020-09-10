import java.io.*;
//import java.nio.file.*;
import java.util.ArrayList;
import java.util.Arrays;
public class test {

	public static void main(String[] args) {
		FileOutputStream outputFile = null;
		PrintWriter outFS = null;
		try {
		outputFile = new FileOutputStream("test.txt");
		outFS = new PrintWriter(outputFile);
		outFS.println("a");
		outFS.close();
		} catch (Exception e) {
			
		}
    		
	}
	
}
