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
	
	/**
	 * @throws Exception 
	 * 
	 */
	public MD5_calc() throws Exception {
	}
	
	public static byte[] computeMatchVal(byte[] condition, boolean asciiPrintable) throws Exception{
		int count = 0;
		if (asciiPrintable){
			while(count != -1){
				byte[] tempB = ByteUtil.int2byteArr(count);
				boolean isPrintable = true;
				for (byte b:tempB)
					if (b<32||126<b)
						isPrintable = false;
				if (isPrintable){
					byte[] tempMD = md5Hash(tempB);
					boolean match = true;
					for (int i=0; i<condition.length; i++)
						if (condition[i]!=tempMD[i])
							match = false;
					if (match)
						return tempB;
				}
				count++;
			}
		} else {
			while(count != -1){
				byte[] tempB = ByteUtil.int2byteArr(count);
				byte[] tempMD = md5Hash(tempB);
				boolean match = true;
				for (int i=0; i<condition.length; i++)
					if (condition[i]!=tempMD[i])
						match = false;
				if (match)
					return tempB;
				count++;
			}
		}
		
		
		return null;
	}
	
	public static byte[] md5Hash (byte[] input) throws Exception{
		return MessageDigest.getInstance("md5").digest(input);
	}

}
