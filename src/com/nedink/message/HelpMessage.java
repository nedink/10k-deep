package com.nedink.message;

public class HelpMessage extends AbstractMessage {

    @Override
    public String toString() {

        StringBuilder message = new StringBuilder();

        message.append("HELP PAGE");

        return message.toString();
    }
}
