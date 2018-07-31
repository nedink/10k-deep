package com.nedink.world;

public class BonusChance {

    private double chance;
    private double bonus;

    public BonusChance(double chance, double bonus) {
        this.chance = chance;
        this.bonus = bonus;
    }

    public double getChance() {
        return chance;
    }

    public double getBonus() {
        return bonus;
    }

}
