package utils;

import java.util.Arrays;
import java.util.List;

import org.apache.lucene.analysis.CharArraySet;
import org.apache.lucene.analysis.en.EnglishAnalyzer;

public class Utils {
	
	public CharArraySet stopSet = CharArraySet.copy(EnglishAnalyzer.ENGLISH_STOP_WORDS_SET);
	
	private List<String> customList = Arrays.asList("how", "do", "when", "any", "has", "have", "from", "which", "why", "find",  "you", "can", "get",  "does", "what", "can't", "far", "anyone", "information", "references", "without", "however", "papers", "else", "progress", "investigations", "readily", "possible", "parameters", "available", "likely", "been", "work", "report", "solutions", "problem", "applicable", "done", "close");
	
	public void addCustomStopSet() {
		this.stopSet.addAll(customList);
	}
   
}
