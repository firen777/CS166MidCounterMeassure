import java.math.BigInteger;
import java.util.Arrays;

/**
 * 
 */

/**
 * @author Albert
 * <p>
 * RSA<br>
N=pq //p,q is large Prime<br>
e coprime (p-1)(q-1)<br>
d = e^-1 mod (p-1)(q-1) //must exist due to last statement<br>
//Also, ed = 1 mod (p-1)(q-1)<br>
public:(N,e)<br>
Private:d<br>
C=M^e mod N<br>
M=C^d mod N<br></p>
 */
public class RSA_calc {
	public int pubN;
	public int p;
	public int q;
	public int pubE;
	public int pvtD;
	
	/**p,q is large Prime; e coprime (p-1)(q-1)
	 * @param p
	 * @param q
	 * @param pubE
	 */
	public RSA_calc(int p, int q, int pubE) {
		super();
		this.p = p;
		this.q = q;
		this.pubE = pubE;
		this.pubN = p*q;
		
		BigInteger bigE = BigInteger.valueOf(pubE);
		BigInteger bigP_1 = BigInteger.valueOf(p-1);
		BigInteger bigQ_1 = BigInteger.valueOf(q-1);
		BigInteger pqMix = bigP_1.multiply(bigQ_1);
		
		this.pvtD = bigE.modInverse(pqMix).intValue();
	}
	
	
	/**
	 * @param n
	 * @param e
	 * @param mod
	 * @return n^e%mod
	 */
	public BigInteger effMod(BigInteger n, BigInteger e, BigInteger mod){
		boolean[] bits = new boolean[32];
		
		for (int i=31; i>=0; i--) //e to 32 bits binary
			bits[31-i] = (e.intValue() & (1<<i)) != 0;
		
//		for (boolean b:bits)
//			if (b)
//				System.out.print(1);
//			else
//				System.out.print(0);
//		
//		System.out.println("\n============");
		
		BigInteger result = BigInteger.ONE;
		for (boolean b:bits)
			if (b)
				result = result.multiply(result).multiply(n).mod(mod);
			else
				result = result.multiply(result).mod(mod);

		return result;
	}
	
	/**
	 * Process message with public key
	 * @param msg
	 * @return
	 */
	public int pub2pvt(int msg){
		return BigInteger.valueOf(msg).pow(pubE).mod(BigInteger.valueOf(pubN)).intValue();
	}
	
	/**
	 * Process message with public key
	 * @param msg
	 * @return
	 */
	public int pub2pvtFast(int msg){
		return effMod(BigInteger.valueOf(msg), BigInteger.valueOf(pubE), BigInteger.valueOf(pubN)).intValue();
	}
	
	/**
	 * Process message with private key
	 * @param cipher
	 * @return
	 */
	public int pvt2pub(int cipher){
		return BigInteger.valueOf(cipher).pow(pvtD).mod(BigInteger.valueOf(pubN)).intValue();
	}
	
	/**
	 * Process message with private key
	 * @param cipher
	 * @return
	 */
	public int pvt2pubFast(int cipher){
		return effMod(BigInteger.valueOf(cipher), BigInteger.valueOf(pvtD), BigInteger.valueOf(pubN)).intValue();
	}
	
}
