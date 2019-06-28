
import java.io.IOException;

import recursiveSpellCheck.SpellCheckRecursive;
import comparisonSpellCheck.ComparisonSpellCheck;

public class main {
	
	public static void main(String args[]) throws IOException
	{
		
		// Test parameters
		int Nchecks = (int) 2e6;
		String word2Check = "erbo";
		 
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
		
		

		System.out.println("\n------------------------------------------------------------------");
		
		// Record the start time for building the comparison tree
		long startTime_compare = System.nanoTime();
		
		// Setup comparison spell check
		ComparisonSpellCheck spellCheck2 = new ComparisonSpellCheck(10);
		
		// Output how long it took to build the spell check
		long endTime_compare = System.nanoTime();
		System.out.println("Took "+(endTime_compare - startTime_compare)/1e9 + " s to build the comparison check."); 
		
		// Check the word
		boolean isWord_compare = false;
		for(int i = 0; i < Nchecks; i++)
		{
			isWord_compare = spellCheck2.checkWord(word2Check);
		}
		System.out.println(word2Check);
		System.out.print("Is it a word? ");
		System.out.println(isWord_compare);
		
		// // Output how long it took to check the word 
		long endTime2_compare = System.nanoTime();
		System.out.println("Took "+(endTime2_compare - endTime_compare)/1e9 + " s to check the word " + Nchecks + " times."); 
		
		
		
	}

}
