

import recursiveSpellCheck.SpellCheckRecursive;
import java.io.IOException;



public class main {
	
	 public static void main(String args[]) throws IOException
	 {
	
		// Test parame4ters
		int Nchecks = (int) 1e6;
	    String word2Check = "recipe";
		 
		// Record the start time for building the recursive tree
		long startTime = System.nanoTime();

	 	// Build the recursive spell check tree
	    SpellCheckRecursive spellCheck = new SpellCheckRecursive (10);

		// Output how long it took to build the tree
		long endTime = System.nanoTime();
		System.out.println("Took "+(endTime - startTime)/1e9 + " s to build the recursive tree."); 
	    
		// Check the word using the spell tree
	    boolean isWord = false;
	    for(int i = 0; i < Nchecks; i++)
	    {
	    		isWord = spellCheck.checkWord(word2Check);
	    }
	    System.out.println(word2Check);
	    System.out.print("Is it a word? ");
	    System.out.println(isWord);
     	
		// Output how long it took to check the word 
		long endTime2 = System.nanoTime();
		System.out.println("Took "+(endTime2 - endTime)/1e9 + " s to check the word " + Nchecks + " times."); 
		    
	 }
	 
}
