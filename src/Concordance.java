// import statements
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
/**
 * This class launches the program. It loads a text file and the user can then
 * search for specific words in the text file that occur in paragraphs. The
 * program makes use of a HashMap to associate each word with a paragraph.
 * 
 * @author		Richard Barney
 * @version		1.0.0 December 2013
 * @since		1.7
 */
public class Concordance {
	/**
	 * main method as required by JVM. Launches the program.
	 * @param  args   standard command line parameters as a string array.
	 */
	public static void main(String[] args) {
		Scanner input = new Scanner( System.in );
		BufferedReader br = null;
		ArrayList<String> arrOfParagraphs = new ArrayList<String>(); // ArrayList for paragraphs
		HashMap<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>(); // HashMap
		String findParagraphs; // String used to find paragraphs in text file
		String sOption = "n"; // give load a null value 
		BuildMap buildMapObj = new BuildMap();
		SearchForWords searchForWordsObj = new SearchForWords();
		System.out.println("Welcome to Richard Barney's Concordance Generator!"
							+"\nThis program allows you to enter the filename of a text or serialized file, "
							+"\nand upon doing so the program will search through the file and allow you to see the occurrences of "
							+"\nany specific word you enter, just like a concordance! Upon quitting, a serialized file "
							+"\nwill be created that you can load when you run the program again.");
		do { // validate user input
			System.out.println("Enter:\n    \"f\" to load a text file\n    \"s\" to load a serialized file");
			sOption = input.nextLine();
		} while (sOption.charAt(0) != 'f' && sOption.charAt(0) != 's' || sOption.length() > 1);
		
		switch(sOption) {
			case "f": // f loads a text file
				try {
					System.out.print("Specify the full filename (e.g. \"filename.txt\"): ");
					String sFileName = input.nextLine(); // let user specify filename
					br = new BufferedReader(new FileReader(sFileName));
					System.out.println("Loading file... This could take a while depeding on how large the file is...");
					while ((findParagraphs = br.readLine()) != null) { // fill ArrayList with paragraphs
						arrOfParagraphs.add ( findParagraphs );
					}
				} catch(IOException e) {
						System.out.print("File not found. Exiting...");
						System.exit(0);
				} 
				buildMapObj.buildMap(arrOfParagraphs, map); // build the HashMap
				searchForWordsObj.searchForWords(map); // search for words
				break;
				
			case "s": // s loads a serialized file
				try {
					System.out.print("Specify the full filename (e.g. \"filename.ser\"): ");
					String sFileName = input.nextLine(); // let user specify filename
					ObjectInputStream in = new ObjectInputStream(new FileInputStream(sFileName)); // create ObjectInputStream for serialized file
					map = (HashMap<String, ArrayList<String>>) in.readObject();
					in.close();
				} catch(IOException e) {
					System.out.print("File not found. Exiting...");
					System.exit(0);
				} catch(ClassNotFoundException e) {
					System.out.print("Class not found. Exiting...");
					System.exit(0);
				}
				buildMapObj.buildMap(arrOfParagraphs, map); // build the HashMap
				searchForWordsObj.searchForWords(map); // search for words
				break;
		} // end switch statement
	} // end method main
} // end class Concordance