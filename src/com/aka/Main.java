package com.aka;

import java.util.HashSet;

public class Main {

    public static void main(String[] args) {
        HashSet<Integer> numbers = new HashSet<>();

        new Thread(new ChronoRandomGenerator(numbers, 1000)).start();
        new Thread(new ChronoRandomAnalyzer(numbers, 5)).start();
    }
}
