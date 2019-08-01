package cloud.isaura.toolbox.utils;

import java.util.BitSet;

import javax.naming.OperationNotSupportedException;

public class Utils {

	
	public static Boolean checkIfSequenceIsOrdered(int[] sequence) {
		Boolean ret = true;
		for (int   i = 0;   i < sequence.length;   i++) {
			if(i==sequence.length-1) {
				break;
			}
			if(sequence[i]>sequence[i+1]) {
				ret = false;
				break;
			}
		}
		return ret;
	}
	
	public static double log(double base, double number ) {
		return Math.log10(number)/Math.log10(base);
	}
	
	public static double logRoundToUpperInteger(double base, double number ) {
		return Math.ceil(log(base, number));
	}
	
	public static String buildStringWithNZeros(int n) {
		StringBuffer sb = new StringBuffer(n);
		for(int i = 0 ; i < n; i ++) {
			sb.append("0");
		}
		return sb.toString();
	}
	
	public static String buildFromIntegerWithLeadingZero(int lenght, int number) {
		StringBuffer binaryString = new StringBuffer(Integer.toBinaryString(number));
		int left = lenght - binaryString.length();
		if(left > 0) {
		   binaryString.insert(0, buildStringWithNZeros(left));
		}
		return binaryString.toString();
		
	}
	
	public static BitSet buildBitSetFromString(String binaryString) {
		char[] ca = binaryString.toCharArray();
		BitSet bs = new BitSet();
		int k = 0;
		for(int i = 0; i < ca.length;i++) {
			if(!(ca[i]=='1' || ca[i]=='0') ) {
				throw new IllegalArgumentException("String must contains 1 or 0");
			}
			
			int indexInsert = ca.length-1-i;
			//System.out.println("Iteration "+i+" ca[i] "+ca[i]+" indexInsert "+indexInsert);
			if(ca[i]=='1') {
				bs.set(indexInsert,true);
			}
			if(ca[i]=='0') {
				bs.set(indexInsert,false);
			}
			
		}
		return bs;
	}
	
	public static int  addToBitSetFromString(String binaryString, BitSet bs, int indexFrom) {
		char[] ca = binaryString.toCharArray();
		int inserted = 0;
		for(int i = ca.length-1; i >= 0;i--) {
			if(!(ca[i]=='1' || ca[i]=='0') ) {
				throw new IllegalArgumentException("String must contains 1 or 0");
			}
			if(ca[i]=='1') {
				bs.set(indexFrom++,true);
			}
			if(ca[i]=='0') {
				bs.set(indexFrom++,false);
			}
			inserted++;			
		}
		return inserted;
		
	}
	
	public static String getBitSetRepresentation(BitSet bs, int len) {
		StringBuilder sb = new StringBuilder(len);
		for(int i = len-1;i>=0;i--) {
			String str = bs.get(i) == true?"1":"0";
			//System.out.println("reading pos "+i+" : "+str);
			sb.append(str);
		}
		return sb.toString();
	}


}
