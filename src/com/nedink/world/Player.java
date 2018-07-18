package com.nedink.world;

import java.util.ArrayList;
import java.util.List;

public class Player {

    private int level;
    private int pointsToNextLevel;
    private int levelProgress;

    public List<Item> inventory;
    private float inventoryMaxVolume;
    private float inventoryMaxWeight;

    public WeaponPart[] weaponAttributeSlots;
    private int weaponSlotsAvailable;

    public Player() {
        level = 0;
        pointsToNextLevel = 5;
        levelProgress = 0;
        inventoryMaxVolume = 4.0f;
        inventoryMaxWeight = 12.0f;
        inventory = new ArrayList<>();

        weaponSlotsAvailable = 1;
        weaponAttributeSlots = new WeaponPart[]{};

    }

    public int acquire(Item item) {
        if (getInventoryVolume() + item.getVolume() > inventoryMaxVolume)
            return 1;

        if (getInventoryWeight() + item.getWeight() > inventoryMaxWeight)
            return 2;

        this.inventory.add(item);

        return 0;
    }

    public double getInventoryWeight() {
        double weight = 0f;

        for (Item item : inventory)
            weight += item.getWeight();

        return weight;
    }

    public double getInventoryVolume() {
        double weight = 0f;

        for (Item item : inventory)
            weight += item.getWeight();

        return weight;
    }

    private static class Inventory {

    }
}
