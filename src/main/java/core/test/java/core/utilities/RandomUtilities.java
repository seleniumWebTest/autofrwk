package core.test.java.core.utilities;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class RandomUtilities {
	public static String generateRandomString(int strLength) {
		String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String returnString = "";

		Random rand = new Random();

		char[] text = new char[strLength];

		for (int i = 0; i < strLength; i++) {
			text[i] = letters.charAt(rand.nextInt(letters.length()));
			returnString += text[i];
		}

		return returnString;
	}

	public static int randomInt(int min, int max) {

		// nextInt is normally exclusive of the top value,
		// so add 1 to make it inclusive
		int randomNum = ThreadLocalRandom.current().nextInt(min, max + 1);

		return randomNum;
	}
}
