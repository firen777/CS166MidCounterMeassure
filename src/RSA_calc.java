import java.math.BigInteger;

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
	public int prvD;
	
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
		
		this.prvD = bigE.modInverse(pqMix).intValue();
	}
	
	/**
	 * Process message with public key
	 * @param msg
	 * @return
	 */
	public int pub2prv(int msg){
		return BigInteger.valueOf(msg).pow(pubE).mod(BigInteger.valueOf(pubN)).intValue();
	}
	
	/**
	 * Process message with private key
	 * @param cipher
	 * @return
	 */
	public int prv2pub(int cipher){
		return BigInteger.valueOf(cipher).pow(prvD).mod(BigInteger.valueOf(pubN)).intValue();
	}
	
	
	
}
