package cloud.isaura.toolbox.algo.simple;

import java.util.Scanner;

public class Fibonacci {

    private static long calc_fib_rec(int n) {
       if (n <= 1)
            return n;

        long res = calc_fib_rec(n - 1) + calc_fib_rec(n - 2);
        //System.out.println("invoke for n "+n+ ": "+res);
        return res;

    }

    private static long calc_fib(int n) {
        if (n < 2)
            return n;
        long[] array = new long[n+1];
        array[0]=0;
        array[1]=1;

        for(int i = 2; i <= n; i++){
            array[i]=array[i-2]+array[i-1];
        }
        return array[n];
    }

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();

        //System.out.println(calc_fib_rec(n));
        stressTest();
    }

    private static void stressTest(){
        for(int i = 0; i < 32; i++){
            long f1 =   calc_fib(i);
            long f2 =   calc_fib_rec(i);

            System.out.println("Calcolo per "+i+" ---> Recursion "+f2+" No recursion "+f1);
            if(f1 != f2 ){
                System.out.println("ERRRORE Calcolo per "+i+" ---> Recursion "+f2+" No recursion "+f1);
            }

        }
    }
}
