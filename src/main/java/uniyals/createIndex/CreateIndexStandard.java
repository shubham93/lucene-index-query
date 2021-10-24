package uniyals.createIndex;
import java.io.IOException;
import org.apache.lucene.analysis.standard.StandardAnalyzer;

import utils.Utils;


//Class to create Standard Analyzer
public class CreateIndexStandard extends CreateIndex
{
	public CreateIndexStandard(String arg) {
		this.arg = arg;
	}
    
    public void configureAnalyser() throws IOException {
    	System.out.println("Processing Standard Analyser");
        Utils utils = new Utils();
        utils.addCustomStopSet();
		this.analyzer = new StandardAnalyzer(utils.stopSet);
    }
    
}
