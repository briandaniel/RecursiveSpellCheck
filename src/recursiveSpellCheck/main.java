package recursiveSpellCheck;

import java.io.IOException;



public class main {
	
	 public static void main(String args[]) throws IOException
	 {
	
		 long startTime = System.nanoTime();

//	    SpellCheck spellCheck = new SpellCheck ();
	    SpellCheck spellCheck = new SpellCheck (10);

		//code
		long endTime = System.nanoTime();
		System.out.println("Took "+(endTime - startTime)/1e9 + " s"); 
	    
		
	    String word = "recipe";
	    boolean isWord = false;
	    for(int i = 0; i < 1e3; i++)
	    		isWord = spellCheck.checkWord(word);

	    System.out.println(word);
	    System.out.print("Is it a word? ");
	    System.out.println(isWord);
     	
		//code
		long endTime2 = System.nanoTime();
		System.out.println("Took "+(endTime2 - endTime)/1e9 + " s"); 
		    
	 }
}
