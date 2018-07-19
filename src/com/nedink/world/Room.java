package com.nedink.world;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static com.nedink.util.Rand.*;

public class Room implements Serializable {

    public int depth;
    public boolean isLeft;
    public Room parent;
    public Room leftChild;
    public Room rightChild;
    public List<Enemy> enemies;

    public Room(Room parent, boolean isLeft) {
        this.isLeft = isLeft;
        this.parent = parent;
        enemies = new ArrayList<>();
        if (parent == null) depth = 0; else depth = parent.depth + 1;

        // enemies
        if (rand.nextDouble() <= Enemy.SPAWN_CHANCE) {
            int howMany = 1;
            for (int i = 0; i < howMany; ++i) {
                enemies.add(Enemy.generateEnemy(this));
            }
        }
    }

    public List<Room> getPath() {
        List<Room> path = new LinkedList<>();
        if (parent != null)
            path.addAll(parent.getPath());
        path.add(this);
        return path;
    }

    public Room spawnLeft() {
        return (leftChild = new Room(this, true));
    }

    public Room spawnRight() {
        return (rightChild = new Room(this, false));
    }
}
