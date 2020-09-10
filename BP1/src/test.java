import java.util.Random;
import java.util.Scanner;
public class test {
	
	public static void main(String[] args) {
		Scanner scnr = new Scanner(System.in);
		char userResponse;
		String userResponseStr;
		boolean playAgain;
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
		System.out.println(playAgain);
	}
}
