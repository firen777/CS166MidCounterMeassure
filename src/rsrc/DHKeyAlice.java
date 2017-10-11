package rsrc;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.security.*;
import java.security.spec.*;
import javax.crypto.*;
import javax.crypto.spec.*;

public class DHKeyAlice {

    public static void main1(String argv[]) throws Exception {

    	//Step 1: Generates DH parameters
        AlgorithmParameterGenerator paramGen = AlgorithmParameterGenerator.getInstance("DH");
        paramGen.init(512);
        AlgorithmParameters params = paramGen.generateParameters();
        DHParameterSpec dhSkipParamSpec = (DHParameterSpec)params.getParameterSpec(DHParameterSpec.class);
        
        //Step 2: Generates DH key pair
        KeyPairGenerator aliceKpairGen = KeyPairGenerator.getInstance("DH");
        aliceKpairGen.initialize(dhSkipParamSpec);
        KeyPair aliceKpair = aliceKpairGen.generateKeyPair();

        //Step 3: Generates DH KeyAgreement object
        KeyAgreement aliceKeyAgree = KeyAgreement.getInstance("DH");
        aliceKeyAgree.init(aliceKpair.getPrivate());

        //Step 4: Send public key to bob.
        byte[] alicePubKeyEnc = aliceKpair.getPublic().getEncoded();
        Utils.prettyPrint("Alice public key", alicePubKeyEnc);
        
        Socket socket = new Socket("localhost", 7890);
    	DataInputStream dIn = new DataInputStream(socket.getInputStream());
        DataOutputStream dOut = new DataOutputStream(socket.getOutputStream());
        Utils.sendBytes(dOut,alicePubKeyEnc);
       
        //Step 4: receive Bob's public key
        byte[] bobPubKeyEnc =Utils.receiveBytes(dIn);
    	
    	//Step 5: instantiate a DH public key from Bob's public key
        KeyFactory aliceKeyFac = KeyFactory.getInstance("DH");
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(bobPubKeyEnc);
        PublicKey bobPubKey = aliceKeyFac.generatePublic(x509KeySpec);
        aliceKeyAgree.doPhase(bobPubKey, true);
        
        //Step 5: calculate the shared secret
        byte[] aliceSharedSecret = aliceKeyAgree.generateSecret();
        Utils.prettyPrint("Shared secret", aliceSharedSecret);
        
        //Step 6: generate symmetric session key for encryption
        aliceKeyAgree.doPhase(bobPubKey, true);
        SecretKey aliceDesKey = aliceKeyAgree.generateSecret("DES");
        
        Cipher aliceCipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
        aliceCipher.init(Cipher.ENCRYPT_MODE, aliceDesKey);
        byte[] cleartext = "Hi this is a secret message from Alice to Bob".getBytes();
        byte[] ciphertext = aliceCipher.doFinal(cleartext);
        Utils.prettyPrint("ciphertext to send", ciphertext);      
        
        //Send to Bob
        Utils.sendBytes(dOut,ciphertext);
        socket.close();
    }

}
