package com.nedink.util;

import java.util.Random;

public class Rand {

    public static Random rand = new Random(0);

    public Rand(long seed) {
        rand = new Random(seed);
    }

    public static int range(int max) {
        return rand.nextInt(max);
    }
    public static int range(int min, int max) {
        return min + rand.nextInt(max - min);
    }
    public static double range(double min, double max) {
        return min + rand.nextDouble() * (max - min);
    }


}
