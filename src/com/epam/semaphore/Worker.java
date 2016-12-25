package com.epam.semaphore;

import java.util.concurrent.Semaphore;

public class Worker implements Runnable {
    private Semaphore semaphore = new Semaphore(1);
    
    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            try {
                semaphore.acquire();
                System.out.println("ping");
                semaphore.release();
                System.out.println("PPPOOONNNGGG");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
