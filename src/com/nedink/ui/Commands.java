package com.nedink.ui;

import java.util.HashMap;
import java.util.Map;

public final class Commands {
    public enum CommandType {
        GO_LEFT,
        GO_RIGHT,
        GO_BACK,
        QUIT
    }
    public static Map<String, CommandType> commandMap;
    static {
        commandMap = new HashMap<>();

        commandMap.put("go left", CommandType.GO_LEFT);
        commandMap.put("left", CommandType.GO_LEFT);
        commandMap.put("l", CommandType.GO_LEFT);

        commandMap.put("go right", CommandType.GO_RIGHT);
        commandMap.put("right", CommandType.GO_RIGHT);
        commandMap.put("r", CommandType.GO_RIGHT);

        commandMap.put("go back", CommandType.GO_BACK);
        commandMap.put("back", CommandType.GO_BACK);
        commandMap.put("b", CommandType.GO_BACK);

        commandMap.put("quit", CommandType.QUIT);
        commandMap.put("q", CommandType.QUIT);
    }
}
