import java.math.BigInteger;

/**
 * 
 */

/**
 * @author Albert
 * <p>
 * Knapsack Spec:<br>
 * m coprime n <br>
 * n > sum of SIK <br>
 * GK[] = SIK[]*m mod n<br>
 * public: GK<br>
 * private: m-1 mod n = magic; SIK<br>
 * Encrypt:<br>
 * M[binary] //same length as GK<br>
 * for every set bit, Sum+=GK[i]<br>
 * Sum = C<br>
 * Decrypt:<br>
 * C*magic mod n = Sum'<br>
 * Solve SIK with Sum'<br>
 * </p>
 */
public class Knapsack_calc {
	public int m;
	public int n;
	public int[] pubKeyGK;
	public int prvMagic;
	public int[] prvSIK;
	
	/**
	 * m coprime n; n > sum of SIK
	 * @param m
	 * @param n
	 * @param prvSIK
	 */
	public Knapsack_calc(int m, int n, int[] prvSIK) {
		super();
		this.m = m;
		this.n = n;
		this.prvSIK = prvSIK;
		
		this.pubKeyGK = new int[prvSIK.length];
		for (int i=0; i<prvSIK.length; i++){
			pubKeyGK[i] = (prvSIK[i] * m) % n;
		}
		BigInteger bigM = BigInteger.valueOf(m);
		BigInteger bigN = BigInteger.valueOf(n);
		
		this.prvMagic = bigM.modInverse(bigN).intValue();
	}
	
	/**
	 * @param msg binary message. Exp: b'1010 => byte[]{1,0,1,0} 
	 * @return int cipher 
	 */
	public int pub2prvEncrypt(byte[] msg){
		int sum=0;
		for (int i=0; i<msg.length; i++){
			if (msg[i]!=0)
				sum+=pubKeyGK[i];
		}
		return sum;
	}
	
	/**
	 * @param cipher int
	 * @return msg byte[]
	 */
	public byte[] prv2pubDecrypt(int cipher){
		int sum = (cipher*prvMagic) % n;
		byte[] msg = new byte[prvSIK.length];
		int current = 0;
		for (int i=msg.length-1; i>=0; i--){
			if (prvSIK[i]+current>sum)
				msg[i] = 0;
			else {
				current += prvSIK[i];
				msg[i] = 1;
			}
		}
		return msg;
	}
}
