package rsrc;

import java.security.Key;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class SecureFile {

	public static void main1(String[] args) throws Exception {
		String desKey = "12345678";
		crypt("data1.txt","data1.des.enc",desKey, "DES", Cipher.ENCRYPT_MODE);			
		crypt("data1.des.enc","data1_des_decrypted.txt",desKey, "DES", Cipher.DECRYPT_MODE);	

		String des3Key = "12345678abcdefgh12345678";
		crypt("data1.txt","data1.3des.enc",des3Key, "DESede", Cipher.ENCRYPT_MODE);			
		crypt("data1.3des.enc","data1_3des_decrypted.txt",des3Key, "DESede", Cipher.DECRYPT_MODE);	

		String aesKey = "12345678abcdefgh";
		crypt("data1.txt","data1.aes.enc",aesKey, "AES", Cipher.ENCRYPT_MODE);			
		crypt("data1.aes.enc","data1_aes_decrypted.txt",aesKey, "AES", Cipher.DECRYPT_MODE);		
	}
	
	public static void crypt(String fileIn, String fileOut, String k, String algo, int mode) throws Exception {
		byte[] pt = Files.readAllBytes( Paths.get(fileIn) );
		Cipher rc4 = Cipher.getInstance(algo);
        Key key = new SecretKeySpec(k.getBytes(),algo);
        rc4.init(mode,key);
        byte[] ct = rc4.doFinal(pt);
        Files.write( Paths.get(fileOut), ct, StandardOpenOption.CREATE);				
	}	
}
