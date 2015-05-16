import java.util.ArrayList;
import java.util.Arrays;


public class Word {
	String theWord;
	String wordCharsAlphabatized;

	public Word(String word){
		theWord = word;
		char[] charArray = word.toCharArray();
		insertSort(charArray);
		wordCharsAlphabatized= new String(charArray);
	}

	String getWord(){
		return theWord;
	}
	String getWordCharsAlpha(){
		return wordCharsAlphabatized;
	}


	public static void insertSort(char[] A){
		for(int i = 1; i < A.length; i++){
			char a = A[i];
			int j = i - 1;
			while(j >= 0 && A[j] > a){
				A[j + 1] = A[j];
				j = j - 1;
			}
			A[j + 1] = a;
		}
	}

	public static void main (String [] arguments) {
		ArrayList<String> A = new ArrayList<String>();
		A.add("fg");
		A.add("asddd");
		A.add("asdf");
		A.add("a");
		insertSort2(A);
		for(String s : A)
			System.out.println(s);

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
}