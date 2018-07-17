package com.nedink.world;

import java.util.LinkedList;
import java.util.List;

import static com.nedink.util.Rand.*;

public class Room {

    public int depth;
    public boolean isLeft;
    public Room parent;
    public Room leftChild;
    public Room rightChild;
    public Enemy[] enemies;

    public Room(Room parent, boolean isLeft) {
        this.parent = parent;
        this.isLeft = isLeft;
        if (parent == null)
            depth = 0;
        else
            depth = parent.depth + 1;
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
