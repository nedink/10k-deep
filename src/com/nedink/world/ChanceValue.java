package com.nedink.world;

public class ChanceValue {

    private double chance;
    private double value;

    public ChanceValue(double chance, double bonus) {
        this.chance = chance;
        this.value = bonus;
    }

    public double getChance() {
        return chance;
    }

    public double getValue() {
        return value;
    }

}
