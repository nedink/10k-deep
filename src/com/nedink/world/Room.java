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
        this.isLeft = isLeft;
        if (parent == null) {
            // this is first room
            depth = 0;
            return;
        }
        depth = parent.depth + 1;

        // consider depth
    }

    public List<Room> getPath() {
        List<Room> path = new LinkedList<>();
        path.add(this);
        if (parent != null)
            path.addAll(parent.getPath());
        return path;
//        List<Room> path = new LinkedList<>();
//        Room room = this;
//        path.add(this);
//        while ((room = room.parent) != null) {
//            path.add(room);
//        }
//        return path;
    }

    public Room spawnLeft() {
        return (leftChild = new Room(this, true));
    }
    public Room spawnRight() {
        return (rightChild = new Room(this, false));
    }
}
