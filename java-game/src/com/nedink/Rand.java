package com.nedink;

import java.util.Random;

public class Rand {

    public static Random rand = new Random();

    public Rand(long seed) {
        rand = new Random(seed);
    }

    static int range(int max) {
        return Math.abs(rand.nextInt()) % max;
    }

    static int range(int min, int max) {
        return Math.abs(rand.nextInt() % (max - min)) + min;
    }
}
