package comparisonSpellCheck;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;


public class ComparisonSpellCheck {

	
	// Local containers
	int NSpellCheckers = 9; // This corresponds to the number of resource files
	// These word lengths correspond to the resource files
	int [] wordLengths = {2, 3, 4, 5, 6, 7, 8, 9, 10};
	ComparisonSpellChecker [] cscs = new ComparisonSpellChecker [NSpellCheckers];
	String [] fileNames = {"resources/twoLetterWords.txt",
			 "resources/threeLetterWords.txt", "resources/fourLetterWords.txt",
			 "resources/fiveLetterWords.txt","resources/sixLetterWords.txt",
			 "resources/sevenLetterWords.txt","resources/eightLetterWords.txt",
			 "resources/nineLetterWords.txt","resources/tenLetterWords.txt" };

	 // Check the length of the word using the appropriate spell tree
	 public boolean checkWord( String word )
	 {
		 boolean isWord = false;
		 for( int i = 0; i < NSpellCheckers; i++ )
		 {
			 if(word.length() == wordLengths[i])
			 {
				 isWord = cscs[i].checkSpelling(word);	 
			 }
		 }
		 return isWord;
	 }
	 
	 
	 // Initializer that sets up the spell checker with a pre-defined maximum word length
	 ComparisonSpellCheck() throws IOException {
		 setupSpellCheck(wordLengths[NSpellCheckers-1]);
	 }
	 
	 // Initializer that sets up the spell check with a specified word length
	 public ComparisonSpellCheck( int maxWordLength ) throws IOException {
		 setupSpellCheck( maxWordLength );
	 }
	 
	 // Sets up the spell check using the resource files for correct spellings
	 public void setupSpellCheck ( int maxWordLength ) throws IOException{
		 
		 for( int k = 0; k < NSpellCheckers; k++ )
		 {
		
			// Load file
			ClassLoader classLoader = this.getClass().getClassLoader();	        
		    File file = new File(classLoader.getResource(fileNames[k]).getFile());      
		    String content = new String(Files.readAllBytes(file.toPath()));
		    int wordsLength = wordLengths[k]; 
		    
			 int Nwords = 0;
		     for (int i=0; i < content.length(); i++)
		     {
		    	 char c = content.charAt(i);
		    	 if (!String.valueOf(c).matches("."))
		    	 {
		    		 if( i > 0 )
		    		 {
			    		char c2 = content.charAt(i-1);
			    		if (String.valueOf(c2).matches("."))
			    		{
			    			Nwords++;
			    		}
		    		 }
		    	 }
			 }
		     
			 // Create char array of words
		     char[][] wordList = new char [Nwords][wordsLength];
		     int m = 0;
		     int n = 0;
		     for (int i=0; i < content.length(); i++)
		     {
		    	 char c = content.charAt(i);
	
		    	 if (String.valueOf(c).matches("."))
		    	 {
		    		 if( n < Nwords && m < wordsLength)
	    			 wordList[n][m] = c;
	    			 m = m+1;
		    	 }
		    	 else
		    	 {
		    		 m = 0;
		    		 n = n+1;
		    	 }
		    	 
		     }

		     // Set up the comparison spell checker
		     cscs[k] = new ComparisonSpellChecker( wordList, Nwords, wordsLength );
		     
		 }
	 }
	 
}
