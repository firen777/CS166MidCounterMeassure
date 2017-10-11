import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 
 */

/**
 * @author Albert
 *
 */
public class MD5_calc {
	public MessageDigest md;
	
	/**
	 * @throws Exception 
	 * 
	 */
	public MD5_calc() throws Exception {
		md = MessageDigest.getInstance("md5");
	}
	
	public byte[] computeMatchHash(byte[] condition){
		return new byte[]{};
	}
	
	public byte[] md5Hash (byte[] input){
		return md.digest(input);
	}

}
