
public class MidtermMain {

	public static void main(String[] args) throws Exception {
		
		System.out.println(ByteUtil.hexToAscii("41"));
		System.out.println(ByteUtil.byteToAscii(new byte[]{0x41,0x42,0x61,(byte)144}));
		System.out.println(ByteUtil.hexstringToByte("1A2B")[1]);
		System.out.println(ByteUtil.byteToHexString(new byte[]{(byte)0xaa,(byte)0xbb}));
		
		
	}

}
