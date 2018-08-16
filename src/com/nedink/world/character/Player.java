package com.nedink.world.character;

import com.nedink.world.item.Item;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Player extends Character implements Serializable {

    public Player() {
        level = 1;
        pointsToNextLevel = 5;
        levelProgress = 0;
        inventoryMaxVolume = 4.0f;
        inventoryMaxWeight = 12.0f;
        inventory = new ArrayList<>();
        equipSlots = new ArrayList<>();
        race = Race.HUMAN;

    }



    public int getLevel() {
        return level;
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
}
