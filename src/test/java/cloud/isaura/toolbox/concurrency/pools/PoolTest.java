
package cloud.isaura.toolbox.concurrency.pools;


import static org.hamcrest.CoreMatchers.is;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author pasquale
 */
public class PoolTest {
    
    @Test
    public void computationFixedPool()
    {
         SumComputationFixedPool computationFixedPool = new SumComputationFixedPool(12);
         computationFixedPool.doWork();
         int res = computationFixedPool.getComplexSum();
         Assert.assertThat(res, is(407150));
    }
    
     
    public void computationScheduledPool() throws InterruptedException
    {
         SumComputationScheduledPool pool = new SumComputationScheduledPool(1);
         pool.doWork();
         int res = pool.getComplexSum();
         Assert.assertThat(res, is(407150));
    }      
}
