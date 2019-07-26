package cloud.isaura.toolbox.algo.example;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Random;
import java.util.StringTokenizer;

public class MaxPairwiseProductStressTest {
    static long getMaxPairwiseProductFast(int[] numbers) {
        long max_product = 0;
        int n = numbers.length;
        int numIterations = n / 2;
        int currentIteration = 0;
        int [][]couples = new int[numIterations][2];
        long largeMax=0;
        long largeLow=0;
        int indexLargeMax=0;
        //System.out.println("array "+getArrayRepr(numbers));
        for (int i = 0; currentIteration< numIterations; i=i+2) {
            int low = numbers[i] < numbers[i + 1] ? numbers[i] : numbers[i + 1];
            int high = numbers[i] > numbers[i + 1] ? numbers[i] : numbers[i + 1];
            int[] couple = new int[2];
            couple[0] = low;
            couple[1] = high;
            couples[currentIteration] = couple;
            if(high>largeMax){
                largeMax = high;
                indexLargeMax = currentIteration;
            }
            if(low>largeLow){
                largeLow=low;
            }
            currentIteration++;

        }

        long secondMax = 0;
        for (int i = 0; i < numIterations;i++){
             if(i != indexLargeMax && couples[i][1]>secondMax){
                 secondMax=couples[i][1];
             }
        }
        //System.out.println("largeMax "+largeMax);
        //System.out.println("indexLargeMax "+indexLargeMax);
        //System.out.println("largeLow "+largeLow);
        //System.out.println("secondMax "+secondMax);
        secondMax = secondMax>largeLow?secondMax:largeLow;
        Boolean pairLength=n % 2 == 0;
        if(pairLength == false) {
            if (numbers[n - 1] > largeMax) {
                secondMax = largeMax;
                largeMax = numbers[n - 1];
            } else if (numbers[n - 1] > secondMax) {
                secondMax = numbers[n - 1];
            }
        }
        max_product=largeMax*secondMax;
        return max_product;
    }

    static long getMaxPairwiseProductNaive(int[] numbers) {
        long max_product = 0;
        int n = numbers.length;
        for (int i = 0; i<n;i++){
            for(int j = 0; j <n;j++){
                if(i != j){
                    long cmax = numbers[i]*numbers[j];
                    if(cmax> max_product){
                        max_product = cmax;
                    }
                }
            }
        }

        return max_product;
    }


    public static void main(String[] args) {
        FastScanner scanner = new FastScanner(System.in);
        int n = scanner.nextInt();
        int[] numbers = new int[n];
        for (int i = 0; i < n; i++) {
            numbers[i] = scanner.nextInt();
        }
        //System.out.println(getMaxPairwiseProductFast(numbers));
        stressTest();
    }

    private static void stressTest() {
        for(int i=0; i < 1000;i++){
            Random r = new Random();
            int al = r.ints(2, (10+ 1)).findFirst().getAsInt();
            int[] array = new int[al];
            for(int j = 0; j < al; j++){
                array[j]=r.ints(0, (100)).findFirst().getAsInt();
            }
            long r1 = getMaxPairwiseProductFast(array);
            long r2 = getMaxPairwiseProductNaive(array);
            //System.out.println("r1: "+r1+" r2: "+r2+" - array "+getArrayRepr(array));
            if(r1 != r2 ){
                System.out.println("!!!!!! r1fast: "+r1+" r2naive: "+r2+" - array "+getArrayRepr(array));
            }
        }
    }

    static String getArrayRepr(int[] a){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < a.length; i++) {
             sb.append(a[i]);
             sb.append(" ");
        }
        return sb.toString();
    }
    static class FastScanner {
        BufferedReader br;
        StringTokenizer st;

        FastScanner(InputStream stream) {
            try {
                br = new BufferedReader(new
                        InputStreamReader(stream));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        String next() {
            while (st == null || !st.hasMoreTokens()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
    }

}