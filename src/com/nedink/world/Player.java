package com.nedink.world;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Player implements Serializable {

    private int level;
    private int pointsToNextLevel;
    private int levelProgress;
    private List<Item> inventory;
    private float inventoryMaxVolume;
    private float inventoryMaxWeight;
    public DamagePart[] weaponSlots;
    private int weaponSlotsAvailable;

    public Player() {
        level = 1;
        pointsToNextLevel = 5;
        levelProgress = 0;
        inventoryMaxVolume = 4.0f;
        inventoryMaxWeight = 12.0f;
        inventory = new ArrayList<>();

        weaponSlotsAvailable = 1;
        weaponSlots = new DamagePart[]{};

    }

    public int acquireItem(Item item) {
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

    public int getLevel() {
        return level;
    }

    public int getPointsToNextLevel() {
        return pointsToNextLevel;
    }

    public int getLevelProgress() {
        return levelProgress;
    }

    public List<Item> getInventory() {
        return inventory;
    }

    public float getInventoryMaxVolume() {
        return inventoryMaxVolume;
    }

    public float getInventoryMaxWeight() {
        return inventoryMaxWeight;
    }

    public DamagePart[] getWeaponSlots() {
        return weaponSlots;
    }

    public int getWeaponSlotsAvailable() {
        return weaponSlotsAvailable;
    }
}
