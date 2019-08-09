package cloud.isaura.toolbox.concurrency.synchro;

/**
 *
 * @author pasquale
 */
public class VolatileCounter {
    
    private volatile int counter = 0;
    
    private volatile int novol = 0;
    
    public void inc()
    {
        counter++;
    }
    
    public int getCount()
    {
        return counter;
    }
    
    public void incNoVol()
    {
        novol++;
    }
    
    public int getNoVol()
    {
        return novol;
    }        
}
