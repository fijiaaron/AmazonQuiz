package amazon.quiz;

public class PalindromeDetector {
	public static boolean IGNORE_SPACING = true;
	public static boolean IGNORE_CAPITALIZATION = true;
	public static int MIN_LENGTH = 3;
	
	public static boolean isPalindrome(String candidate) {
		if (candidate == null || candidate.length() < MIN_LENGTH) {
			return false;
		}
		
		// check with irregular cases and spacing
		if (IGNORE_CAPITALIZATION) {
			candidate = candidate.toUpperCase();
		}
		
		if (IGNORE_SPACING) {
			candidate = candidate.replaceAll(" ", "");
		}

		if (candidate.equals(reverse(candidate))) {
			return true;
		}
		return false;
	}
	
	private static String reverse(String string) {
		return reverseBestAlgorithm(string);
		//return reverseInefficient(string);
		//return reverseCheat(string);
	}
	
	private static String reverseBestAlgorithm(String s) {
		for(int i=0; i < s.length() / 2; i++) {
			char temp = s.charAt(i);
			int position = s.length() - (i+1);
			/** because string are immutable in java, this isn't really efficient **/
			s = s.replace(s.charAt(i), s.charAt(position));
			s = s.replace(s.charAt(position), temp);
		}
		
		return s;
	}
	

	private static String reverseInefficient(String s) {
		StringBuilder reversed = new StringBuilder();
		for (int i=1; i <= s.length() ; i++) {
			reversed.append(s.charAt(s.length() - i));
		}
		
		return reversed.toString();
	}
	
	private static String reverseCheat(String s) {
		return new StringBuilder(s).reverse().toString();
	}
}
