package com.nedink.world;

import com.nedink.lang.Named;

import static com.nedink.util.Rand.rand;
import static com.nedink.world.Rarity.*;
import static com.nedink.world.Rarity.LEGENDARY;

public abstract class ItemPart implements Named {

    private Item item;
    protected int level;
    protected Rarity rarity;
    protected double volume;
    protected double weight;

    protected void initRarity() {

        // level bonus           @ lvl 1         @ lvl 50
        // common:      .90     (90 %)   -> .1  (10%)
        // uncommon:    .10     (9 %)    -> .9  (20%)
        // rare:        .01     (<1 %)   -> .7  (40%)
        // epic:        .001    (<0.1 %) -> .3  (20%)
        // legendary:   .0001   (<0.01 %)-> .1  (10%)

        double rarityRoll = rand.nextDouble();
        double[] rarityWeights = {
                0.9                                                   ,
                0.1   + 0.008   * level + 0.00016 * Math.pow(level, 2),
                0.02  + 0.004   * level + 0.00016 * Math.pow(level, 2),
                0.005 + 0.0015  * level + 0.00006 * Math.pow(level, 2),
                0.001 + 0.00015 * level + 0.00002 * Math.pow(level, 2),
        };
        double total = 0;
        for (double d : rarityWeights)
            total += d;
        double cumulative = 0;
        for (int i = 0; i < rarityWeights.length; ++i) {
            rarityWeights[i] = cumulative += rarityWeights[i] /= total;
        }

        rarity = COMMON; // Any percentile outside of uncommon will overwrite this
        if (rarityRoll > rarityWeights[0])
            rarity = UNCOMMON;
        if (rarityRoll > rarityWeights[1])
            rarity = RARE;
        if (rarityRoll > rarityWeights[2])
            rarity = EPIC;
        if (rarityRoll > rarityWeights[3])
            rarity = LEGENDARY;
    }

    public Item getItem() {
        return item;
    }
    public void setItem(Item item) {
        this.item = item;
    }

    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    @Override
    public String toString() {
        return "ItemPart{" +
               "level=" + level +
               ", volume=" + volume +
               ", weight=" + weight +
               '}';
    }
}
