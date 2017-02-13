package com.aka;

import java.util.HashSet;

public class ChronoRandomAnalyzer implements Runnable {
    private HashSet<Integer> numbers;
    private int strobe;

    public ChronoRandomAnalyzer(HashSet<Integer> numbers, int strobe) {
        this.numbers = numbers;
        this.strobe = strobe;
    }

    @Override
    public void run() {
        int currTick = 0;

        while (true) {
            int currSize;

            synchronized (numbers) {
                try {
                    numbers.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    break;
                }

                ++currTick;
                if (currTick % strobe != 0)
                    continue;
                currSize = numbers.size();
            }

            System.out.println("Current unique count " + currSize);
            if (currSize == 100) {
                System.out.println("Analyze done");
                break;
            }
        }
    }
}
