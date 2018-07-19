package com.nedink.world;

import com.nedink.util.Rand;

import java.util.List;

public class WeaponPart extends ItemPart {

    private Item item;
    private WeaponType weaponType;
    private List<AttackType> attackTypes;
    private List<RangeType> rangeTypes;
//    private double rangeMin;
//    private double rangeMax;

    private double accuracy;

    public static WeaponPart generate(Item item, int level) {

        // Weapons should get more "awesome" the greater the level.

        WeaponPart weaponPart = new WeaponPart();
        weaponPart.item = item;
//        weaponPart.accuracy
//        weaponPart.weaponType = WeaponPart.WeaponType.values()


        // weapon type
        // attack types

        return weaponPart;
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
