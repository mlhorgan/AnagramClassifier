
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;
import java.util.Scanner;




public class Anagram {

	/**
	 * @param args
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws FileNotFoundException {
		//The size of the hash table and the hashtable
		int tableSize = 3;
		LinkedList<String>[] hashTable = (LinkedList<String>[]) new LinkedList<?>[tableSize];

		// read file in from the command line
		FileReader file=null;
		if (args.length==1){
			file = new FileReader(args[0]);

		}
		else{file = new FileReader("test.txt");}

		//Loads words into the hashTable
		Scanner scanner = new Scanner(file);
		while (scanner.hasNextLine()) {
			String word = scanner.nextLine();

			int key = hashFunction(word,tableSize);
			//System.out.println(word+":  "+key);
			

			LinkedList<String> ll;
			if(hashTable[key]==null){
				ll = new LinkedList<String>();
				ll.add(word);
				hashTable[key]=ll;

			}
			else{
				ll = hashTable[key];
				ll.add(word);
			}
		}//end while
		
		//goes through the hash table and add anagram classes to output string
		String output = "";
		int numOfClasses = 0;

		for(int i = 0; i < hashTable.length; i++){
			if(hashTable[i]==null){continue;}

			LinkedList<String> ll = hashTable[i];
			int index = 1;
			
			while(!ll.isEmpty()){
				System.out.println("1:  "+ ll.toString());
				String word1 = ll.getHeadItem();
				index++;
				String word2 = ll.getItem(index);
				if(word2==null){
					output+= word1+"\n";
					ll.delete(1);
	                index=1;
					continue;
				}
			System.out.println(word1);
			System.out.println(word2);
				if(isAnagram(word1,word2)){
			System.out.println("here");
			
					output+=word2+" ";
					System.out.println("index: "+index);
					ll.delete(index);
					index--;
				}
			}//end while
		}//end for
		System.out.println(output);
		
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

	public static int hashFunction(String word, int m){
		int k = 0;
		char[] wordChars = word.toCharArray();
		int pow = wordChars.length-1;
		for(int i=0; i<wordChars.length; i++){
			int ascii = (int)wordChars[i];
			k += (ascii); 
			pow--;
		}
		
		return k%m;
	}



}

