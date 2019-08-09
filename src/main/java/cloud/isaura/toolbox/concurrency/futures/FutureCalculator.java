/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cloud.isaura.toolbox.concurrency.futures;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

/**
 *
 * @author pasquale
 */
public class FutureCalculator {
    private final ExecutorService executor;

    public FutureCalculator(ExecutorService executor) {
        this.executor = executor;
    }

    Future<Integer> calculate(Integer input) {
        return executor.submit(() -> {
            Thread.sleep(1000);
            return input * input;
        });
    }
}
