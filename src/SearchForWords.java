// import statements
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
/**
 * This class searches for words in the text file loaded by the user.
 * The user enters a word and each occurrence of that word within a
 * paragraph will be displayed.
 * 
 * @author		Richard Barney
 * @version		1.0.0 December 2013
 */
public class SearchForWords {
	/**
	 * Void method that searches for specific words.
	 * @param	map		HashMap containing a String as key and an ArrayList of Strings
	 * 					as values.
	 */
	public void searchForWords(HashMap<String, ArrayList<String>> map) {
		Scanner input = new Scanner(System.in);
		String sSearchWord = "null"; // give searchWord a null value
		String sParagraphLimit;
		boolean continueLoop = true;
		do { // ask user how many paragraphs they want to display
			System.out.println("Enter:\n    \"1\" to limit the display to 20 paragraphs\n    \"2\" to display all paragraphs");
			sParagraphLimit = input.nextLine();
		} while (sParagraphLimit.charAt(0) != '1' && sParagraphLimit.charAt(0) != '2' || sParagraphLimit.length() > 1);
		while (continueLoop) {
			System.out.print("Enter a word, or enter \"x\" to exit: ");
			sSearchWord = input.nextLine();
			// check if the HashMap contains a key with the word entered by user
			if (map.containsKey(sSearchWord)) {
				if (sParagraphLimit.equals("1")) {
					System.out.println("Displaying the first 20 paragraphs containing the word entered...\n");
					for (int i = 0; i < map.get(sSearchWord).size() && i < 20; i++) {
						// make word stand out by adding ** before and after
						System.out.println(map.get(sSearchWord).get(i).toString().replaceAll("\\b(?i:" +sSearchWord +")\\b", "**$0**") +"\n");
					}
				}
				else {
					System.out.println("Displaying all paragraphs containing the word entered...\n");
					for (int i = 0; i < map.get(sSearchWord).size(); i++) {
						// make word stand out by adding ** before and after
						System.out.println(map.get(sSearchWord).get(i).toString().replaceAll("\\b(?i:" +sSearchWord +")\\b", "**$0**") +"\n");
					}
				}
			}
			// if no key exists, tell user can't find word in file 
			else if (!map.containsKey(sSearchWord) && sSearchWord.charAt(0) != 'x' && sSearchWord.length() == 1) {
				System.out.println("That word does not exist in the file!");
			}
			else if (sSearchWord.charAt(0) == 'x' && sSearchWord.length() == 1) {
				continueLoop = false;
				try {
					ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("File.ser"));
					out.writeObject(map);
					out.close();
					System.out.println("Goodbye. A serialized file has been created, named File.ser."
									+"\nLoad this file the next time the program launches to load the data again.");
				} catch (Exception e) {
					System.out.print("Error encountered while creating serialized file! Could not create file.");
				}
			}
		} // end while loop
	} // end method searchForWords
} // end class SearchForWords