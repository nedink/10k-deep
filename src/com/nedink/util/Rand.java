package com.nedink.util;

import java.util.Random;

public class Rand {

    public static Random rand = new Random(0);

    public Rand(long seed) {
        rand = new Random(seed);
    }

    public static int intRange(int max) {
        return Math.abs(rand.nextInt()) % max;
    }
    public static int intRange(int min, int max) {
        return Math.abs(rand.nextInt() % (max - min)) + min;
    }
}
