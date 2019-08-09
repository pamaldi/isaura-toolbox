package cloud.isaura.toolbox.concurrency.pools;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author pasquale
 */
public class SumComputationFixedPool {
    
    
    private int complexSum = 0;
    
    private int poolSize = 0;

    public SumComputationFixedPool(int poolSize) {
    
        this.poolSize = poolSize;
    }
    
    
    
    public void doWork()
    {
        ExecutorService executorService = Executors.newFixedThreadPool(poolSize);
        List<Future<Integer>> results = new ArrayList<>();
        for(int i = 100; i <=150; i++)
        {
            final int j = i;
            Future<Integer> sum;
            sum = executorService.submit(
                    (()->{
                        SumComputation task =  new SumComputation(j);
                        
                        return task.call();
                    })
            );
            results.add(sum);
         
        
        }
        results.stream().forEach((result)-> {
            try {
                complexSum=result.get()+complexSum;
            } catch (InterruptedException | ExecutionException ex) {
                Logger.getLogger(SumComputationFixedPool.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        System.out.println("complexSum "+complexSum);
      
    }
    
    public int getComplexSum()
    {
        return this.complexSum;
    }        
    
}
