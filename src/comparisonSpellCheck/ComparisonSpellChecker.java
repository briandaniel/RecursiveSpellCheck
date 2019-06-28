package comparisonSpellCheck;

public class ComparisonSpellChecker {
	
	char [][] wordList;
	int Nwords;
	int wordsLength;
	
	public boolean checkSpelling ( String word )
	{
		int currentIdx = 0;
		
		// Return false if it wasn't the right length
		if(word.length() != wordsLength)
			return false;
		
		// copy to char string
		char [] charWord = new char [wordsLength];
		for(int j = 0; j < wordsLength; j++ ) { 
			charWord[j] = word.charAt(j); 
		}
		
		for(int i = 0; i < Nwords; i++)
		{
			boolean localCheck = true;
			for(int j = 0; j < wordsLength; j++)
			{
				if( charWord[j] != wordList[i][j] )
				{
					localCheck = false;
					break;
				}
			}
			
			// If the local check is still true, then the word was found
			if(localCheck)
			{
				//System.out.print("Found that ");
				//System.out.print(charWord);
				//System.out.println(" is a word.");
				return true;
			}	
			
			
		}
		
		return false; // if nothing was found
	}
	
	
	// Empty constructor
	ComparisonSpellChecker(){};
	
	// Constructor based on word list
    ComparisonSpellChecker(  char [][] wordListIn, int NwordsIn, int wordsLengthIn ) {
		setupComparisonChecker(wordListIn, NwordsIn, wordsLengthIn);
	}

    
	public void setupComparisonChecker(  char [][] wordListIn, int NwordsIn, int wordsLengthIn )
	{
		
		Nwords = NwordsIn;
		wordsLength = wordsLengthIn;
		
		wordList = new char [Nwords][wordsLength];
		
		for(int i = 0; i < Nwords; i++)
		{
			for(int j = 0; j < wordsLength; j++)
			{
				wordList[i][j] = wordListIn[i][j];
				
			}
		}
		
	}


}
