package amazon.quiz;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class DuplicateDetectorTest {
	
	@DataProvider(name = "intArrays")
	public Object[][] scenarios() {
		Object[][] data = new Object[][] {
			{ "null array", null, new int[] {}},
			{ "empty array", new int[] {}, new int[] {} }, 
			{ "one match", new int[] {1,2,1}, new int[] {1} },
			{ "two matches", new int[] {1,2,1,2}, new int[] {1,2}  },
			{ "match a triple", new int[] {1,2,3,1,4,1}, new int[] {1} },
			{ "match a double and triple", new int[] {1,2,1,3,1,2}, new int[] {1,2} },
			{ "match a negative number", new int[] {1,2,-7,3,-7}, new int[] {-7} },
			{ "many matches out of order", new int[] {1,3,3,2,5,4,3,2,7,6,5,9,7,8,2,3,2,3,8}, new int[] {3,2,5,7,8} },
			{ "match a lot of zeros", new int[] {0,0,0,0,0,0}, new int[] {0} },
		};
		
		return data;
	}
	
	@Test(dataProvider = "intArrays")
	public void testScenarios(String description, int[] original, int[] expected) {
		int[] duplicates = DuplicateDetector.find(original);
		
		assertEquals("should be same length", duplicates.length, expected.length);
		// needed to borrow this from junit
		// I can't believe hamcrest (or testng) can't compare arrays
		assertArrayEquals("should have same contents", duplicates, expected);
	}
}
