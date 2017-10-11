package rsrc;

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

public class DHKey {

    public static void main1(String argv[]) throws Exception {

    	//Step 1: Alice generates DH parameters
        AlgorithmParameterGenerator paramGen = AlgorithmParameterGenerator.getInstance("DH");
        paramGen.init(512);
        AlgorithmParameters params = paramGen.generateParameters();
        DHParameterSpec dhSkipParamSpec = (DHParameterSpec)params.getParameterSpec(DHParameterSpec.class);
        
        /*
         * Alice creates her own DH key pair, using the DH parameters from
         * above
         */
        System.out.println("ALICE: Generate DH keypair ...");
        KeyPairGenerator aliceKpairGen = KeyPairGenerator.getInstance("DH");
        aliceKpairGen.initialize(dhSkipParamSpec);
        KeyPair aliceKpair = aliceKpairGen.generateKeyPair();

        // Alice creates and initializes her DH KeyAgreement object
        System.out.println("ALICE: Initialization ...");
        KeyAgreement aliceKeyAgree = KeyAgreement.getInstance("DH");
        aliceKeyAgree.init(aliceKpair.getPrivate());

        // Alice encodes her public key, and sends it over to Bob.
        byte[] alicePubKeyEnc = aliceKpair.getPublic().getEncoded();
        System.out.print("ALICE: public key: ");
        Utils.printHex(alicePubKeyEnc);

        /*
         * Let's turn over to Bob. Bob has received Alice's public key
         * in encoded format.
         * He instantiates a DH public key from the encoded key material.
         */
        KeyFactory bobKeyFac = KeyFactory.getInstance("DH");
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(alicePubKeyEnc);
        PublicKey alicePubKey = bobKeyFac.generatePublic(x509KeySpec);

        /*
         * Bob gets the DH parameters associated with Alice's public key.
         * He must use the same parameters when he generates his own key
         * pair.
         */
        DHParameterSpec dhParamSpec = ((DHPublicKey)alicePubKey).getParams();

        // Bob creates his own DH key pair
        System.out.println("BOB: Generate DH keypair ...");
        KeyPairGenerator bobKpairGen = KeyPairGenerator.getInstance("DH");
        bobKpairGen.initialize(dhParamSpec);
        KeyPair bobKpair = bobKpairGen.generateKeyPair();

        // Bob creates and initializes his DH KeyAgreement object
        System.out.println("BOB: Initialization ...");
        KeyAgreement bobKeyAgree = KeyAgreement.getInstance("DH");
        bobKeyAgree.init(bobKpair.getPrivate());

        // Bob encodes his public key, and sends it over to Alice.
        byte[] bobPubKeyEnc = bobKpair.getPublic().getEncoded();

        /*
         * Alice uses Bob's public key for the first (and only) phase
         * of her version of the DH
         * protocol.
         * Before she can do so, she has to instantiate a DH public key
         * from Bob's encoded key material.
         */
        KeyFactory aliceKeyFac = KeyFactory.getInstance("DH");
        x509KeySpec = new X509EncodedKeySpec(bobPubKeyEnc);
        PublicKey bobPubKey = aliceKeyFac.generatePublic(x509KeySpec);
        System.out.println("ALICE: Execute PHASE1 ...");
        aliceKeyAgree.doPhase(bobPubKey, true);

        /*
         * Bob uses Alice's public key for the first (and only) phase
         * of his version of the DH
         * protocol.
         */
        System.out.println("BOB: Execute PHASE1 ...");
        bobKeyAgree.doPhase(alicePubKey, true);

        /*
         * At this stage, both Alice and Bob have completed the DH key
         * agreement protocol.
         * Both generate the (same) shared secret.
         */
        byte[] aliceSharedSecret = aliceKeyAgree.generateSecret();
        int aliceLen = aliceSharedSecret.length;

        byte[] bobSharedSecret = new byte[aliceLen];
        bobKeyAgree.generateSecret(bobSharedSecret, 0);

        System.out.println("Alice secret: " + toHexString(aliceSharedSecret));
        System.out.println("Bob secret: " + toHexString(bobSharedSecret));

        if (!java.util.Arrays.equals(aliceSharedSecret, bobSharedSecret))
            throw new Exception("Shared secrets differ");
        System.out.println("Shared secrets are the same");

        /*
         * Now let's return the shared secret as a SecretKey object
         * and use it for encryption. First, we generate SecretKeys for the
         * "DES" algorithm (based on the raw shared secret data) and
         * then we use DES in ECB mode
         * as the encryption algorithm. DES in ECB mode does not require any
         * parameters.
         *
         * Then we use DES in CBC mode, which requires an initialization
         * vector (IV) parameter. In CBC mode, you need to initialize the
         * Cipher object with an IV, which can be supplied using the
         * javax.crypto.spec.IvParameterSpec class. Note that you have to use
         * the same IV for encryption and decryption: If you use a different
         * IV for decryption than you used for encryption, decryption will
         * fail.
         *
         * NOTE: If you do not specify an IV when you initialize the
         * Cipher object for encryption, the underlying implementation
         * will generate a random one, which you have to retrieve using the
         * javax.crypto.Cipher.getParameters() method, which returns an
         * instance of java.security.AlgorithmParameters. You need to transfer
         * the contents of that object (e.g., in encoded format, obtained via
         * the AlgorithmParameters.getEncoded() method) to the party who will
         * do the decryption. When initializing the Cipher for decryption,
         * the (reinstantiated) AlgorithmParameters object must be passed to
         * the Cipher.init() method.
         */
        System.out.println("Return shared secret as SecretKey object ...");
        // Bob
        // NOTE: The call to bobKeyAgree.generateSecret above reset the key
        // agreement object, so we call doPhase again prior to another
        // generateSecret call
        bobKeyAgree.doPhase(alicePubKey, true);
        SecretKey bobDesKey = bobKeyAgree.generateSecret("DES");

        // Alice
        // NOTE: The call to aliceKeyAgree.generateSecret above reset the key
        // agreement object, so we call doPhase again prior to another
        // generateSecret call
        aliceKeyAgree.doPhase(bobPubKey, true);
        SecretKey aliceDesKey = aliceKeyAgree.generateSecret("DES");

        /*
         * Bob encrypts, using DES in ECB mode
         */
        Cipher bobCipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
        bobCipher.init(Cipher.ENCRYPT_MODE, bobDesKey);

        byte[] cleartext = "This is just an example".getBytes();
        byte[] ciphertext = bobCipher.doFinal(cleartext);

        /*
         * Alice decrypts, using DES in ECB mode
         */
        Cipher aliceCipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
        aliceCipher.init(Cipher.DECRYPT_MODE, aliceDesKey);
        byte[] recovered = aliceCipher.doFinal(ciphertext);

        if (!java.util.Arrays.equals(cleartext, recovered))
            throw new Exception("DES in CBC mode recovered text is " +
              "different from cleartext");
        System.out.println("DES in ECB mode recovered text is " +
            "same as cleartext");

        /*
         * Bob encrypts, using DES in CBC mode
         */
        bobCipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
        bobCipher.init(Cipher.ENCRYPT_MODE, bobDesKey);

        cleartext = "This is just an example".getBytes();
        ciphertext = bobCipher.doFinal(cleartext);
        // Retrieve the parameter that was used, and transfer it to Alice in
        // encoded format
        byte[] encodedParams = bobCipher.getParameters().getEncoded();

        /*
         * Alice decrypts, using DES in CBC mode
         */
        // Instantiate AlgorithmParameters object from parameter encoding
        // obtained from Bob
        AlgorithmParameters params2 = AlgorithmParameters.getInstance("DES");
        params2.init(encodedParams);
        aliceCipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
        aliceCipher.init(Cipher.DECRYPT_MODE, aliceDesKey, params2);
        recovered = aliceCipher.doFinal(ciphertext);

        if (!java.util.Arrays.equals(cleartext, recovered))
            throw new Exception("DES in CBC mode recovered text is " +
              "different from cleartext");
        System.out.println("DES in CBC mode recovered text is " +
            "same as cleartext");
    }

    /*
     * Converts a byte to hex digit and writes to the supplied buffer
     */
    private static void byte2hex(byte b, StringBuffer buf) {
        char[] hexChars = { '0', '1', '2', '3', '4', '5', '6', '7', '8',
                            '9', 'A', 'B', 'C', 'D', 'E', 'F' };
        int high = ((b & 0xf0) >> 4);
        int low = (b & 0x0f);
        buf.append(hexChars[high]);
        buf.append(hexChars[low]);
    }

    /*
     * Converts a byte array to hex string
     */
    private static String toHexString(byte[] block) {
        StringBuffer buf = new StringBuffer();

        int len = block.length;

        for (int i = 0; i < len; i++) {
             byte2hex(block[i], buf);
             if (i < len-1) {
                 buf.append(":");
             }
        }
        return buf.toString();
    }
}
