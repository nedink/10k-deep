package com.nedink.world;

import com.nedink.util.Rand;

import java.util.List;

public class WeaponPart extends ItemPart {

    WeaponType weaponType;
    List<AttackType> attackType;
    private double rangeMin;
    private double rangeMax;
    private double accuracy;

    public static WeaponPart generate(int level) {

        WeaponPart weaponPart = new WeaponPart();
        Rand
        // weapon type
        // attack types

        return weaponPart;
    }

    public RangeType getRangeType() {

        // TODO: averaging is not the right approach (ranges do not scale linearly)

        if ((rangeMin + rangeMax) / 2 < 5.0) {
            return RangeType.MELEE;
        }
        else if ((rangeMin + rangeMax) / 2 < 25.0) {
            return RangeType.CLOSE;
        }
        else if ((rangeMin + rangeMax) / 2 < 100.0) {
            return RangeType.MID;
        }
        else {
            return RangeType.LONG;
        }
    }

    public double getRangeMin() {
        return rangeMin;
    }

    public void setRangeMin(float rangeMin) {
        // TODO: handle with error instead?
        this.rangeMin = rangeMin > rangeMax ? rangeMax : rangeMin;
    }

    public double getRangeMax() {
        return rangeMax;
    }

    public void setRangeMax(float rangeMax) {
        // TODO: handle with error instead?
        this.rangeMax = rangeMax < rangeMin ? rangeMin : rangeMax;
    }

    public enum WeaponType {
        MAGIC,

    }

    public enum AttackType {
        REPEATER, // up to as many shots as time allows
        ONCE,
        BURST,
    }

    public enum RangeType {
        MELEE,
        CLOSE,
        MID,
        LONG,
        RICOCHET,
    }
}
