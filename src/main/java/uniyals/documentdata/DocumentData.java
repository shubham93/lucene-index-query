package uniyals.documentdata;

import java.util.ArrayList;
import java.util.Arrays;

// Class to maintain DocumentData to be inserted for indexing
public class DocumentData {
	
	
	// ENUM used to distinguish the current attribute when reading the file
	public static enum AttributeType {
		INDEX,
		TITLE,
		WORK,
		AUTHOR,
		BIBLOGRAPHY
	}

	private String index;
	private String title;
	private String author;
	private String biblography;
	private String work;
	private AttributeType currentAttribute;
	

	
	public static ArrayList<String> idenitifierList = new ArrayList<String>(Arrays.asList(".I",".T", ".A", ".B", ".W"));

	public String getIndex() {
		return index;
	}

	public void setIndex(String index) {
		this.index = index;
	}

	public String getAuthor() {
		return this.author != null ? this.author : "";
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getBiblography() {
		return this.biblography != null ? this.biblography : "";
	}

	public void setBiblography(String biblography) {
		this.biblography = biblography;
	}
	
	public void appendBiblography(String biblography) {
		if(this.biblography ==null) {
			this.setBiblography(biblography);
		} else {
			this.biblography = this.biblography + " " + biblography;
		}
	}

	public String getWork() {
		return this.work != null ? this.work : "";
	}

	public void setWork(String work) {
		this.work = work;
	}
	
	public void appendWork(String work) {
		if(this.work == null) {
			this.setWork(work);
		} else {
			this.work = this.work + " " + work;
		}
	}

	public String getTitle() {
		return this.title != null ? this.title : "";
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public void appendTitle(String title) {
		if(this.title == null) {
			this.setTitle(title);
		} else {
			this.title = this.title + " " + title;
		}
	}
	

	public AttributeType getCurrentAttribute() {
		return currentAttribute;
	}

	public void setCurrentAttribute(AttributeType currentAttribute) {
		this.currentAttribute = currentAttribute;
	}
	
	// Checks whether the string is an identifier for class attribute to be set
	public static Boolean isElementPresentInDataList(String txt) {
		return DocumentData.idenitifierList.contains(txt);
	}
	
	// Returns what attribute to be set based on attribute type
	public AttributeType getAttributeType(String txt) {
		String actualText = txt.trim();
		if(actualText.contains(".T")) {
			return AttributeType.TITLE;
		} else if (actualText.contains(".A")) {
			return AttributeType.AUTHOR;
		} else if (actualText.contains(".B")) {
			return AttributeType.BIBLOGRAPHY;	
		} else if (actualText.contains(".W")) {
			return AttributeType.WORK;	
		} 
		else {
			return AttributeType.INDEX;
		}
	}
	
	// sets current attribute data 
	public void setCurrentAtrributeData(String txt) {
		switch(this.currentAttribute) {
		case TITLE:
			this.appendTitle(txt);
			break;
		case AUTHOR:
			this.setAuthor(txt);
			break;
		case BIBLOGRAPHY:
			this.appendBiblography(txt);
			break;
		case WORK:
			this.appendWork(txt);
			break;
		default:
			this.setIndex(txt);
			break;
		}
	}
	
	
	
}
