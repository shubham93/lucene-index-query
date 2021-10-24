package uniyals.createIndex;

import java.io.IOException;

import org.apache.lucene.analysis.en.EnglishAnalyzer;

import utils.Utils;

// Class to create English Analyzer
public class CreateIndexEnglish extends CreateIndex {
	
	public CreateIndexEnglish(String arg) {
		this.arg = arg;
	}
    
    public void configureAnalyser() throws IOException {
    	System.out.println("Processing English Analyser");
        Utils utils = new Utils();
        utils.addCustomStopSet();
		this.analyzer = new EnglishAnalyzer(utils.stopSet);
    }
}
