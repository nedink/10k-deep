package com.nedink.util;

public class CustomMath {

    public static double[] normalizedChances(double... chances) {
        double total = 0;
        for (double chance : chances) total += chance;
        for (int i = 0; i < chances.length; ++i) chances[i] /= total;
        return chances;
    }
}
