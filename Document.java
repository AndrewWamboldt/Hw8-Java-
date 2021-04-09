/**
 * A class that represents a Document, which is the name of the file and its ID.
 * DO NOT MODIFY THIS CLASS!
 */
public class Document {

	private final int docID;
	private final String docName;
	
	/**
	 * Constructor that sets the id and name of a document.
	 * @param id
	 * @param name
	 */
	public Document(int id, String name) {
		docID = id;
		docName = name;
	}
	
	/**
	 * Get the name of the document.
	 * @return Document name
	 */
	public String getName() {
		return this.docName;
	}
	
	/**
	 * Get the ID of the document.
	 * @return Document ID
	 */
	public int getID() {
		return this.docID;
	}
	
	@Override
	public String toString() {
		return "" + docID;
	}
	
	@Override
	public boolean equals(Object other) {
		if(other instanceof Document) {
			Document tmpDoc = (Document) other;
			return tmpDoc.docID == docID && tmpDoc.docName.equals(docName);
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		return docID * 31 + docName.hashCode();
	}
	
}
