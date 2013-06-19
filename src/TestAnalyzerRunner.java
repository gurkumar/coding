import java.util.Scanner;

import text.analyzer.TextAnalyzer;
import text.analyzer.TextAnalyzerImpl;




public class TestAnalyzerRunner {
	
	
	public static void main (String args[]) throws Exception {
			 
	      Scanner in = new Scanner(System.in);
	 
	      System.out.println("Enter the text to be analyzed:");
	      
	      TextAnalyzer textAnalyser = new TextAnalyzerImpl();
	      
	      String analyzedText = textAnalyser.analyzeTextAndGenerateReport(in.nextLine());
	      
	      System.out.println("Output:");
	      
	      System.out.println (analyzedText);
	 
	     
	     
	}

}
