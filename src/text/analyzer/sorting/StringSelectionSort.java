package text.analyzer.sorting;

/**
{@link StringSelectionSort} classes sorts the strings lexicographicaly based on their ASCII codes
This class is used in place of Collections 
**/

public class StringSelectionSort {

	/**
	 * {{@link #sortStrings(String[])} sorts the strings using Selection sort. compareTo method of
	 * String class does ASCII based comparison.
	 * @param stringArray
	 * @return
	 */
	public static String[] sortStrings (String[] stringArray) {
		
		for (int i=0; i<stringArray.length; i++) {
			
			int s = i;
			
			for (int j= i + 1 ; j<stringArray.length ; j++) {
				
				if (stringArray[j].compareTo(stringArray[s])<0) {
					
					s = j;
					
				}
				
			}
			
			String temp = stringArray [i];
			stringArray[i] = stringArray [s];
			stringArray[s] = temp;
			
		}
		
		return stringArray;
	}
	

}
