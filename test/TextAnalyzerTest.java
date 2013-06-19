


import static org.junit.Assert.*;

import org.junit.Test;

import text.analyzer.TextAnalyzer;
import text.analyzer.TextAnalyzerImpl;


public class TextAnalyzerTest {
	
	@Test
	public void testTextAnalyzer () throws Exception{
		
		TextAnalyzer textAnalyzer = new TextAnalyzerImpl ();
		
		String inputString = "The quick brown fox jumped over the lazy brown dog's back";
		
		String analyzedText = 
				      textAnalyzer.analyzeTextAndGenerateReport(inputString);
		
		assertNotNull (analyzedText);

		String outputString = "1 The\r\n1 fox\r\n1 the\r\n1 back\r\n1 lazy\r\n1 over\r\n2 brown\r\n1 dog's\r\n1 quick\r\n1 jumped\r\n";
		
		assertEquals (analyzedText, outputString);
		
		
	}

}
