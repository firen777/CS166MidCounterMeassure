package rsrc;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.*;
import java.security.spec.*;

import javax.crypto.*;
import javax.crypto.spec.*;
import javax.crypto.interfaces.*;

/**
 * This program executes the Diffie-Hellman key agreement protocol
 * between 2 parties: Alice and Bob.
 *
 */

public class DHKeyBob {

    public static void main1(String argv[]) throws Exception {

    	System.out.println("Waiting for Alice public key: ");
    	
    	//Step 1: listen to Alice's public key
    	ServerSocket ss = new ServerSocket(7890);
        Socket socket = ss.accept();
    	DataInputStream dIn = new DataInputStream(socket.getInputStream());
    	DataOutputStream dOut = new DataOutputStream(socket.getOutputStream());
    	
    	byte[] alicePubKeyEnc = Utils.receiveBytes(dIn);
    	Utils.prettyPrint("Alice public key", alicePubKeyEnc);
    	
    	//Step 2: retrieve Alice's public key object
        KeyFactory bobKeyFac = KeyFactory.getInstance("DH");
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(alicePubKeyEnc);
        PublicKey alicePubKey = bobKeyFac.generatePublic(x509KeySpec);

        //Step 3: get DH parameters from Alice public key
        DHParameterSpec dhParamSpec = ((DHPublicKey)alicePubKey).getParams();

        //Step 4: create a compatible key pair
        KeyPairGenerator bobKpairGen = KeyPairGenerator.getInstance("DH");
        bobKpairGen.initialize(dhParamSpec);
        KeyPair bobKpair = bobKpairGen.generateKeyPair();
        KeyAgreement bobKeyAgree = KeyAgreement.getInstance("DH");
        bobKeyAgree.init(bobKpair.getPrivate());

        //Step 5: Send public key to Alice
        byte[] bobPubKeyEnc = bobKpair.getPublic().getEncoded();
        Utils.sendBytes(dOut,bobPubKeyEnc);
    	Utils.prettyPrint("Bob public key", bobPubKeyEnc);
       
        //Step 6a: Do key agreement calculations to generate a byte array
        bobKeyAgree.doPhase(alicePubKey, true); 
        byte[] sharedSecret = bobKeyAgree.generateSecret();
    	Utils.prettyPrint("Shared secret", sharedSecret);
        
        //Step 6b: Do key agreement calculations to generate secret key object
        bobKeyAgree.doPhase(alicePubKey, true);
        SecretKey bobDesKey = bobKeyAgree.generateSecret("DES");
                
        //Receive the encrypted text over the socket
        byte[] ciphertext = Utils.receiveBytes(dIn);

        Cipher bobCipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
        bobCipher.init(Cipher.DECRYPT_MODE, bobDesKey);
        byte[] recovered = bobCipher.doFinal(ciphertext);
        System.out.println("Recovered message: " + new String(recovered));
    }    
}
