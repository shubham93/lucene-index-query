package uniyals.querysearcher;

import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.similarities.ClassicSimilarity;

//Class to run querySearching based on ClassicSimilarity
public class QuerySearcherVsm extends QuerySearcher {

	public String getResultFileName() {
		switch(this.analyzerName) {
		case "Standard":
			return "../result-standard-vsm-similarity.txt";
		case "English":
			return "../result-english-vsm-similarity.txt";
		default:
			return "../result-standard-vsm-similarity.txt";
		}
	}
	
	public void configureSimilarity(IndexSearcher isearcher) {
		System.out.println("Processing VSM Similarity for " + this.analyzerName);
		isearcher.setSimilarity(new ClassicSimilarity());
		
	}

}
