package rsrc;

import java.util.ArrayList;
import java.util.Arrays;

public class Frequency {
//
//	static String fq = "ETAOINSRHDLUCMFYWGPBVKXOJZ";
//
//	public static void main1(String[] args) {
//		
//		String ct = "PBFPVYFBQXZTYFPBFEQJHDXXQVAPTPQJKTOYQWIPBVWLXTOXBTFXQWAXBVCXQWAXFQJVWLEQNTOZQGGQLFXQWAKVWLXQWAEBIPBFXFQVXGTVJVWLBTPQWAEBFPBFHCVLXBQUFEVWLXGDPEQVPQGVPPBFTIXPFHXZHVFAGFOTHFEFBQUFTDHZBQPOTHXTYFTODXQHFTDPTOGHFQPBQWAQJJTODXQHFOQPWTBDHHIXQVAPBFZQHCFWPFHPBFIPBQWKFABVYYDZBOTHPBQPQJTQOTOGHFQAPBFEQJHDXXQVAVXEBQPEFZBVFOJIWFFACFCCFHQWAUVWFLQHGFXVAFXQHFUFHILTTAVWAFFAWTEVOITDHFHFQAITIXPFHXAFQHEFZQWGFLVWPTOFFA";
//		
//		String ret = new String(ct);				
//		ret = ret.replace('P', 'T');
//		ret = ret.replace('B', 'H');
//		ret = ret.replace('F', 'E');
//		ret = ret.replace('Q', 'I');
//		System.out.println(ret);
//		
//		//Count characters
//		//int[] cnts = new int[26];
//		//count(cnts, ct);
//		//System.out.println(Arrays.toString(cnts));
//		/*
//		int[] c2 = Arrays.copyOf(cnts,26);
//		char[] ltrs = new char[26];
//
//		for (int i=0; i<c2.length; i++ ) {
//			int max = 0, ndx = i;
//			for (int j=i; j<c2.length; j++ ) {
//				if ( c2[j] > max) {
//					max = cnts[j];
//					ndx = j;
//				}
//			}
//			ltrs[i] = (char)('A' + ndx);
//			c2[ndx] = -1;
//		}
//		System.out.println(Arrays.toString(ltrs));
//		
//		sub5(ct, ltrs);
//		*/
//	}
//	
//	/*
//	 * Count the letters in the text and populate a TreeMap
//	 */
//	public static void count(int[] cnts, String txt) {
//		
//		for (int i=0; i<txt.length(); i++ ) {
//			int ndx = txt.charAt(i) - 'A';
//			cnts[ndx]++;
//		}
//	}
//	
//	public static void sub5(String txt, char[] ltrs) {
//    	Permutations p = new Permutations();
//        //ArrayList<String> arr = p.performPermutations("FQPXTHVWO");
//    	ArrayList<String> arr = p.performPermutations("FQPXTH");
//        for(int i = 0; i<arr.size();i++) {
//        	//System.out.println(arr.get(i));
//			String ret = new String(txt+"");
//			ret = ret.replace( arr.get(i).charAt(0), 'E');		
//			ret = ret.replace( arr.get(i).charAt(1), 'T');
//			ret = ret.replace( arr.get(i).charAt(2), 'A');
//			ret = ret.replace( arr.get(i).charAt(3), 'O');
//			ret = ret.replace( arr.get(i).charAt(4), 'I');
//			ret = ret.replace( arr.get(i).charAt(5), 'N');
//			//ret = ret.replace( arr.get(i).charAt(6), 'S');
//			//ret = ret.replace( arr.get(i).charAt(7), 'R');
//			//ret = ret.replace( arr.get(i).charAt(8), 'H');
//			System.out.println(ret);
//			
//        }
//	}
//	
//
//	public static String subst(String txt, char[] ltrs) {
//		String ret = txt;
//		
//		//FQPXTH 
//		//ETAOIN
//
//		ret = ret.replace('F', 'E');		
//		ret = ret.replace('P', 'T');
//		ret = ret.replace('Q', 'A');
//		ret = ret.replace('X', 'O');
//		ret = ret.replace('T', 'I');
//		ret = ret.replace('H', 'N');
//
//		/*
//		//Look for the next highest frequency 
//		for (int i=0; i<26; i++ ) {
//			
//			char ptChar = fq.charAt(i);
//			char ctChar = ltrs[i];
//			System.out.println("Replacing " + ctChar + " with " + ptChar );
//			ret = ret.replace(ctChar, ptChar);
//			int x = 3;
//			
//		}
//		*/
//		
//		return ret;
//	}	
//	
//	
//	
//	
//	
}








