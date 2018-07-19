package com.nedink.world;

import com.nedink.util.Named;

import java.util.List;

public class Item extends Named {

    public List<ItemPart> parts;

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
}
