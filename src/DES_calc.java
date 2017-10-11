import java.security.Key;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.spec.SecretKeySpec;

/**
 * 
 */

/**
 * @author Albert
 *
 */
@Deprecated
public class DES_calc {
	
	public static byte[] des_Encrypt(byte[] keybytes, byte[] databytes) throws Exception{
		Cipher cipher = Cipher.getInstance("DES");
        Key key = new SecretKeySpec(keybytes,"DES");
        cipher.init(Cipher.ENCRYPT_MODE ,key);
        return cipher.doFinal(databytes);
	}
	
	public static byte[] des_Encrypt(String mode, byte[] keybytes, byte[] databytes) throws Exception{
		Cipher cipher = Cipher.getInstance(mode);
        Key key = new SecretKeySpec(keybytes,"DES");
        cipher.init(Cipher.ENCRYPT_MODE ,key);
        return cipher.doFinal(databytes);
	}

}
