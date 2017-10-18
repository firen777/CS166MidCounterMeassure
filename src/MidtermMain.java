import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;

import javax.crypto.Cipher;
import rsrc.*;

public class MidtermMain {

	public static void main(String[] args) throws Exception {
		
		ByteUtil b = new ByteUtil();
		////////////////////
//		Knapsack_calc k = new Knapsack_calc(17, 63, new int[]{5, 8, 15, 33});
//		
//		for (int i:k.pubKeyGK){
//			System.out.print(i+",");
//		}
//		System.out.println();
//		System.out.println(k.pub2pvtEncrypt(new byte[]{1,0,1,0}));
//		
//		for (byte bit:k.pvt2pubDecrypt(30)){
//			System.out.print(bit);
//		}
//		System.out.println();
		//////////////////
//		RSA_calc r = new RSA_calc(5, 17, 3);
//		System.out.println(r.pubN + "," + r.pvtD + ";" + r.pub2pvt(7));
//		
//		System.out.println((r.pub2pvtFast(7)));
//		System.out.println((r.pvt2pubFast(2)));
//		
		//////////////////
		MD5_calc md5 = new MD5_calc();
		
		
		byte[] temp = b.hexstringToByte("419ee836f207c09d92566feaa14320d2");
		
		byte[] ans = md5.computeMatchVal(b.hexstringToByte("419ee836f207c09d92566feaa14320d2"), false);
		System.out.println(": " + b.byteToHexString(md5.md5Hash(ans)));
		//////////////////
//		Subs_calc subc = new Subs_calc();
//		
//		System.out.println(subc.caesar("abcdefghijklm,.,.nopqrstuvwxyz", -1));
//		System.out.println(subc.sub("abcdefghijklm,.,.nopqrstuvwxyz", "bcdegfhjiklmnopqrstuvwxyza"));
		////////////////////
//		DH_calc dh = new DH_calc(17, 3, 15, 17);
//		System.out.println(dh.debugWorking);
//		System.out.println(dh.symKey);
		////////////////////
//		byte[] answer = Crypto_calc.encrypt("DES", "DES", new byte[] {1,2,3,4,5,6,7,8}, new byte[] {1,1,1,6,1,4,7,5});
//		System.out.println(Arrays.toString(answer));
	}

}
