/**
 * 
 */

/**
 * @author Albert
 *
 */
public class Subs_calc {
	public static String caesar(String msg, int shift) throws Exception{
		int key = shift % 26;
		byte[] origin = ByteUtil.asciiToByte(msg);
		byte[] result = new byte[origin.length];
		
		for (int i=0; i<origin.length; i++){
			if (!inRange(origin[i])){
				result[i] = origin [i];
			}
			else {
				byte temp = (byte) (origin[i] + key);
				if (!inRange(temp))
					temp-=26;
				result[i] = temp;
			}
		}
		
		return ByteUtil.byteToAscii(result);
	}
	
	public static String sub(String msg, String subs){
		String result = "";
		char[] origin = msg.toUpperCase().toCharArray();
		char[] key = subs.toCharArray();
		
		for (char c: origin){
			if (!inRange((byte)c)){
				result += c;
			} else {
				result += key[((byte)c - 0x41)];
			}
		}
		
		return result;
	}
	
	private static boolean inRange(byte c){
		if ((0x41<=c && c<=0x5a)||(0x61<=c && c<=0x7a))
			return true;
		return false;
	}
}
