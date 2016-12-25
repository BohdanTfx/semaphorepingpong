package com.epam.semaphore;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class App {
    public static final int THREADS_COUNT = 10;
    public static final ExecutorService EXECUTOR = Executors
            .newFixedThreadPool(THREADS_COUNT);

    public static void main(String[] args) {
        final Worker worker = new Worker();
        for (int i = 0; i < THREADS_COUNT; i++) {
            EXECUTOR.execute(worker);
        }
    }
}
