package rsrc;

import java.security.Key;
import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class RC4 {

	public static void main1(String[] args) throws Exception {		
		//Data and key
		byte[] k = "thisisthekey".getBytes();
		byte[] pt = "This is a test message".getBytes();
		
		//Encryption
		Cipher rc4 = Cipher.getInstance("RC4");
        Key key = new SecretKeySpec(k,"RC4");
        rc4.init(Cipher.ENCRYPT_MODE ,key);
        byte[] ct = rc4.doFinal(pt);
        
        //Decryption
        rc4 = Cipher.getInstance("RC4");
		rc4.init(Cipher.DECRYPT_MODE ,key);
		byte[] pt2 = rc4.doFinal(ct);
		
		//Outputs
		System.out.println( Arrays.toString(pt) );		
		System.out.println( Arrays.toString(ct) ); 		
		System.out.println( Arrays.toString(pt2) );
	}
}
