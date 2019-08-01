package cloud.isaura.toolbox.algo.simple;

import java.util.Scanner;

public class FibonacciLastDigit {

    private static int getFibonacciLastDigitNaive(int n) {
        if (n <= 1)
            return n;

        int previous = 0;
        int current  = 1;

        for (int i = 0; i < n - 1; ++i) {
            int tmp_previous = previous;
            previous = current;
            current = tmp_previous + current;
        }

        return current % 10;
    }


    private static int getFibonacciLastDigit(int n){
        if (n < 2)
            return n;
        int[] array = new int[n+1];
        array[0]=0;
        array[1]=1;

        for(int i = 2; i <= n; i++){
            array[i]=(array[i-2]+array[i-1])%10;
        }
        return array[n];
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int c = getFibonacciLastDigit(n);
        //stressTest();
        System.out.println(c);
    }


    private static void stressTest(){
        for(int i = 1; i < 20; i++){
            int f2 =   getFibonacciLastDigit(i);

            System.out.println("Calcolo per "+i+" --->  No naive "+f2);


        }
    }

}
