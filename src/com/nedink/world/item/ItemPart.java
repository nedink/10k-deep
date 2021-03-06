package com.nedink.world.item;

import com.nedink.world.Named;
import com.nedink.world.character.Race;

import static com.nedink.util.Rand.rand;
import static com.nedink.world.item.Rarity.*;
import static com.nedink.world.item.Rarity.LEGENDARY;

public abstract class ItemPart implements Named {

    protected String name;
    protected Item item;
    protected int level;
    protected Race race;
    protected Rarity rarity;
    protected double volume;
    protected double weight;

    @Override
    public String getName() {
        return name;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public Race getRace() {
        return race;
    }

    public Rarity getRarity() {
        return rarity;
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

    protected void initRarity() {

        // level bonus           @ lvl 1         @ lvl 50
        // common:      .90     (90 %)   -> .1  (10%)
        // uncommon:    .10     (9 %)    -> .9  (20%)
        // rare:        .01     (<1 %)   -> .7  (40%)
        // epic:        .001    (<0.1 %) -> .3  (20%)
        // legendary:   .0001   (<0.01 %)-> .1  (10%)

        double rarityRoll = rand.nextDouble();

        // TODO: TESTING
//        double[] rarityWeights = {1, 1, 1, 1, 1};
//        double[] rarityWeights = {2,0,0,0,0};
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

    protected void initRace() {
        race = rand.nextDouble() < .5 ? Race.BLUMKRUUL : Race.HUMAN;
    }

    @Override
    public String toString() {
        return "\n- level: " + level + '\n' +
               "  volume: " + volume + '\n' +
               "  weight: " + weight + '\n'
                ;
    }
}
