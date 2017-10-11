package rsrc;

import java.security.*;
import java.util.Arrays;

public class SecureRand {

	public static void main1(String[] args) throws Exception {
		SecureRandom random = SecureRandom.getInstance("SHA1PRNG", "SUN");
		
		//System.out.println(random.nextDouble());
		for (int i=0; i<20; i++) {
		byte[] rb = new byte[16];
		random.nextBytes(rb);		
		System.out.println(Arrays.toString(rb));
		}
		
	}
}
