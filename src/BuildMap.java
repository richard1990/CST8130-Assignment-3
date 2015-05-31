// import statements
import java.util.ArrayList;
import java.util.HashMap;
/**
 * This class builds the HashMap. An ArrayList of Strings holding
 * paragraphs is the key, while the words being search for are
 * the values.
 * 
 * @author		Richard Barney
 * @version		1.0.0 December 2013
 */
public class BuildMap {
	/**
	 * Void method that builds the map.
	 * @param	paragraphs	ArrayList of Strings containing paragraphs.
	 * @param	map			HashMap containing a String as a key and an ArrayList
	 * 						of Strings as values.
	 */
	public void buildMap(ArrayList<String> paragraphs, HashMap<String, ArrayList<String>> map) {
		String refToParagraph;
		String[] collectionOfWords;
		// loop through all paragraphs to build array & hashmap
		for (int i = 0; i < paragraphs.size(); i++) {
			refToParagraph = paragraphs.get(i); // capture reference to paragraph
			collectionOfWords = refToParagraph.split("[^a-zA-Z_0-9']+"); // invoke split() on paragraph and put into collectionOfWords String
			// capture resulting array of references to words
			for (int j = 0; j < collectionOfWords.length; j++) {
				// if word is not found in HashMap, create new ArrayList & 
				// store the paragraph reference as the first instance in
				if (!map.containsKey( collectionOfWords[j])) {
					// create temporary ArrayList to store list of paragraphs for this word
					ArrayList<String> tmp = new ArrayList<String>();
					tmp.add(refToParagraph);
					map.put(collectionOfWords[j], tmp); // add to HashMap
				}
				// if word is found in HashMap & word is not already in a reference to a paragraph,
				// add this paragraph reference to list of paragraphs for this word
				else if (!map.get( collectionOfWords[j]).contains(refToParagraph)) {
					map.get(collectionOfWords[j]).add(refToParagraph);
				}
			} // end capturing references
		} // end looping through paragraphs
	} // end method buildMap
} // end class BuildMap