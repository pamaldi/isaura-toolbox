/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cloud.isaura.toolbox.concurrency.pools;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author pasquale
 */
public class SumComputationScheduledPool {
    private int complexSum = 0;
    
    private int poolSize = 0;

    public SumComputationScheduledPool(int poolSize) {
    
        this.poolSize = poolSize;
    }
    
    
    
    public void doWork() throws InterruptedException
    {
        ScheduledExecutorService scheduledService = Executors.newScheduledThreadPool(poolSize);
        List<Future<Integer>> results = new ArrayList<>();
        CountDownLatch lock = new CountDownLatch(3);
        for(int i = 100; i <=150; i++)
        {
            final int j = i;
            ScheduledFuture<Integer> sum;
            sum = (ScheduledFuture<Integer>) scheduledService.scheduleAtFixedRate(
                    (()->{
                        SumComputation task =  new SumComputation(j);
                try {
                    task.call();
                } catch (Exception ex) {
                    Logger.getLogger(SumComputationScheduledPool.class.getName()).log(Level.SEVERE, null, ex);
                }
                    }),5,100,TimeUnit.MILLISECONDS);
            
            results.add(sum);
            lock.await(1, TimeUnit.MILLISECONDS);
            sum.cancel(true);
         
        
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
