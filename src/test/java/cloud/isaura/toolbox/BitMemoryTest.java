package cloud.isaura.toolbox;

import cloud.isaura.toolbox.utils.MemoryUtils;

import java.util.BitSet;

public class BitMemoryTest {

	public static void main(String[] args) {
		
		System.out.println("**** Start Memory Usage ****");
		int numberOfBits = 999999999;
		long usedMemoryInit = MemoryUtils.checkMemory(1);
		long usedMemoryTot = usedMemoryInit;
		System.out.println("usedMemoryInit "+usedMemoryInit);
		
		boolean[] booleanArrayOfBits = new boolean[numberOfBits];
		for(int i = 0; i < numberOfBits;i++) {
			booleanArrayOfBits[i]=true;
		}
		MemoryUtils.checkMemory(1024);
		long usedMemoryBoolean = MemoryUtils.checkMemory(1)-usedMemoryTot;
		usedMemoryTot = usedMemoryBoolean;
		System.out.println("usedMemoryBoolean "+usedMemoryBoolean);
		System.out.println("usedMemoryBoolean per bit in bit "+(double)usedMemoryBoolean/numberOfBits*8);
		
		
		BitSet bs = new BitSet(numberOfBits);
		for(int i = 0; i < numberOfBits;i++) {
			bs.set(i);
		}
		long usedMemoryBitset = MemoryUtils.checkMemory(1)-usedMemoryTot;
		System.out.println("usedMemoryBitset "+usedMemoryBitset);
		System.out.println("usedMemoryBitset per bit in bit "+(double)usedMemoryBitset/numberOfBits*8);
	}

}
