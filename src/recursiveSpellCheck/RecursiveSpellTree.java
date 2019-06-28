package recursiveSpellCheck;

public class RecursiveSpellTree {
	
	boolean [] letterContinuation;
	int [] treeLocations;
	RecursiveSpellTree [] recursiveTree;
	int Ntree;
	int wordCheckLength;
	char letterChecked;
	
	
	
	public boolean checkSpelling ( String word )
	{
		int currentIdx = 0;
		return checkSpellingRecur ( word.toCharArray(), currentIdx );

	}
	
	
	public boolean checkSpellingRecur ( char [] word, int currentIdx )
	{
	
		boolean out = false;
		
		int charInt = word[currentIdx]-'a';
		
		/*
		System.out.println(word);
		System.out.print(wordCheckLength);
		System.out.print("  " );
		System.out.print(currentIdx);
		System.out.print("  " );

		System.out.print(word[currentIdx]);
		System.out.print("  " );
		System.out.println( letterContinuation[charInt] );
		

		for(int i = 0; i < 26; i++)
		{
			System.out.print(i);
			System.out.print("  ");
			System.out.print((char)('a'+i));
			System.out.print("  ");
			System.out.print(letterContinuation[i]);
			System.out.println("  ");
		}
		System.out.println(" ------------------------------------ ");
		*/
		
		if( letterContinuation[charInt] && currentIdx < wordCheckLength-1 )
		{
			// Set to true, so that when this recursion kicks back
			// it will have a value of true
			// unless the next recursion value is false
			out = true;
			for (int i = 0; i < Ntree; i++)
			{
			 
				if(treeLocations[i] == charInt)
				{
					// Next recursion value -- if this is false then all the 
					// previous recursions will flag false
					// so that the final return is false
					out = recursiveTree[i].checkSpellingRecur(word, currentIdx+1);

				}
			}
		}
		else if ( letterContinuation[charInt] )
		{
			out = true;
		}
		else
		{
			out = false;
		}
	
		
		return out;
	}
	
	
	RecursiveSpellTree (){
		
	}
	
	RecursiveSpellTree ( int recursionIndex, char [][] wordList, 
			 			 int wordsLength, int idxStart, int idxEnd )
	{
		BuildSpellTree ( recursionIndex, wordList, 
						 wordsLength, idxStart, idxEnd );
	}
	
	
	public void BuildSpellTree ( int recursionIndex, char [][] wordList, 
				  				 int wordsLength, int idxStart, int idxEnd )
	{
		wordCheckLength = wordsLength;
		
		int [] startIndices = new int [26];
		int [] endIndices = new int [26];
		int [] nWordsWithChar = new int [26];
		for(int i = 0; i < 26; i++) {
			startIndices[i] = -1;
			endIndices[i] = -1;
			nWordsWithChar[i] = 0;
		}

		letterContinuation = new boolean [26];

		// The first starting index is always the first point
		int j = 0;
		int charValuePrimer = wordList[idxStart][recursionIndex] - 'a';
		startIndices[charValuePrimer] = idxStart; 
		j = j+1;

		// Search for letters
		for( int i = idxStart; i < idxEnd; i++ )
		{
			int charValue = wordList[i][recursionIndex] - 'a';
			letterContinuation[charValue] = true;
			// Add a value for the number of words with this character value
			nWordsWithChar[charValue] = nWordsWithChar[charValue] + 1; 

			if( i > 0 && wordList[i][recursionIndex] != wordList[i-1][recursionIndex]) 
			{
				// Find the starting index of the next character value
				startIndices[charValue] = i;
			} 
		}
		
		
		for(int i = 0; i < 26; i++)
		{
			endIndices[i] =  startIndices[i] + nWordsWithChar[i];
		}
		
		/*
		// the end indices
		int previousLetterIndex = charValuePrimer;
		for(int i = 0; i < 26; i++)
		{
			if(	letterContinuation[i]  )
			{
				endIndices[previousLetterIndex] =  startIndices[i];
				previousLetterIndex = i;
				
				// If the value wasn't set, it's the last point
				if(endIndices[previousLetterIndex] == -1)
				{
					endIndices[previousLetterIndex] = idxEnd;
					
				}
			
			}
		}
		*/
	
		// Size of the tree
		Ntree = 0;
		for(int i = 0; i < 26; i++)
		{

			if(letterContinuation[i])
			{
				Ntree ++;
			}
		}
		
		
		/*
		String temp = new String( wordList[idxStart] );
		if( temp.contentEquals( "recursion" ) )
		{

		
			System.out.print("  idx start = ");
			System.out.print(idxStart);
			System.out.print("  idx end = ");
			System.out.print(idxEnd);
			System.out.print("  recursion =  ");
			System.out.print(recursionIndex);
			System.out.println(" ------------------------------------ ");
			System.out.println("  ");
	
			for( int i = idxStart; i < idxEnd; i++ )
			{
				System.out.println(wordList[i]);
			}
			
			
			for(int i = 0; i < 26; i++)
			{
				System.out.print(i);
				System.out.print("  ");
				System.out.print((char)('a'+i));
				System.out.print("  ");
				System.out.print(letterContinuation[i]);
				System.out.print("  ");
				System.out.print(startIndices[i]);
				System.out.print("  ");
				System.out.print(nWordsWithChar[i]);
				System.out.print("  ");
				System.out.println(endIndices[i]);
				
			}
		}
		*/
		

		// Recursive call to build any additional 
		recursiveTree = new RecursiveSpellTree [Ntree];
		treeLocations = new int [Ntree];
		int newRecursionIndex = recursionIndex+1;

	
		j = 0;
		for(int i = 0; i < 26; i++)
		{
			if(letterContinuation[i] &&  newRecursionIndex < wordsLength)
			{
				treeLocations[j] = i;
				recursiveTree[j] = new RecursiveSpellTree( newRecursionIndex, wordList, 
										wordsLength, startIndices[i], endIndices[i] );
				j = j+1;
			}
		}
		



	}

	
	
}
