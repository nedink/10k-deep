/*

Room description goes here...
blah blah blah blah blah blah blah
blah blah blah blah blah blah blah blah blah blah blah blah
blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah
blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah
blah blah blah blah blah blah blah blah blah blah blah blah
blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah

MENU DESCRIPTION
    - THING
    - THING
    - THING
    - THING

Input Error.

> input right here

 */

package com.nedink.ui;

import java.util.ArrayList;
import java.util.List;

public class ConsolePrompt {

    private static final int HEIGHT = 32;

    private List<String> mainMessage;
    private Menu menu;
    private String inputLine;

    private int advanceSpace;

    public ConsolePrompt() {
        mainMessage = new ArrayList<>();
        menu = new Menu();
        inputLine = "> ";
    }

    public void print() {

        StringBuilder output = new StringBuilder();

        advanceSpace = HEIGHT
                - mainMessage.size()
                - menu.outputHeight();

        // add space to advance screen position
        for (int i = 0; i < advanceSpace; ++i)
            output.append('\n');

        // main message
        for (String line : mainMessage) {
            output.append(line).append('\n');
        }

        // menu
        output.append(menu);

        output.append('\n');

        // input line
        output.append(inputLine);

        System.out.print(output);
    }

    // ------------------------------------

    public List<String> getMainMessage() {
        return mainMessage;
    }
}
