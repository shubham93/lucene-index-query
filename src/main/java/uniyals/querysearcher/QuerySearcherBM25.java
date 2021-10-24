package uniyals.querysearcher;

import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.similarities.BM25Similarity;

// Class to run querySearching based on BM25Similarity
public class QuerySearcherBM25 extends QuerySearcher {

	public String getResultFileName() {
		switch(this.analyzerName) {
		case "Standard":
			return "../result-standard-bm25-similarity.txt";
		case "English":
			return "../result-english-bm25-similarity.txt";
		default:
			return "../result-standard-bm25-similarity.txt";
		}
	}

	public void configureSimilarity(IndexSearcher isearcher) {
		System.out.println("Processing BM25 Similarity for " + this.analyzerName);
		isearcher.setSimilarity(new BM25Similarity());
	}
}
