package rsrc;

import java.util.Arrays;

public class MyRC4 {

	public static void main1(String[] args) throws Exception {
		byte[] k = {0x1A, 0x2B, 0x3C, 0x4D, 0x5E, 0x6F,0x77};
		byte[] pt = new byte[1001]; //"This is a test message".getBytes();		

		//Encryption
		MyRC4 rc4 = new MyRC4(k);
		byte[] ct = rc4.encrypt(pt);
		
		//Decryption
		rc4 = new MyRC4(k);
		byte[] pt2 = rc4.decrypt(ct);
		
		//System.out.println( Arrays.toString(pt) );
		//System.out.println( Arrays.toString(ct) );		
		//System.out.println( Arrays.toString(pt2) );
	}

	public MyRC4(final byte[] key) {
		if (key.length < 1 || key.length > 256) {
			throw new IllegalArgumentException("key must be between 1 and 256 bytes");
		} else {
			keylen = key.length;
			for (int i = 0; i < 256; i++) {
				S[i] = (byte) i;
				T[i] = key[i % keylen];
			}
			int j = 0;
			byte tmp;
			for (int i = 0; i < 256; i++) {
				j = (j + S[i] + T[i]) & 0xFF;
				tmp = S[j];
				S[j] = S[i];
				S[i] = tmp;
			}
		}
		
		System.out.println("Table S after initialization");
		printS();
		
	}
	
	void printS() {
		for (int i = 0; i < 16; i++) {
			for (int j = 0; j < 16; j++) {
				System.out.print( String.format("%02X ", S[i*16+j]));
			}
			System.out.println();
		}
	}
	
	public byte[] encrypt(final byte[] plaintext) {
		final byte[] ciphertext = new byte[plaintext.length];
		int i = 0, j = 0, k, t;
		byte tmp;
		for (int counter = 0; counter < plaintext.length; counter++) {
			
			if (counter==100) {
				System.out.println("Table S after 100 iterations");
				printS();
			}

			if (counter==1000) {
				System.out.println("Table S after 1000 iterations");
				printS();
			}
			
			i = (i + 1) & 0xFF;
			j = (j + S[i]) & 0xFF;
			tmp = S[j];
			S[j] = S[i];
			S[i] = tmp;
			t = (S[i] + S[j]) & 0xFF;
			k = S[t];
			ciphertext[counter] = (byte) (plaintext[counter] ^ k);
		}
		return ciphertext;
	}

	public byte[] decrypt(final byte[] ciphertext) {
		return encrypt(ciphertext);
	}

	private final byte[] S = new byte[256];
	private final byte[] T = new byte[256];
	private int keylen = 0;	
}
