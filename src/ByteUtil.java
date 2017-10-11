import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Base64;

/**
 * 
 */

/**
 * @author Albert
 *
 */
public class ByteUtil {
	
	/**String of Hex converted to String of ASCII 
	 * @param hex String of Hex
	 * @return String of ASCII
	 */
	public static String hexToAscii(String hex){
		StringBuilder sb = new StringBuilder();
		 //49204c6f7665204a617661 split into two characters 49, 20, 4c...
		 for( int i=0; i<hex.length()-1; i+=2 ){
		      //grab the hex in pairs
		      String output = hex.substring(i, (i + 2));
		      //convert hex to decimal
		      int decimal = Integer.parseInt(output, 16);
		      //convert the decimal to character
		      sb.append((char)decimal);
		  }
		  return sb.toString();
	}

	/**String of ASCII converted to byte[]
	 * @param ascii String of ascii
	 * @return byte array
	 */
	public static byte[] asciiToByte(String ascii){
		char[] chars = ascii.toCharArray();
		
		byte[] hexB = new byte[chars.length];
		
		for (int i=0; i<chars.length; i++){
			hexB[i] = (byte)chars[i];
		}
		
		return hexB;
	}


	/** byte[] converted to String of ASCII
	 * @param bytes byte array
	 * @return ascii String
	 * @throws Exception
	 */
	public static String byteToAscii(byte[] bytes) throws Exception{
		//return new String(bytes);
		return new String(bytes, "UTF-8");
	}
	
	/**hex String to byte[]
	 * @param hex String of hex
	 * @return byte array
	 */
	public static byte[] hexstringToByte(String hex){
		return asciiToByte(hexToAscii(hex));
	}
	
	/**byte[] to String of hex
	 * @param bytes byte array
	 * @return String of hex
	 */
	public static String byteToHexString(byte[] bytes){
		String str="";
		for (byte b:bytes){
			str+=String.format("%02x", b);
		}
		return str;
	}

	/**Base64 byte[] decoded into numeric byte[]
	 * @param src byte[] Base64 input
	 * @return byte[] numeric
	 */
	public static byte[] decodeB64(byte[] src){
		return Base64.getDecoder().decode(src);
	}
	
	/**Numeric byte[] encoded into Base64 byte[]
	 * @param src byte[] numeric
	 * @return byte[] Base64
	 */
	public static byte[] encodeB64(byte[] src){
		return Base64.getEncoder().encode(src);
	}
	
	/**
	 * @param n
	 * @param e
	 * @param mod
	 * @return n^e%mod
	 */
	public static BigInteger effMod(BigInteger n, BigInteger e, BigInteger mod){
		boolean[] bits = new boolean[32];
		
		for (int i=31; i>=0; i--) //e to 32 bits binary
			bits[31-i] = (e.intValue() & (1<<i)) != 0;
		
//		for (boolean b:bits)
//			if (b)
//				System.out.print(1);
//			else
//				System.out.print(0);
//		
//		System.out.println("\n============");
		
		BigInteger result = BigInteger.ONE;
		for (boolean b:bits)
			if (b)
				result = result.multiply(result).multiply(n).mod(mod);
			else
				result = result.multiply(result).mod(mod);

		return result;
	}
	
	/**
	 * @param n
	 * @param e
	 * @param mod
	 * @return n^e%mod
	 */
	public static int effMod(int n, int e, int mod){
		boolean[] bits = new boolean[32];
		
		for (int i=31; i>=0; i--) //e to 32 bits binary
			bits[31-i] = (e & (1<<i)) != 0;
		
//		for (boolean b:bits)
//			if (b)
//				System.out.print(1);
//			else
//				System.out.print(0);
//		
//		System.out.println("\n============");
		
		int result = 1;
		for (boolean b:bits)
			if (b)
				result = result*result*n % mod;
			else
				result = result*result % mod;

		return result;
	}
}
