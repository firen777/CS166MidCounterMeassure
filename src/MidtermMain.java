import java.math.BigInteger;
import java.util.Arrays;

import javax.crypto.Cipher;
import rsrc.*;

public class MidtermMain {

	public static void main(String[] args) throws Exception {
		
		ByteUtil b = new ByteUtil();
		////////////////////
//		Knapsack_calc k = new Knapsack_calc(11, 40, new int[]{2, 5, 8, 17});
//		
//		for (int i:k.pubKeyGK){
//			System.out.print(i+",");
//		}
//		System.out.println();
//		System.out.println(k.pub2prvEncrypt(new byte[]{1,0,1,0}));
//		
//		for (byte bit:k.prv2pubDecrypt(30)){
//			System.out.print(bit);
//		}
//		System.out.println();
		////////////////////
//		RSA_calc r = new RSA_calc(1049, 1787, 1549);
//		System.out.println(r.pubN + "," + r.prvD + ";" + r.pub2prv(5));
//		
//		System.out.println(r.prv2pub(r.pub2prv(77)));
//		System.out.println(r.pub2prv(r.prv2pub(81)));
//		System.out.println(r.prv2pubFast(r.pub2prvFast(77)));
//		System.out.println(r.pub2prvFast(r.prv2pubFast(81)));
//		
//		BigInteger big = r.effMod(BigInteger.valueOf(5), BigInteger.valueOf(20), BigInteger.valueOf(35));
//		System.out.println(big);
		////////////////////
//		System.out.println(b.byteToHexString(b.int2byteArr(112233)));
//		System.out.println(b.byteArr2int(new byte[]{0, 1, (byte)0xb6, 0x69}));
		
		MD5_calc md5 = new MD5_calc();
		
		byte[] ans = md5.computeMatchVal(new byte[]{0x00}, true);
		System.out.println(b.byteToAscii(ans) + ": " + b.byteToHexString(md5.md5Hash(ans)));
		
		
	}

}
