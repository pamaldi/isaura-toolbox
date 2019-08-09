package cloud.isaura.toolbox.concurrency.pools;

import java.util.concurrent.Callable;

/**
 *
 * @author pasquale
 */
public class SumComputation implements Callable<Integer> {

    private int n;
    private int result;
    
    public void setN(int n)
    {
        this.n = n;
    }        

    public SumComputation(int n) {
        this.n = n;
    }
 

    @Override
    public Integer call() throws Exception {
        System.out.println(" Start thread "+Thread.currentThread()+" with n "+n);
        for(int i = 0; i<=n;i++)
        {
            result += i;
        }
        System.out.println(" End thread "+Thread.currentThread()+" with n "+n+" and result "+result);
       
        return result;
    }
}
