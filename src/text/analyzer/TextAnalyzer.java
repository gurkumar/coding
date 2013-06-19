package text.analyzer;

public interface TextAnalyzer {
	
	String analyzeTextAndGenerateReport (String textToAnalyze, String textDelimiter) throws Exception;
	
	String analyzeTextAndGenerateReport (String textToAnalyze) throws Exception;

}
