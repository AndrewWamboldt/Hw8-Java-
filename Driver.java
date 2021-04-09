import java.io.File;
import java.util.Scanner;

/**
 * A Driver to test the Indexer class.
 */
public class Driver {

	/**
	 * A driver that creates a Reversed Index. Fills the Reversed Index with Tokens.
	 * Then performs a query that only works with single terms.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		Scanner scr = new Scanner(System.in);
		Indexer rIndex = new Indexer();

		// TODO - Pass each file in "TextFiles" folder to rIndex
//		rIndex.reverseIndex;
		File a = new File("Brats.txt");
		File b = new File("Brewers.txt");
		File c = new File("CitizenKane.txt");
		File d = new File("DeerHunting.txt");
		File e = new File("Exports.txt");
		File f = new File("HappyDays.txt");
		File g = new File("Lakes.txt");
		File h = new File("Madison.txt");
		rIndex.indexDocument(a);
		rIndex.indexDocument(b);
		rIndex.indexDocument(c);
		rIndex.indexDocument(d);
		rIndex.indexDocument(e);
		rIndex.indexDocument(f);
		rIndex.indexDocument(g);
		rIndex.indexDocument(h);

		System.out.println(
				"The documents have been indexed.  If done correctly, all 10 documents should appear below, with DocID and DocName shown correctly.");
		rIndex.printOutAllDocs();
		System.out.println();

		String query = "";
		String[] qArray;

		System.out.print("Please enter a query to search for or type 'quit': ");
		query = scr.nextLine();

		while (!query.equals("quit")) {

			if (!query.isEmpty()) {
				qArray = query.split(" ");
				if (qArray.length == 1)
					rIndex.singleQuery(query.toLowerCase());
				else if (qArray.length == 2) {
					rIndex.twoWordQuery(qArray);
				} else {
					System.out.println("\nError in query entry.\n");
				}
			} else {
				System.out.println("\nYou did not enter anything.\n");
			}

			System.out.print("Please enter a query to search for or type 'quit': ");
			query = scr.nextLine();

		}

		System.out.println();
		System.out.print("Would you like to save the file (yes/no)? ");
		String response = scr.nextLine().trim().toLowerCase();

		if (response.equals("yes")) {
			System.out.print("What should the save file be called? ");
			String fileName = scr.nextLine().trim();
			rIndex.save(fileName);
		}

		System.out.println("Goodbye");
		scr.close();

	}

}
