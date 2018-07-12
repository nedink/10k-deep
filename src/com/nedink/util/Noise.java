package com.nedink.util;

import java.util.Random;

public class Noise extends Random {



//    @Override
//    protected int next(int bits) {
//        long oldseed, nextseed;
////        AtomicLong seed = seed;
//        do {
//            oldseed = seed.get();
//            nextseed = (oldseed * multiplier + addend) & mask;
//        } while (!seed.compareAndSet(oldseed, nextseed));
//        return (int)(nextseed >>> (48 - bits));
//    }
}
