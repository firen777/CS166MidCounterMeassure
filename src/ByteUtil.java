import java.io.UnsupportedEncodingException;

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
	
	
}
