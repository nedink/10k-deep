package com.nedink.message;

public class HelpMessage extends AbstractMessage {

    @Override
    public String getMessage() {
        message.append("HELP PAGE");
        return message.toString();
    }
}
