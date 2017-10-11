package rsrc;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public class Utils {

	public static void printHex(byte[] ba) {
        //convert the byte to hex format method 2
        StringBuffer hexString = new StringBuffer();
    	for (int i=0;i<ba.length;i++) {
    		String hex=Integer.toHexString(0xff & ba[i]);
   	     	if(hex.length()==1) hexString.append('0');
   	     	hexString.append(hex);
    	}
    	System.out.println(hexString.toString());		
	}

	public static void prettyPrint(String msg, byte[] ba) {
		System.out.println("\n"+msg+" begin===");
		printHex(ba);
		System.out.println(msg+" end===\n");		
	}
	
    public static byte[] receiveBytes(DataInputStream dIn){   	
    	try {
    		int length = dIn.readInt();
    		byte[] ab = new byte[length];
    		dIn.readFully(ab, 0, length);
    		//System.out.print("Received: " + length);
    		//Utils.printHex(ab);
    		return ab;
    	} catch ( Exception e) {
    		System.out.println(e.getMessage());
    		return new byte[0];
    	}
    }

    public static void sendBytes(DataOutputStream dOut, byte[] ab){   	
    	try {
    	   	dOut.writeInt(ab.length);
    		dOut.write(ab);
    		dOut.flush();
    	} catch ( Exception e) {
    		System.out.println(e.getMessage());
    	}
    }   
    
}
