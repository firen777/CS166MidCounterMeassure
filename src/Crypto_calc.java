import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 * 
 */

/**
 * @author albertchan
 *
 */
public class Crypto_calc {

	/**
	 * 
	 */
	public Crypto_calc() {
		// TODO Auto-generated constructor stub
	}
	
	public static byte[] encrypt(String cipherSpec, String keySpec, byte[] keybytes, byte[] databytes) throws Exception{
		Cipher cipher = Cipher.getInstance(cipherSpec);
        Key key = new SecretKeySpec(keybytes,keySpec);
        cipher.init(Cipher.ENCRYPT_MODE ,key);
        return cipher.doFinal(databytes);
	}
	
	/**Decryption
	 * @param cipherSpec Specification of the cipher. Exp: "DES/CBC/NoPadding"
	 * @param keySpec Specification of the key. Exp: DES
	 * @param keybytes
	 * @param databytes
	 * @return
	 * @throws Exception
	 */
	public static byte[] decrypt(String cipherSpec, String keySpec, byte[] keybytes, byte[] databytes) throws Exception{
		Cipher cipher = Cipher.getInstance(cipherSpec);
        Key key = new SecretKeySpec(keybytes,keySpec);
        cipher.init(Cipher.ENCRYPT_MODE ,key);
        return cipher.doFinal(databytes);
	}

}
