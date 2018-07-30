package com.nedink.world.item;

import com.nedink.ui.ConsoleColor;

import static com.nedink.ui.ConsoleColor.*;

public enum Rarity {
    COMMON, // white
    UNCOMMON, // green
    RARE, // blue
    EPIC, // purple
    LEGENDARY // yellow
    ;

    public static String getColor(Rarity rarity) {
        switch (rarity) {
            case COMMON:
                return WHITE_BRIGHT;
            case UNCOMMON:
                return GREEN_BRIGHT;
            case RARE:
                return CYAN_BRIGHT;
            case EPIC:
                return PURPLE_BRIGHT;
            case LEGENDARY:
                return YELLOW_BRIGHT;
            default:
                return WHITE;
        }
    }
}
