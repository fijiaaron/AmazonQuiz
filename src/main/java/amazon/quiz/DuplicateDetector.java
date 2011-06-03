package amazon.quiz;

import java.util.ArrayList;
import java.util.List;

public class DuplicateDetector {
	public static int[] find(int[] array) {
		
		// no matches for empty array
		if (array == null || array.length == 0 ) {
			return new int[0]; 
		} 

		// no matches for a single element
		if (array.length == 1 ) {
			return new int[0];
		}

		List<Integer> original = intArrayToList(array);
		List<Integer> duplicates = new ArrayList<Integer>();

		while (! original.isEmpty()) {
			
			// remove the first item from the list
			int candidate = original.remove(0);
			
			// and check to see if there's a duplicate
			if (original.contains(candidate)) {
				
				// don't need to add candidate if it's already there
				if (! duplicates.contains(candidate)) {
					duplicates.add(candidate);
				}
				
				// NOTE: this could be improved by removing all duplicates we find in one pass
				// but that would require iteration
			}
		}
		
		return intListToArray(duplicates);
	}
	
	/**
	 * Java, how we love thee.  
	 * Guava and Commons have a way to do this but I didn't want to add a dependency
	 * 
	 * @param list
	 * @return
	 */
	public static int[] intListToArray(List<Integer> list) {
		int[] array = new int[list.size()];
		
		for(int i=0; i<array.length; i++) {
			array[i] = list.get(i);
		}
		
		return array;
	}
	
	/**
	 *  I use this in the test so it's public
	 * 
	 * @param array
	 * @return
	 */
	public static List<Integer> intArrayToList(int[] array) {
		List<Integer> list = new ArrayList<Integer>();
		
		for (int i=0; i<array.length; i++) {
			list.add(array[i]);
		}
		
		return list;
	}
	
	///////////////////////////////////////////
	// This is a more primitive implementation
	///////////////////////////////////////////
	
//	public static int[] primitiveFind(int[] array) {
//		int size = array.length;
//		int[] duplicates = new int[size];
//		int count = 0;
//		
//		for (int i=0; i < size; i++) {
//			for (int j=0; j < size; j++) {
//				if (array[i] == array[j]) {
//					duplicates[count] = array[i];
//					count++;
//					break; // we don't need to keep searching, we've found a duplicate
//					// note, we still could end up with multiples with more than 1 duplicate
//				}
//			}
//		}
//		
//		return intTrimArray(duplicates, count);
//	}
//	
//	
//	/**
//	 * Removes any empty values at the end of an array
//	 * 
//	 * @param array
//	 * @return
//	 */
//	private static int[] intTrimArray(int[] array, int size) {
//		int[] trimmed = new int[size];
//		boolean hasZero = false; // there should only be 1 zero -- use this to detect padding
//		
//		for(int i=0; i<array.length; i++) {
//			if (array[i] == 0) {
//				if (hasZero == false) {
//					trimmed[i] = array[i];
//					hasZero = true;
//				}
//			} else {
//				trimmed[i] = array[i]; 
//			}
//		} 
//			
//		return trimmed;
//	}
}
