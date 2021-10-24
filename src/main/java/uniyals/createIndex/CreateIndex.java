package uniyals.createIndex;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Paths;
import java.util.ArrayList;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import uniyals.documentdata.DocumentData;


// Abstract Class for Creating index using args
public abstract class CreateIndex {
	// Directory where the search index will be saved
	public static String INDEX_DIRECTORY = "../index";
	public String arg;
	public Analyzer analyzer;

	// Abstract method to configure analyzer
	public abstract void configureAnalyser() throws IOException;
	
	// Method to processIndex
    public void processIndex() throws IOException {
		// ArrayList of documents in the corpus
    	ArrayList<Document> documents = new ArrayList<Document>();

		// Open the directory that contains the search index
		Directory directory = FSDirectory.open(Paths.get(INDEX_DIRECTORY));

		// Set up an index writer to add process and save documents to the index
		IndexWriterConfig config = new IndexWriterConfig(this.analyzer);
		config.setOpenMode(IndexWriterConfig.OpenMode.CREATE);
		IndexWriter iwriter = new IndexWriter(directory, config);
		

		System.out.printf("Indexing \"%s\"\n", this.arg);
		documents.addAll(this.getIndexedDocuments(this.arg));
			

		// Write all the documents in the linked list to the search index
		iwriter.addDocuments(documents);

		// Commit everything and close
		iwriter.close();
		directory.close();
    }
    
    // Method to return indexed documents
    public ArrayList<Document> getIndexedDocuments(String arg) throws IOException {
		ArrayList<Document> documents = new ArrayList<Document>();
		try  {
			InputStream inputStream = CreateIndexStandard.class.getResourceAsStream(arg);
			BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
			String currentLine = null;
			Document doc = null;
			DocumentData docData = null;
			while((currentLine = reader.readLine()) != null) {
				if(currentLine.contains(".I")) {
					if(doc != null && docData != null) {
						doc = this.getCompleteDocument(doc, docData);
						documents.add(doc);
					}
					doc = new Document();
					docData = new DocumentData();
					String index = currentLine.split(" ")[1];
					docData.setIndex(index);
				} else if(DocumentData.isElementPresentInDataList(currentLine)) {
					docData.setCurrentAttribute(docData.getAttributeType(currentLine));
				} else {
					docData.setCurrentAtrributeData(currentLine);
				}
			}
			// For the last index
			doc = this.getCompleteDocument(doc, docData);
			documents.add(doc);
			return documents;
			
		} catch(Exception e) {
				throw e;
		}
    }
    
    // Method to get the document to be added in iwriter based on DocData
    
    public Document getCompleteDocument(Document doc, DocumentData docData) {
    	doc.add(new StringField("index", docData.getIndex(), Field.Store.YES));
		doc.add(new TextField("title", docData.getTitle(), Field.Store.YES));
		doc.add(new TextField("author", docData.getAuthor(), Field.Store.YES));
		doc.add(new TextField("biblography", docData.getBiblography(), Field.Store.YES));
		doc.add(new TextField("work", docData.getWork(), Field.Store.YES));
		return doc;
    }
}
