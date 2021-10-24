package uniyals.querysearcher;

import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.similarities.BM25Similarity;
import org.apache.lucene.search.similarities.ClassicSimilarity;
import org.apache.lucene.search.similarities.MultiSimilarity;
import org.apache.lucene.search.similarities.Similarity;

//Class to run querySearching based on BM25Similarity and ClassicSimilarity
public class QuerySearcherMulti extends QuerySearcher {

	public String getResultFileName() {
		switch(this.analyzerName) {
		case "Standard":
			return "../result-standard-multi-similarity.txt";
		case "English":
			return "../result-english-multi-similarity.txt";
		default:
			return "../result-standard-multi-similarity.txt";
		}
	}

	public void configureSimilarity(IndexSearcher isearcher) {
		System.out.println("Processing Multi Similarity for " + this.analyzerName);
		isearcher.setSimilarity(new MultiSimilarity(new Similarity[]{new BM25Similarity(),new ClassicSimilarity()}));
	}

}
