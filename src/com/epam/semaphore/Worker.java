package com.epam.semaphore;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

public class Worker implements Runnable {
    private static AtomicInteger finishedThreads = new AtomicInteger(0);
    private Semaphore semaphore = new Semaphore(1);

    @Override
    public void run() {
        String threadName = Thread.currentThread().getName();
        for (int i = 0; i < 5; i++) {
            try {
                semaphore.acquire();
                System.out.println("ping " + threadName);
                System.out.println("PPPOOONNNGGG " + threadName);
                semaphore.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        if (finishedThreads.incrementAndGet() == App.THREADS_COUNT) {
            App.EXECUTOR.shutdown();
        }
    }

}
