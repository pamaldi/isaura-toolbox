package cloud.isaura.toolbox.utils;

public class MemoryUtils {

	public static long checkMemory(int unit) {
		// Getting the runtime reference from system
		
		Runtime runtime = Runtime.getRuntime();

		//System.out.println("##### Heap utilization statistics [MB] #####");

		// Print used memory
		long usedMemory = (runtime.totalMemory() - runtime.freeMemory()) / unit;
		//System.out.println("Used Memory:" + usedMemory);

		// Print free memory
		//System.out.println("Free Memory:" + runtime.freeMemory() / unit);

		// Print total available memory
		//System.out.println("Total Memory:" + runtime.totalMemory() / unit);

		// Print Maximum available memory
		//System.out.println("Max Memory:" + runtime.maxMemory() / unit);
		
		return usedMemory;
	}

}
