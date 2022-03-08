//Amisha Antiya CWID:10475122   Homework Assignment-6
package Anagrams;
import java.util.*;
import java.io.*;

public class Anagrams {
	//Data
	final Integer[] primes= {2,  3,  5,  7,  11,  13,  17,  19,  23,  29,  31,  37,  41,  43,  47,  53,  59,  61, 
			                 67,  71,  73,  79,  83,  89,  97,  101};  
	Map<Character,Integer> letterTable;  
	Map<Long,ArrayList<String>> anagramTable;
	
	//constructor
	public Anagrams(){
		letterTable= new HashMap<Character,Integer>();
		buildLetterTable();
		anagramTable= new HashMap<Long,ArrayList<String>>();
	}
	
	//To build a letterTable for hash code
	private void buildLetterTable() {
		Character[] a= {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
		for (int i=0;i<26;i++) {
			//To assign respective prime number to a character 
			letterTable.put(a[i],primes[i]);
		}
	}
	
	//To add input string to anagram table
	private void addWord(String s) {
		if (anagramTable.containsKey(myHashCode(s))) {
			//if the string's anagram is present then the hash code is fetched from the anagram word (both words will have same hash code)
			ArrayList<String> x=anagramTable.get(myHashCode(s));
			x.add(s);
			anagramTable.replace(myHashCode(s), x);
		}
		else {
			//when string is not present add the word to anagram table and compute hash code
			ArrayList<String> x=new ArrayList<String>();
			x.add(s);
			anagramTable.put(myHashCode(s), x);
		}
	}
	
	//To compute unique hash code for an input string
	private Long myHashCode(String s) {
		int j=0;
		long code=1;
		//loop over the length of the string
		while(j<s.length()) {
			Character c=s.charAt(j);
			code*=letterTable.get(c);   //get the value of character from letter table and compute code
			j++;
		}
		return code;
	}
	
	//To process the file containing words
	private void processFile(String s) throws IOException{
		  FileInputStream  fstream  =  new  FileInputStream(s);  
		  BufferedReader br = new BufferedReader (new InputStreamReader(fstream));  
		  String  strLine ;  
		  while ((strLine = br.readLine())!= null ){ 
			  this.addWord ( strLine );
		  }  
		  br.close ();  
	} 
	
	//To get the entry having largest number of anagrams
	private ArrayList<Map.Entry<Long,ArrayList<String>>> getMaxEntries(){
		ArrayList<Map.Entry<Long,ArrayList<String>>> y= new ArrayList<Map.Entry<Long,ArrayList<String>>>();
		int m=0;
		for (Map.Entry<Long,ArrayList<String>> z:anagramTable.entrySet()) {
			if(z.getValue().size()>m) {
				y.clear();
				y.add(z);
				m=z.getValue().size();
			}
			else {
				if(z.getValue().size()==m) {
					y.add(z);
				}
			}
		}
		return y;
	}
	
	//To read all the strings in the file and to place them in hash table and to get the highest number of anagrams
	public static void main (String[] args){
		Anagrams a = new  Anagrams ();
		final long startTime  = System.nanoTime();  
		try  {
			a.processFile ("words_alpha.txt");
		}catch  ( IOException e1 )  {
			e1.printStackTrace ();
		}
		ArrayList <Map.Entry<Long,ArrayList<String>>> maxEntries = a.getMaxEntries();  
		final long  estimatedTime = System.nanoTime() - startTime;
		final double  seconds  =  ((double) estimatedTime / 1000000000);
		System.out.println ("Elapsed Time :  "+  seconds );
		System.out.println("Key  of  max  anagrams : " + maxEntries.get(0).getKey());
		System.out.println ("List  of  max  anagrams :  "+ maxEntries.get(0).getValue() ); 
		System.out.println("Length  of  list  of  max  anagrams : "+maxEntries.get(0).getValue().size());
	} 
}
