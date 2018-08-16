package com.nedink.message;

import com.nedink.ui.ConsoleColor;

public class UnknownCommandMessage extends AbstractMessage {
    @Override
    public String toString() {
        return ConsoleColor.RED +
               "Unknown command. Use " +
               ConsoleColor.RESET +
               "'help' " +
               ConsoleColor.RED +
               "for a list of commands." +
               ConsoleColor.RESET +
               '\n';
    }
}
