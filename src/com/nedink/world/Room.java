package com.nedink.world;

import com.nedink.world.character.Enemy;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static com.nedink.util.Rand.*;

public class Room implements Serializable {

    private int depth;
    private boolean isLeft;
    private Room parent;
    private Room leftChild;
    private Room rightChild;
    private List<Enemy> enemies;

    public int getDepth() {
        return depth;
    }

    public boolean isLeft() {
        return isLeft;
    }

    public Room getParent() {
        return parent;
    }

    public Room getLeftChild() {
        return leftChild;
    }

    public Room getRightChild() {
        return rightChild;
    }

    public List<Enemy> getEnemies() {
        return enemies;
    }

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
