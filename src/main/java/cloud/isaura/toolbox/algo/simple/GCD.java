package cloud.isaura.toolbox.algo.simple;

import java.util.Scanner;

public class GCD {

    private static int gcd(int a, int b) {
        int current_gcd = 1;
        int max = a > b? a:b;
        int min = b < a ? b : a;

        while(min > 0) {
            //System.out.println("max "+max+" min "+min);
            int div = max / min;
            int next_min = max - (div * min);
            //System.out.println("     next min "+next_min+" div "+div);
            if(next_min == 0){
               current_gcd = min;
               break;
            }
            max = min;
            min = next_min;

        }


        return current_gcd;
    }

    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);
        int a = scanner.nextInt();
        int b = scanner.nextInt();

        System.out.println(gcd(a, b));
    }
}
