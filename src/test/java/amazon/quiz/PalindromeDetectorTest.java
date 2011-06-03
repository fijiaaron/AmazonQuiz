package amazon.quiz;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

public class PalindromeDetectorTest {

	enum Result {
		PASS, FAIL, EXCEPTION, UNDEFINED;
	}
	
	@DataProvider(name = "palindromes")
	public Object[][] validPalindromes() {
		Object[][] data = new Object[][] {
				{"mom"},
				{"level"},
				{"poop"},
				{"RADAR"},
		};
		
		return data;
	}
	
	@DataProvider(name = "not-palindromes")
	public Object[][] notPalindromes() {
		Object[][] data = new Object[][] {
				{"cat"},
				{"wobbly"},
				{"SONAR"},
				{"blue"},
		};
		
		return data;
	}
	
	
	@DataProvider(name = "tricky-cases")
	public Object[][] tricky() {
		Object[][] data = new Object[][] {
				{"Dad", Result.PASS}, // capitalization
				{"R.A.D.A.R.", Result.FAIL}, // punctuation
				{"123321", Result.PASS}, // numbers
				{" kook ", Result.PASS}, // spaces
				{"Satan oscillate my metallic sonatas", Result.PASS}, // irregular spacing
				{"A", Result.FAIL}, // single letter
				{"", Result.FAIL}, // empty string
				{null, Result.FAIL}, // null
				{"dàd", Result.PASS}, // accented character
				{"adidà", Result.FAIL},

		};
		
		return data;
	}
	
	@DataProvider(name ="invalid-types")
	public Object[][] types() {
		Object[][] data = new Object[][] {
				{'a'}, // char
				{1}, // int
				{999.999}, // float
				{PalindromeDetector.class}, // class
				{new String[] {"ko", "ok"}}, // array
				{new StringBuilder("palindrome")},  
		};
		
		return data;
	}
	
	@Test(dataProvider="palindromes") 
	public void testValidPalindromes(String palindrome) {
		assertTrue(PalindromeDetector.isPalindrome(palindrome));
	}
	

	@Test(dataProvider="not-palindromes") 
	public void testInvalidPalindromes(String nonpalindrome) {
		assertFalse(PalindromeDetector.isPalindrome(nonpalindrome));
	}
	
	@Test(dataProvider="tricky-cases", enabled=true) 
	public void testUndefined(String uncertain, Result expected) {
		//see assumptions
		if (expected == Result.PASS) {
			assertTrue(PalindromeDetector.isPalindrome(uncertain));
		} else {
		assertFalse(PalindromeDetector.isPalindrome(uncertain));
		}
	}
	
	@Test
	public void testMinLength() {
		assertFalse(PalindromeDetector.isPalindrome("A"));
		assertFalse(PalindromeDetector.isPalindrome("OO"));
		assertTrue(PalindromeDetector.isPalindrome("EEE"));
	}	
	
	@Test(dataProvider = "invalid-types", expectedExceptions = Exception.class)
	public void testInvalidTypes(String type) {
		PalindromeDetector.isPalindrome(type);
	}
}
