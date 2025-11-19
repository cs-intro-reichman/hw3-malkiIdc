/** Functions for checking if a given string is an anagram. */
public class Anagram {
	public static void main(String args[]) {
		// Tests the isAnagram function.
		System.out.println(isAnagram("silent", "listen")); // true
		System.out.println(isAnagram("William Shakespeare", "I am a weakish speller")); // true
		System.out.println(isAnagram("Madam Curie", "Radium came")); // true
		System.out.println(isAnagram("Tom Marvolo Riddle", "I am Lord Voldemort")); // true

		// Tests the preProcess function.
		System.out.println(preProcess("What? No way!!!"));

		// Tests the randomAnagram function.
		System.out.println("silent and " + randomAnagram("silent") + " are anagrams.");

		// Performs a stress test of randomAnagram
		String str = "1234567";
		Boolean pass = true;
		//// 10 can be changed to much larger values, like 1000
		for (int i = 0; i < 10; i++) {
			String randomAnagram = randomAnagram(str);
			System.out.println(randomAnagram);
			pass = pass && isAnagram(str, randomAnagram);
			if (!pass)
				break;
		}
		System.out.println(pass ? "test passed" : "test Failed");
	}

	// Returns true if the two given strings are anagrams, false otherwise.
	public static boolean isAnagram(String str1, String str2) {
		str2 = preProcess(str2);
		str1 = preProcess(str1);

		int found = 0;

		if (str1.length() != str2.length())
			return false;

		else {
			for (int i = 0; i < str1.length(); i++) {
				for (int j = 0; j < str2.length(); j++) {

					if (str1.charAt(i) == str1.charAt(j)) {
						found++;
					}

				}
				if (found == 0) {
					return false;
				}
				found = 0;
			}
			return true;
		}
	}

	// Returns a preprocessed version of the given string: all the letter characters
	// are converted
	// to lower-case, and all the other characters are deleted, except for spaces,
	// which are left
	// as is. For example, the string "What? No way!" becomes "whatnoway"
	public static String preProcess(String str) {

		String ans = "";
		for (int i = 0; i < str.length(); i++) {
			if ((str.charAt(i) >= 'A' && str.charAt(i) <= 'Z')) {
				ans += (char) (str.charAt(i) + 32);
			} else if ((str.charAt(i) >= 'a' && str.charAt(i) <= 'z')) {
				ans += str.charAt(i);
			}
		}
		return ans;
	}

	// Returns a random anagram of the given string. The random anagram consists of
	// the same
	// characters as the given string, re-arranged in a random order.
// Note: This relies on the existence of the preProcess(String str) method.
public static String randomAnagram(String str) {
    // 1. Preprocess the string (though for random anagrams, often just the 
    // original string is used; we'll assume the goal is just to shuffle the input characters).
    // If str needs to be cleaned first, call preProcess(str) here.
    
    // We will use the input string 'str' as our mutable pool by overwriting 
    // the variable with a new string in each iteration.
    String mutablePool = str; 
    String randomAna = ""; 
    
    // 2. Loop N times (once for every character we need to pick)
    // The loop condition must use the ORIGINAL length of the string, NOT the mutablePool length.
    for (int i = 0; i < str.length(); i++) {
        
        // 3. Generate a random index based on the CURRENT size of the mutablePool
        // We ensure the index is between 0 and mutablePool.length() - 1.
        int randomIndex = (int) (Math.random() * mutablePool.length());
        
        // 4. Get the character at the random index and append it to the result
        char randomChar = mutablePool.charAt(randomIndex);
        randomAna += randomChar;
        
        // 5. CRITICAL: Remove the chosen character by creating a NEW string
        // Part 1: All characters from the start (index 0) up to the random index
        String part1 = mutablePool.substring(0, randomIndex);
        
        // Part 2: All characters starting AFTER the random index
        String part2 = mutablePool.substring(randomIndex + 1);
        
        // Reassign the pool to the new, shorter string (the deletion)
        mutablePool = part1 + part2;
    }
    
    return randomAna;
}
}