package uniyals.querysearcher;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.ArrayList;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.en.EnglishAnalyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;

import org.apache.lucene.document.Document;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.tartarus.snowball.ext.PorterStemmer;

import uniyals.querydata.QueryData;
import utils.Utils;

import org.apache.lucene.index.DirectoryReader;


import org.apache.lucene.search.IndexSearcher;

import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.queryparser.classic.ParseException;


// Abstract Class to generate query resylts based on analyser and Similarity
public abstract class QuerySearcher {
	// the location of the search index
	private static String INDEX_DIRECTORY = "../index";
		
	// Limit the number of search results we get
	private static int MAX_RESULTS = 999;
	
	public String arg;
	public Analyzer analyzer;
	public String analyzerName;
	ArrayList<QueryData> queryList = new ArrayList<QueryData>();
	
	
//  Abstract Method for Getting Result name
	public abstract String getResultFileName();

//  Abstract Method for Configuring Similarity
	public abstract void configureSimilarity(IndexSearcher isearcher);
	
	public void configureAnalyser(String analyzerName) {
		this.analyzerName = analyzerName;
	    Utils utils = new Utils();
	    utils.addCustomStopSet();
	    if(analyzerName == "Standard") {
	    	this.analyzer = new StandardAnalyzer(utils.stopSet);
	    } else {
	    	this.analyzer = new EnglishAnalyzer(utils.stopSet);
	    }
	}
	
	// Method to set query list based on file path in args
	public void setQueryList(String arg) throws IOException {
		try  {
			InputStream inputStream = QuerySearcher.class.getResourceAsStream(arg);
			BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
			String currentLine = null;
			QueryData queryData = null;
			Integer queryId = Integer.valueOf(1);

			while((currentLine = reader.readLine()) != null) {
				if(currentLine.contains(".I")) {
					if(queryData != null) {
						this.queryList.add(queryData);
					}
					queryData = new QueryData();
					queryData.setId(queryId.toString());
					queryId ++;
				} else if(QueryData.isElementPresentInDataList(currentLine)) {
					queryData.setQueryTarget(queryData.getQueryTargetData(currentLine));
				} else {
					queryData.appendQuery(currentLine);
				}
			}
			if(queryData != null) {
				this.queryList.add(queryData);
			}
			
		} catch(Exception e) {
				throw e;
		}
	}
	
	// Method to generate result file based on generated queryList
	public void generateResultFile() throws IOException, ParseException  {
		// Open the folder that contains our search index
		Directory directory = FSDirectory.open(Paths.get(INDEX_DIRECTORY));
		
		// create objects to read and search across the index
		DirectoryReader ireader = DirectoryReader.open(directory);
		IndexSearcher isearcher = new IndexSearcher(ireader);
		this.configureSimilarity(isearcher);
		
		File file = new File(this.getResultFileName());
		FileWriter fw = new FileWriter(file);
	    PrintWriter pw = new PrintWriter(fw);
		for (QueryData queryData: this.queryList) {
			QueryParser parser = new QueryParser(queryData.getQueryTargetValue(), this.analyzer);
		
			// trim leading and trailing whitespace from the query
			String trimmedString = queryData.getQuery().trim();
			
			// Stemming the querystring
			PorterStemmer stemmer = new PorterStemmer();
			stemmer.setCurrent(trimmedString);
			stemmer.stem(); 
			String queryString = stemmer.getCurrent();

			if (queryString.length() > 0)
			{
				// parse the query with the parser with escaped string
				Query query = parser.parse(QueryParser.escape(queryString));

				// Get the set of results
				ScoreDoc[] hits = isearcher.search(query, MAX_RESULTS).scoreDocs;
				queryData.setHits(hits);

				// print the queryOutput to file
				for (int i = 0; i < hits.length; i++)
				{
					Document hitDoc = isearcher.doc(hits[i].doc);
					String queryOutput = queryData.getId() + " Q0 " + hitDoc.get("index") + " 1 " + hits[i].score + " STANDARD";
					pw.println(queryOutput);
//					System.out.println(queryOutput);
				}
			}
				
		}
		
		pw.close();
		ireader.close();
		directory.close();
	}

	
}
