package com.nedink.world.item;

import com.nedink.world.Named;
import com.nedink.world.Selectable;

import java.util.*;

public class Item implements Named, Selectable {

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
        String name = "";
        for (ItemPart part : parts) { // concatenate names of parts
            name += part.getName();
            name += " ";
        }
        name = name.substring(0, name.length() - 1); // remove space at end
        return name;
    }

    public Set<ItemType> getTypes() {
        Set<ItemType> types = new HashSet<>();

        for (ItemPart part : parts) {
            if (part instanceof DamagePart)
                types.add(ItemType.WEAPON);
        }

        return types;
    }

    @Override
    public String selectableName() {
        return getName();
    }

    @Override
    public String toString() {
        return "parts: " + Arrays.toString(parts.toArray()) + '\n' +
               "name: '" + name + '\'' + '\n' +
               "";
    }

    public enum ItemType {
        WEAPON,
    }
}
