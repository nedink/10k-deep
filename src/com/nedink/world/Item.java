package com.nedink.world;

import com.nedink.lang.Named;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Item implements Named {

    private List<ItemPart> parts;

    private String name;
    public Item() {
        parts = new ArrayList<>();
        name = "";
    }

    public double getWeight() {
        double weight = 0.0;
        for (ItemPart part : parts)
            weight += part.getWeight();
        return weight;
    }

    public double getVolume() {
        double volume = 0.0;
        for (ItemPart part : parts)
            volume += part.getVolume();
        return volume;
    }

    public void addPart(ItemPart part) {
        part.setItem(this);
        parts.add(part);
    }

    public ItemPart getPart(int index) {
        return parts.get(index);
    }

    public List<ItemPart> getParts() {
        return parts;
    }

    public void removePart(int index) {
        parts.get(index).setItem(null);
        parts.remove(index);
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public String toString() {
        return "Item{" +
               "parts=" + Arrays.toString(parts.toArray()) +
               ", name='" + name + '\'' +
               '}';
    }
}
