package rsrc;

import java.security.MessageDigest;

public class MD5 {

	public static void main1(String[] args) throws Exception {
		byte[] data = "abcdefgh".getBytes();
		
		MessageDigest digest = MessageDigest.getInstance("MD5");
		Utils.printHex( digest.digest(data) );
		
		data = "abcdefgi".getBytes();
		digest = MessageDigest.getInstance("MD5");
		Utils.printHex( digest.digest(data) );		
		
		/*

		digest = MessageDigest.getInstance("SHA-1");
		Utils.printHex( digest.digest(data) );

		digest = MessageDigest.getInstance("SHA-256");
		Utils.printHex( digest.digest(data) );

		digest = MessageDigest.getInstance("SHA-384");
		Utils.printHex( digest.digest(data) );
		
		digest = MessageDigest.getInstance("SHA-512");
		Utils.printHex( digest.digest(data) );		
		*/
	}

}
