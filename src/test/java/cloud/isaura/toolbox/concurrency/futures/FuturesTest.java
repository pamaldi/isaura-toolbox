/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cloud.isaura.toolbox.concurrency.futures;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import static junit.framework.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author pasquale
 */
public class FuturesTest {
    private FutureCalculator futureCalculator;

    @Test
    public void givenExecutorIsSingleThreaded_whenTwoExecutionsAreTriggered_thenRunInSequence() throws InterruptedException, ExecutionException {
        futureCalculator = new FutureCalculator(Executors.newSingleThreadExecutor());

        Future<Integer> result1 = futureCalculator.calculate(4);
        Future<Integer> result2 = futureCalculator.calculate(1000);

        while (!result1.isDone() || !result2.isDone()) {
            System.out.println(String.format("Task 1 is %s and Task 2 is %s.", result1.isDone() ? "done" : "not done", result2.isDone() ? "done" : "not done"));

            Thread.sleep(300);
        }

        assertEquals(16, result1.get().intValue());
        assertEquals(1000000, result2.get().intValue());
    }
    
    @Test
    public void givenExecutorIsMultiThreaded_whenTwoExecutionsAreTriggered_thenRunInParallel() throws InterruptedException, ExecutionException {
        futureCalculator = new FutureCalculator(Executors.newFixedThreadPool(2));

        Future<Integer> result1 = futureCalculator.calculate(4);
        Future<Integer> result2 = futureCalculator.calculate(1000);

        while (!result1.isDone() || !result2.isDone()) {
            System.out.println(String.format("Task 1 is %s and Task 2 is %s.", result1.isDone() ? "done" : "not done", result2.isDone() ? "done" : "not done"));

            Thread.sleep(300);
        }

        assertEquals(16, result1.get().intValue());
        assertEquals(1000000, result2.get().intValue());
    }

}
