
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;




public class Anagrams3 {

	/**
	 * @param args
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws FileNotFoundException {
		
		long timeStart = System.currentTimeMillis();
		
		//The size of the hash table and the hashtable
		int tableSize;
		
		
		FileReader file=null;
		// read file in from the command line  file and number of lines as args
		if (args.length==2){
			file = new FileReader(args[0]);
            int numOfLines = Integer.parseInt(args[1]);
			tableSize =  numOfLines;
		}
		else{ System.out.println("INPUT ARGS: file name and number of lines, program exiting try again!"); System.exit(0);tableSize = 5000;}

		WordList[] hashTable =  new WordList[tableSize];
		
		//Loads words into the hashTable
		Scanner scanner = new Scanner(file);
		while (scanner.hasNextLine()) {
			String in = scanner.nextLine();
			Word word = new Word(in);
			
   
			int key = hashFunction(word.getWordCharsAlpha(),tableSize);
			
			

			WordList list;
			if(hashTable[key]==null){
				list = new WordList();
				list.getList().add(in);
				hashTable[key]=list;

			}
			else{
				list = hashTable[key];
				list.getList().add(in);
			}
		}//end while

		//goes through the hash table and add anagram classes to output string
		String output = "";
		int numOfClasses = 0;

		for(int i = 0; i < hashTable.length; i++){
			
			if(hashTable[i]==null){;continue;}

			WordList list = hashTable[i];
			ArrayList<String> temp = list.getList();
			insertSort2(temp);
			int length = temp.get(0).length() ;
			String t = temp.get(0);
			numOfClasses++;
			for(String s : temp){
				int length1 = s.length();
				if(length1 == length  && isAnagram(s,t)){
					output+= s + " ";
				}
				else{
					length = length1;
					output+= "\r\n" +s+" ";
					numOfClasses++;
					t=s;
				}
			}output+="\r\n";
			
			
		}//end for
	
		// write  output file 
		File report = new File("output");
		FileWriter writer;
		try {
			writer = new FileWriter(report,true);

			writer.write(output);

			writer.flush();
		} catch (IOException e) {
			System.out.println("FAILED TO WRITE FILE");
			e.printStackTrace();
		}
		long timeFinish = System.currentTimeMillis();
		System.out.println("Time: "+TimeUnit.MILLISECONDS.toSeconds(timeFinish-timeStart)+" seconds.");
		System.out.println("Number of anagram classes: "+(numOfClasses-1)+".");
		
	}


	//hash function based off of the sum of the word's ascci values
	public static int hashFunction(String word, int m){
		int k = 0;
		char[] wordChars = word.toCharArray();
		int pow = wordChars.length-1;
		for(int i=0; i<wordChars.length; i++){
			int ascii = (int)wordChars[i];
			k += (ascii)*(Math.pow(128, pow)); 
			pow--;
		}

		return k%m;
	}


	public static void insertSort2(ArrayList<String> A){
		for(int i = 1; i < A.size(); i++){
			String temp = A.get(i);
			int value = A.get(i).length();
			int j ;
			for(j = i -1; j >= 0 && value < A.get(j).length(); j--){
				A.set(j+1,A.get(j));
			}
			A.set(j+1,temp);
		}
	}
	// method for determining if two words are anagrams
		public static boolean isAnagram(String word1, String word2){

			//convert strings to char arrays
			char[] word1Chars = word1.toCharArray();
			char[] word2Chars = word2.toCharArray();

			//count the frequency of each ascii value in the char arrays
			int[] word1CharFrequency = new int[128];
			int[] word2CharFrequency = new int[128];

			for(char a : word1Chars){
				int ascci = (int) a;
				word1CharFrequency[ascci]++;
			}
			for(char a : word2Chars){
				int ascci = (int) a;
				word2CharFrequency[ascci]++;
			}

			//compare the two arrays of the frequencies
			return Arrays.equals(word1CharFrequency, word2CharFrequency);
		}
}

