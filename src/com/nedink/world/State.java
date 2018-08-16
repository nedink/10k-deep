package com.nedink.world;

import com.nedink.ui.Command;
import com.nedink.ui.CommandAction;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static com.nedink.ui.CommandAction.*;

public enum State {

    CUTSCENE,
    ROOM,
    COMBAT,
    HOSTILES_APPEAR,

    ;

    private Set<CommandAction> commandActions;

    private static Set commandsRoom;
    private static Set commandsCutscene;
    static {
        commandsRoom = new HashSet();
        commandsRoom.add(HELP);
        commandsRoom.add(GO);
        commandsRoom.add(TAKE);

        commandsCutscene = new HashSet();

        ROOM.commandActions = commandsRoom;
        CUTSCENE.commandActions = commandsCutscene;
    }

    public Set<CommandAction> getCommandActions() {
        return commandActions;
    }

    public boolean allowsAction(CommandAction commandAction) {
        return commandActions.contains(commandAction);
    }
}
