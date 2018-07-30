package com.nedink.world.character;

import com.nedink.world.item.Item;

import java.util.List;

public abstract class Character {

    protected int level;
    protected int pointsToNextLevel;
    protected int levelProgress;
    protected float inventoryMaxVolume;
    protected float inventoryMaxWeight;
    protected float health;
    protected List<Item> inventory;
    protected List<Item> equipSlots;
    protected Race race;

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

}
