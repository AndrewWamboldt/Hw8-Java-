import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * Class that represents a Token, which is a String and all locations a string occurs at in a document.
 * DO NOT MODIFY ANYTHING HERE!
 */
public class Token {

	//The actual word represented as a token
	private final String token;
	//Maps a document to a list of integers.  These integers are the positional index for Token in that document
	private final Map<Document , List<Integer>> positionalIndex;

	/**
	 * Constructor sets String of Token object.
	 * Also initializes HashMap.
	 * @param t
	 */
	public Token(String t) {
		token = t;
		positionalIndex = new HashMap<>();
	}

	/**
	 * Get the positions of the Token for the document passed in.
	 * If the Token has no positions in the document, meaning there is no Map from the doc to the list, return null.
	 * 
	 * @param doc
	 * @return list of integers or null
	 */
	public List<Integer> getPositions(Document doc){
			return positionalIndex.get(doc);
	}
	
	/**
	 * Sets the position of the Token in the document passed in.
	 * If doc already occurs in the postionalIndex, it means the token has already appeared in the document.
	 * If doc doesn't exist in positionalIndex, it means this is the first time the token appeared in the document.
	 * Result is the index of the Token for the Document is added to a new List, or an existing one.
	 * 
	 * @param doc
	 * @param p
	 */
	public void setPositions(Document doc, Integer p) {
		List<Integer> positions;

		if(positionalIndex.containsKey(doc)) {
			positions = positionalIndex.get(doc);
		} else {
			positions = new ArrayList<Integer>();
		}

		positions.add(p);
		positionalIndex.put(doc, positions);

	}
	
	@Override
	public String toString() {
		return token;
	}

	@Override
	public boolean equals(Object other) {
		if(other == null) {
			return false;
		}
		
		if(other == this) {
			return true;
		}

		if(other.getClass() != this.getClass()) {
			return false;
		}

		return token.equals(((Token)other).token);

	}
	
	@Override
	public int hashCode() {
		//Do not include HashMap in hashcode.
		//Hashmap can change as the program runs.
		return token.hashCode() * 31;
	}

}
