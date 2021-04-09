
//import java.awt.List;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * Class that creates a reversed index. You must finish implementing this class.
 */
public class Indexer {

	/**
	 * Create the four instance variables needed for this class var1) reversedIndex
	 * -is a Map that maps a Token to a List of Documents var2) allTokens -is a Map
	 * that maps a String to a Token -use this to determine no duplicate Tokens are
	 * created var3) allDocs -is a Map that maps a string to a Document -use this to
	 * determine no duplicate Documents are created var4) assignID -A int to assign
	 * docIDs -Should increment with each new document created
	 */

	// TODO - Declare all instance variables.

	public HashMap<Token, List> reverseIndex = new HashMap<Token, List>();
	public HashMap<String, Token> allTokens = new HashMap<String, Token>();
	public HashMap<String, Document> allDocs = new HashMap<String, Document>();
	public int assignID = 0;

	/**
	 * Constructor takes no arugments. Simply intializes all instance variables.
	 * 
	 * @param reverseIndex
	 * @param allDocs
	 * @param allTokens
	 * @param assignID
	 */
	public Indexer() {
		// TODO

	}

	/**
	 * A method used to add a String document to the Reversed Index. Pay attention
	 * to the return types of each method. You must 1) Make sure File isn't null.
	 * Get the File name. A File object is passed in, so there may some helpful
	 * methods already available. 2) Use the Map allDocs to make sure the document
	 * name(a String) currently being checked has not been added already. If it has
	 * been seen, simply return. We do not want to add duplicate docs. If it hasn't
	 * been added, update allDocs and proceed to the next steps. 3) Parse out the
	 * file contents. You will need to use a Scanner to read the file contents. 4)
	 * Break the file contents into individual Strings. 5) Loop over all String
	 * contained in a file. a) Call removePunctuation(String str) on each String to
	 * remove all punctuation. This method returns a new String. b) Call
	 * checkToken(String str) on the formatted String. This method returns a Token.
	 * c) Update positional index for the token returned from checkToken. d) Update
	 * the reversedIndex so the term refers to the correct documents.
	 * 
	 * @param docString
	 */
	int keyValue = 0;

	public void indexDocument(File f) {

		String docName = "";
		docName = f.getName();
		String[] words;
		if (f.equals(null)) {
			return;
		}
		if (allDocs.containsKey(docName)) {
			return;
		}

		++keyValue;

		Document holder = new Document(keyValue, docName);
		allDocs.put(docName, holder);

		try (Scanner scr = new Scanner(Paths.get(f.getName()))) {
			int wordCount = 1;
			while (scr.hasNextLine()) {
				String output = scr.nextLine();
				words = output.split(" ", output.length());

				for (int i = 0; i < words.length; i++) {

					words[i] = removePunctuation(words[i]);

					System.out.println(words[i] + wordCount);
					Token newToken = checkToken(words[i]);
					newToken.setPositions(holder, wordCount);

					updateReversedIndex(newToken, holder);
					wordCount++;

				}

				System.out.println(output);

				// removePunctuation(output);
				// checkToken(output);

			}

			scr.close();
		} catch (IOException e) {
			System.out.println("Error opening " + f.getName());

//			} catch (NullPointerException e) {
//				System.out.println("farts " + f.getName());

		}

	}

	/**
	 * Remove all punctuation and convert String to lower case. Surround white space
	 * should also be removed. Punctuation to remove: "," "." "!" "?"
	 * 
	 * @param str
	 * @return a formatted String
	 */
	protected String removePunctuation(String str) {
		String removedP = str.toLowerCase();

		removedP = removedP.replaceAll("[\\.$|,|!|?]", "");

		removedP = removedP.trim();

		return removedP;
	}

	/**
	 * Use allTokens to see if the String passed in currently is a token. If it is
	 * there, it just returns the token. If it isn't, what set of operations should
	 * happen? This method should never return null, meaning a Token object is
	 * always returned.
	 * 
	 * @param str
	 * @return Token
	 */
	protected Token checkToken(String str) {

		if (allTokens.containsKey(str)) {

			return allTokens.get(str);
		}

		Token newtoken = new Token(str);
		allTokens.put(str, newtoken);
		return newtoken;
	}

	/**
	 * Use reversedIndex to see if the Token is part of the reversedIndex. Remember,
	 * the reversedIndex maps a Token to a List of Documents. If the token is there,
	 * what should the Token be mapped to? If the Token isn't there, is the Token
	 * mapped to anything? What should happen if it isn't mapped to anything? If the
	 * List of Documents mapped to by the Token does not contain the Document passed
	 * in, add it to the list. Make sure no duplicate docs are added to the List of
	 * Documents. When the List of Documents is updated, update the reversedIndex.
	 * 
	 * @param token
	 * @param doc
	 */
	protected void updateReversedIndex(Token token, Document doc) {
		// TODO
		List<Document> docList = reverseIndex.get(token);

		if (docList == null) {
			docList = new ArrayList<Document>();
			reverseIndex.put(token, docList);
		}
		if (!(docList.contains(doc))) {
			docList.add(doc);
		}
//		if (!(reverseIndex.containsKey(token))) {
//			List<Document> DocList = new ArrayList<Document>();
//			
//			if(!(DocList.contains(doc))) {
//				DocList.add(doc);
//				reverseIndex.put(token, DocList);
//			}
//			DocList.add(doc);
//			reverseIndex.put(token, DocList);
//		}

	}

	/**
	 * Use the reversedIndex to answer the query and print out the list of documents
	 * that contain the term. If the query is not in the reversedIndex, Simply print
	 * out the query was not found. If the query is there: 1) Print out the token
	 * name and the list of documents containing the query. You may use the built in
	 * toString method for Lists to print them out. 2) For each document the query
	 * appears in ,print out each docID followed by the query location(s) for each
	 * document. See the driver output in the homework description for example
	 * output.
	 * 
	 * @param query
	 */
	public void singleQuery(String query) {

		if (allTokens.containsKey(query)) {
			Token token = allTokens.get(query);

			System.out.println("Single Query: " + token.toString());
			ArrayList<Document> tmpDocList = (ArrayList<Document>) reverseIndex.get(token);
			System.out.println("Documents containing:\"" + token.toString() + "\": " + tmpDocList);
			for (int i = 0; i < tmpDocList.size(); i++) {
				System.out.println(
						"Doc ID: " + tmpDocList.get(i) + " DocPositions: " + token.getPositions(tmpDocList.get(i)));
			}

			// Single Query: fonz
//			Documents containing "fonz": [6, 9]
//			DocID: 6 , DocPositions = [23]
//			DocID: 9 , DocPositions = [20]

		}
		// TODO - Everyone must complete this method
	}

	/**
	 * Extra Credit. Write the code to perform a two word query. This should print
	 * out a result if both words in the query occur next to each other.
	 * 
	 * @param query
	 */
	public void twoWordQuery(String[] query) {
		// TODO - Extra credit - must be complete and fully functional to recieve credit
	}

	/**
	 * A method that prints out all Documents that have been seen in order.
	 */
	public void printOutAllDocs() {
		Map<Integer, String> tmpHash = new HashMap<Integer, String>();
		for (Map.Entry<String, Document> entry : allDocs.entrySet()) {

			tmpHash.put(entry.getValue().getID(), entry.getKey());
		}

		System.out.println(allDocs.toString());
		System.out.println(tmpHash);
	}

	/**
	 * Save the contents of the Index. Display should go as follows.
	 * 
	 * Token DocID : Locations in Document Repeat for all Tokens.
	 * 
	 * The Tokens should be printed out in alphabetical order.
	 */
	public void save(String fileName) {
		// TODO
	}

}
