package recursiveSpellCheck;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;


public class SpellCheck {
	
	 
	 int [] rstWordLengths = {2, 3, 4, 5, 6, 7, 8, 9, 10};
	 int Nrsts = 8;	 
	 RecursiveSpellTree [] rsts = new  RecursiveSpellTree [Nrsts];
	 String [] fileNames = {"resources/twoLetterWords.txt",
			"resources/threeLetterWords.txt", "resources/fourLetterWords.txt",
			"resources/fiveLetterWords.txt","resources/sixLetterWords.txt",
			"resources/sevenLetterWords.txt","resources/eightLetterWords.txt",
			"resources/nineLetterWords.txt","resources/tenLetterWords.txt" };

	 public boolean checkWord( String word )
	 {
		 boolean isWord = false;
		 for( int i = 0; i < Nrsts; i++ )
		 {
			 if(word.length() == rstWordLengths[i])
			 {
				 isWord = rsts[i].checkSpelling(word);	 
			 }
		 }
		 return isWord;
	 }
	 
	 
	 SpellCheck() throws IOException {
		
		 // Sets up the spell checker
		 setupSpellCheck(rstWordLengths[Nrsts-1]);
	 }
	 
	 SpellCheck( int maxWordLength ) throws IOException {
			
		 // Sets up the spell checker
		 setupSpellCheck( maxWordLength );
	 }
	 
	 public void setupSpellCheck ( int maxWordLength ) throws IOException{
		 
		 for( int i = 0; i < Nrsts; i++ )
		 {
			 if(maxWordLength == rstWordLengths[i])
			 {
				 Nrsts = i+1;
			 }
		 }
		 
		 for( int k = 0; k < Nrsts; k++ )
		 {
		
			// Load file
			ClassLoader classLoader = new main().getClass().getClassLoader();	        
		    File file = new File(classLoader.getResource(fileNames[k]).getFile());      
		    String content = new String(Files.readAllBytes(file.toPath()));
		    int wordsLength = rstWordLengths[k]; 
		    
			    
			// int Nwords = content.length()/(wordsLength + 1);
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
	    			 

		    		 /*
		    		 String temp = new String( wordList[n-1] );

		    		 if( temp.equals("recursive") )
		    		 {
		    			 System.out.println(wordList[n-1]);
		    			 System.out.println(wordList[n-2]);

		    		 }
		    		 */
		    	 }
		    	 
		     }
	
		     
		     int recursionIndex = 0;
		     int idxStart = 0;
		     int idxEnd = Nwords;
		     
		     // System.out.print(content);
		     rsts[k] = new RecursiveSpellTree( recursionIndex, 
		    		 				wordList, wordsLength, idxStart, idxEnd );
		     
		 }
	 }
	 
	 
	


}
