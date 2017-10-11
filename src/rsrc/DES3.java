package rsrc;

import java.security.Key;
import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class DES3 {

	public static void main1(String[] args) throws Exception {		
		//Data and key
		byte[] k = "01234567abcdefghABCDEFGH".getBytes();
		byte[] pt = "data2".getBytes();
		
		//Encryption
		Cipher cipher = Cipher.getInstance("DESede");
        Key key = new SecretKeySpec(k,"DESede");
        cipher.init(Cipher.ENCRYPT_MODE ,key);
        byte[] ct = cipher.doFinal(pt);
        
        //Decryption
        cipher = Cipher.getInstance("DESede");
		cipher.init(Cipher.DECRYPT_MODE ,key);
		byte[] pt2 = cipher.doFinal(ct);
		
		//Outputs
		System.out.println( Arrays.toString(pt) );		
		System.out.println( Arrays.toString(ct) ); 		
		System.out.println( Arrays.toString(pt2) );
	}
}
