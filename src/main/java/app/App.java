package app;

import java.io.IOException;

import org.apache.lucene.queryparser.classic.ParseException;

import uniyals.createIndex.CreateIndexEnglish;
import uniyals.createIndex.CreateIndexStandard;
import uniyals.querysearcher.QuerySearcherBM25;
import uniyals.querysearcher.QuerySearcherMulti;
import uniyals.querysearcher.QuerySearcherStandard;
import uniyals.querysearcher.QuerySearcherVsm;

public class App {
   public static void main( String[] args ) throws IOException, ParseException
    {
	   // Standard Analyzer
	   CreateIndexStandard standardIndex = new CreateIndexStandard(args[0]);
	   standardIndex.configureAnalyser();
	   standardIndex.processIndex();
	   
	   // Standard query searcher for Standard
	   QuerySearcherStandard standardSearcher = new QuerySearcherStandard();
	   standardSearcher.setQueryList(args[1]);
	   standardSearcher.configureAnalyser("Standard");
	   standardSearcher.generateResultFile();
	   
	   // VSM query Searcher for Standard
	   QuerySearcherVsm vsmSearcher = new QuerySearcherVsm();
	   vsmSearcher.setQueryList(args[1]);
	   vsmSearcher.configureAnalyser("Standard");
	   vsmSearcher.generateResultFile();
	   
	   // BM25 query Searcher for Standard
	   QuerySearcherBM25 bmSearcher = new QuerySearcherBM25();
	   bmSearcher.setQueryList(args[1]);
	   bmSearcher.configureAnalyser("Standard");
	   bmSearcher.generateResultFile();
	   
	   // Multi query Searcher for Standard
	   QuerySearcherMulti multiSearcher = new QuerySearcherMulti();
	   multiSearcher.setQueryList(args[1]);
	   multiSearcher.configureAnalyser("Standard");
	   multiSearcher.generateResultFile();
	   
	   
	   // English Analyzer
	
	   CreateIndexEnglish standardEnglishIndex = new CreateIndexEnglish(args[0]);
	   standardEnglishIndex.configureAnalyser();
	   standardEnglishIndex.processIndex();
	   
	   // Standard query searcher for Standard
	   QuerySearcherStandard standardEnglishSearcher = new QuerySearcherStandard();
	   standardEnglishSearcher.setQueryList(args[1]);
	   standardEnglishSearcher.configureAnalyser("English");
	   standardEnglishSearcher.generateResultFile();
	   
	   // VSM query Searcher for Standard
	   QuerySearcherVsm vsmEnglishSearcher = new QuerySearcherVsm();
	   vsmEnglishSearcher.setQueryList(args[1]);
	   vsmEnglishSearcher.configureAnalyser("English");
	   vsmEnglishSearcher.generateResultFile();
	   
	   // BM25 query Searcher for Standard
	   QuerySearcherBM25 bmEnglishSearcher = new QuerySearcherBM25();
	   bmEnglishSearcher.setQueryList(args[1]);
	   bmEnglishSearcher.configureAnalyser("English");
	   bmEnglishSearcher.generateResultFile();
	   
	   // Multi query Searcher for Standard
	   QuerySearcherMulti multiEnglishSearcher = new QuerySearcherMulti();
	   multiEnglishSearcher.setQueryList(args[1]);
	   multiEnglishSearcher.configureAnalyser("English");
	   multiEnglishSearcher.generateResultFile();
    }
}
