import java.util.ArrayList;
import java.util.Collections;

/**
 * 
 */

/**Re-use HW2 codes
 * @author Albert
 *
 */
public class RC4_calc {
	private ArrayList<Integer> S;
	private ArrayList<Integer> K;
	private int streamI;
	private int streamJ;

	/**
	 * Create S[] and K[]
	 * initializing i, j
	 */
	public RC4_calc(){
		this.S = new ArrayList<Integer>(Collections.nCopies(256, 0));
		this.K = new ArrayList<Integer>(Collections.nCopies(256, 0));
		this.streamI=0;
		this.streamJ=0;
	}
	
	/**
	 * initialize S[]
	 * @param key key
	 */
	public void initialization(ArrayList<Integer> key){
		resetS();
		for (int i=0; i < 256; i++){
			K.set(i, key.get(i%key.size()));
		}
		
		int j=0;
		for (int i=0; i< 256; i++){
			j = (j+S.get(i)+K.get(i)) % 256;
			Collections.swap(S, i, j);
		}
	}
	
	/**
	 * @return the streamI
	 */
	public int getStreamI() {
		return streamI;
	}

	/**
	 * @return the streamJ
	 */
	public int getStreamJ() {
		return streamJ;
	}

	/**
	 * call initialization() before calling this function 
	 * @return a byte of key stream.
	 */
	public int getKeyStream(){
		
		streamI = (streamI+1) % 256;
		streamJ = (streamJ + S.get(streamI)) % 256;
		Collections.swap(S, streamI, streamJ);
		
		int t = (S.get(streamI)+S.get(streamJ)) % 256;
		
		
		return S.get(t);
	}
	
	
	/**
	 * get String of S[] in the form of 16x16 matrix and in Hex form
	 */
	public String getSMatrix() {
		String result = "";
		for (int i=0; i<16; i++){
			for (int j=0; j<16; j++){
				int temp = S.get(i*16+j);
				result = result + String.format("%02X ", temp) + " ";
			}
			result += "\n";
		}
		return result;
	}
	

	/**
	 * reset S to [0x00..0xFF]
	 */
	private void resetS(){
		for (int i=0; i < 256; i++){
			S.set(i, i);
		}
	}

}
