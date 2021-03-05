package CS570;
import java.util.Map;
import java.util.*;
import java.io.BufferedReader;
import java.util.ArrayList;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.io.IOException;
/** 
 * 
 * @author   wenxuanwang
 * @data     Dec.4.2019
 * @Purpose: HomeWork 6 Anagrams
 */
public class Anagrams {
	//filed;
	final Integer[] primes= {2,3,5,7,11,13,17,19,23,29,31,37,41,43,47,53,59,61,67
					         ,71,73,79,83,89,97,101};
	Map<Character, Integer> letterTable;
	Map<Long, ArrayList<String>> anagramTable;
	
	//constructor
	public Anagrams() {
		buildLetterTable();
		anagramTable = new HashMap<>();
	}
	//4 Mthod 1.Method should be invoked by constructor;
    //        2. and should build the hashtable letterTable
	//		  key is alphabet; value is primes;
	private void buildLetterTable() {
		letterTable = new HashMap<>();
		Character[] alphabet = {'a','b','c','d','e','f','g','h','i','j','k','l','m',
						   'n','o','p','q','r','s','t','u','v','w','x','y','z'};
		for(int i =0;i<alphabet.length; i++) {
			letterTable.put(alphabet[i], primes[i]);
		}
	}
	// 6 Method
	//this method compute the hash code ,
	//and should add this word to the hash table anagramTable;
	private void addWord(String s) {
		long key = myHashCode(s);
		//if this key(hashCode) is already existed
		//get the arraylist under this key, and add String s to this Arraylist;
		if(anagramTable.get(key)!=null) {
			anagramTable.get(key).add(s);
		}else {
			//if this key is an new one;
			//create a new arrayList store it in anagramTable;
			ArrayList<String> hold = new ArrayList<>();
			hold.add(s);
			anagramTable.put(key, hold);
		}
	}
	//5 Method
	//given string s, compute its hash code
	//key formula is the product of each letter refer prime.
	private Long myHashCode(String s) {
		long key=1;
		for(int i=0; i<s.length();i++) {
			int currentDigit = letterTable.get(s.charAt(i));
			//updata key;
			key = key*currentDigit;
		}
		return key;
	}
	//main method code given by HW6
	public void processFile(String s) throws IOException{
		FileInputStream fStream = new FileInputStream(s);
		BufferedReader br = new BufferedReader ( new InputStreamReader(fStream));
		String strLine;
		while((strLine = br.readLine()) != null) {
			this.addWord(strLine);
		}
		br.close();
	}
	//7 Method
	//this method should return the entries in the anagramTable which have the largest number
	//对于keySet其实是遍历了2次，一次是转为iterator，一次就从hashmap中取出key所对于的value。
	//而entryset只是遍历了第一次，他把key和value都放到了entry中，所以就快了。
	//https://blog.csdn.net/cruise_h/article/details/28591269
	private ArrayList<Map.Entry<Long,ArrayList<String >>> getMaxEntries(){
		ArrayList<Map.Entry<Long,ArrayList<String >>> maxEntries = new ArrayList<>();
		//get a max temp value 0;
		int max =0;
		for(Map.Entry<Long, ArrayList<String>> entry: anagramTable.entrySet()) {
			//if the new number is bigger than temp max;
			//clear the old one, add the new one, then update the max;
			if(entry.getValue().size()>max) {
				maxEntries.clear();
				maxEntries.add(entry);
				max = entry.getValue().size();
			}else if(entry.getValue().size() == max) {
				maxEntries.add(entry);
			}
		}
		return maxEntries;
	}

	public static void main(String[] args) {
		Anagrams a = new Anagrams();
		
		final long startTime = System.nanoTime();
		try {
			a.processFile("words_alpha.txt");
		}catch(IOException e1) {
			e1.printStackTrace();
		}
		ArrayList<Map.Entry<Long,ArrayList<String >>> maxEntries = a.getMaxEntries();
		final long estimatedTime = System.nanoTime() - startTime;
		final double seconds = ((double) estimatedTime/1000000000);
		System.out.println("Time: "+ seconds);
		System.out.println("List of max anagrams: "+ maxEntries);
	}

}
