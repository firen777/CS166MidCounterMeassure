/**
 * 
 */

/**
 * @author albertchan
 * 
 * <p>
 * Let p be Prime, g be Generator, both public<br>
∀ x ∈ {1,2...,p-1}, ∃ n s.t. x=g^n mod p//uniformly distribute answer <br>
Alice: a //private<br>
Bob: b //private<br>
Alice -> Bob: g^a mod p;<br>
Bob -> Alice: g^b mod p;<br>
Bob: (g^a mod p)^b mod p = (g^a)^b mod p = g^ab mod p<br>
Alice: (g^b mod p)^a mod p = g^ab mod p //based on mod of products property<br>
g^ab mod p is the secret symmetric key<br>
 * </p>
 *
 */
public class DH_calc {
	
	public int pPub;
	public int gPub;
	public int alicePvt;
	public int bobPvt;
	
	public int a2bPub;
	public int b2aPub;
	
	public int symKey;
	
	public boolean debugWorking;


	/**
	 * 
	  <p>
	  Let p be Prime, g be Generator, both public<br>
	∀ x ∈ {1,2...,p-1}, ∃ n s.t. x=g^n mod p//uniformly distribute answer <br>
	Alice: a //private<br>
	Bob: b //private<br>
	Alice -> Bob: g^a mod p;<br>
	Bob -> Alice: g^b mod p;<br>
	Bob: (g^a mod p)^b mod p = (g^a)^b mod p = g^ab mod p<br>
	Alice: (g^b mod p)^a mod p = g^ab mod p //based on mod of products property<br>
	g^ab mod p is the secret symmetric key<br>
	  </p>
	 * @param p Prime number
	 * @param g Generator, ∀ x ∈ {1,2...,p-1}, ∃ n s.t. x=g^n mod p
	 * @param a Alice private number
	 * @param b Bob private number
	 */
	public DH_calc(int p, int g, int a, int b) {
		this.pPub = p;
		this.gPub = g;
		this.alicePvt = a;
		this.bobPvt = b;
		this.a2bPub = ByteUtil.effMod(g, a, p);
		this.b2aPub = ByteUtil.effMod(g, b, p);
		
		this.debugWorking = (ByteUtil.effMod(a2bPub, b, p)==ByteUtil.effMod(b2aPub, a, p));
		
		this.symKey = ByteUtil.effMod(a2bPub, b, p);
	}
	
	
	

}
