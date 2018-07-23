package com.nedink.world;

import sun.misc.Cleaner;

import java.util.Arrays;
import java.util.List;

import static com.nedink.util.Rand.*;
import static com.nedink.world.Rarity.*;

public class DamagePart extends ItemPart {

    private Item item;
    public Item getItem() {
        return item;
    }
    public void setItem(Item item) {
        this.item = item;
    }

    private DamageType damageType;

    private double baseRangeMin; // Range ~ in meters. Max possible should be 100m.
    private double baseRangeMax;
    private double baseAccuracy;
    private double baseDamage;
    private String serialCode;
    private String name;

    // getters
    public Rarity getRarity() {
        return rarity;
    }

    public DamageType getDamageType() {
        return damageType;
    }

    public double getBaseRangeMin() {
        return baseRangeMin;
    }

    public double getBaseRangeMax() {
        return baseRangeMax;
    }

    public double getBaseAccuracy() {
        return baseAccuracy;
    }

    public double getBaseDamage() {
        return baseDamage;
    }

    public int getDamage() {
        return (int) baseDamage;
    }

    public String getSerialCode() {
        return serialCode;
    }

    @Override
    public String getName() {
        return name;
    }

    public static DamagePart generate(int level) {

        DamagePart part = new DamagePart();
        part.level = level;

        part.initRarity();

        part.initDamageType();

        part.initBaseDamage();

        return part;
    }

    private void initDamageType() {
        double damageTypeRoll = rand.nextDouble();
        DamageType[] damageTypes = DamageType.values();
        double[] damageTypeWeights = {
                4.0,    // blunt
                3.0,    // cleaving
                2.0,    // penetrative
                1.0,    // lacerative
                0.5     // explosive
        };

        damageType = DamageType.BLUNT;

        if (rarity == COMMON || rarity == UNCOMMON) {
            // chose from only the basic types
            double total = 0;

            for (double d : damageTypeWeights)
                total += d;

            double cumulative = 0;

            for (int i = 0; i < damageTypeWeights.length; ++i) {
                damageTypeWeights[i] = cumulative += damageTypeWeights[i] /= total;
            }

            if (damageTypeRoll > damageTypeWeights[0])
                damageType = DamageType.CLEAVING;
            if (damageTypeRoll > damageTypeWeights[1])
                damageType = DamageType.PENETRATIVE;
            if (damageTypeRoll > damageTypeWeights[2])
                damageType = DamageType.LACERATIVE;
            if (damageTypeRoll > damageTypeWeights[3])
                damageType = DamageType.EXPLOSIVE;
        }
        else {
            // TODO: implement rolls for other damage types
        }
    }

    private void initBaseDamage() {
        double damageRoll = rand.nextDouble();

        double damage = level * 10;

        Rarity[] rarities = Rarity.values();
        for (int i = 0; i < rarities.length; ++i) {
            double rarityBonus = rand.nextDouble();
            if (rarities[i].equals(rarity)) {
                damage += (i * (2 + (level / 5))) * rarityBonus;
            }
        }

        damage += (2 + level / 5.0) * (damageRoll - 0.5);

        this.baseDamage = damage;
    }

    public enum DamageType {

        // common +
        BLUNT,          // 4 parts      // generally more suited for closer range except when on projectiles
        CLEAVING,       // 3 parts
        PENETRATIVE,    // 2 parts
        LACERATIVE,     // 1 part       // somewhat uncommon
        EXPLOSIVE,      // 0.5 parts    // most uncommon // generally more suited for greater range except when on melee

        // rare +
        BURNING,
        FREEZING,
        ELECTRIC,
        CORROSIVE,
        TOXIC,

        // effected only by true damage modifiers
        TRUE,

        // TODO: special effects
        BLINDING,
        EARSPLITTING,
        PSYCHOLOGICAL,
        EMOTIONAL,
    }

    public enum AttackStyle {
        RANGED,
        MELEE
    }


}
