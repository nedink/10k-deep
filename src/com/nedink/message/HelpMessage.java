package com.nedink.message;

public class HelpMessage extends AbstractMessage {

    @Override
    public String getMessage() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("HELP PAGE");
        return stringBuilder.toString();
    }
}
