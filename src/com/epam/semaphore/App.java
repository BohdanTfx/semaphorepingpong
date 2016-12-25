package com.epam.semaphore;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class App {
    private static final int THREADS_COUNT = 10;
    private static final Executor EXECUTOR = Executors.newFixedThreadPool(THREADS_COUNT);

    public static void main(String[] args) {
        final Worker worker = new Worker();
        for (int i = 0; i < THREADS_COUNT; i++) {
            EXECUTOR.execute(worker);
        }
    }
}
