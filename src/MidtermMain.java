
public class MidtermMain {

	public static void main(String[] args) throws Exception {
		
		ByteUtil b = new ByteUtil();
		////////////////////
		Knapsack_calc k = new Knapsack_calc(11, 40, new int[]{2, 5, 8, 17});
		
		for (int i:k.pubKeyGK){
			System.out.print(i+",");
		}
		System.out.println();
		System.out.println(k.pub2prvEncrypt(new byte[]{1,0,1,0}));
		
		for (byte bit:k.prv2pubDecrypt(30)){
			System.out.print(bit);
		}
		System.out.println();
		////////////////////
		RSA_calc r = new RSA_calc(3, 17, 3);
		System.out.println(r.pubN + "," + r.prvD + ";" + r.pub2prv(5));
		
		System.out.println(r.prv2pub(r.pub2prv(77)));
		System.out.println(r.pub2prv(r.prv2pub(81)));
		
	}

}
