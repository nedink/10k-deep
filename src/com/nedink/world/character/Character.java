package com.nedink.world.character;

import com.nedink.world.item.Item;

import java.util.List;

public abstract class Character {

    int level;
    int pointsToNextLevel;
    int levelProgress;
    float inventoryMaxVolume;
    float inventoryMaxWeight;
    float health;
    Race race;
    List<Item> inventory;
    List<Item> equipSlots;

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

    public int getHitPoints() {
        return (int) health;
    }

    public void doDamage(int hp) {
        // TODO
    }

}
