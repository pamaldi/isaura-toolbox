package cloud.isaura.toolbox.data_structure;

import java.util.BitSet;
import java.util.HashMap;

import cloud.isaura.toolbox.utils.Utils;

public class EliasFanoEncoder {
    //S(n,u)
	private int[] sequence;

	//last number in sequence
	private int u;

	//sequence lenght
	private int n;

	//upper (log2(u/n)) -> number of bits for each low part
	private int numberOfLowBits =0;
	//upper(log2(n)) -> umber of bits for each high part
	private int numberOfHighBits =0;

	private BitSet lowValues;
	private BitSet highValues;
	
	
	public void setSequence(int[] sequence) {
		this.sequence = sequence;
	}
	
	private void checkSequencePrerequisites() {
		if(sequence == null || sequence.length == 0 || sequence.length==1) {
			throw new IllegalArgumentException("Sequence null or length 0 or 1");
		}
		if(Utils.checkIfSequenceIsOrdered(sequence) == false) {
			throw new IllegalArgumentException("Sequence not ordered");
		}
	}
	
	private void checkEncodePrerequisites() {
		if(this.numberOfLowBits == 0|| this.numberOfHighBits == 0) {
			throw new IllegalArgumentException("Bit vectors null");
		}
	}
	
	public void prepare() {
		checkSequencePrerequisites();
		this.u = this.sequence[this.sequence.length-1];
		this.n = this.sequence.length;
		Double u_div_n = new Double(this.u)/new Double(this.n);
		this.numberOfLowBits = (int) Utils.logRoundToUpperInteger(2, u_div_n);
		this.numberOfHighBits = (int)Utils.logRoundToUpperInteger(2, u)-numberOfLowBits;
	}
	
	private void processSequenceElementForEncoding(HashMap<String, Integer> bucketsOfLow,
			StringBuffer stringBufferForLow, int k) {
		String binaryString = Utils.buildFromIntegerWithLeadingZero(numberOfLowBits + numberOfHighBits, this.sequence[k]);
		System.out.println("Processing "+this.sequence[k]+" with binaryString "+binaryString);
		int binaryStringLength = binaryString.length();
		int endIndexForLowBinaryString = binaryStringLength;
		int startIndexForLowBinaryString = binaryStringLength-this.numberOfLowBits;
		String lowBinaryString = binaryString.substring(startIndexForLowBinaryString,endIndexForLowBinaryString);
		stringBufferForLow.append(lowBinaryString);
		String highBinaryString = binaryString.substring(0,startIndexForLowBinaryString);
		System.out.println("Processing "+this.sequence[k]+" with lowbinaryString "+lowBinaryString);
		System.out.println("Processing "+this.sequence[k]+" with highbinaryString "+highBinaryString);
		if(!bucketsOfLow.containsKey(highBinaryString)) {
		   bucketsOfLow.put(highBinaryString, 0);	
		}
		Integer highStringOccurences = bucketsOfLow.get(highBinaryString);
		bucketsOfLow.put(highBinaryString, highStringOccurences+1);
	}
	
	public void encode() {
		checkEncodePrerequisites();
		HashMap<String, Integer> bucketsOfLow = new HashMap<String, Integer>();
		StringBuffer stringBufferForLow = new StringBuffer();
		for(int k = 0; k< this.n;k++) {
			processSequenceElementForEncoding(bucketsOfLow, stringBufferForLow, k);
			
		}
		System.out.println(" Low string "+stringBufferForLow.toString());
		//System.out.println("Print buckets of High");
		//bucketsOfLow.forEach((k, v) -> System.out.println((k + ":" + v)));	
		this.lowValues = Utils.buildBitSetFromString(stringBufferForLow.toString());
		System.out.println("Print low "+Utils.getBitSetRepresentation(this.lowValues, getCurrentSizeLowValues()));
		bucketsOfLow.forEach((k, v) -> {
				System.out.println(k + ":" + v);
				
		});
		
	}


	private int getCurrentSizeLowValues() {
		int currentSizeLowValues = this.numberOfLowBits *this.n;
		return currentSizeLowValues;
	}

	
	public int getNumberOfHighBits() {
		return numberOfHighBits;
	}

	public int getU() {
		return u;
	}

	public int getN() {
		return n;
	}

	public int getNumberOfLowBits() {
		return numberOfLowBits;
	}

	public void setNumberOfLowBits(int numberOfLowBits) {
		this.numberOfLowBits = numberOfLowBits;
	}
	
	public String getLowBitString() {
		return Utils.getBitSetRepresentation(this.lowValues, getCurrentSizeLowValues());
	}
	
	
}
