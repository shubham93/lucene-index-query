package uniyals.querysearcher;

import org.apache.lucene.search.IndexSearcher;

//Class to run querySearching based on no Similarity
public class QuerySearcherStandard extends QuerySearcher{

	public String getResultFileName() {
		switch(this.analyzerName) {
		case "Standard":
			return "../result-standard-no-similarity.txt";
		case "English":
			return "../result-english-no-similarity.txt";
		default:
			return "../result-standard-no-similarity.txt";
		}
	}

	public void configureSimilarity(IndexSearcher isearcher) {
		System.out.println("Processing No Similarity for " + this.analyzerName);
	}

}
