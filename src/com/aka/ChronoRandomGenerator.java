package com.aka;

import java.util.HashSet;
import java.util.Random;

public class ChronoRandomGenerator implements Runnable {
    private HashSet<Integer> numbers;
    private int period;

    public ChronoRandomGenerator(HashSet<Integer> numbers, int period) {
        this.numbers = numbers;
        this.period = period;
    }

    @Override
    public void run() {
        Random random = new Random();

        while (true) {
            try {
                Thread.sleep(period);
            } catch (InterruptedException e) {
                e.printStackTrace();
                break;
            }

            int r;
            boolean ready;
            synchronized (numbers) {
                while (true) {
                    r = random.nextInt(100);
                    if (!numbers.contains(r)) {
                        numbers.add(r);
                        numbers.notify();
                        ready = numbers.size() == 100;
                        break;
                    }
                }
            }

            System.out.println("New number is " + r);

            if (ready) {
                System.out.println("Generation complete");
                break;
            }
        }
    }
}
