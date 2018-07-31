package com.nedink.world.item;

import com.nedink.ui.ConsoleColor;
import com.nedink.world.BonusChance;

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

    public double getDamage() {
        return 10 + Math.pow(baseDamage, level);
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

        part.initBaseDamage();

        //

        return part;
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
        // BLUNT,          // +
        // CLEAVING,       // =
        // PENETRATIVE,    // -
        // LACERATIVE,     // -
        // EXPLOSIVE,      // *

        switch (race) {
            case BLUMKRUUL: {
                switch (damageType) {
                    case BLUNT: {
                        break;
                    }
                    default: {
                        break;
                    }
                }
                break;
            }
            default: {
                break;
            }
        }
    }

    private void initVolume() {
        // BLUNT,          // +
        // CLEAVING,       // =
        // PENETRATIVE,    // =
        // LACERATIVE,     // -
        // EXPLOSIVE,      // *
    }

    private void initBaseDamage() {
        double damageRoll = rand.nextDouble();

        // minimum damage
        double damage = 1.5;

        // bonuses
        List<BonusChance> bonusChances = new ArrayList<>();

        // rarity
        switch (rarity) {
            case COMMON: {
                bonusChances.add(new BonusChance(0.1, 0.001));
                break;
            }
            default:
                break;
        }

        // race -> damage type
        switch (race) {
            case BLUMKRUUL: {
                switch (damageType) {
                    case BLUNT: {
                        bonusChances.add(new BonusChance(1.0, 0.001));
                        break;
                    }
                    default:
                        break;
                }
                break;
            }
            default:
                break;
        }


        this.baseDamage = damage;
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
