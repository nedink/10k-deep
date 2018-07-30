package com.nedink.world.character;

import com.nedink.world.item.Item;
import com.nedink.world.Room;

import java.util.List;

import static com.nedink.util.Rand.rand;

public class Enemy {

    private Room room;
    private int level;
    private double agility;
    private List<Item> items;

    public static final double SPAWN_CHANCE = 0.15;

    public static Enemy generateEnemy(Room room) {

        if (rand.nextDouble() > SPAWN_CHANCE)
            return null;

        Enemy enemy = new Enemy();
        enemy.room = room;
        enemy.level = room.getDepth() / 50;
//        enemy.agility =

        return enemy;
    }
}
