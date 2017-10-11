package rsrc;

import java.io.*;
import java.nio.file.*;
import java.security.*;
import java.util.*;

public class Sign {

	public static void main1(String[] args) throws Exception {

		//Data
		byte[] data = "This is a test message".getBytes();
		
		Signature dsa = Signature.getInstance("SHA1withDSA");
		
		KeyPairGenerator keyGen = KeyPairGenerator.getInstance("DSA");
		SecureRandom random = SecureRandom.getInstance("SHA1PRNG", "SUN");
		keyGen.initialize(1024, random);				
		KeyPair pair = keyGen.generateKeyPair();
		PrivateKey priv = pair.getPrivate();
		dsa.initSign(priv);
		
		/* Update and sign the data */
		dsa.update(data);
		byte[] sig = dsa.sign();
		Utils.printHex(sig);
		
		//Verify signature
		PublicKey pub = pair.getPublic();
		dsa.initVerify(pub);

		/* Update and verify the data */
		dsa.update(data);
		boolean verifies = dsa.verify(sig);
		System.out.println("signature verifies: " + verifies);
		
		getPrivateKeyFromStore();
		
	}	
	
	public static void main2(String[] args) throws Exception {

		//Data
		byte[] data = "This is a test message".getBytes();
		
		Signature dsa = Signature.getInstance("SHA1withDSA");
		
		KeyPairGenerator keyGen = KeyPairGenerator.getInstance("DSA");
		SecureRandom random = SecureRandom.getInstance("SHA1PRNG", "SUN");
		keyGen.initialize(1024, random);				
		KeyPair pair = keyGen.generateKeyPair();
		PrivateKey priv = pair.getPrivate();
		dsa.initSign(priv);
		
		/* Update and sign the data */
		dsa.update(data);
		byte[] sig = dsa.sign();
		Utils.printHex(sig);
		
		//Verify signature
		PublicKey pub = pair.getPublic();
		dsa.initVerify(pub);

		/* Update and verify the data */
		dsa.update(data);
		boolean verifies = dsa.verify(sig);
		System.out.println("signature verifies: " + verifies);
		
		getPrivateKeyFromStore();
		
	}
	
	static PrivateKey getPrivateKeyFromStore() throws Exception {
		KeyStore ks = KeyStore.getInstance(  KeyStore.getDefaultType() );
		Path kpath = Paths.get("c:/users/patawale", ".keystore");
		InputStream fis = Files.newInputStream( kpath);
		char[] pass = {'1','2','3','4','5','6'};
		ks.load(fis,pass);
		PrivateKey pk = (PrivateKey) ks.getKey("testkey1", pass);
		System.out.println(  Arrays.toString(pk.getEncoded()));
		return pk;
	}
	
	
}
