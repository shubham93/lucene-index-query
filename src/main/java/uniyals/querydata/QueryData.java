package uniyals.querydata;
import java.util.ArrayList;
import java.util.Arrays;

import org.apache.lucene.search.ScoreDoc;


// Class to maintain queryData to be inserted in documents
public class QueryData {
	
	// ENUM to maintain to which field is the query for
	public static enum QueryTarget {
		TITLE,
		WORK,
		AUTHOR,
		BIBLOGRAPHY
	}
	
	private String id;
	private String query;
	private ScoreDoc[] hits;
	private QueryTarget queryTarget;
	
	static ArrayList<String> idenitifierList = new ArrayList<String>(Arrays.asList(".I", ".W")); 
	
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getQuery() {
		return query;
	}
	
	public void setQuery(String query) {
		this.query = query;
	}
	
	public void appendQuery(String query) {
		if(this.query == null) {
			this.setQuery(query);
		} else {
			this.query = this.query + " " + query;
		}
	}

	public ScoreDoc[] getHits() {
		return hits;
	}

	public void setHits(ScoreDoc[] hits) {
		this.hits = hits;
	}
	
	// Checks whether the string is identifier for attribute
	public static Boolean isElementPresentInDataList(String txt) {
		return QueryData.idenitifierList.contains(txt);
	}

	public QueryTarget getQueryTarget() {
		return queryTarget;
	}

	public void setQueryTarget(QueryTarget queryTarget) {
		this.queryTarget = queryTarget;
	}
	
	// Sets the query target
	public QueryTarget getQueryTargetData(String txt) {
		String actualText = txt.trim();
		if(actualText.contains(".W")) {
			return QueryTarget.WORK;
		} else if (actualText.contains(".A")) {
			return QueryTarget.AUTHOR;
		} else if (actualText.contains(".B")) {
			return QueryTarget.BIBLOGRAPHY;	
		} else  {
			return QueryTarget.WORK;	
		} 
	}
	
	// Gets the query target value
	public String getQueryTargetValue() {
		switch(this.queryTarget) {
		case WORK:
			return "work";
		case TITLE:
			return "title";
		case AUTHOR:
			return "author";
		case BIBLOGRAPHY:
			return "biblography";
		default:
			return "work";
		}
	}
	
}
