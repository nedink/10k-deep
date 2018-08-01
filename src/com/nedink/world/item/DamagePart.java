package com.nedink.world.item;

import com.nedink.lang.Lang;
import com.nedink.ui.ConsoleColor;
import com.nedink.world.ChanceValue;

import java.util.ArrayList;
import java.util.List;

import static com.nedink.util.Rand.*;
import static com.nedink.world.item.Rarity.*;

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
    private List<Double> damageModifiers;
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

    public int getDamage() {
        double levelDamage = 10 + Math.pow(1.5, level);
        double baseDamage = levelDamage;
        double total = baseDamage;
        for (Double d : damageModifiers) {
            total += baseDamage * d;
        }
        return (int) Math.round(total);
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

        //

        part.initRarity();

        part.initRace();

        //

        part.initDamageType();

        part.initWeight();

        part.initVolume();

        part.initDamageMod();

        //

        part.initName();

        //

        return part;
    }

    protected void initName() {
        name = Lang.generateName(this);
    }

    private void initDamageType() {
        double damageTypeRoll = rand.nextDouble();
        DamageType[] damageTypes = DamageType.values();
        double[] damageTypeWeights;

        // weight for race
        switch (race) {
            case BLUMKRUUL: {
                damageTypeWeights = new double[]{
                        4.0,    // blunt
                        3.0,    // cleaving
                        2.0,    // penetrative
                        1.0,    // lacerative
                        0.5     // explosive
                };
                break;
            }
            default: {
                damageTypeWeights = new double[]{
                        1.0,    // blunt
                        1.0,    // cleaving
                        1.0,    // penetrative
                        1.0,    // lacerative
                        1.0     // explosive
                };
                break;
            }
        }

        // weight for rarity
        if (rarity == COMMON || rarity == UNCOMMON) {
            // chose from only the basic types
            double total = 0;

            for (double d : damageTypeWeights)
                total += d;

            double cumulative = 0;

            for (int i = 0; i < damageTypeWeights.length; ++i) {
                damageTypeWeights[i] = cumulative += damageTypeWeights[i] /= total;
            }

        }
        else {
            // TODO: implement rolls for other rarities types
        }

        damageType = DamageType.BLUNT;
        if (damageTypeRoll > damageTypeWeights[0])
            damageType = DamageType.CLEAVING;
        if (damageTypeRoll > damageTypeWeights[1])
            damageType = DamageType.PENETRATIVE;
        if (damageTypeRoll > damageTypeWeights[2])
            damageType = DamageType.LACERATIVE;
        if (damageTypeRoll > damageTypeWeights[3])
            damageType = DamageType.EXPLOSIVE;
    }

    private void initWeight() {
        // - RACE: BLUMKRUUL
        // ++

        double baseWeight = 10.0; // kg

        double weightModifier = 0.0;

        // race
        switch (race) {
            case BLUMKRUUL: {
                weightModifier += 0.20; // 20 %
                break;
            }
            default: {
                break;
            }
        }

        weight = baseWeight + baseWeight * weightModifier;
    }

    private void initVolume() {
        // - RACE: BLUMKRUUL
        // ++

        double baseVolume = 1.0; // units

        double volumeModifier = 0.0;

        // race
        switch (race) {
            case BLUMKRUUL: {
                volumeModifier += 0.20; // 20 %
                break;
            }
            default: {
                break;
            }
        }

        volume = baseVolume + baseVolume * volumeModifier;
    }

    private void initDamageMod() {

        // bonuses
        List<ChanceValue> bonusChances = new ArrayList<>();

        // modifiers
        damageModifiers = new ArrayList<>();

        // rarity
        switch (rarity) {
            case COMMON: {
                damageModifiers.add(range(0.00, 0.05)); // 0 - 5 %
                break;
            }
            case UNCOMMON: {
                damageModifiers.add(range(0.05, 0.10)); // 10 - 20 %
                break;
            }
            case RARE: {
                damageModifiers.add(range(0.10, 0.15)); // 10 - 15 %
                break;
            }
            case EPIC: {
                damageModifiers.add(range(0.20, 0.25)); // 20 - 25 %
                break;
            }
            case LEGENDARY: {
                damageModifiers.add(range(0.40, 0.50)); // 40 - 50 %
                break;
            }
            default:
                break;
        }

        // race
        switch (race) {
            case BLUMKRUUL: {
                damageModifiers.add(range(0.30, 0.30)); // 30 %
            }
            default:
                break;
        }
    }

    @Override
    public String toString() {
        return super.toString() +
                Rarity.getColor(rarity) + "> " + rarity + ConsoleColor.RESET + '\n' +
                "  " + damageType + '\n' +
                "  damage: " + (int)baseDamage + '\n' +
                "  " +
                "";
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
