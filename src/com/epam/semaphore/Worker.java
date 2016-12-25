package com.epam.semaphore;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

public class Worker implements Runnable {
    private static AtomicInteger finishedThreads = new AtomicInteger(0);
    private static final Semaphore SEMAPHORE = new Semaphore(1);
    private static final CyclicBarrier barrier = new CyclicBarrier(App.THREADS_COUNT);

    @Override
    public void run() {
        String threadName = Thread.currentThread().getName();
        try {
            System.out.println(threadName + " waiting");
            barrier.await();
        } catch (InterruptedException | BrokenBarrierException e1) {
            e1.printStackTrace();
        }
        for (int i = 0; i < 5; i++) {
            try {
                SEMAPHORE.acquire();
                System.out.println("ping " + threadName);
                System.out.println("PPPOOONNNGGG " + threadName);
                SEMAPHORE.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        if (finishedThreads.incrementAndGet() == App.THREADS_COUNT) {
            App.EXECUTOR.shutdown();
        }
    }

}
