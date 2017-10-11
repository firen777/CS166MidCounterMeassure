
public class MidtermMain {

	public static void main(String[] args) throws Exception {
		
		System.out.println(ByteUtil.hexToAscii("41"));
		System.out.println(ByteUtil.byteToAscii(new byte[]{0x41,0x42,0x61,(byte)144}));
		
		
	}

}
