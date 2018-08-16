package com.nedink.world;

import com.nedink.ui.ConsoleColor;
import com.nedink.world.character.Enemy;
import com.nedink.world.item.Item;

import java.io.Serializable;
import java.util.*;

import static com.nedink.util.Rand.*;

public class Room implements Serializable {

    private int depth;
    private boolean isLeft;
    private Room parent;
    private Room leftChild;
    private Room rightChild;
    private Set<Enemy> enemies;
    private Set<Item> items;

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

    public Set<Enemy> getEnemies() {
        return enemies;
    }

    public Set<Item> getItems() {
        return items;
    }

    public Room(Room parent, boolean isLeft) {
        this.isLeft = isLeft;
        this.parent = parent;
        enemies = new HashSet<>();
        items = new HashSet<>();
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

    public String getPathString() {
        StringBuilder roomPath = new StringBuilder();
        for (Room room : getPath()) {
            roomPath.append(room.getLeftChild() == null && room.getRightChild() == null ? ConsoleColor.RED :
                    room.getLeftChild() != null && room.getRightChild() != null ? ConsoleColor.GREEN : ConsoleColor.YELLOW)
                    .append(room.getParent() != null ? "-" : "")
                    .append(room.isLeft() ? "L" : "R")
                    .append(ConsoleColor.RESET);
        }
        return roomPath.toString();
    }

    public Room spawnLeft() {
        return (leftChild = new Room(this, true));
    }

    public Room spawnRight() {
        return (rightChild = new Room(this, false));
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public void removeItem(Item item) {
        items.remove(item);
    }
}
