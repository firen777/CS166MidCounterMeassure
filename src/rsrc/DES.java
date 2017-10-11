package rsrc;

import java.security.Key;
import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class DES {

	public static void main1(String[] args) throws Exception {		

		//Data and key
		byte[] k = "12345678".getBytes();
		byte[] pt = "This is a test message".getBytes();
		
		//Encryption
		Cipher cipher = Cipher.getInstance("DES");
        Key key = new SecretKeySpec(k,"DES");
        cipher.init(Cipher.ENCRYPT_MODE ,key);
        byte[] ct = cipher.doFinal(pt);
        
        //Decryption
        cipher = Cipher.getInstance("DES");
		cipher.init(Cipher.DECRYPT_MODE ,key);
		byte[] pt2 = cipher.doFinal(ct);
		
		//Outputs
		System.out.println( Arrays.toString(pt) );		
		System.out.println( Arrays.toString(ct) ); 		
		System.out.println( Arrays.toString(pt2) );
	}
}
