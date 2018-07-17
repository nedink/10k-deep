package com.nedink.world;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.ErrorManager;

public class Weapon {
    List<WeaponType> types;
    List<AttackType> attackTypes;
    private float rangeMin;
    private float rangeMax;

    public List<RangeType> getRangeTypes() {

        List<RangeType> rangeTypes = new ArrayList<>();

        // TODO: handle range. multiple attack modes, multiple ranges.

        return rangeTypes;
    }

    public float getRangeMin() {
        return rangeMin;
    }

    public void setRangeMin(float rangeMin) {
        // TODO: handle with error instead?
        this.rangeMin = rangeMin > rangeMax ? rangeMax : rangeMin;
    }

    public float getRangeMax() {
        return rangeMax;
    }

    public void setRangeMax(float rangeMax) {
        // TODO: handle with error instead?
        this.rangeMax = rangeMax < rangeMin ? rangeMin : rangeMax;
    }

    public enum WeaponType {
        STAFF
    }

    public enum AttackType {
        REPEATER,
        ONESHOT,
        BURSTSHOT,
    }

    public enum RangeType {
        MELE,
        CLOSERANGE,
        MIDRANGE,
        LONGRANGE,
        RICOCHET,
    }

}
