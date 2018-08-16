package com.nedink.world.item;

public class DefensePart extends ItemPart {

    private DefenseType defenseType;
    private double baseDefense;

    public static DefensePart generate(int level) {
        DefensePart part = new DefensePart();

        //

        part.initRarity();

        part.initRace(); // TODO: only spawns 'BLUMKRUHL'

        //

        part.initDefenseType();

        //

        return part;
    }

    private void initDefenseType() {

        // race
        switch (race) {
            case BLUMKRUUL: {
                //
                break;
            }
            default: {
                break;
            }
        }
    }

    public enum DefenseType {
        // DamageTypes
        // - Blunt
        // - Cleaving
        // - Impaling
        // - Lacerating
        // - Exploding

        //              Bl  Cl  Im  La  Ex
        PLATE,      //  ++  -   -   +   +
        MAIL,       //  -   -   +   +   -
        LATTICE,    //  +   +   -   +   -

    }
}
