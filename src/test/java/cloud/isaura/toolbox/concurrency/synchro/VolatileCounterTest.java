package cloud.isaura.toolbox.concurrency.synchro;

import static junit.framework.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author pasquale
 */
public class VolatileCounterTest {
    @Test
    public void whenOneThreadWrites_thenVolatileReadsFromMainMemory() throws InterruptedException {
        VolatileCounter volatileCounter = new VolatileCounter();

        Thread writer = new Thread(() -> {
            volatileCounter.inc();
            volatileCounter.incNoVol();
         }
        );
        
        writer.start();
        Thread.sleep(100);

        Thread readerOne = new Thread(() -> {
            int valueReadByThread2 = volatileCounter.getCount();
            assertEquals(1, valueReadByThread2);
            assertEquals(1, volatileCounter.getNoVol());
        });
        readerOne.start();

        Thread readerTwo = new Thread(() -> {
            int valueReadByThread3 = volatileCounter.getCount();
            assertEquals(1, valueReadByThread3);
            assertEquals(1, volatileCounter.getNoVol());
        });
        readerTwo.start();

    }
    
    @Test
    public void whenTwoThreadWrites_thenVolatileReadsFromMainMemory() throws InterruptedException {
        VolatileCounter volatileCounter = new VolatileCounter();
        Thread writerOne = new Thread(() -> {
            volatileCounter.inc();
            volatileCounter.incNoVol();
         }
        );
        writerOne.start();
        Thread.sleep(100);

        Thread writerTwo = new Thread(() -> {
            volatileCounter.inc();
            volatileCounter.incNoVol();
         }
        );
        writerTwo.start();
        Thread.sleep(100);

        Thread readerOne = new Thread(() -> {
            int valueReadByThread2 = volatileCounter.getCount();
            assertEquals(2, valueReadByThread2);
            System.out.println("volatileCounter.getNoVol() "+volatileCounter.getNoVol());
            assertEquals(2, volatileCounter.getNoVol());
        });
        readerOne.start();

        Thread readerTwo = new Thread(() -> {
            int valueReadByThread3 = volatileCounter.getCount();
            assertEquals(2, valueReadByThread3);
            assertEquals(2, volatileCounter.getNoVol());
        });
        readerTwo.start();

    }

}
