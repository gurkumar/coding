package text.analyzer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

import text.analyzer.sorting.StringSelectionSort;

/**
 * {@link TextAnalyzerImpl} analyzes the text and generates a report which outputs the frequency of words that
 * appear in the text in sorted manner. Words are sorted with primary sort of word length and secondary ASCII sort.
 * 
 *  COLLECTIONS CLASSES ARE NOT USED FOR SORTING. I AM ASSUMING ARRAYS WILL FALL INTO SAME CATEGORY SO
 *  NOT LEVERAGING ARRAYS.SORT USING COMPARATOR/COMPARABLE AS WELL.
 * @author gukumar
 *
 */

public class TextAnalyzerImpl implements TextAnalyzer {
	
  private static final String DEFAULT_DELIMTER = "\\s+";	
  
  /**
   * 
   */
  
  public String analyzeTextAndGenerateReport
                       (String textToAnalyze) throws Exception{
	  return analyzeTextAndGenerateReport (textToAnalyze, DEFAULT_DELIMTER);     
  }
  
  /**
   * <p>
   * {{@link #analyzeTextAndGenerateReport(String, String)} takes into the text to be
   * analyzed and returns the analyzed text which is sorted primarily by length and secondarily by ASCII code
   * </p>
   * <p>
   * Sorting is done without the Collections usage
   * 1. Text is spilt into words based on the delimiter.
   * 2. Each word is scanned for its length.
   * 3. A Map<String, Integer> is created where key is the word and value is the word count
   * 4. if the text has duplicte words, than same entry in Map is used and count is incremeneted by 1.
   * 5. Step 4 eliminates duplicate words to be outputted, instead count of how many times word appears is
   *    generated.
   * </p>
   * @param textToAnalyze
   * @param textDelimiter
   * @return String 
   */
  	
  public String analyzeTextAndGenerateReport 
           (String textToAnalyze, String textDelimiter) throws Exception{
		
	 try {	
		/** precondition checks **/ 
		if (textToAnalyze.isEmpty()) {
		   return "";	
		}
		Map <String , Integer> wordCount = new HashMap<String, Integer> ();
		
		String[] words = textToAnalyze.split(textDelimiter);
		
	    for (int i=0; i < words.length; i++) {
			
			int count = wordCount.containsKey(words[i]) ? wordCount.get(words[i]) : 0;
			
			wordCount.put (words[i], count + 1);

		}
	    
	    if ( !wordCount.isEmpty()) {
	    	
	       return sortTextByLengthAndASCIIOrder (wordCount);
	       
	    }
	    
	  } catch (Exception exception) {
		 
		  throw new Exception ("An error occured during Text Analysis!!");   
	  } 
	 
	 return null;
	 
   }
  
  /**
   * <p>
   * {{@link #sortTextByLengthAndASCIIOrder(Map)} takes in the HashMap<String, Integer) where key is
   * word and value is word count and returns a sorted analyzed text.
   * </p>
   * <p>
   * 1. {{@link #sortByLength(Set)} is called on keys of the input (words from text).
   * 2. Map <Integer,List<String> is returned from {{@link #sortByLength(Set)} where key is the 
   *    length of the word and value is the list of Strings with same length. TreeMap is used to
   *    preserve the sorted order of keys using length.
   * 3. Now that map is sorted using length, further ASCII order sorting is applied by indexing each length
   *    and sorting List of Strings with same length by calling sortStrings in {@link StringSelectionSort}.
   * 4. StringBuilder is created by appending the word count from the wordCount Map which is input to this method.
   *    and the list of strings obtained after sorting.     
   * </p>
   * @param wordCount
   * @return analyzedText
   */
  
   private String sortTextByLengthAndASCIIOrder (Map <String, Integer> wordCount) {
	    
	    if ( ! wordCount.isEmpty() ) {
	    	
	    	/** ResultingMap is TreeMap where key is length and value is List<String> of same length**/
	    	Map <Integer, List<String>> sortedWords = sortByLength (wordCount.keySet());
	    	
	    	if (! sortedWords.isEmpty()) {
	    		
	    		StringBuilder sortedStringBuilder = new StringBuilder ();
	    		
	    		Iterator<Entry<Integer, List<String>>> iter = sortedWords.entrySet().iterator();
	        
	    	    while (iter.hasNext()) {
	    	    	
	                 Map.Entry<Integer, List<String>> pairs = (Map.Entry<Integer, List<String>>)
	                		                                iter.next();
	            
	                 if (pairs.getValue() != null) {
	                	 
	                	List<String> sameLengthStringList = (List<String>)pairs.getValue();
	                 
	                	 String[] arrayToSort = new String[sameLengthStringList.size()];
	                	 
	                	 if (arrayToSort != null 
	                			 && arrayToSort.length >0 ) {
	                	 
	                		 /** sorting the list of strings using ASCII order**/
	                	     String[] sortedStrings = StringSelectionSort.
	                		    		   sortStrings (sameLengthStringList.toArray(arrayToSort));
	                	     
	                		 for (int i =0 ;i < sortedStrings.length ; i++) {
	                		
	                			  /** appending the word count **/
	                			  sortedStringBuilder.append (wordCount.get(sortedStrings[i]));
	                			  
	                			  sortedStringBuilder.append (" ");
	                			  
	                			  /** appending the string**/
	                			  sortedStringBuilder.append (sortedStrings[i]);
	                			  
	                			  sortedStringBuilder.append(System.getProperty("line.separator")); 
	                		 }		  
	                		                	                		 
	                	 } 
	            
	                 } 	 
	                 iter.remove(); // avoids ConcurrentModificationException
	            }	
	    	    
	    	    return sortedStringBuilder.toString();
	    	}    
	    	    
	    } 	
	   
      return null;
	     
	}
	
	/**
	 * {@link #sortWordsByLength()} method takes in a set of words and sorts them by length.
	 * A sorted Map <Integer, List> is created where Integer is the length of word and List is words 
	 * with same length.
	 * @param wordSet
	 * @return
	 */
   
	private Map <Integer,List <String>> sortByLength (Set <String> wordSet) {

	   Map <Integer ,List <String>> wordSort = new TreeMap <Integer , List <String>> ();
		   
	   Iterator <String> wordIterator = wordSet.iterator();	
	     
	   while ( wordIterator.hasNext() ) {
	    	  
	         String word = wordIterator.next ();
	         
	         int key = word.length();
	           
	         if (wordSort.containsKey(key)) {
	        	 
	        	List <String> wordList = wordSort.get (key);
	        	
	        	wordList.add(word);
	        	
	        	wordSort.put(key, wordList);
	        	 
	         } else {
	        	
	           List <String> wordList = new ArrayList <String> ();
	           
	           wordList.add(word);
	           
	           wordSort.put (key, wordList);
	        	 
	         }  	 
	    	  
	    }
	    
	    return wordSort;
	    
	 }
	
	
	

}
